package com.maksymzelinskyi.service;

import com.google.gson.Gson;
import com.maksymzelinskyi.data.FetchedGrades;
import com.maksymzelinskyi.data.Grade;
import com.maksymzelinskyi.util.PropertiesHolder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GradeService {

    private static PropertiesHolder propertiesHolder = PropertiesHolder.getInstance();
    private static final String FILE_PATH = propertiesHolder.getProps().getProperty("STORAGE_PATH");

    public List<Grade> diff(List<Grade> list) {
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
}
