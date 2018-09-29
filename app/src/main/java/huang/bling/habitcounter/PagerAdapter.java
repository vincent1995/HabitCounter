package huang.bling.habitcounter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import huang.bling.habitcounter.Fragment.AchieveFragment;
import huang.bling.habitcounter.Fragment.ChartFragment;
import huang.bling.habitcounter.Fragment.MainFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numTag;
    public PagerAdapter(FragmentManager fm,int numTag) {
        super(fm);
        this.numTag = numTag;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0: return new MainFragment();
            case 1: return new ChartFragment();
            case 2: return new AchieveFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numTag;
    }
}
