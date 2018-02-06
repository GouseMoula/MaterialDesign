package com.materialdesign;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.anims.ListViewActivity;
import com.anims.ViewPagerActivity;
import com.anims.WidgetActivity;
import com.materialdesign.adapters.ViewPagerAdapter;
import com.materialdesign.fragments.FragmentOne;
import com.materialdesign.fragments.FragmentThree;
import com.materialdesign.fragments.FragmentTwoWithRecycler;
import com.recycler.MainActivityRecycler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, ThemeInterface, SharedPreferences.OnSharedPreferenceChangeListener {
    private FloatingActionButton fab;
    private SharedPreferences preferences;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        setTheme(preferences.getInt("theme", R.style.ThemeBlue));
        preferences.registerOnSharedPreferenceChangeListener(this);
        setContentView(R.layout.activity_main);
        appBarLayout = findViewById(R.id.appBarMain);
        boolean isExpanded = preferences.getBoolean("appbar", false);
        appBarLayout.setExpanded(isExpanded);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("Expanded")) {
                    preferences.edit().putBoolean("appbar", true).apply();
                } else if (state.name().equalsIgnoreCase("Collapsed")) {
                    preferences.edit().putBoolean("appbar", false).apply();
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a snack bar", Snackbar.LENGTH_LONG)
                        .setAction("Okay", null).show();
            }
        });
        fab.setVisibility(View.GONE);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                hideSoftKeyboard(MainActivity.this);
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        setUp();
    }

    private void setUp() {
        ViewPager viewPager;
        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new FragmentOne(), getString(R.string.fragment_1_title));
//        pagerAdapter.addFragment(new FragmentTwo(), getString(R.string.fragment_2_title));
        pagerAdapter.addFragment(new FragmentTwoWithRecycler(), getString(R.string.fragment_2_title));
        pagerAdapter.addFragment(new FragmentThree(), getString(R.string.fragment_3_title));
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        final TabLayout tabLayout = findViewById(R.id.tabs_layout);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("position")) {
                viewPager.setCurrentItem(intent.getIntExtra("position", 1));
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_recyler_view:
                gotoNextActivity(MainActivityRecycler.class);
                break;
            case R.id.nav_anims_list:
                gotoNextActivity(ListViewActivity.class);
                break;
            case R.id.nav_anims_viewpager:
                gotoNextActivity(ViewPagerActivity.class);
                break;
            case R.id.nav_anims_image_view:
                gotoNextActivity(WidgetActivity.class);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoNextActivity(Class activityClass) {
        startActivity(new Intent(this, activityClass));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        fab.hide();
        hideSoftKeyboard(this);
        if (position == 2) {
            fab.show();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void changeTheme(int themeId) {
        preferences.edit().putInt("theme", themeId).apply();
//        recreate();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null)
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equalsIgnoreCase("theme")) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra("position", 1);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
//        recreate();
        }

    }
}
