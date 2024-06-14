package Controle;

import Classes.Cais;

import java.util.ArrayList;

public class CaisContr {
    private ArrayList<Object> cais = new ArrayList<>();

    public void add(Cais c) {
        this.cais.add(c);
    }

    public Object get(int index) {
        return this.cais.get(index);
    }

    public ArrayList<Object> getCais() {
        return this.cais;
    }

    public void remove(int index) {
        this.cais.remove(index);
    }

    public void remove(Object o) {
        this.cais.remove(o);
    }
}
