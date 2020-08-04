package com.am.demo.entity;

public class Player
{
    private String name;
    private int score;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
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

    public int hashCode(){
        return name.hashCode() + 33;
    }

    public boolean equals(Object object){
        if(object == null)
            return false;
        if(object.getClass() != this.getClass())
            return false;
        Player player= (Player)object;
        return this.score== player.score;
    }
}
