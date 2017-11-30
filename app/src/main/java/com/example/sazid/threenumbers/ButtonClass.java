package com.example.sazid.threenumbers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Sazid on 11/8/2017.
 */

public class ButtonClass {
    DatabaseOperation databaseOperation;

    public ButtonClass(Context ctx, Activity act) {
        databaseOperation = new DatabaseOperation(ctx,act);
    }

    public View.OnClickListener getClickListenerForDelete(final EditText editTextDeletingNumber) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                int toBeDeleted = databaseOperation.getIntegerFromEditTextInput(editTextDeletingNumber);
                List<FirstClass> deletingList = databaseOperation.getFirstClassList(toBeDeleted);
                databaseOperation.deleteFromTable(deletingList);
                databaseOperation.showTotal();
            }
        };
    }

    public View.OnClickListener getClickListenerForRegister(final EditText editTextFirstNumber, final EditText editTextSecondNumber, final EditText editTextThirdNumber, final EditText editTextInputNumber) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                int firstNumber = databaseOperation.getIntegerFromEditTextInput(editTextFirstNumber);
                int secondNumber = databaseOperation.getIntegerFromEditTextInput(editTextSecondNumber);
                int thirdNumber = databaseOperation.getIntegerFromEditTextInput(editTextThirdNumber);
                if (firstNumber != -1 && secondNumber != -1 && thirdNumber != -1) {//if any of this is -1 then the field is empty
                    List<SecondClass> secondclassList = databaseOperation.getSecondClassList(secondNumber, thirdNumber);
                    SecondClass secondClass = databaseOperation.checkSecondClassHavingEntity(secondNumber, thirdNumber, secondclassList);
                    List<FirstClass> registeredList = databaseOperation.getFirstClassList(firstNumber);
                    String text = databaseOperation.getRegistrationOutputText(firstNumber, secondClass, registeredList);
                    databaseOperation.showToast(text);
                    databaseOperation.showTotal();
                }
            }
        };
    }

    public View.OnClickListener getClickListenerForUpdate(final EditText editTextInputNumber) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                int inputNumber = databaseOperation.getIntegerFromEditTextInput(editTextInputNumber);
                if (inputNumber != -1) {
                    List<FirstClass> registeredList = databaseOperation.getFirstClassList(inputNumber);
                    String text = databaseOperation.getQueryOutputText(registeredList);
                    databaseOperation.showToast(text);
                    databaseOperation.showTotal();
                }
            }
        };
    }

    public View.OnClickListener getClickListenerForDeleteBySecondClass(final EditText editTextDeleteSecondNumber, final EditText editTextDeleteThirdNumber) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                int secondInputNumber = databaseOperation.getIntegerFromEditTextInput(editTextDeleteSecondNumber);
                int thirdInputNumber = databaseOperation.getIntegerFromEditTextInput(editTextDeleteThirdNumber);
                if (secondInputNumber != -1 && thirdInputNumber != -1) {
                    List<SecondClass> secondClassList = databaseOperation.getSecondClassList(secondInputNumber, thirdInputNumber);
                    if (secondClassList.size()!=0)
                    databaseOperation.deleteBySecondClass(secondClassList.get(0));
                    else
                        databaseOperation.showToast("No Document found to delete");
                    databaseOperation.showTotal();
                }
            }
        };
    }

}
