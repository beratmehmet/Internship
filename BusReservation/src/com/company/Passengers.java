package com.company;


public class Passengers {
    private String name;
    private Destination destination;

    public Passengers(String name, Destination destination){
        this.destination=destination;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
