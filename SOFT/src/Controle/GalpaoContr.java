package Controle;

import Classes.Galpao;

import java.util.ArrayList;

public class GalpaoContr {
    private ArrayList<Object> galpoes = new ArrayList<>();

    public void add(Galpao g) {
        this.galpoes.add(g);
    }

    public Object get(int index) {
        return this.galpoes.get(index);
    }

    public ArrayList<Object> getGalpoes() {
        return this.galpoes;
    }

    public void remove(int index) {
        this.galpoes.remove(index);
    }

    public void remove(Object o) {
        this.galpoes.remove(o);
    }
}

