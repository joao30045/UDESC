package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class EditorTexto {
    public void gravaTexto(String caminho, List<String> dados){
        Writer arquivo;
        try{
            arquivo = new FileWriter(caminho);
            for(int i = 0; i < dados.size(); i++){
                arquivo.write(dados.get(i) + "\n");
            }
            arquivo.close();
        }catch(Exception e){
            System.err.println("Erro ao manipular arquivo");
            System.exit(0);
        }
    }
    public void gravaTexto(String caminho, String linha){
        Writer arquivo;
        try{
            arquivo = new FileWriter(caminho, true);
            arquivo.write(linha);
            arquivo.close();
        }catch(Exception e){
            System.err.println("Erro ao manipular arquivo");
            System.exit(0);
        }
    }
    public List<String> leTexto(String caminho){
        List<String> dados = new ArrayList<String>();

        Reader arquivo;
        BufferedReader r;

        try{
            arquivo = new FileReader(caminho);
            r = new BufferedReader(arquivo);

            String s = r.readLine();

            while(s != null){
                dados.add(s);
                s = r.readLine();
            }
            arquivo.close();
        }catch(Exception e){
            System.err.println("Erro ao manipular arquivo");
            System.exit(0);
        }
        return dados;
    }
}
