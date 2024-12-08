package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

import dados.Departamento;

public class DepartamentoDAO {
    private static DepartamentoDAO instance = null;
    private MongoCollection<Document> collection;

    public static DepartamentoDAO getInstance() {
        if (instance == null) {
            instance = new DepartamentoDAO();
        }
        return instance;
    }

    private DepartamentoDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("departamento");
    }

    private int selectNewId() {
        Document lastDocument = collection.find().sort(new Document("_id", -1)).first();
        return lastDocument != null ? lastDocument.getInteger("_id") + 1 : 1;
    }

    public void insert(Departamento departamento) {
        if (!ProfessorDAO.getInstance().existsById(departamento.getLider())) {
            throw new IllegalArgumentException("O líder informado não existe.");
        }
    
        int newId = selectNewId();
        Document doc = new Document("_id", newId)
                .append("nome", departamento.getNome())
                .append("escritorio_principal", departamento.getEscritorio_principal())
                .append("lider", departamento.getLider());
        collection.insertOne(doc);
    }

    public List<Departamento> selectAll() {
        List<Departamento> departamentos = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            String nome = doc.getString("nome");
            String escritorio_principal = doc.getString("escritorio_principal");
            int lider = doc.getInteger("lider");
            departamentos.add(new Departamento(id, nome, escritorio_principal, lider));
        }
        return departamentos;
    }

    public void delete(Departamento departamento) {
        collection.deleteOne(Filters.eq("_id", departamento.getId_departamento()));
    }

    public void update(Departamento departamento) {
        if (!ProfessorDAO.getInstance().existsById(departamento.getLider())) {
            throw new IllegalArgumentException("O líder não existe.");
        }
    
        collection.updateOne(
                Filters.eq("_id", departamento.getId_departamento()),
                Updates.combine(
                        Updates.set("nome", departamento.getNome()),
                        Updates.set("escritorio_principal", departamento.getEscritorio_principal()),
                        Updates.set("lider", departamento.getLider())
                )
        );
    }
    
    public boolean existsById(int id) {
        return collection.find(Filters.eq("_id", id)).first() != null;
    }
    
}