package com.maksymzelinskyi;

import com.maksymzelinskyi.data.Grade;
import com.maksymzelinskyi.service.Client;
import com.maksymzelinskyi.service.GradeService;
import com.maksymzelinskyi.service.TelegramMessanger;

import java.util.List;


public class Main {
    
    private Client client;
    private GradeService gradeService;
    private TelegramMessanger messanger;

    public Main() {
        client = new Client();
        gradeService = new GradeService();
        messanger = new TelegramMessanger();
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.sendGrades(6);
    }

    public void sendNotif() {
        List<Grade> grades = client.fetchGrades();
        grades = gradeService.diff(grades);
        if(grades.size()==1) {
            sendGrade(grades.get(0));
        } else if (grades.size()>1) {
            sendGrades(grades.size());
        }
    }

    public void sendGrade(Grade grade) {
        String comment = grade.comment();
        sendMessage("Vous avez reçu une nouvelle note " + (comment!=null && !comment.isEmpty() ? "avec le commentaire: \"" + comment + "\"" : "."));
    }

    public void sendGrades(int qty) {
        sendMessage("Vous avez reçu " + qty + " nouvelles notes");
    }

    public void sendMessage(String message) {
        messanger.sendMessage(message);
    }
}