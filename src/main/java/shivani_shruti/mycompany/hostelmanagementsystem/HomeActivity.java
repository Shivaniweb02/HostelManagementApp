package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {
private Button student_auth, staff_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        student_auth=findViewById(R.id.button_student);
        staff_auth=findViewById(R.id.button_staff);

        student_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStudentAuthentication();
            }
        });

        staff_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStaffAuthentication();
            }
        });

    }

    private void startStudentAuthentication() {
        Intent i = new Intent(HomeActivity.this,StudentLogin.class);
        startActivity(i);
        finish();
    }

    private void startStaffAuthentication() {
        Intent i = new Intent(HomeActivity.this,StaffLogin.class);
        startActivity(i);
        finish();
    }

}

