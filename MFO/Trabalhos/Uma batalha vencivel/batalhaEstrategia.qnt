module batalhaEstrategia{
    import basicSpells.* from "./spells/basicSpells"
	import rareSpells.* from "./spells/rareSpells"

    type ClasseCriatura = Sacerdote | Bardo | Necromante | Inimigo

    type Criatura = {
        classe: ClasseCriatura,
        hp: int,
        paralisia: bool,
        iniciativa: int,
        id: int
    }

    type DescricaoTurno = 
        | Ataque({criatura: Criatura, alvo: Criatura, dano: int})
        | Paralisia({criatura: Criatura, alvo: Criatura})
        | RemoverParalisia({criatura: Criatura, alvo: Criatura})
        | Cura(Criatura)
        | Canto({criatura: Criatura})
        | LancaSangue({criatura: Criatura, alvo: Criatura, dano: int})
        | Ritual(Criatura)
        | TurnoPulado(Criatura)
        | RitualFinalizado(Criatura)

    pure def dano(c: Criatura, d: int): Criatura = {
        {...c, hp: c.hp - d}
    }

    pure def estaVivo(criatura:Criatura): bool = {
        criatura.hp>0
    }

    pure def ehPersonagem(criatura: Criatura): bool={
        criatura.classe== Sacerdote or
        criatura.classe== Bardo or
        criatura.classe== Necromante
    }

    pure def ehInimigo(criatura: Criatura): bool={
        criatura.classe == Inimigo
    }

    pure def aplicarIniciativa(criatura: Criatura, iniciativa: int): Criatura = {
        {...criatura, iniciativa: iniciativa}
    }

    pure def paralisado(c: Criatura): Criatura = {
        {...c, paralisia: true}
    }

    pure def naoParalisado(c: Criatura): Criatura = {
        {...c, paralisia: false}
    }

    pure def curarAliado(c: Criatura): Criatura = {
        val cura = if (c.hp + 10 > 20) 20 else c.hp + 10
        {...c, hp: cura}
    }

    pure val CRIATURAS = Set (
        {classe: Sacerdote, hp: 20, paralisia: false, iniciativa: 0, id: 1},
        {classe: Bardo, hp: 20, paralisia: false, iniciativa: 0, id: 2},
        {classe: Necromante, hp: 20, paralisia: false, iniciativa: 0, id: 3},
        {classe: Inimigo, hp: 80, paralisia: false, iniciativa: 0, id: 4},
        {classe: Inimigo, hp: 80, paralisia: false, iniciativa: 0, id: 5}
    )

    var criaturas: Set[Criatura]
    var turno: int
    var inspiracao: bool 
    var turnoRitualista: int
    var ritualAtivo: bool
    var inimigoRitualista: Criatura
    var ultimoTurno: Option[DescricaoTurno]
    pure val d20 = 1.to(20)
    val inimigos = criaturas.filter(c => ehInimigo(c))
    val personagens = criaturas.filter(c => ehPersonagem(c))
    val criaturasPorIniciativa = criaturas.toList((c1, c2) => intCompare(c2.iniciativa, c1.iniciativa))
	
    action personagemAtacar(criatura: Criatura, alvo: Criatura): bool = {
        val danoCausado = if (inspiracao) 10 * 3 else 10
        all {
            estaVivo(criatura),
            ehInimigo(alvo),
            estaVivo(alvo),
            criaturas' = criaturas.map(c => if (c == alvo) dano(c,danoCausado) else c),
            ultimoTurno' = Some(Ataque({criatura: criatura, alvo: alvo, dano: danoCausado})),
            inspiracao' = false,
            turnoRitualista'=turnoRitualista,
            ritualAtivo'= ritualAtivo,
            inimigoRitualista' = inimigoRitualista
        }
    }

    action inimigoAtacar(criatura: Criatura, alvo: Criatura): bool = {
        val danoCausado = if (inspiracao) 10 * 3 else 10
        all {
            estaVivo(criatura),
            ehPersonagem(alvo),
            estaVivo(alvo),
            criaturas' = criaturas.map(c => if (c == alvo) dano(c,danoCausado) else c),
            ultimoTurno' = Some(Ataque({criatura: criatura, alvo: alvo, dano: danoCausado})),
            inspiracao' = false,
            inimigoRitualista' = inimigoRitualista,
            turnoRitualista'= if (criatura.id == inimigoRitualista.id) turnoRitualista + 1 else turnoRitualista,
            ritualAtivo'= ritualAtivo
        }
    }

    action aplicarParalisia(inimigo: Criatura, alvo: Criatura): bool = {
        all {
            estaVivo(inimigo),
            estaVivo(alvo),
            ehPersonagem(alvo),
            alvo.paralisia == false,
            criaturas' = criaturas.map(c => if (c == alvo) paralisado(c) else c),
            inspiracao' = inspiracao,
            ultimoTurno' = Some(Paralisia({criatura: inimigo, alvo: alvo})),
            inimigoRitualista' = inimigoRitualista,
            turnoRitualista'= if (inimigo.id == inimigoRitualista.id) turnoRitualista + 1 else turnoRitualista,
            ritualAtivo'= ritualAtivo
        }
    }

    action removerParalisia(criatura: Criatura, alvo: Criatura): bool = {
        all {
            criatura != alvo,
            estaVivo(criatura),
            estaVivo(alvo),
            ehPersonagem(alvo),
            alvo.paralisia == true,
            criaturas' = criaturas.map(c => if (c == alvo) naoParalisado(c) else c),
            ultimoTurno' = Some(RemoverParalisia({criatura: criatura, alvo: alvo})),
            inspiracao' = inspiracao,
            turnoRitualista'=turnoRitualista,
            ritualAtivo'= ritualAtivo,
            inimigoRitualista' = inimigoRitualista
        }
    }

    action curaSacerdote(criatura: Criatura) : bool = {
        all {
            criatura.classe == Sacerdote,
            estaVivo(criatura),
            criaturas' = criaturas.map(c => if (ehPersonagem(c) and estaVivo(c)) curarAliado(c) else c),
            ultimoTurno' = Some(Cura(criatura)),
            inspiracao' = inspiracao,
            turnoRitualista' = turnoRitualista,
            ritualAtivo' = ritualAtivo,
            inimigoRitualista' = inimigoRitualista
        }
    }

    action cantoBardo(criatura: Criatura): bool = {
        all {
            criatura.classe == Bardo,
            estaVivo(criatura),
            inspiracao' = true,
            ultimoTurno' = Some(Canto({criatura: criatura})),
            criaturas' = criaturas,
            turnoRitualista'=turnoRitualista,
            ritualAtivo'= ritualAtivo,
            inimigoRitualista' = inimigoRitualista
        }
    }
    
    action lancaSangue(criatura: Criatura, alvo: Criatura): bool = {
        val danoCausado = if (inspiracao) 20 * 3 else 20
        val danoNecromante = dano(criatura, 10)
        val alvoFerido = dano(alvo, danoCausado)
        all {
            criatura.classe == Necromante,
            estaVivo(criatura),
            criatura.hp > 10,
            estaVivo(alvo),
            ehInimigo(alvo),
            criaturas' = criaturas.map(c => if (c == criatura) danoNecromante else if (c == alvo) alvoFerido else c),
            inspiracao' = false,
            ultimoTurno' = Some(LancaSangue({criatura: criatura, alvo: alvo, dano: danoCausado})),
            turnoRitualista'=turnoRitualista,
            ritualAtivo'= ritualAtivo,
            inimigoRitualista' = inimigoRitualista
        }
    }

    action iniciarRitual(inimigoRitual: Criatura): bool = {
        all{
            estaVivo(inimigoRitualista),
            turnoRitualista' = turnoRitualista + 1,
            criaturas' = criaturas,
            ritualAtivo' = true,
            inspiracao' = inspiracao,
            inimigoRitualista' = inimigoRitualista,
            ultimoTurno' = Some(Ritual(inimigoRitualista)),
        }
    }

    action finalizarRitual(inimigoRitual: Criatura): bool = {
        all{
            estaVivo(inimigoRitualista),
            turnoRitualista == 4,
            criaturas' = criaturas.map(c => if (ehPersonagem(c)) ({...c, hp: 0}) else c),
            turnoRitualista' = turnoRitualista,
            ritualAtivo' = false,
            inspiracao' = inspiracao,
            inimigoRitualista' = inimigoRitualista,
            ultimoTurno' = Some(RitualFinalizado(inimigoRitual))
        }
    }

    action turnoInimigo(inimigo: Criatura): bool = {
        if (turnoRitualista == 0 and not (ritualAtivo)) {
            all{
                ehInimigo(inimigo),
                iniciarRitual(inimigo)  
            }
        } else if (turnoRitualista == 4){
            all{
                ehInimigo(inimigo),
                finalizarRitual(inimigo)
            }
        } else {
            nondet alvo = personagens.oneOf()
            all {
                ehInimigo(inimigo),
                any {
                    inimigoAtacar(inimigo, alvo),
                    aplicarParalisia(inimigo, alvo)
                }
            }
        }
    }
    action turnoPersonagem(personagem: Criatura): bool = {
        nondet alvo = inimigos.oneOf()
        nondet aliado = personagens.oneOf()
        val aliadoParalisado = criaturas.exists(c => c.ehPersonagem() and c.paralisia == true)
        val aliadoFerido = criaturas.exists(c => c.ehPersonagem() and c.hp < 20)
        val proximoJogador = criaturasPorIniciativa[(turno+1) % criaturas.size()] 
        
        any{
            all{
                personagem.classe == Sacerdote,
                if (aliadoFerido and turnoRitualista != 4) {
                    curaSacerdote(personagem)
                }else if ((aliadoParalisado and turnoRitualista != 4) or (proximoJogador.classe == Necromante and proximoJogador.paralisia == true)) { 
                    removerParalisia(personagem, alvo)
                } else {
                    personagemAtacar(personagem, alvo)
                }
            },
            all{
                personagem.classe == Bardo,
                if (aliadoParalisado and turnoRitualista != 4) {
                    removerParalisia(personagem, aliado)
                }else if ((not (inspiracao) and proximoJogador.classe == Necromante and proximoJogador.paralisia == false) or 
                (turnoRitualista == 4 and proximoJogador.classe != Inimigo and proximoJogador.paralisia == false) or 
                (proximoJogador.classe != Inimigo and proximoJogador.paralisia == false) and not (aliadoFerido))  {
                    cantoBardo(personagem)
                } else {
                    personagemAtacar(personagem, alvo)
                } 
            },
            all{
                personagem.classe == Necromante,
                if (aliadoParalisado and turnoRitualista != 4) {
                    removerParalisia(personagem, aliado)
                }else if ((proximoJogador.classe == Sacerdote and proximoJogador.paralisia == false) or turnoRitualista == 4 
                or alvo.hp == 20) {
                    lancaSangue(personagem, alvo)
                }else {
                    personagemAtacar(personagem, alvo)
                }
            }
        }
    }

    action init = {
        nondet dadoSacerdote = d20.oneOf()
        nondet dadoBardo = d20.oneOf()
        nondet dadoNecromante = d20.oneOf()
        nondet dadoInimigo1 = d20.oneOf()
        nondet dadoInimigo2 = d20.oneOf()

        def iniciativaAux(criatura: Criatura): Criatura = {
            if (criatura.classe == Sacerdote) criatura.aplicarIniciativa(dadoSacerdote) else
            if (criatura.classe == Bardo) criatura.aplicarIniciativa(dadoBardo) else
            if (criatura.classe == Necromante) criatura.aplicarIniciativa(dadoNecromante) else
            if(criatura.id == 4) criatura.aplicarIniciativa(dadoInimigo1) else
            criatura.aplicarIniciativa(dadoInimigo2)
        }

        val criaturasAux = CRIATURAS.map(iniciativaAux)
        nondet inimigoEscolhido = criaturasAux.filter(c => ehInimigo(c)).oneOf()

        all {
            criaturas' = criaturasAux,
            turno' = 0,
            turnoRitualista' = 0,
            ritualAtivo' = false,
            inspiracao' = false,
            inimigoRitualista' = inimigoEscolhido
        }
    }

    action step = {
	
        val criaturaJogando = criaturasPorIniciativa[turno % criaturas.size()]
        
        any{
            if ( estaVivo(criaturaJogando) and not (criaturaJogando.paralisia) ) {
                all {
                    any{
                        turnoPersonagem(criaturaJogando), 
                        turnoInimigo(criaturaJogando) 
                    },
                    turno' = turno + 1
                }
            } else {
                all {
                    criaturas' = criaturas,
                    ultimoTurno' = Some(TurnoPulado(criaturaJogando)),
                    turno' = turno + 1,
                    inspiracao' = inspiracao,
                    turnoRitualista'=turnoRitualista,
                    ritualAtivo'= ritualAtivo,
                    inimigoRitualista' = inimigoRitualista
                }
            }
        } 
    }       
    val invarianteInimigo = inimigos.forall(i => estaVivo(i))
    val invariantePersonagens = personagens.forall(c => estaVivo(c)) 
}