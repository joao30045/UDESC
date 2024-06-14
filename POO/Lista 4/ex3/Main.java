package ex3;

public class Main {
    public static void main(String[] args) {
        SistemaArquivos sistema = new SistemaArquivos();

        try {
            sistema.criarMusica("Mansão Thug Stronda - Bonda da Stronda","Downloads",300);
            sistema.criarDocumento("Lista 4 POO","Documentos","asdasdasd");
            sistema.criarVideo("Aula_12_interface_grafica","Downloads",Qualidade.hd_1024p);
            sistema.criarDocumento("Trabalho Final POO","Documentos","0,0,0,0");
            sistema.criarMusica("Leão - Marília Mendonça", "Downloads", 164);
        }catch (NomeInvalidoException e){
            System.out.println("Nome Inválido");
        }
        System.out.println(sistema.toString());
    }
}