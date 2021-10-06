package com.company;

import sun.security.krb5.internal.crypto.Des;

public class Main {

    public static void main(String[] args) {

        Bus bus1 = new Bus(3, Destination.ADANA);

        bus1.insertPassenger(new Passengers("Ali",Destination.ADANA));
        bus1.insertPassenger(new Passengers("Veli",Destination.ANKARA));
        bus1.insertPassenger(new Passengers("Mehmet",Destination.ADANA));
        bus1.insertPassenger(new Passengers("İrem",Destination.ADANA));
        bus1.insertPassenger(new Passengers("Ayşe",Destination.ADANA));




    }
}
