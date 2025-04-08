package fr.diginamic.crud.sample;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class Transaction
{

    /*
    1. Start client session
    2. Define transaction options
    3. Define the sequence of operations to perform inside the transactions
    4. Start the transaction by using the ClientSession's withTransaction() method
    5. Release the resources used by the transaction
     */
    public static void main(String[] args)
    {
        String connectionString = "mongodb+srv://root:root@cluster0.ednit.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        final MongoClient client = MongoClients.create(connectionString);
        final ClientSession clientSession = client.startSession();

        TransactionBody txnBody = new TransactionBody<String>()
        {
            public String execute()
            {

                MongoCollection<Document> bankingCollection = client.getDatabase("bank").getCollection("accounts");

                Bson fromAccount = Filters.eq("account_id", "itsAnAccountValue029090");
                Bson withdrawal = Updates.inc("balance", -499);

                Bson toAccount = Filters.eq("account_id", "itsAnAccountValue029091");
                Bson deposit = Updates.inc("balance", 799);

                System.out.printf("Withdrawing %s from %s and depositing %s to %s", withdrawal.toBsonDocument().toJson(),
                        fromAccount.toBsonDocument().toJson(), toAccount.toBsonDocument().toJson(), deposit.toBsonDocument().toJson());

                bankingCollection.updateOne(clientSession, fromAccount, withdrawal);
                bankingCollection.updateOne(clientSession, toAccount, deposit);

                return "Transfered funds";
            }

        };

        try
        {
            clientSession.withTransaction(txnBody);
        } catch (RuntimeException e)
        {
            System.out.println(e);
        } finally
        {
            clientSession.close();
        }


    }


}