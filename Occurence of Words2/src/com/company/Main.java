package com.company;

import jdk.nashorn.internal.runtime.options.OptionTemplate;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here


        BufferedReader objReader = null;
        String data="";
        try {
            String strCurrentLine;
            HashMap<String, Integer> myMap = new HashMap<String,Integer>();

            objReader = new BufferedReader(new FileReader("C:/Users/berat/Desktop/sample_text.txt"));

            while ((strCurrentLine = objReader.readLine()) != null) {


                String[] words = strCurrentLine.toLowerCase().split(" ");

                for(String str : words){



                    if (myMap.containsKey(str)){
                        myMap.put(str, myMap.get(str)+1);

                    }else {
                        myMap.put(str,1);


                    }

                }
            }

            ArrayList<String> list = new ArrayList<>(myMap.keySet());
            Collections.sort(list);

            for (String key:list
                 ) {
                System.out.println(key+"->"+myMap.get(key));
            }





        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }







    }


}
