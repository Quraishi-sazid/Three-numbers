package com.example.sazid.threenumbers;

import com.orm.SugarRecord;

/**
 * Created by Sazid on 11/7/2017.
 */

public class SecondClass extends SugarRecord {
    private int secondNumber;
    private int thirdNumber;

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(int thirdNumber) {
        this.thirdNumber = thirdNumber;
    }
}
