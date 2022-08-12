package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StaffRegister extends AppCompatActivity {
    private TextView login;
    private EditText staffName,staffEmail,staffGender,staffAddress,staffPhoneNo,staffRole,staffPassword;
    private Button RegisterButton;
    private StaffDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_register);
        getSupportActionBar().hide();
        login = findViewById(R.id.textView11);

        staffName=findViewById(R.id.staff_name);
        staffEmail=findViewById(R.id.staff_email);
        staffGender=findViewById(R.id.staff_Gender);
        staffAddress=findViewById(R.id.staff_address);
        staffPhoneNo=findViewById(R.id.staff_phone_no);
        staffRole=findViewById(R.id.staff_role);
        staffPassword=findViewById(R.id.staff_password);

        RegisterButton=findViewById(R.id.staff_register_button);

        db = new StaffDatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }


        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = staffName.getText().toString();
                String email = staffEmail.getText().toString();
                String address=staffAddress.getText().toString();
                String gender = staffGender.getText().toString();
                String phone_no = staffPhoneNo.getText().toString();
                String role = staffRole.getText().toString();
                String password =staffPassword.getText().toString();

                boolean check = db.StaffDataInsert ( email,  name,  gender,  address,  phone_no, role,  password);

                if(check==true)
                {
                    Toast.makeText(StaffRegister.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startLoginActivity();
                }
                else
                {
                    Toast.makeText(StaffRegister.this, "Error Occurred, Try Again!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void startLoginActivity() {
        Intent i = new Intent(StaffRegister.this,StaffLogin.class);
        startActivity(i);
        finish();
    }



}