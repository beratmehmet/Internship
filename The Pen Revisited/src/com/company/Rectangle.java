package com.company;

public class Rectangle extends Shape{

    private int width;
    private int height;

    public Rectangle(String color,int width,int height){
        super(color);
        this.height=height;
        this.width=width;
    }

    public double getArea(){

        return (this.width*this.height);
    }

    @Override
    public String toString() {

        return "Rectangle\n" + "Width: " + width + " Height: " + height + " Color: " + getColor();
    }
}
