package io.mathdojo.model;

public class Question {

    public Question() {
    }

    public Question(String message, String[] stuffs) {
        this.message = message;
        this.stuff = stuffs;
    }

    private String message;
    private String[] stuff;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getStuff() {
        return stuff;
    }

    public void setStuff(String[] stuffToSet) {
        this.stuff = stuffToSet;
    }
}

