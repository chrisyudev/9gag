package app.android.nineninegag.com.a99gag;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import app.android.nineninegag.com.a99gag.Adapter.ViewPager.FrontPageViewPagerAdapter;
import app.android.nineninegag.com.a99gag.Fragment.ListViewFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NineHomeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)  Toolbar toolbar;
    @Bind(R.id.tabs)  TabLayout tabLayout;
    @Bind(R.id.viewpager)  ViewPager viewPager;

    private ArrayList<String> tabName = new ArrayList<String>() {{
        add("HOT");
        add("TRENDING");
        add("FRESH");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_home);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        FrontPageViewPagerAdapter adapter = new FrontPageViewPagerAdapter(getSupportFragmentManager());

        for(int i = 0; i < tabName.size(); i++)
            adapter.addFragment(new ListViewFragment(i), tabName.get(i));

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(tabName.size() - 1);
    }
}
