package com.example.springcalculator.Service;

import com.example.springcalculator.Util.AcademicCalcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScientificCalculator implements Calculator{

    @Override
    public long calculate(long num1, long num2) {
        return num1/num2;
    }
}
