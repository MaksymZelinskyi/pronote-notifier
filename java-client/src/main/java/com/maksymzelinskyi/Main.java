package com.maksymzelinskyi;

import java.io.*;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
    
    private HttpClient httpClient;
    private static final String FILE_PATH = "classpath:last-period.json";
    private List<Grade> lastFetched;

    public Main() {
        try {
            httpClient = HttpClient.newBuilder().localAddress(InetAddress.getLocalHost()).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public static void main(String[] args) {
        TelegramMessanger messanger = new TelegramMessanger();
        messanger.sendMessage("Hello, my master");
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

    private List<Grade> diff(List<Grade> list) {
        List<Grade> diff = new ArrayList<>();
        try {
            Reader reader = new FileReader(FILE_PATH);
            Gson gson = new Gson();
            FetchedGrades fg = gson.fromJson(reader, FetchedGrades.class);
            for(int i = list.size()-1; i>=0; i--) {
                if(!fg.getGrades().contains(list.get(i))) {
                    diff.add(list.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return list;
    }

    public void sendNotif() {
        List<Grade> grades = fetchGrades();
        grades = diff(grades);
        if(grades.size()==1) {
            sendGrade(grades.get(0));
        } else if (grades.size()>1) {
            sendGrades(grades.size());
        }
    }

    public void sendGrade(Grade grade) {

    }

    public void sendGrades(int qty) {

    }
}