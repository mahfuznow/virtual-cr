package mahfuz.virtualcr01;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    public Button signUp;
    FirebaseAuth mauth;
    String myemail;
    String mypass;
    ProgressDialog dialog;


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
        signUp = (Button) findViewById(R.id.signUp);
        dialog = new ProgressDialog(MainActivity.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myemail = email.getText().toString().trim();
                mypass = pass.getText().toString().trim();
                dialog.setMessage("Please Wait ...");
                dialog.show();



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
                    Toast.makeText(mahfuz.virtualcr01.MainActivity.this,"Please fill up all the required fields",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signUp.class);
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
