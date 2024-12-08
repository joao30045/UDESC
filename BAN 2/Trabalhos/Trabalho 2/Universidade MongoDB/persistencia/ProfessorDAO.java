package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import dados.Professor;

public class ProfessorDAO {
    private static ProfessorDAO instance = null;
    private MongoCollection<Document> collection;

    public static ProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAO();
        }
        return instance;
    }

    private ProfessorDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("professor");
    }

    private int selectNewId() {
        Document lastDocument = collection.find().sort(new Document("_id", -1)).first();
        return lastDocument != null ? lastDocument.getInteger("_id") + 1 : 1;
    }

    public void insert(Professor professor) {
        int newId = selectNewId();
        Document doc = new Document("_id", newId)
                .append("nome", professor.getNome())
                .append("idade", professor.getIdade())
                .append("sala", professor.getSala())
                .append("especialidade", professor.getEspecialidade());
        collection.insertOne(doc);
    }

    public List<Professor> selectAll() {
        List<Professor> professores = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            String nome = doc.getString("nome");
            int idade = doc.getInteger("idade");
            String sala = doc.getString("sala");
            String especialidade = doc.getString("especialidade");
            professores.add(new Professor(id, nome, idade, sala, especialidade));
        }
        return professores;
    }

    public void delete(Professor professor) {
        collection.deleteOne(Filters.eq("_id", professor.getId_professor()));
    }

    public void update(Professor professor) {
        collection.updateOne(
                Filters.eq("_id", professor.getId_professor()),
                Updates.combine(
                        Updates.set("nome", professor.getNome()),
                        Updates.set("idade", professor.getIdade()),
                        Updates.set("sala", professor.getSala()),
                        Updates.set("especialidade", professor.getEspecialidade())
                )
        );
    }

    public boolean existsById(int id) {
        return collection.find(Filters.eq("_id", id)).first() != null;
    }
    
}