package ru.pushapp.misteryofegypt;

public class LeaderModel {

    int number;
    String name;
    int score;

    public LeaderModel(int number, String name, int score) {
        this.number = number;
        this.name = name;
        this.score = score;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
