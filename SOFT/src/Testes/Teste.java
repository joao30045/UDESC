package Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Classes.*;
import Main.Sistema;

public class Teste {
    public Teste(){

    }

    @Test
    void test_moverBarco(){
        Sistema sis = new Sistema();

        Funcionario func = new Funcionario( 1,"Alex", "Nenhum", "email", "Nenhum", "Nenhum");
        Companhia comp = new Companhia( 1, "Barcos e Barcos", "barcos@email", "47 98924", "0000011", func);
        Local local = new Local( 1,"Galpao", "Nenhum");
        Local local2 = new Local( 1,"Cais", "Nenhum");
        Barco barco = new Barco(1, "Barco", "Nenhum", func, comp, local);

        sis.moverBarco(barco, local2);
        assertTrue(barco.hasLocal());
        assertEquals(local2, barco.getLocal());
    }

    @Test
    void test_carregarMercadoria(){
        Sistema sis = new Sistema();

        Funcionario func = new Funcionario( 1,"Alex", "Nenhum", "email", "Nenhum", "Nenhum");
        Companhia comp = new Companhia( 1, "Barcos e Barcos", "barcos@email", "47 98924", "0000011", func);
        Local local = new Local( 1,"Galpao", "Nenhum");
        Barco barco = new Barco(1, "Barco", "Nenhum", func, comp, local);
        Cais cais = new Cais(1, "Cais", "123", true, barco);

        sis.carregarMercadoria(barco, cais);
        assertTrue(barco.isTemMercadoria());
    }

    @Test
    void test_descarregarMercadoria(){
        Sistema sis = new Sistema();

        Funcionario func = new Funcionario( 1,"Alex", "Nenhum", "email", "Nenhum", "Nenhum");
        Companhia comp = new Companhia( 1, "Barcos e Barcos", "barcos@email", "47 98924", "0000011", func);
        Local local = new Local( 1,"Galpao", "Nenhum");
        Barco barco = new Barco(1, "Barco", "Nenhum", func, comp, local);
        Cais cais = new Cais(1, "Cais", "123", true, barco);

        sis.descarregarMercadoria(barco, cais);
        assertFalse(barco.isTemMercadoria());
    }

    @Test
    void test_consertarBarco(){
        Sistema sis = new Sistema();

        Funcionario func = new Funcionario( 1,"Alex", "Nenhum", "email", "Nenhum", "Nenhum");
        Companhia comp = new Companhia( 1, "Barcos e Barcos", "barcos@email", "47 98924", "0000011", func);
        Local local = new Local( 1,"Galpao", "Nenhum");
        Barco barco = new Barco(1, "Barco", "Nenhum", func, comp, local);

        sis.consertarBarco(barco);
        assertFalse(barco.isQuebrado());
    }

    @Test
    void test_transportarMercadoria(){
        Sistema sis = new Sistema();

        Funcionario func = new Funcionario( 1,"Alex", "Nenhum", "email", "Nenhum", "Nenhum");
        Companhia comp = new Companhia( 1, "Barcos e Barcos", "barcos@email", "47 98924", "0000011", func);
        Local local = new Local( 1,"Galpao", "Nenhum");
        Barco barco = new Barco(1, "Barco", "Nenhum", func, comp, local);
        Cais cais = new Cais(1, "Cais", "123", true, barco);
        Galpao galpao = new Galpao(1, "Galpao", "1234", 2);

        sis.transportarMercadoria(cais, galpao);
        assertFalse(cais.temMercadoria());
        assertEquals(3, galpao.getQtdMercadoria());
    }
}
