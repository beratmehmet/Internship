package com.example.springcalculator.Service;

import com.example.springcalculator.Util.PrimarySchoolCalcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SimpleCalculator implements Calculator{

    PrimarySchoolCalcUtil primarySchoolCalcUtil;
    @Autowired
    public SimpleCalculator(PrimarySchoolCalcUtil prmsclutl){
        this.primarySchoolCalcUtil=prmsclutl;
    }

    @Override
    public long calculate(long num1, long num2) {
        return num1*num2;
    }
}
