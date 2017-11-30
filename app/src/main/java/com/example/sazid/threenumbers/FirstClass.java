package com.example.sazid.threenumbers;

import com.orm.SugarRecord;

/**
 * Created by Sazid on 11/7/2017.
 */

public class FirstClass extends SugarRecord {
    private int firstNumber;
    private SecondClass secondClass;

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public SecondClass getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(SecondClass secondClass) {
        this.secondClass = secondClass;
    }
}
