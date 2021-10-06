package com.company;


public class Bus {

    private Destination destination;
    Passengers[] passengers;

    public Bus(int capacity, Destination destination){
        this.passengers=new Passengers[capacity];
        this.destination=destination;

    }

    public void insertPassenger(Passengers passenger){
        boolean inserted=false;
        int i=0;

        for (Passengers item : passengers){
            if (item==null && passenger.getDestination()==this.destination){
                passengers[i]=passenger;
                inserted=true;
                System.out.println("Passenger inserted");
                break;
            }
            i++;
        }
        if (inserted==false){
            if (passenger.getDestination()!=this.destination){
                System.out.println("Destinations do not match");
            }else{
                System.out.println("Bus is full");
            }
        }

    }



}
