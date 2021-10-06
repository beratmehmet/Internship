package com.example.springcalculator.Util;

import com.example.springcalculator.Service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrimarySchoolCalcUtil {

    private Calculator calculator;

    PrimarySchoolCalcUtil(){}
    public PrimarySchoolCalcUtil(@Qualifier("simpleCalculator")Calculator calculator){
        this.calculator=calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }
}
