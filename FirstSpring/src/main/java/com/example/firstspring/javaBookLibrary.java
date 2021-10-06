package com.example.firstspring;

import java.util.ArrayList;
import java.util.List;

public class javaBookLibrary implements BookLibrary {

    public List<Book> search(String title){
        List<Book> books = new ArrayList<>();
        books.add(new Book("aaaaa",15));

        return books;
    }




}
