package fr.diginamic.connection.sample;

import static com.mongodb.client.model.Filters.eq;

import fr.diginamic.crud.Constant;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class QuickStart
{
    public static void main(String[] args)
    {
        String uri = Constant.MONGO_URI;

        try (MongoClient mongoClient = MongoClients.create(uri))
        {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document doc = collection.find(eq("title", "Back to the Future")).first();
            if (doc != null)
            {
                System.out.println(doc.toJson());
            } else
            {
                System.out.println("No matching documents found.");
            }
        }
    }
}