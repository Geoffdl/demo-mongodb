package fr.diginamic.crud.sample;

import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import fr.diginamic.connection.MongoConnection;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Date;


public class Insertion
{
    public static void main(String[] args)
    {

        try
        {
            MongoDatabase database = MongoConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("inspections");


            Document inspection = new Document("_id", new ObjectId())
                    .append("id", "1000-2015-ENFO")
                    .append("certificate_number", 939393)
                    .append("business_name", "SUPER BOITE")
                    .append("date", Date.from(LocalDate.of(2015, 2, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .append("result", "No Violation Issued")
                    .append("sector", "Cigarette Retail Dealer - 127")
                    .append("adresse", new Document().append("city", "PARIS").append("ZIP", 2020).append("street", "cool lane").append("number", 20));

            InsertOneResult result = collection.insertOne(inspection);
            BsonValue id = result.getInsertedId();
            System.out.println(id);


        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }


}