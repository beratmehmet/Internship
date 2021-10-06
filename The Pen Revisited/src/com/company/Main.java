package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Rectangle rectangle = new Rectangle("Red",20,40);
        Circle circle= new Circle("Black",3);

        Pen pen = new Pen();
        System.out.println(circle.toString()+"\n"+rectangle.toString());

        System.out.println("Area of circle:");
        pen.drawShape(circle);

        System.out.println("Area of rectangle:");
        pen.drawShape(rectangle);



        pen.setColor(circle,"Blue");
        System.out.println(circle.toString()+"\n"+rectangle.toString());


    }
}
