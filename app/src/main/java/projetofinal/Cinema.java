package projetofinal;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class Cinema {
    private final MongoCollection<Document> cinemaMovies;

    public Cinema(MongoDatabase database){
        this.cinemaMovies = database.getCollection("movies");
    }
    Bson projection = Projections.exclude("_id");

    public void addMovie(Document movie) {
        try {
            cinemaMovies.insertOne(movie);
            System.out.println("movie added successfully.");
        } catch (MongoException e) {
            System.err.println("Erro ao adicionar o movie: " + e.getMessage());
        }
    }

    public Document findMovieById(String id) {
        try {
            
            ObjectId objectId = new ObjectId(id);
            Document movie = cinemaMovies
            .find(eq("_id", objectId))
            .projection(projection)
            .first();

            if (movie != null) {
                return movie;
            } else {
                System.out.println("Nenhum filme encontrado com esse codigo " + id);
                return null;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de ID invalido: " + id);
            return null;
        }    
	}

    public Document findMovieByName(String name) {
        try {
            Document movie = cinemaMovies
            .find(eq("Titulo", name))
            .projection(projection)
            .first();

            if (movie != null) {
                return movie;
            } else {
                System.out.println("Nenhum filme encontrado com esse nome " + name);
                return null;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de Nome invalido: " + name);
            return null;
        }    
	}

    //* Listagem de todos os filmes na tabela para uma lista
    public List<Document> listarMovies() {
        List<Document> movies = cinemaMovies
        .find()
        .projection(projection)
        .into(new ArrayList<>());
    
        // Log the count of movies retrieved
        System.out.println("Number of movies retrieved: " + movies.size());

        return movies;
    }
    public List<Document> listarMoviesDespoisDe(int ano) {
        List<Document> movies = cinemaMovies
        .find(gt("anoDeLançamento",ano))
        .projection(projection)
        .into(new ArrayList<>());
    
        // Log the count of movies retrieved
        System.out.println("Number of movies retrieved: " + movies.size());

        return movies;
    }
    public List<Document> listarMoviesAntesDe(int ano) {
        List<Document> movies = cinemaMovies
        .find(lt("anoDeLançamento",ano))
        .projection(projection)
        .into(new ArrayList<>());
    
        // Log the count of movies retrieved
        System.out.println("Number of movies retrieved: " + movies.size());

        return movies;
    }
    public List<Document> listarMoviesEm(int ano) {
        List<Document> movies = cinemaMovies
        .find(eq("anoDeLançamento",ano))
        .projection(projection)
        .into(new ArrayList<>());
    
        // Log the count of movies retrieved
        System.out.println("Number of movies retrieved: " + movies.size());

        return movies;
    }
    
    //* update Movie por id
    public void updateMovieValue(String id, String field, String value) {

        if(field == "anoDeLançamento" || field =="Tempo de Tela"){
            int altValue =Integer.valueOf(value);
            try {
                ObjectId objectId = new ObjectId(id);
                cinemaMovies.updateOne(eq("_id", objectId), set(field, altValue));
                System.out.println("Car updated successfully.");
            } catch (Exception e) {
                System.out.println("Error updating car: " + e.getMessage());
            }
        }else{
            try {
                ObjectId objectId = new ObjectId(id);
                cinemaMovies.updateOne(eq("_id", objectId), set(field, value));
                System.out.println("Car updated successfully.");
            } catch (Exception e) {
                System.out.println("Error updating car: " + e.getMessage());
            }
        }
    }
    
    //* delet movie por id
    public void deleteMovieById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            cinemaMovies.deleteOne(eq("_id", objectId));
            System.out.println("Car deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting car: " + e.getMessage());
        }
    }

    public Document encontrarAnoComMaisFilmes() {
        Bson groupStage = Aggregates.group("$anoDeLançamento", Accumulators.sum("count", 1));
        Bson sortStage = Aggregates.sort(Sorts.descending("count"));
        Bson limitStage = Aggregates.limit(1);

        List<Document> resultado = cinemaMovies.aggregate(Arrays.asList(groupStage, sortStage, limitStage)).into(new ArrayList<>());

        if (!resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            System.out.println("Nenhum filme encontrado.");
            return null;
        }
    }
}
