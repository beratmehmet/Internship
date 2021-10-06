package com.company;

public class Circle extends Shape{

    int radius;

    public Circle(String color,int radius){
        super(color);
        this.radius=radius;
    }

    public double getArea(){

        return (Math.PI * this.radius * this.radius);
    }

    @Override
    public String toString() {

        return "Circle:\nRadius: "+radius+" Color: "+getColor();
    }

}
