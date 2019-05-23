package adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PhotoFragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private int len;
    private String[] photoType={"照片","插画","向量"};

    public PhotoFragmentPageAdapter(FragmentManager fm, List<Fragment> fragments) {
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return photoType[position];
    }
}
