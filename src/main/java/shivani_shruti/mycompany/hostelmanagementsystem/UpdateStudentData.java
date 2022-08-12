package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateStudentData extends AppCompatActivity {
    private EditText nstudentName,nstudentRollNo,nstudentEnrolNo,nstudentBranch,nstudentSemester,nstudentGender,nstudentAddress,nstudentPhone;
    private Button Update_Button;
    private StudentDatabaseHelper db;
    String nemail;
    ImageButton button89;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_data);

        nemail = getIntent().getStringExtra("p_email");
        getSupportActionBar().hide();

        nstudentName=findViewById(R.id.st_student_name);
        nstudentRollNo=findViewById(R.id.st_student_roll_no);
        nstudentEnrolNo=findViewById(R.id.st_student_enrolment_no);
        nstudentBranch=findViewById(R.id.st_student_branch);
        nstudentSemester=findViewById(R.id.st_student_sem);
        nstudentGender=findViewById(R.id.st_student_Gender);
        nstudentAddress=findViewById(R.id.st_student_address);
        nstudentPhone=findViewById(R.id.st_student_phone_no);
        button89 =  findViewById(R.id.back89);
        button89.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateStudentData.this, StudentPage.class);
                startActivity(intent);
            }
        });
        Update_Button=findViewById(R.id.student_update_button);
        db = new StudentDatabaseHelper(this);

        Update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nstudentName.getText().toString();
                String address=nstudentAddress.getText().toString();
                String gender = nstudentGender.getText().toString();
                String phone_no = nstudentPhone.getText().toString();
                String roll = nstudentRollNo.getText().toString();
                String branch = nstudentBranch.getText().toString();
                String sem = nstudentSemester.getText().toString();
                String enrolment_no = nstudentEnrolNo.getText().toString();

                boolean check = db.StudentDataUpdate ( nemail,  name,  gender,  address,  phone_no, roll,  enrolment_no,branch,sem);
                if(check==true)
                {
                    Toast.makeText(UpdateStudentData.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    startPageActivity();
                }
                else
                {
                    Toast.makeText(UpdateStudentData.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void startPageActivity() {
        Intent i = new Intent(UpdateStudentData.this,StudentLogin.class);
        startActivity(i);
        finish();
    }
}