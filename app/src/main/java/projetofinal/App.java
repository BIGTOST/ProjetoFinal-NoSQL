package projetofinal;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try(MongoClient mongoClient = connectionMongoDB.getMongoClient()){
            MongoDatabase database = mongoClient.getDatabase("cinema");
            Cinema movies = new Cinema(database);
            System.out.println("Bem Vindo ao Cinemas Nos");
            while(true){
                System.out.print("\033[H\033[2J");
                System.out.println("\nMenu Inicial");
                System.out.println("1- Listar Todos os Filmes");
                System.out.println("2- Introduzir filmes");
                System.out.println("3- Atualizar Filmes");
                System.out.println("4- Remover Filmes");
                System.out.println("5- Listar Todos os Filmes");
                
                
                System.out.println("0- Encerrar");
                System.out.println("Selecione a opção");

                int opcao = scanner.nextInt();
                System.out.print("\033[H\033[2J");
                switch(opcao){
                    case 1 ->{

                    } 
                    case 0 ->{
                        System.out.println("A sair do Cinema");
                        return;
                    }
                }
                
            }

        }
        finally{
            connectionMongoDB.closeMongoClient();
        }


        //
    }
}
