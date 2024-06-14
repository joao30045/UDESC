package projeto.dados;

public class TrimestresConstantes {
    public static final int PRIMEIRO = 1;
    public static final int SEGUNDO = 4;
    public static final int TERCEIRO = 7;
    public static final int QUARTO = 10;

    public int getTrimestre(int tri){
        switch (tri) {
            case 1:
                return PRIMEIRO;
            case 2:
                return SEGUNDO;
            case 3:
                return TERCEIRO;
            case 4:
                return QUARTO;
        
            default:
                return PRIMEIRO;
        }
    }

}
