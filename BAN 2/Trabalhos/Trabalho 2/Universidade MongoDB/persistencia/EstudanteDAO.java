package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import dados.Estudante;

import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO {
    private static EstudanteDAO instance = null;
    private MongoCollection<Document> collection;

    public static EstudanteDAO getInstance() {
        if (instance == null) {
            instance = new EstudanteDAO();
        }
        return instance;
    }

    private EstudanteDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("estudante");
    }

    private int selectNewId() {
        Document lastDocument = collection.find().sort(new Document("_id", -1)).first();
        return lastDocument != null ? lastDocument.getInteger("_id") + 1 : 1;
    }

    public void insert(Estudante estudante) {
        if (estudante.getAconselhador() != 0) {
            Document docAconselhador = collection.find(Filters.eq("_id", estudante.getAconselhador())).first();
            if (docAconselhador == null) {
                throw new IllegalArgumentException("O aconselhador não existe.");
            }
            int idadeAconselhador = docAconselhador.getInteger("idade");
            if (idadeAconselhador <= estudante.getIdade()) {
                throw new IllegalArgumentException("O aconselhador deve ser mais velho do que o estudante.");
            }
        }
        if (!DepartamentoDAO.getInstance().existsById(estudante.getDepart())) {
            throw new IllegalArgumentException("O departamento não existe.");
        }
        int newId = selectNewId();
        Document doc = new Document("_id", newId)
                .append("nome", estudante.getNome())
                .append("idade", estudante.getIdade())
                .append("tipo_do_curso", estudante.getTipo_do_curso())
                .append("depart", estudante.getDepart())
                .append("aconselhador", estudante.getAconselhador());
        collection.insertOne(doc);
    }
    
    

    public List<Estudante> selectAll() {
        List<Estudante> estudantes = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            String nome = doc.getString("nome");
            int idade = doc.getInteger("idade");
            String tipo_do_curso = doc.getString("tipo_do_curso");
            int depart = doc.getInteger("depart");
            int aconselhador = doc.getInteger("aconselhador");
            estudantes.add(new Estudante(id, nome, idade, tipo_do_curso, depart, aconselhador));
        }
        return estudantes;
    }

    public void delete(Estudante estudante) {
        collection.deleteOne(Filters.eq("_id", estudante.getId_estudante()));
    }

    public void update(Estudante estudante) {
        if (estudante.getAconselhador() != 0) {
            Document docAconselhador = collection.find(Filters.eq("_id", estudante.getAconselhador())).first();
            if (docAconselhador == null) {
                throw new IllegalArgumentException("O aconselhador não existe.");
            }
            int idadeAconselhador = docAconselhador.getInteger("idade");
            if (idadeAconselhador <= estudante.getIdade()) {
                throw new IllegalArgumentException("O aconselhador deve ser mais velho do que o estudante.");
            }
        }
        if (!DepartamentoDAO.getInstance().existsById(estudante.getDepart())) {
            throw new IllegalArgumentException("O departamento não existe.");
        }
        collection.updateOne(
                Filters.eq("_id", estudante.getId_estudante()),
                Updates.combine(
                        Updates.set("nome", estudante.getNome()),
                        Updates.set("idade", estudante.getIdade()),
                        Updates.set("tipo_do_curso", estudante.getTipo_do_curso()),
                        Updates.set("depart", estudante.getDepart()),
                        Updates.set("aconselhador", estudante.getAconselhador())
                )
        );
    }
    
    public boolean existsById(int id) {
        return collection.find(Filters.eq("_id", id)).first() != null;
    }
    
}