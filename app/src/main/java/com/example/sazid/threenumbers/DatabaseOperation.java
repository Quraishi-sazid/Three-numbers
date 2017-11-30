package com.example.sazid.threenumbers;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sazid on 11/7/2017.
 */

public class DatabaseOperation {

    private static Context context;
    private Activity activity;


    public DatabaseOperation(Context context, Activity activity) {
        this.context = context;
        this.activity=activity;
    }

    public SecondClass createAndSaveSecondClass(int secondNumber, int thirdNumber) {
        SecondClass secondClass = new SecondClass();
        secondClass.setSecondNumber(secondNumber);
        secondClass.setThirdNumber(thirdNumber);
        secondClass.save();
        return secondClass;
    }

    public void createAndSaveFirstClass(int firstNumber, SecondClass secondClass) {
        FirstClass firstClass = new FirstClass();
        firstClass.setFirstNumber(firstNumber);
        firstClass.setSecondClass(secondClass);
        firstClass.save();
    }

    public String getRegistrationOutputText(int firstNumber, SecondClass secondClass, List<FirstClass> registeredList) {
        String text;
        if (registeredList.size() == 0) {
            createAndSaveFirstClass(firstNumber, secondClass);
            text = "Document Registered";
        } else {
            FirstClass savedFirstClass = registeredList.get(0);
            updateDatabase(secondClass, savedFirstClass);
            text = "Document Updated";
        }
        return text;
    }

    public void updateDatabase(SecondClass secondClass, FirstClass savedFirstClass) {
        savedFirstClass.setSecondClass(secondClass);
        savedFirstClass.save();
    }

    public String getQueryOutputText(List<FirstClass> registeredList) {
        String text;
        if (registeredList.size() == 0) {
            text = "NO Document is Registered with this number";
        } else {
            text = "First Number= " + Integer.toString(registeredList.get(0).getFirstNumber()) + " Second Number= "
                    + Integer.toString(registeredList.get(0).getSecondClass().getSecondNumber()) + " Third Number= "
                    + Integer.toString(registeredList.get(0).getSecondClass().getThirdNumber());
        }
        return text;
    }

    public int getIntegerFromEditTextInput(EditText editText) {
        String string = editText.getText().toString();
        if (string.equals(""))
            return -1;
        return Integer.parseInt(string);
    }

    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    public SecondClass checkSecondClassHavingEntity(int secondNumber, int thirdNumber, List<SecondClass> secondClassList) {
        if (secondClassList.size() == 0) {
            showToast("Created");
            return createAndSaveSecondClass(secondNumber, thirdNumber);
        } else {
            showToast("Not Created");
            return secondClassList.get(0);
        }
    }
    public List<FirstClass> getFirstClassList(int firstNumber) {
        return FirstClass.find(FirstClass.class, "first_number = ?", Integer.toString(firstNumber));
    }
    public void deleteFromTable(List<FirstClass> deletingList) {
        if (deletingList.size() == 0)
            showToast("No Record Found To delete");
        else {
            deletingList.get(0).delete();
            showToast("Record deleted");
        }
    }
    public void deleteBySecondClass(SecondClass secondClass) {
        Long id = secondClass.getId();
        List<FirstClass> firstClassList = FirstClass.find(FirstClass.class, "second_class = ? ", Long.toString(id));
        if (firstClassList.size() == 0) {
            showToast("No document found to delete");
        } else {
            deleteAllRecordsForSecondClass(firstClassList);
            secondClass.delete();
            showToast("Document deleted");
        }
    }

    private void deleteAllRecordsForSecondClass(List<FirstClass> firstClassList) {
        for (FirstClass iterator:firstClassList)
            iterator.delete();
    }

    public List<SecondClass> getSecondClassList(int secondNumber, int thirdNumber) {
        return SecondClass.find(SecondClass.class, "second_number = ? and third_number = ? ", Integer.toString(secondNumber), Integer.toString(thirdNumber));
    }
    public void showTotal()
    {
        List<FirstClass> firstClassList = FirstClass.listAll(FirstClass.class);
        List<SecondClass> secondClassList = FirstClass.listAll(SecondClass.class);
        TextView firstClassNumberTextView = (TextView)this.activity.findViewById(R.id.first_class_total);
        TextView secondClassNumberTextView = (TextView)this.activity.findViewById(R.id.second_class_total);
        firstClassNumberTextView.setText(Integer.toString(firstClassList.size()));
        secondClassNumberTextView.setText(Integer.toString(secondClassList.size()));
    }
}
