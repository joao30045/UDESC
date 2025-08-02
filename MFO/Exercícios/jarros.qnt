module jarros {
    var grande : int 
    var pequeno : int

    action enche_grande = all {
        grande' = 5,
        pequeno' = pequeno,
    }
    action enche_pequeno = all {
        grande' = grande,
        pequeno' = 3,
    }
    action esvazia_grande = all {
        grande' = 0,
        pequeno' = pequeno,
    }
    action esvazia_pequeno = all {
        grande' = grande,
        pequeno' = 0,
    }
    action grande_pro_pequeno = all {
        if (pequeno + grande <= 3) all {
            pequeno' = pequeno + grande,
            grande' = 0,
        } else all {
            pequeno' = 3,
            grande' = pequeno - (3 - grande),
        }
    }
    action pequeno_pro_grande = all {
        if (pequeno + grande <= 5) all {
            grande' = pequeno + grande,
            pequeno' = 0,
        } else all {
            grande' = 5,
            pequeno' = grande - (5 - pequeno),
        }
    }
    action init = all {
        grande' = 0, 
        pequeno' = 0,
    }
    action step = any {
        enche_grande,
        enche_pequeno,
        esvazia_grande,
        esvazia_pequeno,
        grande_pro_pequeno,
        pequeno_pro_grande,
    }
    val inv = grande != 4
}
