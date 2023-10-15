package org.First;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpMethod {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com/users";

    private static final String PUT_URI = "https://jsonplaceholder.typicode.com/users";

    private static final String DELETE_URI = "https://jsonplaceholder.typicode.com/users";

    private static final String USERNAME_URI = "https://jsonplaceholder.typicode.com/users?username=";


    public static String post(User user) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder(new URI(BASE_URI))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }

    public static String put(User user, String id) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder(new URI(PUT_URI + "/" + id))
                .header("Content-type", "application/json;charset=UTF-8")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static int delete(User user, String id) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder(new URI(DELETE_URI + "/" + id))
                .header("Content-type", "application/json;charset=UTF-8")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public static String getUsers() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(BASE_URI))
                .header("Content-type", "application/json;charset=UTF-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return (String) response.body();
    }

    public static String GetUserById(String id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(BASE_URI + "/" + id))
                .header("Content-type", "application/json;charset=UTF-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return (String) response.body();
    }

    public static String GetUserByUsername(String username) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(USERNAME_URI + username))
                .header("Content-type", "application/json;charset=UTF-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return (String) response.body();
    }

}
