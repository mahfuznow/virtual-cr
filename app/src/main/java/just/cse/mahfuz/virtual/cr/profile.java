package just.cse.mahfuz.virtual.cr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    Button signout;

    StorageReference mystorage;
    FirebaseAuth auth;
    TextView name,roll,phone,email,identity,department,year,semester;
    FirebaseFirestore myFirestore;
    String myidentity,myname,myroll,myemail,myphone,uid,mydepartment,myyear,mysemester;
    ProgressBar progressBar;
    FloatingActionButton edit;

    CircleImageView circleImage;
    public Uri imguri;

    static final int PICK_IMAGE=1;


    private BottomNavigationView bottomNav;


    @Override
    protected void onPostResume() {
        bottomNav.setSelectedItemId(R.id.navigation_profile);
        super.onPostResume();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        myFirestore = FirebaseFirestore.getInstance();
        mystorage= FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();


        circleImage= (CircleImageView)findViewById(R.id.profileImg);
        identity = (TextView) findViewById(R.id.identity);
        department = (TextView) findViewById(R.id.dept);
        year = (TextView) findViewById(R.id.yr);
        semester = (TextView) findViewById(R.id.sm);
        name = (TextView) findViewById(R.id.name);
        roll = (TextView) findViewById(R.id.roll);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        signout = (Button) findViewById(R.id.signout);
        progressBar = (ProgressBar) findViewById(R.id.loading);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNav);
        edit = (FloatingActionButton) findViewById(R.id.edit);


        imguri=null;

        circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select a Picture"),PICK_IMAGE);

            }









        });


        uid= auth.getUid();


        myFirestore.collection("user").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                myname =documentSnapshot.getString("name");
                myroll =documentSnapshot.getString("roll");
                myphone =documentSnapshot.getString("phone");
                myemail =documentSnapshot.getString("email");
                myidentity =documentSnapshot.getString("identity");

                mydepartment = documentSnapshot.getString("department");
                myyear = documentSnapshot.getString("year");
                mysemester = documentSnapshot.getString("semester");


                identity.setText(myidentity);
                department.setText(mydepartment);
                year.setText(myyear);
                semester.setText(mysemester);
                name.setText(myname);
                roll.setText(myroll);
                phone.setText(myphone);
                email.setText(myemail);


            }
        });




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, profileEdit.class);
                startActivity(intent);
            }
        });



        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                finish();

                Intent intent = new Intent(profile.this, MainActivity.class);
                startActivity(intent);

            }
        });



        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_class:
                        Intent intent_class =  new Intent(profile.this, TabedActivity.class);
                        startActivity(intent_class);
                        return true;
                        /*
                    case R.id.navigation_notice:
                        Intent intent_notice =  new Intent(profile.this,notice.class);
                        startActivity(intent_notice);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        return true; */
                    case R.id.navigation_profile:
                        //Intent intent_profile =  new Intent(profile.this,profile.class);
                        //startActivity(intent_profile);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_800));
                        return true;

                        /*
                    case R.id.navigation_dashboard:
                        Intent intent_dashboard =  new Intent(profile.this,dashboard.class);
                        startActivity(intent_dashboard);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true; */
                    case R.id.navigation_about:
                        Intent intent_about =  new Intent(profile.this, about.class);
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



        if(requestCode==PICK_IMAGE)
        {
            progressBar.setVisibility(View.INVISIBLE);
            imguri= data.getData();
            circleImage.setImageURI(imguri);
        }




    }
}
