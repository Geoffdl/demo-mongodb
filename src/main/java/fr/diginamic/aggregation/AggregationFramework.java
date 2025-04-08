package fr.diginamic.aggregation;

import fr.diginamic.crud.MongoCrud;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class AggregationFramework
{
    /*
    aggregation pipeline
    Finding
    Sorting
    Grouping
    Projecting
    Perk breaks down query, debugging
    Stage can use expression operators
     */

    /*
    ex of pipeline with 2 stages and return documents that match specified criteria
     */

    public static void main(String[] args)
    {
        MongoCrud crud = new MongoCrud("test_aggregation");

        Document account = new Document()
                .append("_id", "62a3638521a9ad028fdf779d")
                .append("account_id", "MDB643731035")
                .append("account_holder", "Andrea Long")
                .append("account_type", "savings")
                .append("balance", 444.08)
                .append("transfers_complete", Arrays.asList());

        crud.insertOne(account);
//        Document updateData = new Document().append("balance", 444.08);
//        crud.updateOne("account_holder", "Andrea Long", updateData);

        List<Document> aggregatedDocument = Arrays.asList(new Document("$match",
                        new Document("balance",
                                new Document("$gt", 200L))),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("account_holder",
                                        new Document("$toUpper", "$account_holder"))
                                .append("balance", 1L)
                                .append("account_type",
                                        new Document("$toUpper", "$account_type"))));
    }
}