package mahfuz.virtualcr01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

    BottomNavigationView bottomNav;
    TextView m11,m12,m13,m14,m15;
    TextView m21,m22,m23,m24,m25;
    TextView m31,m32,m33,m34,m35;
    TextView m41,m42,m43,m44,m45;
    TextView m51,m52,m53,m54,m55;
    TextView m61,m62,m63,m64,m65;
    TextView m71,m72,m73,m74,m75;

    String mym11,mym12,mym13,mym14,mym15;
    TextView mym21,mym22,mym23,mym24,mym25;
    TextView mym31,mym32,mym33,mym34,mym35;
    TextView mym41,mym42,mym43,mym44,mym45;
    TextView mym51,mym52,mym53,mym54,mym55;
    TextView mym61,mym62,mym63,mym64,mym65;
    TextView mym71,mym72,mym73,mym74,mym75;


    @Override
    protected void onPostResume() {
        //bottomNav.setSelectedItemId(R.id.navigation_dashboard);
        super.onPostResume();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        m11= findViewById(R.id.m11);
        m12= findViewById(R.id.m12);
        m13= findViewById(R.id.m13);
        m14= findViewById(R.id.m14);
        m15= findViewById(R.id.m15);

        m21= findViewById(R.id.m21);
        m22= findViewById(R.id.m22);
        m23= findViewById(R.id.m23);
        m24= findViewById(R.id.m24);
        m25= findViewById(R.id.m25);

        m31= findViewById(R.id.m31);
        m32= findViewById(R.id.m32);
        m33= findViewById(R.id.m33);
        m34= findViewById(R.id.m34);
        m35= findViewById(R.id.m35);

        m41= findViewById(R.id.m41);
        m42= findViewById(R.id.m42);
        m43= findViewById(R.id.m43);
        m44= findViewById(R.id.m44);
        m45= findViewById(R.id.m45);

        m51= findViewById(R.id.m51);
        m52= findViewById(R.id.m52);
        m53= findViewById(R.id.m53);
        m54= findViewById(R.id.m54);
        m55= findViewById(R.id.m55);

        m61= findViewById(R.id.m61);
        m62= findViewById(R.id.m62);
        m63= findViewById(R.id.m63);
        m64= findViewById(R.id.m64);
        m65= findViewById(R.id.m65);

        m71= findViewById(R.id.m71);
        m72= findViewById(R.id.m72);
        m73= findViewById(R.id.m73);
        m74= findViewById(R.id.m74);
        m75= findViewById(R.id.m75);





        bottomNav= (BottomNavigationView) findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_class:
                        Intent intent_class =  new Intent(dashboard.this,TabedActivity.class);
                        startActivity(intent_class);
                        return true;

                        /*
                    case R.id.navigation_notice:
                        Intent intent_notice =  new Intent(dashboard.this,notice.class);
                        startActivity(intent_notice);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        return true; */
                    case R.id.navigation_profile:
                        Intent intent_profile =  new Intent(dashboard.this,profile.class);
                        startActivity(intent_profile);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_800));
                        return true;
                        /*
                    case R.id.navigation_dashboard:
                        //Intent intent_dashboard =  new Intent(dashboard.this,profile.class);
                        //startActivity(intent_dashboard);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true;*/
                    case R.id.navigation_about:
                        Intent intent_about =  new Intent(dashboard.this,about.class);
                        startActivity(intent_about);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.grey_60));
                        return true;
                }
                return false;
            }
        });

    }
}
