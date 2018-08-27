package com.ahsan.a52_roompersistenceapi28;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for entering a word.
 */

public class NewWordActivity extends AppCompatActivity {

    private static final String TAG = "NewWordActivity";

    public static final String EMPLOYEE_ID = "employee-id";
    public static final String FIRST_NAME = "first-name";
    public static final String LAST_NAME = "last-name";
    public static final String TITLE = "title";
    public static final String DEPARTMENT = "department";

    private EditText edit_employee_id,
            edit_employee_firstname,
            edit_employee_lastname,
            edit_employee_title,
            edit_employee_department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        edit_employee_id = findViewById(R.id.edit_employee_id);
        edit_employee_firstname = findViewById(R.id.edit_employee_firstname);
        edit_employee_lastname = findViewById(R.id.edit_employee_lastname);
        edit_employee_title = findViewById(R.id.edit_employee_title);
        edit_employee_department = findViewById(R.id.edit_employee_department);

        Button button = (Button) findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent replyIntent = new Intent();
                if (
                        TextUtils.isEmpty(edit_employee_id.getText()) ||
                        TextUtils.isEmpty(edit_employee_firstname.getText()) ||
                        TextUtils.isEmpty(edit_employee_lastname.getText()) ||
                        TextUtils.isEmpty(edit_employee_title.getText()) ||
                        TextUtils.isEmpty(edit_employee_department.getText())
                        )
                {
                    setResult(RESULT_CANCELED, replyIntent);//If button is clicked without entering the data, do not encapsulate data in setResult
                }

                //Send the user entered data to MainActivity using intent extra
                else {
                    String employee_id = edit_employee_id.getText().toString();
                    String first_name = edit_employee_firstname.getText().toString();
                    String last_name = edit_employee_lastname.getText().toString();
                    String title = edit_employee_title.getText().toString();
                    String department = edit_employee_department.getText().toString();

                    replyIntent.putExtra(EMPLOYEE_ID, employee_id);
                    replyIntent.putExtra(FIRST_NAME, first_name);
                    replyIntent.putExtra(LAST_NAME, last_name);
                    replyIntent.putExtra(TITLE, title);
                    replyIntent.putExtra(DEPARTMENT, department);
                    setResult(RESULT_OK, replyIntent);
                }
                finish(); //Go back to previous activity which called this activity.
            }
        });

    }

}
