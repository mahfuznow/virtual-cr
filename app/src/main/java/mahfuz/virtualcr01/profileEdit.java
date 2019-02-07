package mahfuz.virtualcr01;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileEdit extends AppCompatActivity {

    Button signout;

    StorageReference mystorage;
    FirebaseAuth auth;
    TextView phone, email, identity, department;
    EditText  name, roll;
    Spinner year, semester;
    FirebaseFirestore myFirestore;
    String myidentity, myname, myroll, myemail, myphone, uid, mydepartment, myyear, mysemester;
    ProgressBar progressBar;
    FloatingActionButton save;

    CircleImageView circleImage;
    public Uri imguri;

    static final int PICK_IMAGE = 1;


    private BottomNavigationView bottomNav;


    @Override
    protected void onPostResume() {
        bottomNav.setSelectedItemId(R.id.navigation_profile);
        super.onPostResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        myFirestore = FirebaseFirestore.getInstance();
        mystorage = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();


        circleImage = (CircleImageView) findViewById(R.id.profileImg);
        identity = (TextView) findViewById(R.id.identity);
        department = (TextView) findViewById(R.id.dept);
        year = (Spinner) findViewById(R.id.year);
        semester = (Spinner) findViewById(R.id.semester);
        name = (EditText) findViewById(R.id.name);
        roll = (EditText) findViewById(R.id.roll);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        signout = (Button) findViewById(R.id.signout);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        save = (FloatingActionButton) findViewById(R.id.save);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNav);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter4);


        imguri = null;

        circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a Picture"), PICK_IMAGE);

            }


        });


        uid = auth.getUid();


        myFirestore.collection("user").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                myname = documentSnapshot.getString("name");
                myroll = documentSnapshot.getString("roll");
                myphone = documentSnapshot.getString("phone");
                myemail = documentSnapshot.getString("email");
                myidentity = documentSnapshot.getString("identity");

                mydepartment = documentSnapshot.getString("department");
                myyear = documentSnapshot.getString("year");
                mysemester = documentSnapshot.getString("semester");


                identity.setText(myidentity);
                department.setText(mydepartment);
                name.setText(myname);
                roll.setText(myroll);
                phone.setText(myphone);
                email.setText(myemail);


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





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myname = name.getText().toString().trim();
                myroll = roll.getText().toString().trim();

                if (!TextUtils.isEmpty(myname) && !TextUtils.isEmpty(myroll)) {


                    Map<String, Object> setvalue1 = new HashMap<>();
                    setvalue1.put("name", myname);
                    setvalue1.put("roll",myroll);

                    myFirestore.collection("user").document(uid).update(setvalue1);


                    if (myyear != null && mysemester != null) {

                        Map<String, Object> setvalue = new HashMap<>();
                        setvalue.put("year", myyear.toLowerCase());
                        setvalue.put("semester", mysemester.toLowerCase());

                        myFirestore.collection("user").document(uid).update(setvalue);

                    }
                    Intent intent = new Intent(profileEdit.this,profile.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(mahfuz.virtualcr01.profileEdit.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                }



            }
        });











        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_class:
                        Intent intent_class = new Intent(profileEdit.this, TabedActivity.class);
                        startActivity(intent_class);
                        return true;
                        /*
                    case R.id.navigation_notice:
                        Intent intent_notice = new Intent(profileEdit.this, notice.class);
                        startActivity(intent_notice);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        return true;
                        */
                    case R.id.navigation_profile:
                        //Intent intent_profile =  new Intent(profile.this,profile.class);
                        //startActivity(intent_profile);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_800));
                        return true;
                        /*
                    case R.id.navigation_dashboard:
                        Intent intent_dashboard = new Intent(profileEdit.this, dashboard.class);
                        startActivity(intent_dashboard);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true;
                        */
                    case R.id.navigation_about:
                        Intent intent_about = new Intent(profileEdit.this, about.class);
                        startActivity(intent_about);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.grey_60));
                        return true;
                }
                return false;
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE) {
            progressBar.setVisibility(View.INVISIBLE);
            imguri = data.getData();
            circleImage.setImageURI(imguri);
        }


    }
}
