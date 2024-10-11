package com.example.api.api;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ApiService {
    public static void main(String[] args) {
        try {
            // Configurando o cliente HTTP com proxy
            HttpClient client = HttpClient.newBuilder()
                    .proxy(ProxySelector.of(new InetSocketAddress(
                            "proxy.br.bosch.com"
                            , 8080)))
                    .build();

            // leitura da opção do filme
            Scanner scanner = new Scanner(System.in);
            System.out.println("informe o filme: ");
//            String movie = scanner.nextLine().replace(" ","+");
            String movie = URLEncoder.encode(scanner.nextLine(), StandardCharsets.UTF_8);

            // Criando a requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.omdbapi.com/?t="+movie+"&apikey=5f77a6a2"))
                    .build();

            // Enviando a requisição e obtendo a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimindo a resposta
            System.out.println(
                    "Resposta da API: "
                            + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
