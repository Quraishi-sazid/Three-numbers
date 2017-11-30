package com.example.sazid.threenumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    ButtonClass buttonClass=new ButtonClass(this,this);
    DatabaseOperation databaseOperation =new DatabaseOperation(this,this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseOperation.showTotal();
        onButtonPress();
    }
    private void onButtonPress() {
        EditText editTextFirstNumber = (EditText) findViewById(R.id.first_number);
        EditText editTextSecondNumber = (EditText) findViewById(R.id.second_number);
        EditText editTextThirdNumber = (EditText) findViewById(R.id.third_number);
        EditText editTextInputNumber = (EditText) findViewById(R.id.input_first_number);
        EditText editTextDeletingNumber = (EditText) findViewById(R.id.number_to_be_deleted);
        EditText editTextDeleteSecondNumber=(EditText) findViewById(R.id.deletable_second_number);
        EditText editTextDeleteThirdNumber=(EditText) findViewById(R.id.deletable_third_number);
        Button registerButton = (Button) findViewById(R.id.submit_button);
        Button queryButton = (Button) findViewById(R.id.query_button);
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        Button deleteButtonBySecondClass=(Button) findViewById(R.id.delete_button_by_second_class);
        registerButton.setOnClickListener(buttonClass.getClickListenerForRegister(editTextFirstNumber, editTextSecondNumber, editTextThirdNumber, editTextInputNumber));
        queryButton.setOnClickListener(buttonClass.getClickListenerForUpdate(editTextInputNumber));
        deleteButton.setOnClickListener(buttonClass.getClickListenerForDelete(editTextDeletingNumber));
        deleteButtonBySecondClass.setOnClickListener(buttonClass.getClickListenerForDeleteBySecondClass(editTextDeleteSecondNumber,editTextDeleteThirdNumber));
    }
}
