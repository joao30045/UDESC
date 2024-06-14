package Classes;

import java.util.ArrayList;

public class Barco {
    private int id;
    private String nome, tipo;
    private Funcionario func;
    private ArrayList<Object> capitaes = new ArrayList<Object>();
    private Companhia dono;
    private Local local;
    private boolean temMercadoria;
    private boolean quebrado;

    public boolean isTemMercadoria() {
        return temMercadoria;
    }

    public void setTemMercadoria(boolean temMercadoria) {
        this.temMercadoria = temMercadoria;
    }

    public boolean isQuebrado() {
        return quebrado;
    }

    public void setQuebrado(boolean quebrado) {
        this.quebrado = quebrado;
    }

    public Barco(int id, String nome, String tipo, Funcionario func, Companhia dono, Local local) {
        super();
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.func = func;
        this.capitaes.add(func);
        this.dono = dono;
        this.local = local;
    }

    public Barco() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public ArrayList<Object> getCapitaes() {
        return capitaes;
    }

    public void setCapitaes(ArrayList<Object> capitaes) {
        this.capitaes = capitaes;
    }

    public boolean addCapitao(Funcionario cap) {
        if (this.func != null && this.func.equals(cap)){
            System.err.println(cap.getNome() + " já é capitão deste barco");
            return false;
        }
        this.func = cap;
        this.capitaes.add(cap);
        return true;
    }

    public boolean isCapitao(Funcionario cap) {
        if (this.func != null && this.func.equals(cap))
            return true;
        else
            return false;
    }

    public boolean hasCapitao() {
        if (this.func != null && this.func.getId() != 0)
            return true;
        else
            return false;
    }

    public Companhia getDono() {
        return dono;
    }

    public void setDono(Companhia dono) {
        this.dono = dono;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public boolean hasLocal() {
        if (this.local != null && this.local.getId() != 0)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String str = this.id + " - " + this.nome + ", " + this.tipo;
        if (this.dono != null)
            str += ", Dono: " + dono.getNome();
        if (this.func != null)
            str += ", Capitão: " + this.func.getNome();
        if (this.local != null)
            str += ", Local: " + this.local.getNome();
        return str;
    }
}
