package com.example.nam.dtapp.view.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.WrapContentViewPager;
import com.example.nam.dtapp.view.adapter.ProfileVPAdapter;
import com.example.nam.dtapp.view.fragment.ProfileFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG_NAME = "ProfileActivity";

    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapToolbar;
    @BindView(R.id.toolbar_profile) Toolbar toolbar;
    @BindView(R.id.iv_image_profile) ImageView ivProfile;
    @BindView(R.id.tab_profile) SmartTabLayout smartTabLayout;
    @BindView(R.id.view_pager_profile)
    WrapContentViewPager viewPager;
    @BindView(R.id.Fullname) TextView tvName;
    @BindView(R.id.Phone) TextView tvPhone;
    @BindView(R.id.Email) TextView tvEmail;
    ProfileVPAdapter.ProfileAdapter adapter;

    Fragment FRAGMENT_CONTRUCT[] = {
            ProfileFragment.getInstance(),
            ProfileFragment.getInstance(),
            ProfileFragment.getInstance()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        collapToolbar.setTitle(Config.user.getFullname());
        Glide.with(getApplicationContext())
                .load(Config.user.getAvatar())
                .fitCenter()
                .into(ivProfile);
        tvEmail.setText(Config.user.getEmail());
        tvName.setText(Config.user.getFullname());
        tvPhone.setText(Config.user.getPhone());
        initView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.profile_edit:
                startActivity(new Intent(this,UpdateProfileActivity.class));
                return true;
        }
        return false;
    }

    public void initView(){
        adapter = new ProfileVPAdapter.ProfileAdapter(getSupportFragmentManager(), FRAGMENT_CONTRUCT);
//        setSupportActionBar(toolbar);
//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
        Log.e(TAG_NAME,"Init finish view");
    }

}
