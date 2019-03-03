package mahfuz.virtualcr01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity {


    EditText name, roll, phone, email, pass;
    Button signUp;
    RadioButton male, female;
    RadioButton student, cr, teacher, staff;
    FirebaseAuth auth;
    Firebase PrimaryKeyUid;
    FirebaseFirestore myFirestore;
    public String myname, mygender, myroll, myphone, myemail, mypass, uid;
    ProgressDialog dialog;

    String identity, myunit, mydepartment,myyear,mysemester;
    Spinner unit, department,year,semester;

    RadioGroup catagory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        auth = FirebaseAuth.getInstance();
        myFirestore = FirebaseFirestore.getInstance();

        Firebase.setAndroidContext(signUp.this);


        name = (EditText) findViewById(R.id.name);
        roll = (EditText) findViewById(R.id.roll);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        signUp = (Button) findViewById(R.id.signUp);
        dialog = new ProgressDialog(signUp.this);


        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        student = (RadioButton) findViewById(R.id.student);
        cr = (RadioButton) findViewById(R.id.cr);
        teacher = (RadioButton) findViewById(R.id.teacher);
        staff = (RadioButton) findViewById(R.id.staff);

        catagory=findViewById(R.id.catagory);

        unit = (Spinner) findViewById(R.id.unit);
        department = (Spinner) findViewById(R.id.department);
        year = (Spinner) findViewById(R.id.year);
        semester = (Spinner) findViewById(R.id.semester);


       

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.department, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter4);


        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myunit = (String) adapterView.getItemAtPosition(i);
                //Toast.makeText(mahfuz.virtualcr01.signUp.this, myunit, Toast.LENGTH_SHORT).show();

                if ("--Unit--".equals(myunit)) {
                    myunit = null;
                } else if ("A".equals(myunit)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mahfuz.virtualcr01.signUp.this, R.array.unitA, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    department.setAdapter(adapter);

                } else if ("B".equals(myunit)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mahfuz.virtualcr01.signUp.this, R.array.unitB, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    department.setAdapter(adapter);

                } else if ("C".equals(myunit)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mahfuz.virtualcr01.signUp.this, R.array.unitC, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    department.setAdapter(adapter);

                } else if ("D".equals(myunit)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mahfuz.virtualcr01.signUp.this, R.array.unitD, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    department.setAdapter(adapter);

                } else if ("E".equals(myunit)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mahfuz.virtualcr01.signUp.this, R.array.unitE, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    department.setAdapter(adapter);

                } else if ("F".equals(myunit)) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mahfuz.virtualcr01.signUp.this, R.array.unitF, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    department.setAdapter(adapter);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mydepartment = (String) adapterView.getItemAtPosition(i);
                if ("--Department--".equals(mydepartment)) {
                    mydepartment = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myyear = (String) adapterView.getItemAtPosition(i);
                if ("--Year--".equals(myyear)) {
                    myyear=null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mysemester = (String) adapterView.getItemAtPosition(i);
                if ("--Semester--".equals(mysemester)) {
                    mysemester=null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Please Wait ...");
                dialog.show();

                myname = name.getText().toString().trim();
                myroll = roll.getText().toString().trim();
                myphone = phone.getText().toString().trim();
                myemail = email.getText().toString().trim();
                mypass = pass.getText().toString().trim();


                    if (male.isChecked()) {
                        mygender = "male";
                    } else if (female.isChecked()) {
                        mygender = "female";
                    }

                    if (student.isChecked()) {
                        identity = "student";
                    } else if (cr.isChecked()) {
                        identity = "cr";
                    } else if (teacher.isChecked()) {
                        identity = "teacher";
                    } else if (staff.isChecked()) {
                        identity = "staff";
                    }






                    if (myunit != null && mydepartment != null && myyear != null && mysemester != null) {


                        if (!TextUtils.isEmpty(myname) && !TextUtils.isEmpty(myroll) && !TextUtils.isEmpty(myemail) && !TextUtils.isEmpty(mypass)) {


                            auth.createUserWithEmailAndPassword(myemail, mypass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        dialog.dismiss();
                                        Intent homeintent = new Intent(mahfuz.virtualcr01.signUp.this, MainActivity.class);
                                        Toast.makeText(mahfuz.virtualcr01.signUp.this, "Registration successfully completed", Toast.LENGTH_SHORT).show();
                                        startActivity(homeintent);
                                        finish();


                                        // Write data to the database


                                        uid = auth.getUid();

                                        //writing into firebase database

                                        PrimaryKeyUid = new Firebase("https://virtual-cr.firebaseio.com/users/" + uid);
                                        Firebase userName = PrimaryKeyUid.child("name");
                                        userName.setValue(myname);
                                        Firebase userRoll = PrimaryKeyUid.child("roll");
                                        userRoll.setValue(myroll);
                                        Firebase userPhone = PrimaryKeyUid.child("phone");
                                        userPhone.setValue(myphone);
                                        Firebase userEmail = PrimaryKeyUid.child("email");
                                        userEmail.setValue(myemail);
                                        Firebase userPass = PrimaryKeyUid.child("password");
                                        userPass.setValue(mypass);


                                        //Writing into firestore

                                        Map<String, String> setvalue = new HashMap<>();
                                        setvalue.put("name", myname);
                                        setvalue.put("gender", mygender.toLowerCase());
                                        setvalue.put("unit", myunit.toLowerCase());
                                        setvalue.put("department", mydepartment.toLowerCase());
                                        setvalue.put("year", myyear.toLowerCase());
                                        setvalue.put("semester", mysemester.toLowerCase());
                                        setvalue.put("roll", myroll);
                                        setvalue.put("phone", myphone);
                                        setvalue.put("email", myemail);
                                        setvalue.put("pass", mypass);
                                        setvalue.put("identity", identity.toLowerCase());
                                        myFirestore.collection("user").document(uid).set(setvalue);


                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    if (e instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(mahfuz.virtualcr01.signUp.this, "An account already exist with this email", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else if (e instanceof FirebaseAuthWeakPasswordException) {
                                        Toast.makeText(mahfuz.virtualcr01.signUp.this, "Please use a strong password using both number & characters minimum 6 digit", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                        Toast.makeText(mahfuz.virtualcr01.signUp.this, "Invalid Email format", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }
                            });


                        } else {
                            Toast.makeText(mahfuz.virtualcr01.signUp.this, "Please fill up all the required fields", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    } else {
                        Toast.makeText(mahfuz.virtualcr01.signUp.this, "Select your Unit,Department,Year,Semester", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
            });

        }


    }
