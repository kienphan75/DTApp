package com.example.nam.dtapp.view.activity.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.Case;
import com.example.nam.dtapp.model.Status;
import com.example.nam.dtapp.view.activity.newfeed.Acivity_tag;
import com.example.nam.dtapp.view.activity.newfeed.UpLoadActivity;
import com.example.nam.dtapp.view.activity.profile.ProfileActivity;
import com.example.nam.dtapp.view.adapter.HomeVPAdapter;
import com.example.nam.dtapp.view.fragment.NewFeedFragment;
import com.example.nam.dtapp.view.fragment.NotifcationFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends ActionBarActivity implements
        SmartTabLayout.TabProvider, SearchView.OnQueryTextListener {
    public static final String TAG_NAME = "HomeActivity";
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    SmartTabLayout smartTabLayout;
    Case aCase;
    ArrayList<Case> listCase= new ArrayList<>();
    HomeVPAdapter.HomePagerAdapter adapter;
    Fragment FRAGMENT_CONTRUCT[] = {
            NewFeedFragment.getInstance(),
            NewFeedFragment.getInstance(),
            NotifcationFragment.getInstance()
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        saveSizeScreen();



        Intent intent = getIntent();
        aCase=intent.getParcelableExtra("doituong");
        if (aCase != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("dataCase", aCase);
            FRAGMENT_CONTRUCT[0].setArguments(bundle);
            adapter.notifyDataSetChanged();
        }






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallery = new Intent(HomeActivity.this,UpLoadActivity.class);
                startActivity(gallery);


            }
        });
    }

    /**
     * Luu kich co man hinh
     */
    public void saveSizeScreen(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        Config.heightScreen = displaymetrics.heightPixels;
        Config.widthScreen= displaymetrics.widthPixels;
    }
    public void initView(){
        adapter = new HomeVPAdapter.HomePagerAdapter(getSupportFragmentManager(), FRAGMENT_CONTRUCT);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("");
        viewPager.setAdapter(adapter);
        smartTabLayout.setCustomTabView(this);
        smartTabLayout.setViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch (id){
            case R.id.notication_icon:
                break;
            case R.id.profile_icon:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.action_search:
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
        return false;
    }

    @Override
    public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View tab = inflater.inflate(R.layout.custom_tab_icon_and_notification_mark, container, false);
        View mark = tab.findViewById(R.id.custom_tab_notification_mark);
        mark.setVisibility(View.GONE);
        ImageView icon = (ImageView) tab.findViewById(R.id.custom_tab_icon);
        switch (position) {
            case 0:
                icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_home_24dp));
                break;
            case 1:
                icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chat_24dp));
                break;
            case 2:
                icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_notification_24dp));
                break;
            default:
                throw new IllegalStateException("Invalid position: " + position);
        }
        return tab;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    class TaskLoading extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... strings) {

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void integer) {
            super.onPostExecute(integer);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
