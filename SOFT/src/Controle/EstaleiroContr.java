package Controle;

import Classes.Estaleiro;

import java.util.ArrayList;

public class EstaleiroContr {
    private ArrayList<Object> estaleiros = new ArrayList<>();

    public void add(Estaleiro e) {
        this.estaleiros.add(e);
    }

    public Object get(int index) {
        return this.estaleiros.get(index);
    }

    public ArrayList<Object> getEstaleiros() {
        return this.estaleiros;
    }

    public void remove(int index) {
        this.estaleiros.remove(index);
    }

    public void remove(Object o) {
        this.estaleiros.remove(o);
    }
}

