package fr.diginamic.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import fr.diginamic.crud.Constant;


public class MongoConnection
{
    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase()
    {
        return getMongoClient().getDatabase("sample_training");
    }

    private static synchronized MongoClient getMongoClient()
    {
        if (mongoClient == null)
        {
            ConnectionString connectionString = new ConnectionString(Constant.DB_URL);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .serverApi(ServerApi.builder()
                            .version(ServerApiVersion.V1)
                            .build())
                    .build();
            mongoClient = MongoClients.create(settings);
        }
        return mongoClient;
    }
}