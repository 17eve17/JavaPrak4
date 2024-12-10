package ex3;

import java.net.http.*;
import java.net.URI;

public class p3_2 {
    public static void main(String[] args) {
        String url = "https://jsonplaceholder.typicode.com/posts";
        String json = """
                {
                    "title": "foo",
                    "body": "bar",
                    "userId": 1
                }
                """;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Статус код: " + response.statusCode());
            System.out.println("Заголовки:");
            response.headers().map().forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println("Тіло відповіді:");
            System.out.println(response.body());

        } catch (Exception e) {
            System.err.println("Сталася помилка під час виконання POST-запиту: " + e.getMessage());
        }
    }
}
