package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class DialogImageViewAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private int len;

    public DialogImageViewAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        len=fragments.size();
        return len;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }
}
