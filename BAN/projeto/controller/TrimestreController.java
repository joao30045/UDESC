package projeto.controller;

import java.util.Scanner;

import projeto.dados.TrimestresConstantes;


public class TrimestreController {

    private TrimestresConstantes trimestre = new TrimestresConstantes();

    public int getTrimestre(int tri){
        return trimestre.getTrimestre(tri);
    }

    public int menuTrimestre(){
        System.out.println("Trimestres: ");
        System.out.println("1 -- Primeiro, de Janeiro a Março");
        System.out.println("2 -- Segundo, de Abril a Junho");
        System.out.println("3 -- Teceiro, de Julho a Setembro");
        System.out.println("4 -- Quarto, de Outubro a Dezembro");
        System.out.println("Sua opção: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
