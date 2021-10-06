package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int index=0;
        ArrayList<Student> objList = new ArrayList<>();
        String[][] objInfo = {{"123","Berat", "Topuz", "CUMHURIYET MAH. ALI OZDEN CAD. EDİRNE" },{"456","Ali", "Bakan", "124.YIL BULVARI BOSNA IS MRK. NO:35/66 OSTIM"},
                                {"789", "Doğaç", "Öztürk", "ATATURK CAD.PAZAR SOK.NO:9 MADEN, ELAZıĞ"},{"741","Sude", "Kaçan","124.YIL BULVARI BOSNA IS MRK. NO:35/66 OSTIM"},
                                {"852", "Ayşe", "Demir", "KUSKONMAZ SK. NO:83/C BALÇOVA"},{"963","Baran","Ölmez","TAHTACILAR CAD.NO:11 ERZURUM"},
                                {"6874","Sıla","yılmaz","Merkez Efendi Mah. G-70 Sok. No 19/A Zeytinburnu"},{"4652","Barış","Baykal","Çankaya Mahallesi 4721 Sokak 5C (Milli Piyango Sokağı) Mersin"},
                                {"564","İsa","Kaya","Topçular Kışla Cad. Metal Han. No:83/2 Eyüp Rami"},{"5456","Biriçim","Demiröz","BALAKGAZİ CAD.NO:16 ELAZIĞ, ELAZıĞ"}};

        String[][] objGrades = {{"BLGM419","100.0"},{"BLGM420","85.0"},{"ABC123","87.0"},{"DEF456","8.0"},{"CMPE288","11.0"},
                                {"BCM8797","18.0"},{"ASDF898","99.0"},{"SDF101","77.0"},{"ASDA454","96.0"},{"ADSG200","28.0"},
                                {"RET587","40.0"},{"LOKI789","32.0"},{"ASDA782","58.0"},{"RESX300","52.0"},{"BLGM419","55.0"},
                                {"BLGM420","66.0"},{"DEF456","34.0"},{"AXCV522","54.0"},{"REWX211","11.0"},{"UHG478","54.0"}};


        for(int i=0; i<10; i++){
            objList.add(new Student(Integer.parseInt(objInfo[i][0]),objInfo[i][1],objInfo[i][2],objInfo[i][3]));
        }




        for (Student item:objList) {
            item.addGrade(objGrades[index][0],Double.parseDouble(objGrades[index][1]));
            item.addGrade(objGrades[index+1][0],Double.parseDouble(objGrades[index+1][1]));
            index+=2;
        }



        try {
            FileOutputStream file = file = new FileOutputStream(new File("Students.txt"));
            ObjectOutputStream obj = new ObjectOutputStream(file);

            for (Student item: objList) {
                obj.writeObject(item);
            }

            obj.close();
            file.close();

            System.out.println("Finished!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
