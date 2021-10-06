package com.company;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private int id;
    private String name, surname, address;
    private List<Pair<String, Double>> grades = new ArrayList<>();
    private transient double cumulativeGPA;

    public Student(int id, String name, String surname, String address){

        this.id=id;
        this.name=name;
        this.surname=surname;
        this.address=address;

    }

    public void addGrade(String courseID, double grade){
        double totalGrade;
        grades.add(new Pair<String,Double>(courseID,grade));
        totalGrade=totalGradeCalculator();
        cumulativeGPA=totalGrade/grades.size();

    }

    private void readObject(ObjectInputStream objInp) throws Exception
    {
        objInp.defaultReadObject();

        double totalGrade=totalGradeCalculator();

        cumulativeGPA=totalGrade/grades.size();

    }

    public double totalGradeCalculator(){
        double totalGrade=0;

        for(Pair item : grades){

            totalGrade+=(double)item.getPair2();

        }
        return totalGrade;
    }

    @Override
    public String toString() {
        return  String.format("| ID=%-5s| Name=%-8s| Surname=%-10s| Cumulative GPA=%-4.2f|",id,name,surname,cumulativeGPA);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
