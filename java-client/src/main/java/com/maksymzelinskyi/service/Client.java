package com.maksymzelinskyi.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maksymzelinskyi.data.Grade;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class Client {

    private HttpClient httpClient;

    public Client() {
        try {
            httpClient = HttpClient.newBuilder().localAddress(InetAddress.getLocalHost()).build();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Grade> fetchGrades() {
        try {
            String json = httpClient.send(HttpRequest.newBuilder().GET().uri(URI.create("https://localhost:8080/grades")).build(), HttpResponse.BodyHandlers.ofString()).body();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Grade>>(){}.getType();
            List<Grade> grades = gson.fromJson(json, type);
            for (int i = 0; i < grades.size(); i++) {
                System.out.println(grades.get(i));
            }
            return grades;
        } catch (Exception e) {
            System.out.println(e);
        }
        return Collections.emptyList();
    }
}
