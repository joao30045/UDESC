package persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import dados.Projeto;

import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private static ProjetoDAO instance = null;
    private MongoCollection<Document> collection;

    public static ProjetoDAO getInstance() {
        if (instance == null) {
            instance = new ProjetoDAO();
        }
        return instance;
    }

    private ProjetoDAO() {
        MongoDatabase database = DataBaseConnection.getConnection();
        collection = database.getCollection("projeto");
    }

    public void insert(Projeto projeto) {
        if (!ProfessorDAO.getInstance().existsById(projeto.getPesquisador_principal())) {
            throw new IllegalArgumentException("O pesquisador principal não existe.");
        }
        Document doc = new Document("_id", projeto.getId_projeto())
                .append("orgao_financiador", projeto.getOrgao_financiador())
                .append("data_inicio", projeto.getData_inicio())
                .append("data_fim", projeto.getData_fim())
                .append("orcamento", projeto.getOrcamento())
                .append("pesquisador_principal", projeto.getPesquisador_principal());
        collection.insertOne(doc);
    }

    public List<Projeto> selectAll() {
        List<Projeto> projetos = new ArrayList<>();
        for (Document doc : collection.find()) {
            int id = doc.getInteger("_id");
            String orgao_financiador = doc.getString("orgao_financiador");
            String data_inicio = doc.getString("data_inicio");
            String data_fim = doc.getString("data_fim");
            float orcamento = doc.getDouble("orcamento").floatValue();
            int pesquisador_principal = doc.getInteger("pesquisador_principal");
            projetos.add(new Projeto(id, orgao_financiador, data_inicio, data_fim, orcamento, pesquisador_principal));
        }
        return projetos;
    }

    public void delete(Projeto projeto) {
        collection.deleteOne(Filters.eq("_id", projeto.getId_projeto()));
    }

    public void update(Projeto projeto) {
        if (!ProfessorDAO.getInstance().existsById(projeto.getPesquisador_principal())) {
            throw new IllegalArgumentException("O pesquisador principal não existe.");
        }
        collection.updateOne(
                Filters.eq("_id", projeto.getId_projeto()),
                Updates.combine(
                        Updates.set("orgao_financiador", projeto.getOrgao_financiador()),
                        Updates.set("data_inicio", projeto.getData_inicio()),
                        Updates.set("data_fim", projeto.getData_fim()),
                        Updates.set("orcamento", projeto.getOrcamento()),
                        Updates.set("pesquisador_principal", projeto.getPesquisador_principal())
                )
        );
    }

    public boolean existsById(int id) {
        return collection.find(Filters.eq("_id", id)).first() != null;
    }
    
}