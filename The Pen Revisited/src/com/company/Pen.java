package com.company;

public class Pen {
    public void drawShape(Shape shape){

        System.out.println(shape.getArea());
    }

    public void setColor(Shape shape,String color){

        shape.setColor(color);
    }

}
