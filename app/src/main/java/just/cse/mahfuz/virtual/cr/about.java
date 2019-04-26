package just.cse.mahfuz.virtual.cr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class about extends AppCompatActivity {

    BottomNavigationView bottomNav;


    @Override
    protected void onPostResume() {
        bottomNav.setSelectedItemId(R.id.navigation_about);
        super.onPostResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);






        bottomNav= (BottomNavigationView) findViewById(R.id.bottomNav);




        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_class:
                        Intent intent_class =  new Intent(about.this, TabedActivity.class);
                        startActivity(intent_class);
                        return true;

                        /*
                    case R.id.navigation_notice:
                        Intent intent_notice =  new Intent(about.this,notice.class);
                        startActivity(intent_notice);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        return true;*/
                    case R.id.navigation_profile:
                        Intent intent_profile =  new Intent(about.this, profile.class);
                        startActivity(intent_profile);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_800));
                        return true;

                        /*
                    case R.id.navigation_dashboard:
                        Intent intent_dashboard =  new Intent(about.this,dashboard.class);
                        startActivity(intent_dashboard);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        return true;
                        */
                    case R.id.navigation_about:
                        //Intent intent_about =  new Intent(about.this,profile.class);
                        //startActivity(intent_about);
                        //navigation.setBackgroundColor(getResources().getColor(R.color.grey_60));
                        return true;
                }
                return false;
            }
        });
    }
}
