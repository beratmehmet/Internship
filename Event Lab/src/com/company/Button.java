package com.company;

import java.util.ArrayList;
import java.util.List;

public class Button {

    List<MouseListener> clickListeners = new ArrayList<>();

    public void addMouseClickListener(MouseListener listener){
        clickListeners.add(listener);
    }
    public void removeMouseClickListener(MouseListener listener){
        clickListeners.remove(listener);
    }

    public void click(){

            ClickEvent clickEvent = new ClickEvent(this);
        for (MouseListener clickListener:clickListeners
             ) {
            clickListener.mouseClicked(clickEvent);
        }

    }

}
