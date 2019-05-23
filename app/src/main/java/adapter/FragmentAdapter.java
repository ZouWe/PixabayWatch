package adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> datas;
    private int len;
    private String[] titles={"图片","视频"};

    public FragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        datas=fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return datas.get(i);
    }

    @Override
    public int getCount() {
        len=datas.size();
        return len;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
