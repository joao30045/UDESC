package dados;

import java.util.ArrayList;
import java.util.List;

public class Matriz<T>{
    private int linhas;
    private int colunas;
    private List<List<T>> matriz;

    public Matriz(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        this.matriz = new ArrayList<List<T>>();
        for(int i = 0; i < linhas; i++){
            matriz.add(new ArrayList<T>());
            for(int j = 0; j < colunas; j++){
                matriz.get(i).add(null);
            }
        }
    }
    public boolean set(T objeto, int i, int j){
        if(i >= linhas || j >= colunas){
            return false;
        }
        matriz.get(i).set(j, objeto);
        return true;
    }
    public T get(int i, int j){
        if(i >= linhas || j >= colunas){
            return null;
        }
        return matriz.get(i).get(j);
    }
    public List<T> getLinha(int linha){
        if(linha >= linhas){
            return null;
        }
        return matriz.get(linha);
    }
    public List<T> getColuna(int coluna){
        if(coluna >= colunas){
            return null;
        }
        List<T> aux = new ArrayList<T>();
        for(List<T> linha : matriz){
            aux.add(linha.get(coluna));
        }
        return aux;
    }
    public List<T> getElementosQuadrante(Quadrante quadrante){
        int meioLinhas = linhas / 2;
        int meioColunas = colunas / 3;
        int i, j;
        List<T> aux = new ArrayList<T>();
        switch(quadrante){
            case PRIMEIRO:
                for(i = 0; i <= meioLinhas; i++){
                    for(j = 0; j <= meioColunas; j++){
                        aux.add(matriz.get(i).get(j));
                    }
                }
            break;
            case SEGUNDO:
				for(i = 0; i <= meioLinhas; i++){
					for(j = meioColunas + 1; j < colunas; j++){
						aux.add(matriz.get(i).get(j));
                    }
                }
			break;
			case TERCEIRO:
				for(i = meioLinhas + 1; i < linhas; i++){
					for(j = 0; j <= meioColunas; j++){
						aux.add(matriz.get(i).get(j));
                    }
                }
			break;
			case QUARTO:
				for(i = meioLinhas + 1; i < linhas; i++){
					for(j = meioColunas + 1; j < colunas; j++){
						aux.add(matriz.get(i).get(j));
                    }
                }
			break;
		}
        return aux; 
    }
}