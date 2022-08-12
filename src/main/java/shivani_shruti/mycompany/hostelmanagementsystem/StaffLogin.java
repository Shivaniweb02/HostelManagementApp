package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StaffLogin extends AppCompatActivity {
private TextView register;
private EditText mEmail,mPassword;
private Button LoginButton;
private StaffDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        getSupportActionBar().hide();
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = new StaffDatabaseHelper(this);

        register=findViewById(R.id.textView10);

        mEmail=findViewById(R.id.staff_login_userEmail);
        mPassword=findViewById(R.id.staff_login_password);

        LoginButton=findViewById(R.id.staff_login_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterActivity();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                boolean check = db.checkIfUserExists(email,password);
                if(email!="" && password!="" && check==true)
                {
                    Toast.makeText(StaffLogin.this, "LogIn Successful!", Toast.LENGTH_SHORT).show();
                    startProfileActivity(email);
                }
                else
                {
                    Toast.makeText(StaffLogin.this, "User doesn't exists, Register first!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void startProfileActivity(String mEmail) {
        Intent i = new Intent(StaffLogin.this,StaffPage.class);
        i.putExtra("email", mEmail);
        startActivity(i);
        finish();
    }

    private void startRegisterActivity() {
        Intent i = new Intent(StaffLogin.this,StaffRegister.class);
        startActivity(i);
        finish();
    }
}