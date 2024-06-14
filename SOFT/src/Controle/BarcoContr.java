package Controle;

import java.util.ArrayList;
import java.util.Iterator;

import Classes.Barco;
import Classes.Local;

public class BarcoContr {
    private ArrayList<Object> barcos = new ArrayList<>();

    public void add(Barco b) {
        this.barcos.add(b);
    }

    public Object get(int index) {
        return this.barcos.get(index);
    }

    public ArrayList<Object> getBarcos() {
        return this.barcos;
    }

    public void remove(int index) {
        this.barcos.remove(index);
    }

    public void remove(Object o) {
        this.barcos.remove(o);
    }

    public ArrayList<Object> getBarcosAtracados() {
        Iterator<Object> it = this.barcos.iterator();
        ArrayList<Object> lista = new ArrayList<>();
        Barco barco = new Barco();
        while(it.hasNext()) {
            barco = (Barco)it.next();
            if (barco.hasLocal())
                lista.add(barco);
        }
        return lista;
    }

    public ArrayList<Object> getBarcosCCapitaes() {
        Iterator<Object> it = this.barcos.iterator();
        ArrayList<Object> lista = new ArrayList<>();
        Barco barco = new Barco();
        while(it.hasNext()) {
            barco = (Barco)it.next();
            if (barco.hasCapitao())
                lista.add(barco);
        }
        return lista;
    }

    public ArrayList<Object> getBarcosNoLocal(Local local){
        Iterator<Object> it = this.barcos.iterator();
        ArrayList<Object> lista = new ArrayList<>();
        Barco barco;
        while(it.hasNext()) {
            barco = (Barco)it.next();
            if (barco.getLocal().equals(local))
                lista.add(barco);
        }

        return lista;
    }
}