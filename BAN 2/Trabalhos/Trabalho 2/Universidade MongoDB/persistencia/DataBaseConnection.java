package persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DataBaseConnection {
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    public static MongoDatabase getConnection() {
        if (database == null) {
            String uri = "mongodb://localhost:27017"; 
            String dbName = "Universidade";         

            try {
                mongoClient = MongoClients.create(uri);
                database = mongoClient.getDatabase(dbName);
                System.out.println("Conexão com o MongoDB estabelecida.");
            } catch (Exception e) {
                System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            }
        }
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão com o MongoDB fechada.");
        }
    }
}