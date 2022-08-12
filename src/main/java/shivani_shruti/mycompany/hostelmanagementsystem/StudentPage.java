package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StudentPage extends AppCompatActivity {
    private Button m_show_my_info,m_update_info,m_delete_info,m_show_all_staff,hostel_button;
    private StudentDatabaseHelper db;
    Button Logout;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        getSupportActionBar().hide();
        email = getIntent().getStringExtra("m_email");
        m_show_my_info=findViewById(R.id.show_my_info_button_student);
        m_update_info=findViewById(R.id.update_my_info_button_student);
        m_delete_info=findViewById(R.id.delete_my_info_button_student);
        m_show_all_staff=findViewById(R.id.show_all_users_student);
        hostel_button=findViewById(R.id.hostel_button);


        Logout = (Button) findViewById(R.id.logout2);

        Intent in = getIntent();
        String string = in.getStringExtra("message");
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentPage.this);
                builder.setTitle("Confirmation").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),
                                        HomeActivity.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
        db = new StudentDatabaseHelper(this);

        m_show_my_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor res = db.ShowMyInfoStudent(email);
                if(res.getCount()==0)
                {
                    Toast.makeText(StudentPage.this, "No Entry exists!", Toast.LENGTH_SHORT).show();
                    return ;}

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Email : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Gender : "+res.getString(2)+"\n");
                    buffer.append("Address : "+res.getString(3)+"\n");
                    buffer.append("Phone No : "+res.getString(4)+"\n");
                    buffer.append("Roll No : "+res.getString(5)+"\n");
                    buffer.append("Enrolment No : "+res.getString(6)+"\n");
                    buffer.append("Branch : "+res.getString(7)+"\n");
                    buffer.append("Semester : "+res.getString(8)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentPage.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        m_delete_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = db.StudentDataDelete(email);
                if(check==true)
                {Toast.makeText(StudentPage.this, "Your Profile deleted!", Toast.LENGTH_SHORT).show();
                    startHomeActivity();finish();}
                else
                    Toast.makeText(StudentPage.this, "Try Again!", Toast.LENGTH_SHORT).show();
            }
        });

        m_update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUpdateActivity(email);
            }
        });


        m_show_all_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.DataShow();
                if(res.getCount()==0)
                {
                    Toast.makeText(StudentPage.this, "No Entry exists!", Toast.LENGTH_SHORT).show();
                    return ;}

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Email : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Gender : "+res.getString(2)+"\n");
                    buffer.append("Address : "+res.getString(3)+"\n");
                    buffer.append("Phone No : "+res.getString(4)+"\n");
                    buffer.append("Roll No : "+res.getString(5)+"\n");
                    buffer.append("Enrolment No : "+res.getString(6)+"\n");
                    buffer.append("Branch : "+res.getString(7)+"\n");
                    buffer.append("Semester : "+res.getString(8)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentPage.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        hostel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHostelActivity(email);
            }
        });

    }

    private void startHostelActivity(String h_email) {
        Intent i = new Intent(StudentPage.this,HostelAllotment.class);
        i.putExtra("h_email", h_email);
        startActivity(i);
        finish();
    }

    private void startUpdateActivity(String p_email) {
        Intent i = new Intent(StudentPage.this,UpdateStudentData.class);
        i.putExtra("p_email", p_email);
        startActivity(i);
    }


    private void startHomeActivity() {
        Intent i = new Intent(StudentPage.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}