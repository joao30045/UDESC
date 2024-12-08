package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import dados.TrabalhaDepart;

public class TrabalhaDepartDAO {
    private static TrabalhaDepartDAO instance = null;
    private MongoCollection<Document> collection;

    public static TrabalhaDepartDAO getInstance() {
        if (instance == null) {
            instance = new TrabalhaDepartDAO();
        }
        return instance;
    }

    private TrabalhaDepartDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("trabalhaDepart");
    }

    private int selectNewId() {
        Document lastDocument = collection.find().sort(new Document("_id", -1)).first();
        return lastDocument != null ? lastDocument.getInteger("_id") + 1 : 1;
    }

    public void insert(TrabalhaDepart trabalhaDepart) {
        if (!ProfessorDAO.getInstance().existsById(trabalhaDepart.getId_prof())) {
            throw new IllegalArgumentException("O professor não existe.");
        }
        if (!DepartamentoDAO.getInstance().existsById(trabalhaDepart.getId_depart())) {
            throw new IllegalArgumentException("O departamento não existe.");
        }
        int newId = selectNewId();
        Document doc = new Document("_id", newId)
                .append("id_prof", trabalhaDepart.getId_prof())
                .append("id_depart", trabalhaDepart.getId_depart())
                .append("horas", trabalhaDepart.getHoras());
        collection.insertOne(doc);
    }

    public List<TrabalhaDepart> selectAll() {
        List<TrabalhaDepart> trabalhamDepart = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            int id_prof = doc.getInteger("id_prof");
            int id_depart = doc.getInteger("id_depart");
            int horas = doc.getInteger("horas");
            trabalhamDepart.add(new TrabalhaDepart(id, id_prof, id_depart, horas));
        }
        return trabalhamDepart;
    }

    public void delete(TrabalhaDepart trabalhaDepart) {
        collection.deleteOne(Filters.eq("_id", trabalhaDepart.getId_trabalhaDepart()));
    }

    public void update(TrabalhaDepart trabalhaDepart) {
        if (!ProfessorDAO.getInstance().existsById(trabalhaDepart.getId_prof())) {
            throw new IllegalArgumentException("O professor não existe.");
        }
        if (!DepartamentoDAO.getInstance().existsById(trabalhaDepart.getId_depart())) {
            throw new IllegalArgumentException("O departamento não existe.");
        }

        collection.updateOne(
                Filters.eq("_id", trabalhaDepart.getId_trabalhaDepart()),
                Updates.combine(
                        Updates.set("id_prof", trabalhaDepart.getId_prof()),
                        Updates.set("id_depart", trabalhaDepart.getId_depart()),
                        Updates.set("horas", trabalhaDepart.getHoras())  
                )
        );
    }
}

