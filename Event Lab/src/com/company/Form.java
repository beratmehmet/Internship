package com.company;

public class Form implements MouseListener {

    Button button;

    @Override
    public void mouseClicked(ClickEvent clickEvent) {

        System.out.println("Clicked");
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
