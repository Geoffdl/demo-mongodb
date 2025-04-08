package fr.diginamic.crud;

import org.bson.Document;

import java.util.Arrays;

public class TestCrud
{
    public static void main(String[] args)
    {
        MongoCrud crud = new MongoCrud("test_crud");

        Document recipe = new Document()
                .append("name", "Pasta")
                .append("prep_time", 3)
                .append("cook_time", 10)
                .append("ingredients", Arrays.asList("pasta","cream", "mushroom"))
                ;

        crud.insertOne(recipe);

        //read
        Document found = crud.findOne("name", "Pasta");
        System.out.println(found);

        // update
        Document update = new Document("prep_time", 13);
        crud.updateOne("name", "Pasta", update);

        // delete
        crud.deleteOne("name", "Pasta");
    }
}