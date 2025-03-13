package org.example.task;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class Task4 {
    private static final String URL = "https://httpbin.org/headers";

    public static void fetchAndPrintHeaders() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONObject headers = jsonResponse.getJSONObject("headers");

                String headerValues = String.join(", ", headers.toMap().values().stream()
                        .map(Object::toString)
                        .toArray(String[]::new));

                System.out.println("Заголовки запроса: " + headerValues);
            } else {
                System.out.println("Ошибка запроса: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}
