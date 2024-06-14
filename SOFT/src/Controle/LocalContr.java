package Controle;

import java.util.ArrayList;

import Classes.Local;

public class LocalContr {
    private ArrayList<Object> locais = new ArrayList<>();

    public void add(Local l) {
        this.locais.add(l);
    }

    public Object get(int index) {
        return this.locais.get(index);
    }

    public ArrayList<Object> getLocais() {
        return this.locais;
    }

    public void remove(int index) {
        this.locais.remove(index);
    }

    public void remove(Object o) {
        this.locais.remove(o);
    }
}