package com.company;

public class Circle {

    private int radius;
    private String color;

    public Circle(int radius, String color){
        this.radius=radius;
        this.color=color;

    }

    public double areaOfCircle(){
        return (Math.PI * this.radius * this.radius);
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Circle:\nRadius: "+radius+" Color: "+color;
    }
}
