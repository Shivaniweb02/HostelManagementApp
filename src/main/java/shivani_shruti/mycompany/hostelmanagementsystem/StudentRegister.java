package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentRegister extends AppCompatActivity {
private TextView mLogin;
    private EditText studentName,studentEmail,studentRollNo,studentEnrolNo,studentBranch,studentSemester,studentGender,studentAddress,studentPhone;
    private Button RegisterButton;
    private StudentDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        getSupportActionBar().hide();

        studentName=findViewById(R.id.student_name);
        studentEmail=findViewById(R.id.student_email);
        studentRollNo=findViewById(R.id.student_roll_no);
        studentEnrolNo=findViewById(R.id.student_enrolment_no);
        studentBranch=findViewById(R.id.student_branch);
        studentSemester=findViewById(R.id.student_sem);
        studentGender=findViewById(R.id.student_Gender);
        studentAddress=findViewById(R.id.student_address);
        studentPhone=findViewById(R.id.student_phone_no);

        RegisterButton=findViewById(R.id.student_register_button);
        db = new StudentDatabaseHelper(this);

        mLogin=findViewById(R.id.textView13);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentName.getText().toString();
                String email = studentEmail.getText().toString();
                String address=studentAddress.getText().toString();
                String gender = studentGender.getText().toString();
                String phone_no = studentPhone.getText().toString();
                 String roll_no = studentRollNo.getText().toString();
                 String  enrol_no = studentEnrolNo.getText().toString();
                 String branch = studentBranch.getText().toString();
                 String sem = studentSemester.getText().toString();

                boolean check = db.StudentDataInsert ( email,  name,  gender,  address,  phone_no, roll_no, enrol_no, branch, sem);

                if(check==true)
                {
                    Toast.makeText(StudentRegister.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startLoginActivity();
                }
                else
                {
                    Toast.makeText(StudentRegister.this, "Error Occurred, Try Again!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void startLoginActivity() {
        Intent i = new Intent(StudentRegister.this,StudentLogin.class);
        startActivity(i);
        finish();
    }


}