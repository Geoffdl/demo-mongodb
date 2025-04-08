package fr.diginamic.crud;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import fr.diginamic.connection.MongoConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MongoCrud
{
    private final MongoCollection<Document> collection;

    public MongoCrud(String collectionName)
    {
        this.collection = MongoConnection.getDatabase().getCollection(collectionName);
    }

    //Create
    public void insertOne(Document document){
        collection.insertOne(document);
    }
    public void insertMany(List<Document> documents){
        collection.insertMany(documents);
    }

    // READ
    public Document findOne(String field, String value) {
        return collection.find(Filters.eq(field, value)).first();
    }
    public List<Document> findMany(String field, String value) {
        return collection.find(Filters.eq(field, value)).into(new ArrayList<>());
    }

    //Update
    public void updateOne(String field, String value, Document newData){
        collection.updateOne(Filters.eq(field, value), new Document("$set", newData));
    }

    //Delete
    public void deleteOne(String field, String value){
        collection.deleteOne(Filters.eq(field, value));
    }

    public List<Document> findByFieldAndSort(String field, String value, String sortField, int sortOrder) {
        return collection.find(Filters.eq(field, value))
                .sort(Sorts.ascending(sortField))
                .into(new ArrayList<>());
    }

}