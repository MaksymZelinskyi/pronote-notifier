package com.maksymzelinskyi;

import java.util.ArrayList;
import java.util.List;

public class FetchedGrades {

    private List<Integer> grades = new ArrayList<>();
    private Period period;

    public FetchedGrades(List<Integer> grades, Period period) {
        this.grades = grades;
        this.period = period;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void addGrades(List<Integer> grades) {
        this.grades.addAll(grades);
    }

    public void addGrade(Integer id) {
        this.grades.add(id);
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
