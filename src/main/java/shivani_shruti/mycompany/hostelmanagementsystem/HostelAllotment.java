package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HostelAllotment extends AppCompatActivity {
String email;
private Button Add_button;
private RadioGroup hostel_name,room_type;
private RadioButton ac,non_ac,a,b,rb,rb2;
private StudentDatabaseHelper db;
ImageButton Back43;
String m_hostel_name,m_room_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_allotment);

        db = new StudentDatabaseHelper(this);
        email = getIntent().getStringExtra("h_email");

        hostel_name=findViewById(R.id.hostel_name);
        room_type=findViewById(R.id.room_type);
        Back43 =  findViewById(R.id.back43);
        Back43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HostelAllotment.this, StudentPage.class);
                startActivity(intent);
            }
        });
        ac=findViewById(R.id.ac_room);
        non_ac=findViewById(R.id.non_ac_room);
        a=findViewById(R.id.hostel_a);
        b=findViewById(R.id.hostel_b);

        Add_button=findViewById(R.id.add_button);


        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int select = hostel_name.getCheckedRadioButtonId();
                rb = findViewById(select);
                String s = rb.getText().toString();
                if(s=="Hostel A")
                    m_hostel_name= "Hostel A";
                else
                    m_hostel_name="Hostel B";

                int select2 = room_type.getCheckedRadioButtonId();
                rb2 = findViewById(select2);
                String s2 = rb.getText().toString();
                if(s2=="AC")
                    m_room_type= "AC";
                else
                    m_room_type="NON_AC";
                boolean check = db.HostelDataInsert ( email, m_hostel_name,m_room_type );

                if(check==true)
                {
                    Toast.makeText(HostelAllotment.this, "Info added Successfully!", Toast.LENGTH_SHORT).show();
                    startHomepageActivity();
                    finish();
                }
                else
                {
                    Toast.makeText(HostelAllotment.this, "Error Occurred, Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startHomepageActivity() {
        Intent i = new Intent(HostelAllotment.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}