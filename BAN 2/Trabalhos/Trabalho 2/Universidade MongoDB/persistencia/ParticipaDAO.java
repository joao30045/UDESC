package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import dados.Participa;

public class ParticipaDAO {
    private static ParticipaDAO instance = null;
    private MongoCollection<Document> collection;

    public static ParticipaDAO getInstance() {
        if (instance == null) {
            instance = new ParticipaDAO();
        }
        return instance;
    }

    private ParticipaDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("participa");
    }

    private int selectNewId() {
        Document lastDocument = collection.find().sort(new Document("_id", -1)).first();
        return lastDocument != null ? lastDocument.getInteger("_id") + 1 : 1;
    }

    public void insert(Participa participa) {
        if (!EstudanteDAO.getInstance().existsById(participa.getId_estud())) {
            throw new IllegalArgumentException("O estudante não existe.");
        }
        else if (!ProjetoDAO.getInstance().existsById(participa.getId_proj())) {
            throw new IllegalArgumentException("O projeto não existe.");
        }
        else if (!ProfessorDAO.getInstance().existsById(participa.getId_prof_super())) {
            throw new IllegalArgumentException("O supervisor não existe.");
        }
        int newId = selectNewId();
        Document doc = new Document("_id", newId)
                .append("id_prof_super", participa.getId_prof_super())
                .append("id_proj", participa.getId_proj())
                .append("id_estud", participa.getId_estud());
        collection.insertOne(doc);
    }

    public List<Participa> selectAll() {
        List<Participa> participacoes = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            int id_prof_super = doc.getInteger("id_prof_super");
            int id_proj = doc.getInteger("id_proj");
            int id_estud = doc.getInteger("id_estud");
            participacoes.add(new Participa(id, id_prof_super, id_proj, id_estud));
        }
        return participacoes;
    }

    public void delete(Participa participa) {
        collection.deleteOne(Filters.eq("_id", participa.getId_participa()));
    }

    public void update(Participa participa) {
        if (!EstudanteDAO.getInstance().existsById(participa.getId_estud())) {
            throw new IllegalArgumentException("O estudante não existe.");
        }
        else if (!ProjetoDAO.getInstance().existsById(participa.getId_proj())) {
            throw new IllegalArgumentException("O projeto não existe.");
        }
        else if (!ProfessorDAO.getInstance().existsById(participa.getId_prof_super())) {
            throw new IllegalArgumentException("O supervisor não existe.");
        }
        collection.updateOne(
                Filters.eq("_id", participa.getId_participa()),
                Updates.combine(
                        Updates.set("id_prof_super", participa.getId_prof_super()),
                        Updates.set("id_proj", participa.getId_proj()),
                        Updates.set("id_estud", participa.getId_estud())
                        
                )
        );
    }
}
