package Controle;

import java.util.ArrayList;

import Classes.Companhia;

public class CompanhiaContr {
    private ArrayList<Object> companhias = new ArrayList<>();

    public void add(Companhia c) {
        this.companhias.add(c);
    }

    public Object get(int index) {
        return this.companhias.get(index);
    }

    public ArrayList<Object> getCompanhias() {
        return this.companhias;
    }

    public void remove(int index) {
        this.companhias.remove(index);
    }

    public void remove(Object o) {
        this.companhias.remove(o);
    }
}