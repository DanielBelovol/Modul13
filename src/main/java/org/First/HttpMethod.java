package org.First;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public static String getUserById(String id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(BASE_URI + "/" + id))
                .header("Content-type", "application/json;charset=UTF-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return (String) response.body();
    }

    public static String getUserByUsername(String username) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(USERNAME_URI + username))
                .header("Content-type", "application/json;charset=UTF-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return (String) response.body();
    }

    //SECOND TASK!!!!!!!!!!!_____________________

    @SneakyThrows
    public static void getUserLastPostComments(String uri, String userId) {
        String PostsUri = uri + String.format("/users/%s/posts", userId);
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        String userPostsJson = sendGetJsonRequest(PostsUri);
        List<PostDto> list = gson.fromJson(userPostsJson, new TypeToken<List<PostDto>>() {
        }.getType());
        System.out.println(list);
        int last = list.stream().map(PostDto::getId).max(Comparator.naturalOrder()).orElseThrow();
        String lastPostCommentsJson = sendGetJsonRequest(uri + String.format("/posts/%s/comments", last));
        System.out.println(lastPostCommentsJson);
        String filePath =
                "src/main/java/" + String.format("user-%s-post-%s-comments.json", userId, last);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(lastPostCommentsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void printTodosForUser(String uri, int userId) {
        String todosUri = uri + String.format("users/%s/todos", userId);
        String todosJson = sendGetJsonRequest(todosUri);
        Gson gson = new Gson();
        gson.newBuilder().setPrettyPrinting().create();
        List<TodoDto> list = gson.fromJson(todosJson, new TypeToken<List<TodoDto>>() {
        }.getType());
        List openTodos = list.stream().filter(t -> t.isCompleted() == false).toList();
        openTodos.forEach(System.out::println);
    }

    @SneakyThrows
    private static String sendGetJsonRequest(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("content-type", "application/json; charset=utf-8")
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return response.body();
    }


}

