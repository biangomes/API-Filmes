import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexao http e buscar os top 250 filmes
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key={}";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();     // criamos um builder que aciona um GET
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        // extrair (parsear) os dados importantes do filme (titulo, poster e a classificaçao)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        

        // exibir e manipular os dados
            // sintaxe do for-each
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("overview"));
            System.out.println("https://image.tmdb.org/t/p/w500" + filme.get("backdrop_path"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
        }
    }
}
