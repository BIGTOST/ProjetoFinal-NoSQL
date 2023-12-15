package projetofinal;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;



public class connectionMongoDB {

    private static String bd = "";

    //* to find the link to the database in the .env
    static {
        try {
            File myObj = new File(".env");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                bd = myReader.nextLine();
            }
            myReader.close(); // Close the scanner to release resources
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
            e.printStackTrace();
        }
    }

    //* Connection with the MongoDB database
    private static final String CONNECT_STRING = bd;

    private static MongoClient mongoClient = null;
    //class constructor
    private connectionMongoDB(){};

    //Gerar conecção com a Base de dados
    public static MongoClient getMongoClient(){
        if(mongoClient == null){
            MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(CONNECT_STRING)).build();
            mongoClient = MongoClients.create(settings);
        }
        return mongoClient;
    }

    //encerramento da conecção com o Mongo
    public static void closeMongoClient(){
        if(mongoClient!= null){
            mongoClient.close();
            mongoClient = null;
        }
    }
}
// String Data="a";
//     try {
//         File myObj = new File(".env");
//         Scanner myReader = new Scanner(myObj);
//         while (myReader.hasNextLine()) {
//            Data = myReader.nextLine();
//         }
//         myReader.close(); // Close the scanner to release resources
//     } catch (FileNotFoundException e) {
//         System.out.println("File not found: " + e.getMessage());
//         e.printStackTrace();
//     }