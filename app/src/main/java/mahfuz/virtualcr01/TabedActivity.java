package mahfuz.virtualcr01;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import static mahfuz.virtualcr01.R.color.colorPrimary;

public class TabedActivity extends AppCompatActivity {

    private Button today,tomorrow,profile;
    private ViewPager viewPager;

    private  PagerViewAdapter pagerViewAdapter;

    private BottomNavigationView bottomNav;


    @Override
    protected void onPostResume() {
        bottomNav.setSelectedItemId(R.id.navigation_class);
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabed);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        today= (Button) findViewById(R.id.today);
        tomorrow= (Button) findViewById(R.id.tomorrow);
        profile= (Button) findViewById(R.id.profile);



        viewPager = (ViewPager) findViewById(R.id.viewpager);

        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNav);



        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });




        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                changeTabs(i);

            }


            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });





        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_class:

                        return true;

                        /*
                    case R.id.navigation_notice:
                        Intent intent_notice =  new Intent(TabedActivity.this,notice.class);
                        startActivity(intent_notice);
                        //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        return true; */
                    case R.id.navigation_profile:
                        Intent intent_profile =  new Intent(TabedActivity.this,profile.class);
                        startActivity(intent_profile);
                        //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_800));
                        return true;
                        /*
                    case R.id.navigation_dashboard:
                        Intent intent_dashboard =  new Intent(TabedActivity.this,dashboard.class);
                        startActivity(intent_dashboard);
                        //overridePendingTransition(android.R.anim.accelerate_interpolator,android.R.anim.accelerate_decelerate_interpolator);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true;
                        */
                    case R.id.navigation_about:
                        Intent intent_about =  new Intent(TabedActivity.this,about.class);
                        startActivity(intent_about);
                        //overridePendingTransition(android.R.anim.bounce_interpolator,android.R.anim.bounce_interpolator);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.grey_60));
                        return true;
                }
                return false;
            }
        });

    }









    private void changeTabs(int i) {
        if(i==0) {
            today.setBackground(getDrawable(R.drawable.semi_round));
            tomorrow.setBackground(getDrawable(R.color.colorPrimary));
            profile.setBackground(getDrawable(R.color.colorPrimary));

            today.setTextColor(getResources().getColor(R.color.colorPrimary));
            tomorrow.setTextColor(getResources().getColor(R.color.white));
            profile.setTextColor(getResources().getColor(R.color.white));

        }
        if(i==1) {
            today.setBackground(getDrawable(R.color.colorPrimary));
            tomorrow.setBackground(getDrawable(R.drawable.semi_round));
            profile.setBackground(getDrawable(R.color.colorPrimary));

            today.setTextColor(getResources().getColor(R.color.white));
            tomorrow.setTextColor(getResources().getColor(R.color.colorPrimary));
            profile.setTextColor(getResources().getColor(R.color.white));
        }
        if(i==2) {
            today.setBackground(getDrawable(R.color.colorPrimary));
            tomorrow.setBackground(getDrawable(R.color.colorPrimary));
            profile.setBackground(getDrawable(R.drawable.semi_round));

            today.setTextColor(getResources().getColor(R.color.white));
            tomorrow.setTextColor(getResources().getColor(R.color.white));
            profile.setTextColor(getResources().getColor(R.color.colorPrimary));
        }





     //subscription for receiving notifications
       // FirebaseMessaging.getInstance().subscribeToTopic("class");

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
