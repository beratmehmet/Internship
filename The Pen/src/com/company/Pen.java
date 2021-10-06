package com.company;

public class Pen {

    public void draw(Rectangle r){
        System.out.println("Area of rectangle: "+r.areaOfRectangle());
    }

    public void draw(Circle c){
        System.out.println("Area of circle: "+c.areaOfCircle());
    }

    public void changeColor(String color, Rectangle r){
        r.setColor(color);
    }

    public void changeColor(String color, Circle c){
        c.setColor(color);
    }


}
