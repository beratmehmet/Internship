package com.company;

import java.io.Serializable;

public class Pair<P1,P2> implements Serializable {

    private P1 pair1;
    private P2 pair2;

    public Pair(P1 pair1, P2 pair2){
        this.pair1=pair1;
        this.pair2=pair2;
    }

    public P1 getPair1() {
        return pair1;
    }

    public P2 getPair2() {
        return pair2;
    }
}
