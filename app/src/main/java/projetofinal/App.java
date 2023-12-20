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
                //System.out.print("\033[H\033[2J");
                //*Menu Inicial
                System.out.println("\nMenu Inicial");
                System.out.println("1- Listagens");
                System.out.println("2- Introduzir filmes");
                System.out.println("3- Atualizar Filmes");
                System.out.println("4- Remover Filmes");
                
                
                System.out.println("0- Encerrar");
                System.out.println("Selecione a opção");

                int opcao = scanner.nextInt();

                switch(opcao){
                    case 1 ->{
                        boolean sair = true ;
                        int op = 20;
                        while (sair) {
                            //*Submenu
                            scanner.nextLine();
                            System.out.println("\nMenu Inicial");
                            System.out.println("x- Listagens");
                            System.out.println("\t1-Listar Todos");
                            System.out.println("\t2-Encontrar Nome");
                            System.out.println("\t3-Listar filmes por Ano");
                            System.out.println("\t4-Ano com mais filmes");
                            System.out.println("x- Introduzir filmes");
                            System.out.println("x- Atualizar Filmes");
                            System.out.println("x- Remover Filmes");
                            System.out.println("0- voltar");
                            System.out.println("Selecione a opção");
                            op = scanner.nextInt();
                            switch (op) {
                                case 1->{
                                    List<Document> listaDeFilmes = movies.listarMovies();
                                    if (listaDeFilmes.isEmpty())
                                        System.out.println("Cinema Vazio :(");
                                    else{
                                        for (Document moviDocument : listaDeFilmes) {
                                            System.out.println("\n");
                                           for (Map.Entry<String, Object> entry : moviDocument.entrySet()) {
                                                System.out.println(entry.getKey() + ": " + entry.getValue());
                                            }
                                        }
                                    }

                                }
                                case 2->{
                                    scanner.nextLine();
                                    System.out.println("Indique o nome do filme a pesquisar: ");
                                    String name = scanner.nextLine();
                                    Document movie = movies.findMovieByName(name);
                                    //System.out.println(car.toJson());
                                    System.out.println("\n");
                                    for (Map.Entry<String, Object> entry : movie.entrySet()) {
                                        System.out.println(entry.getKey() + ": " + entry.getValue());
                                    }
                                }
                                case 3->{
                                    //* SubSubmenu
                                    scanner.nextLine();
                                    System.out.println("Indique o ano: ");
                                    int ano = scanner.nextInt();
                                    System.out.println("\nMenu Inicial");
                                    System.out.println("x- Listagens");
                                    System.out.println("\txx-Listar Todos");
                                    System.out.println("\txx-Encontrar Nome");
                                    System.out.println("\txx-Listar filmes por Ano");
                                    System.out.println("\t\t1-Antes de "+ ano);
                                    System.out.println("\t\t2-Depois de "+ ano);
                                    System.out.println("\t\t3-Em "+ ano);
                                    System.out.println("x- Introduzir filmes");
                                    System.out.println("x- Atualizar Filmes");
                                    System.out.println("x- Remover Filmes");
                                    System.out.println("0- voltar");
                                    System.out.println("Selecione a opção");
                                    op = scanner.nextInt();
                                    switch (op) {
                                        case 1->{
                                            List<Document> listaDeFilmes = movies.listarMoviesAntesDe(ano);
                                            if (listaDeFilmes.isEmpty())
                                                System.out.println("Nenhum Filme antes desse ano:(");
                                            else{
                                                for (Document moviDocument : listaDeFilmes) {
                                                    System.out.println("\n");
                                                for (Map.Entry<String, Object> entry : moviDocument.entrySet()) {
                                                        System.out.println(entry.getKey() + ": " + entry.getValue());
                                                    }
                                                }
                                            }
                                        }
                                        case 2->{
                                            List<Document> listaDeFilmes = movies.listarMoviesDespoisDe(ano);
                                            if (listaDeFilmes.isEmpty())
                                                System.out.println("Nenhum Filme depois desse ano:(");
                                            else{
                                                for (Document moviDocument : listaDeFilmes) {
                                                    System.out.println("\n");
                                                for (Map.Entry<String, Object> entry : moviDocument.entrySet()) {
                                                        System.out.println(entry.getKey() + ": " + entry.getValue());
                                                    }
                                                }
                                            }
                                        }
                                        case 3->{
                                            List<Document> listaDeFilmes = movies.listarMoviesEm(ano);
                                            if (listaDeFilmes.isEmpty())
                                                System.out.println("Nenhum Filme nesse ano:(");
                                            else{
                                                for (Document moviDocument : listaDeFilmes) {
                                                    System.out.println("\n");
                                                for (Map.Entry<String, Object> entry : moviDocument.entrySet()) {
                                                        System.out.println(entry.getKey() + ": " + entry.getValue());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                case 4 ->{
                                    movies.encontrarAnoComMaisFilmes();
                                }
                                case 0->{
                                    sair = false;
                                }
                                default->{
                                    System.out.println("Opção não encontrada");
                                }
                            }
                        }
                    }
                    case 2 -> {
                        scanner.nextLine();
                        System.out.println("Quantos Deseja Inserir: ");
                        int Numero = scanner.nextInt();
                        if(Numero>1){
                            for (int i = 0; i < Numero; i++) {
                                System.out.println("Indique o Titulo do Filme: ");
                                String Titulo = scanner.nextLine();
                                System.out.println("Indique o nome do Produtor: ");
                                String nomeProdutor = scanner.nextLine();
                                System.out.println("Indique o genero do Produtor: ");
                                String genero = scanner.nextLine();
                                System.out.println("Indique o ano de lançamento: ");
                                int anoDeLaçamento = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Indique o tempo de tela: ");
                                int tempoDeTela = scanner.nextInt();

                                Document newMovie = new Document("Titulo", Titulo)
                                        .append("Produtor", nomeProdutor)
                                        .append("genero", genero)
                                        .append("anoDeLançamento", anoDeLaçamento)
                                        .append("Tempo de Tela", tempoDeTela);
                                movies.addMovie(newMovie);
                            }
                        }else{
                            System.out.println("Indique o Titulo do Filme: ");
                            String Titulo = scanner.nextLine();
                            System.out.println("Indique o nome do Produtor: ");
                            String nomeProdutor = scanner.nextLine();
                            System.out.println("Indique o genero do Produtor: ");
                            String genero = scanner.nextLine();
                            System.out.println("Indique o ano de lançamento: ");
                            int anoDeLaçamento = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Indique o tempo de tela: ");
                            String tempoDeTela = scanner.nextLine();

                            Document newMovie = new Document("Titulo", Titulo)
                                    .append("Produtor", nomeProdutor)
                                    .append("genero", genero)
                                    .append("anoDeLançamento", anoDeLaçamento)
                                    .append("Tempo de Tela", tempoDeTela);
                            movies.addMovie(newMovie);
                        }
                    }
                    case 3 ->{
                        scanner.nextLine();
                        System.out.println("Indique o id do filme que deseja atualizar: ");
                        String id = scanner.nextLine();

                        System.out.println("Qual o campo a atualizar");
                        String field = scanner.nextLine();

                        System.out.println("Escreva o valor do campo: ");
                        String value = scanner.nextLine();


                        movies.updateMovieValue(id, field, value);
                    }
                    case 4 ->{
                        scanner.nextLine();
                        System.out.println("Indique o id do movie que deseja apagar: ");
                        String id = scanner.nextLine();

                        movies.deleteMovieById(id);
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
