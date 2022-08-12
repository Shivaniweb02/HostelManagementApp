package shivani_shruti.mycompany.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class StaffPage extends AppCompatActivity {
private Button show_my_info,update_info,delete_info,show_all_staff;
private StaffDatabaseHelper db;
StudentDatabaseHelper db1;
Button Logout,Stud;
ImageButton back1;
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);
        getSupportActionBar().hide();
        email = getIntent().getStringExtra("email");
        show_my_info=findViewById(R.id.show_my_info_button);
        update_info=findViewById(R.id.update_my_info_button);
        delete_info=findViewById(R.id.delete_my_info_button);
        show_all_staff=findViewById(R.id.show_all_users);
        Stud=findViewById(R.id.stud);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Logout = (Button) findViewById(R.id.logout);

        Intent in = getIntent();
        String string = in.getStringExtra("message");
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StaffPage.this);
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

        db1 = new StudentDatabaseHelper(this);
        db = new StaffDatabaseHelper(this);

        show_my_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                back1 =  findViewById(R.id.back1);
//                back1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                    }
//                });

                Cursor res = db.ShowMyInfo(email);
                if(res.getCount()==0)
                {
                    Toast.makeText(StaffPage.this, "No Entry exists!", Toast.LENGTH_SHORT).show();
                    return ;}

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Email : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Gender : "+res.getString(2)+"\n");
                    buffer.append("Address : "+res.getString(3)+"\n");
                    buffer.append("Phone No : "+res.getString(4)+"\n");
                    buffer.append("Role : "+res.getString(5)+"\n");
                    buffer.append("Password : "+res.getString(6)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(StaffPage.this);
                builder.setCancelable(true);
                builder.setTitle("Staff Entry");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
//changes


        Stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor res = db1.DataShow();
                if(res.getCount()==0)
                {
                    Toast.makeText(StaffPage.this, "No Entry exists!", Toast.LENGTH_SHORT).show();
                    return ;}

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Email : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Gender : "+res.getString(2)+"\n");
                    buffer.append("Address : "+res.getString(3)+"\n");
                    buffer.append("Phone No : "+res.getString(4)+"\n");
                    buffer.append("Role : "+res.getString(5)+"\n");
                    //buffer.append("Password : "+res.getString(6)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(StaffPage.this);
                builder.setCancelable(true);
                builder.setTitle("All Student Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
     delete_info.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             boolean check = db.StaffDataDelete(email);
             if(check==true)
             {Toast.makeText(StaffPage.this, "Your Profile deleted!", Toast.LENGTH_SHORT).show();
             startHomeActivity();finish();}
             else
                 Toast.makeText(StaffPage.this, "Try Again!", Toast.LENGTH_SHORT).show();
         }
     });


        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUpdateActivity(email);
            }
        });

        show_all_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.Show();
                if(res.getCount()==0)
                {
                    Toast.makeText(StaffPage.this, "No Entry exists!", Toast.LENGTH_SHORT).show();
                    return ;}

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Email : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Gender : "+res.getString(2)+"\n");
                    buffer.append("Address : "+res.getString(3)+"\n");
                    buffer.append("Phone No : "+res.getString(4)+"\n");
                    buffer.append("Role : "+res.getString(5)+"\n");
                    buffer.append("Password : "+res.getString(6)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(StaffPage.this);
                builder.setCancelable(true);
                builder.setTitle("Staff Entry");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
    private void startHomeActivity() {
        Intent i = new Intent(StaffPage.this,HomeActivity.class);
        startActivity(i);
        finish();
    }

    private void startUpdateActivity(String mEmail) {
        Intent i = new Intent(StaffPage.this,UpdateStaffData.class);
        i.putExtra("email", mEmail);
        startActivity(i);
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}