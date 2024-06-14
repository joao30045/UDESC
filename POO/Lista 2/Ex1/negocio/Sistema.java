package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dados.Pessoa;

public class Sistema {
    private List<Pessoa> criancas = new ArrayList<Pessoa>();
    private List<Pessoa> adolescentes = new ArrayList<Pessoa>();
    private List<Pessoa> jovens = new ArrayList<Pessoa>();
    private List<Pessoa> adultos = new ArrayList<Pessoa>();
    private List<Pessoa> idosos = new ArrayList<Pessoa>();

    public void addPessoa(Pessoa p){
        int idade = p.getIdade();
        if(idade >= 1 && idade <= 12){
            criancas.add(p);
            Collections.sort(criancas);
        }else if(idade >= 13 && idade <= 18){
            adolescentes.add(p);
            Collections.sort(adolescentes);
        }else if(idade >= 19 && idade <= 25){
			jovens.add(p);
			Collections.sort(jovens);
		}else if(idade >= 26 && idade <= 56){
			adultos.add(p);
			Collections.sort(adultos);
		}else{
			idosos.add(p);
			Collections.sort(idosos);
		}
    }
    public List<Pessoa> getCriancas() {
        return criancas;
    }
    public List<Pessoa> getAdolescentes() {
        return adolescentes;
    }
    public List<Pessoa> getJovens() {
        return jovens;
    }
    public List<Pessoa> getAdultos() {
        return adultos;
    }
    public List<Pessoa> getIdosos() {
        return idosos;
    }
}
