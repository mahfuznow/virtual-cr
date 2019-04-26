package just.cse.mahfuz.virtual.cr;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText pass;
    public Button login;
    Button privacy;
    public Button signUp;
    FirebaseAuth mauth;
    String myemail;
    String mypass;
    ProgressDialog dialog;

    RadioGroup radioGroup;
    RadioButton student, teacher;
    Spinner department,name;
    String mDepartment,mName;



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mauth.getCurrentUser();
        if (currentUser!=null){
            Intent intent = new Intent(MainActivity.this, TabedActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mauth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        privacy=findViewById(R.id.privacy);
        signUp = (Button) findViewById(R.id.signUp);
        dialog = new ProgressDialog(MainActivity.this);

        radioGroup=findViewById(R.id.radioGroup);
        student=findViewById(R.id.student);
        teacher=findViewById(R.id.teacher);

        department=findViewById(R.id.department);
        name=findViewById(R.id.name);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.student) {
                    department.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                    email.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                }
                else if (checkedId==R.id.teacher) {
                    email.setVisibility(View.GONE);
                    pass.setVisibility(View.GONE);
                    department.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                }
            }
        });

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDepartment= (String) parent.getItemAtPosition(position);

                if ("--Department--".equals(mDepartment)) {
                    mDepartment="null";
                }
                else if ("CSE".equals(mDepartment)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.TeacherCSE, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    name.setAdapter(adapter);
                    name.requestFocus();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mName= (String) parent.getItemAtPosition(position);
                if ("--Name--".equals(mName)) {
                    mName="null";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




















        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setMessage("Please Wait ...");
                dialog.show();

                if (student.isChecked()) {
                    myemail = email.getText().toString().trim();
                    mypass = pass.getText().toString().trim();

                    if (!TextUtils.isEmpty(myemail) && !TextUtils.isEmpty(mypass)) {

                        mauth.signInWithEmailAndPassword(myemail, mypass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(MainActivity.this, TabedActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(MainActivity.this, "LogIn failed. Please input correct email & password", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                    }
                    else {
                        Toast.makeText(MainActivity.this,"Please fill up all the required fields",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
                else {
                    if (!TextUtils.isEmpty(mDepartment) && !TextUtils.isEmpty(mName)&& !("null".equals(mDepartment))&& !("null".equals(mName))) {
                        Intent intent =new Intent(MainActivity.this,TabedActivity.class);
                        intent.putExtra("tDepartment",mDepartment);
                        intent.putExtra("tName",mName);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                    else {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"Please fill up all the required fields",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PrivacyPolicy.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, just.cse.mahfuz.virtual.cr.signUp.class);
                startActivity(intent);
            }
        });


        //setting notification chanel for android oreo and higher

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("VCR","VCR",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to exit?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                moveTaskToBack(true);
                finish();
                // android.os.Process.killProcess(android.os.Process.myPid());
                // System.exit(1);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        //  super.onBackPressed();
    }




}
