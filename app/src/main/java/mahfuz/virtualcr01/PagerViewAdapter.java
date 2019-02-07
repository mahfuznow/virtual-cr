package mahfuz.virtualcr01;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class PagerViewAdapter extends FragmentPagerAdapter{
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {

            case 0:
                todayFragment mtodayFragment = new todayFragment();
                return  mtodayFragment;
            case 1:
                tomorrowFragment mtomorrowFragment = new tomorrowFragment();
                return  mtomorrowFragment;
            case 2:
                othersFragment mothersFragment =new othersFragment();
                return  mothersFragment;
             default:
                 return  null;



        }


    }

    @Override
    public int getCount() {
        return 3;
    }
}
