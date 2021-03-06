package zechat.android.training.zemoso.zechat.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import zechat.android.training.zemoso.zechat.R;
import zechat.android.training.zemoso.zechat.adapters.HomePagerAdapter;
import zechat.android.training.zemoso.zechat.fragments.ActiveChatFragment;
import zechat.android.training.zemoso.zechat.fragments.Contacts;

public class MainActivity extends AppCompatActivity {

    // region Variables
    private HomePagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    Realm bgRealm;
    String tag = MainActivity.class.getSimpleName();
    //endregion

    //region Inherited Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager = (ViewPager) findViewById(R.id.main_pager);
        mViewPager.setAdapter(mPagerAdapter);

        updateFragments();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "Activity Resumed");

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgRealm = Realm.getDefaultInstance();
        bgRealm.close();
        Log.d(tag, "relam closed");
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
*/
    //endregion

    //region Private Methods

    private void updateFragments() {
        fragmentList.add(ActiveChatFragment.newInstance());
        fragmentList.add(Contacts.newInstance());
        mPagerAdapter.notifyDataSetChanged();
    }

    //endregion

}

