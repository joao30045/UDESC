package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import dados.Trabalha;

public class TrabalhaDAO {
    private static TrabalhaDAO instance = null;
    private MongoCollection<Document> collection;

    public static TrabalhaDAO getInstance() {
        if (instance == null) {
            instance = new TrabalhaDAO();
        }
        return instance;
    }

    private TrabalhaDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("trabalha");
    }

    private int selectNewId() {
        Document lastDocument = collection.find().sort(new Document("_id", -1)).first();
        return lastDocument != null ? lastDocument.getInteger("_id") + 1 : 1;
    }

    public void insert(Trabalha trabalha) {
        if (!ProjetoDAO.getInstance().existsById(trabalha.getId_proj())) {
            throw new IllegalArgumentException("O projeto não existe.");
        }
        if (!ProfessorDAO.getInstance().existsById(trabalha.getId_prof())) {
            throw new IllegalArgumentException("O professor não existe.");
        }
        
        int newId = selectNewId();
        Document doc = new Document("_id", newId)
                .append("id_prof", trabalha.getId_prof())
                .append("id_proj", trabalha.getId_proj());
        collection.insertOne(doc);
    }

    public List<Trabalha> selectAll() {
        List<Trabalha> trabalham = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            int id_prof = doc.getInteger("id_prof");
            int id_proj = doc.getInteger("id_proj");
            trabalham.add(new Trabalha(id, id_prof, id_proj));
        }
        return trabalham;
    }

    public void delete(Trabalha trabalha) {
        collection.deleteOne(Filters.eq("_id", trabalha.getId_trabalha()));
    }

    public void update(Trabalha trabalha) {
        if (!ProjetoDAO.getInstance().existsById(trabalha.getId_proj())) {
            throw new IllegalArgumentException("O projeto não existe.");
        }
        if (!ProfessorDAO.getInstance().existsById(trabalha.getId_prof())) {
            throw new IllegalArgumentException("O professor não existe.");
        }
        collection.updateOne(
                Filters.eq("_id", trabalha.getId_trabalha()),
                Updates.combine(
                        Updates.set("id_prof", trabalha.getId_prof()),
                        Updates.set("id_proj", trabalha.getId_proj()) 
                )
        );
    }
}

