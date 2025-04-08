package fr.diginamic.crud;

import fr.diginamic.connection.MongoConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant
{
    public static final String DB_URL = loadMongoURI();


    private static String loadMongoURI() {
        try (InputStream input = MongoConnection.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("MONGODB_URI");
        } catch (IOException ex) {
            throw new RuntimeException("Could not load MongoDB URI", ex);
        }
    }
}