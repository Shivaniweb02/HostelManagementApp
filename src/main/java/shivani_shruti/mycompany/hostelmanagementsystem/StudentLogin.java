package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLogin extends AppCompatActivity {
private TextView mregister;
    private EditText mEmail,mPassword;
    private Button LoginButton;
    private StudentDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        getSupportActionBar().hide();
        db = new StudentDatabaseHelper(this);


        mEmail=findViewById(R.id.student_username_email);
        mPassword=findViewById(R.id.student_login_password);

        LoginButton=findViewById(R.id.student_login_button);

        mregister=findViewById(R.id.textView12);
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterActivity();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memail = mEmail.getText().toString();
                String menrolment_no = mPassword.getText().toString();

                boolean check = db.checkIfUserExistsStudent(memail,menrolment_no);
                if(memail!="" && menrolment_no!="" && check==true)
                {
                    Toast.makeText(StudentLogin.this, "LogIn Successful!", Toast.LENGTH_SHORT).show();
                    startProfileActivity(memail);
                }
                else
                {
                    Toast.makeText(StudentLogin.this, "User doesn't exists, Register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void startProfileActivity(String email) {
        Intent i = new Intent(StudentLogin.this,StudentPage.class);
        i.putExtra("m_email", email);
        startActivity(i);
        finish();
    }

    private void startRegisterActivity() {
        Intent i = new Intent(StudentLogin.this,StudentRegister.class);
        startActivity(i);
        finish();
    }
}