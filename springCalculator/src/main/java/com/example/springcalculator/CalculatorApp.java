package com.example.springcalculator;

import com.example.springcalculator.Service.Calculator;
import com.example.springcalculator.Service.SimpleCalculator;
import com.example.springcalculator.Util.AcademicCalcUtil;
import com.example.springcalculator.Util.PrimarySchoolCalcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CalculatorApp implements CommandLineRunner {
    private Calculator calculator;
    private Calculator calculator2;

    @Autowired
    public CalculatorApp( @Qualifier("scientificCalculator") Calculator calculator,@Qualifier("simpleCalculator")  Calculator calculator2) {
        this.calculator = calculator;
        this.calculator2 = calculator2;
    }

   /* @Qualifier("scientificCalculator")
    @Autowired
    private Calculator calculator;

    @Qualifier("simpleCalculator")
    @Autowired
    private Calculator calculator2;
*/

    @Override
    public void run(String... args) throws Exception {
       System.out.println(calculator.calculate(10,2)+" "+calculator2.calculate(1,2));

    }


   /* public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }*/


    /*public void setCalculator2(Calculator calculator2) {
        this.calculator2 = calculator2;
    }*/
}
