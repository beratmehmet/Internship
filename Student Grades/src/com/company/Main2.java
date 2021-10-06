package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        int index=0;

        try {
            FileInputStream file = file = new FileInputStream(new File("Students.txt"));
            ObjectInputStream oi = new ObjectInputStream(file);



            /*for(int i=0;i<10;i++){
                list.add((Student)(oi.readObject())) ;
                System.out.println(list.get(i).toString());
            }*/


            while (true){
                try {
                    list.add((Student)(oi.readObject())) ;
                    System.out.println(list.get(index).toString());
                    index++;
                }catch (EOFException e){
                    System.out.println("END OF FILE!");
                    break;
                }

            }

            oi.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
