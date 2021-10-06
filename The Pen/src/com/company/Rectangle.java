package com.company;

public class Rectangle {

    private int width;
    private int height;
    private String color;

    public Rectangle(int width,int height,String color){

        this.height=height;
        this.width=width;
        this.color=color;

    }

    public int areaOfRectangle(){
        return this.width*this.height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rectangle\n" + "Width: " + width + " Height: " + height + " Color: " + color;
    }
}
