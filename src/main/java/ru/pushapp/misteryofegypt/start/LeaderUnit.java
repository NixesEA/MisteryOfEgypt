package ru.pushapp.misteryofegypt.start;


import android.support.annotation.NonNull;

public class LeaderUnit implements Comparable {

    int number;
    String name;
    int score;

    public LeaderUnit(String name, int score) {
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

    @Override
    public int compareTo(@NonNull Object o) {
//    public int compareTo(@NonNull LeaderUnit leaderUnit) {
        int comparescore = ((LeaderUnit) o).getScore();
        return -(this.score - comparescore);
    }
}
