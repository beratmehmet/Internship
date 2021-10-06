package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Circle circle = new Circle(3,"Red");
        Rectangle rectangle = new Rectangle(5,15,"Blue");
        Pen pen = new Pen();

        System.out.println(circle.toString()+"\n"+rectangle.toString());

        pen.draw(rectangle);
        pen.draw(circle);
        pen.changeColor("Black",circle);
        pen.changeColor("Pink",rectangle);

        System.out.println("After methods:\n");

        System.out.println(circle.toString()+"\n"+rectangle.toString());



    }
}
