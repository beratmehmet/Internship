package com.example.springcalculator.Util;

import com.example.springcalculator.Service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AcademicCalcUtil {

    private Calculator calculator;

    public AcademicCalcUtil(){}

    @Autowired
    public AcademicCalcUtil(@Qualifier("scientificCalculator") Calculator calculator){
        this.calculator=calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }
}
