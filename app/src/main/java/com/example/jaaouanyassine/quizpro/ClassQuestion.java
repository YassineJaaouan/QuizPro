package com.example.jaaouanyassine.quizpro;

public class ClassQuestion {
    public int id ;
    public String Question;
    public  String Anser1;
    public String Anser2;
    public  String Anser3;
    public String Correct;

    public ClassQuestion(int id, String question, String anser1, String anser2, String anser3, String correct) {
        this.id = id;
        Question = question;
        Anser1 = anser1;
        Anser2 = anser2;
        Anser3 = anser3;
        Correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnser1() {
        return Anser1;
    }

    public void setAnser1(String anser1) {
        Anser1 = anser1;
    }

    public String getAnser2() {
        return Anser2;
    }

    public void setAnser2(String anser2) {
        Anser2 = anser2;
    }

    public String getAnser3() {
        return Anser3;
    }

    public void setAnser3(String anser3) {
        Anser3 = anser3;
    }

    public String getCorrect() {
        return Correct;
    }

    public void setCorrect(String correct) {
        Correct = correct;
    }
}


