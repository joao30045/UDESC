package ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digrafo {
    protected Map<Integer, List<Integer>> ma = new HashMap<Integer, List<Integer>>();

    public void adicionarVertice(){
        for(Integer key : ma.keySet()){
            ma.get(key).add(0);
        }
        List<Integer> l = new ArrayList<>();
        int i = ma.size();
        for(int j = 0; j < i; j++){
            l.add(0);
        }
        l.add(0);
        ma.put(i, l);
    }
    public Map<Integer, List<Integer>> getMa() {
        return ma;
    }
    public void adicionarAresta(int i, int j){
        ma.get(i).set(j, 1);
    }
    public String toString(){
        String s = new String();
        for(List<Integer> lista : ma.values()){
            s += lista.toString() + "\n";
        }
        return s;
    }
}
