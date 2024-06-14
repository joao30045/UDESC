package Controle;

import java.util.ArrayList;

import Classes.Funcionario;


public class FuncionarioContr {
    private ArrayList<Object> funcionarios = new ArrayList<>();

    public void add(Funcionario f) {
        this.funcionarios.add(f);
    }

    public Object get(int index) {
        return this.funcionarios.get(index);
    }

    public ArrayList<Object> getFuncionarios() {
        return this.funcionarios;
    }

    public void remove(int index) {
        this.funcionarios.remove(index);
    }

    public void remove(Object o) {
        this.funcionarios.remove(o);
    }
}