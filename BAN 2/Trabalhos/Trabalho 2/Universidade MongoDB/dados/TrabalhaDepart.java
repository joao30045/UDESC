package dados;

public class TrabalhaDepart {
    private int id_trabalhaDepart;
    private int id_prof;
    private int id_depart;
    private int horas;
   

    public TrabalhaDepart (int id_trabalhaDepart, int id_prof, int id_depart, int horas) {
        this.id_trabalhaDepart = id_trabalhaDepart;
        this.id_prof = id_prof;
        this.id_depart = id_depart;
        this.horas = horas;
    }
    public TrabalhaDepart(){
    }

   public int getId_trabalhaDepart() {
       return id_trabalhaDepart;
   }
   public int getId_prof() {
       return id_prof;
   }
   public int getId_depart() {
       return id_depart;
   }
   public int getHoras() {
       return horas;
   }
   public void setId_trabalhaDepart(int id_trabalhaDepart) {
       this.id_trabalhaDepart = id_trabalhaDepart;
   }
   public void setId_prof(int id_prof) {
       this.id_prof = id_prof;
   }
   public void setId_depart(int id_depart) {
       this.id_depart = id_depart;
   }
   public void setHoras(int horas) {
       this.horas = horas;
   }

    public String toString(){
        return "Id do professor: " + id_prof + ", Id do departamento: " + id_depart + ", horas de trabalho: " + horas;
    }
}


