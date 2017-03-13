package com.example.nam.dtapp.view.activity.newfeed;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.Case;
import com.example.nam.dtapp.model.CustomViewPager;

import com.example.nam.dtapp.model.Status;
import com.example.nam.dtapp.model.User;
import com.example.nam.dtapp.model.UserFriend;
import com.example.nam.dtapp.model.modelAPI.AddNewCase;
import com.example.nam.dtapp.model.modelAPI.GetFriendList;
import com.example.nam.dtapp.model.modelAPI.Login;
import com.example.nam.dtapp.model.modelAPI.UploadImage;
import com.example.nam.dtapp.service.API;
import com.example.nam.dtapp.util.Utils;
import com.example.nam.dtapp.view.activity.home.HomeActivity;
import com.example.nam.dtapp.view.activity.start.LoginActivity;
import com.example.nam.dtapp.view.activity.start.RegisterActivity;
import com.example.nam.dtapp.view.adapter.UpLoadNewFeedAdapter;
import com.example.nam.dtapp.view.fragment.Fragment_Newfeed_step1;
import com.example.nam.dtapp.view.fragment.Fragment_Newfeed_step2;
import com.example.nam.dtapp.view.fragment.Fragment_Newfeed_step3;
import com.example.nam.dtapp.view.fragment.NewFeedFragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Activity dùng để thêm newfeed
 */
public class UpLoadActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_NAME = "UpLoadActivity";
    CustomViewPager viewPager;
    FragmentManager manager = getSupportFragmentManager();
    List<Fragment> listfragment = new ArrayList<>();
    public static Button btnnext, btnprevious;
    Status status = new Status();
    int vitri = 0;
    public static int RESULT_LOAD_IMAGE = 21;
    private BroadcastReceiver updateReceiver;
    UpLoadNewFeedAdapter.Adapter adapter;
    Fragment_Newfeed_step1 fr1;
    Fragment_Newfeed_step2 fr2;
    Fragment_Newfeed_step3 fr3;
    Case aC = new Case();
    ProgressDialog progressDialog;
    ArrayList<String> DS = new ArrayList<>();
    private ArrayList<User> listFriend = new ArrayList<>();
    ArrayList<String> listFriendname = new ArrayList<>();
    ArrayList<User> Friends = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upnewfeed);
        progressDialog = new ProgressDialog(UpLoadActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        viewPager = (CustomViewPager) findViewById(R.id.pagerPost);
        btnnext = (Button) findViewById(R.id.btnnext);
        btnprevious = (Button) findViewById(R.id.btnprevious);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(UpLoadActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        btnnext.setOnClickListener(this);
        btnprevious.setOnClickListener(this);
        new TaskGetListFrend().execute();


    }

    private void init() {
        fr1 = new Fragment_Newfeed_step1();
        fr2 = new Fragment_Newfeed_step2();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("listNameFriend", listFriendname);
        fr2.setArguments(bundle);

        fr3 = new Fragment_Newfeed_step3();
        listfragment.add(fr1);
        listfragment.add(fr2);
        listfragment.add(fr3);
        adapter = new UpLoadNewFeedAdapter.Adapter(manager, listfragment);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        btnnext.setEnabled(false);
        btnprevious.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnnext:
                switch (vitri) {
                    case 0:
                        status.setListImg(fr1.getPath());
                        btnprevious.setText("PREVIOUS");
                        vitri++;
                        viewPager.setCurrentItem(vitri);
                        btnnext.setEnabled(false);
                        if (fr2.getEd1().getText().length() > 4) {
                            btnnext.setEnabled(true);
                        }
                        break;
                    case 1:
                        vitri++;
                        viewPager.setCurrentItem(vitri);
                        btnnext.setText("UPLOAD");
                        break;
                    case 2:
                        progressDialog.show();
                        aC.setFieldId((int) fr2.getSpinner().getSelectedItemId() + 1);
                        aC.setTitle(fr2.getEd1().getText().toString());
                        aC.setDescription(fr3.getEd().getText().toString());
                        aC.setCreateDate(getTimeSystem());

                        String s2 = fr2.getEd2().getText().toString();
                        listFriendname = TachDanhSachBan(s2);
                        for (int i = 0; i < listFriendname.size(); i++) {
                            if (checkFriend(listFriendname.get(i)) != null) {
                                Friends.add(checkFriend(listFriendname.get(i)));
                            }

                        }
                        Log.e(TAG_NAME, "DSFRIEND : " + Friends.size());


                        aC.setTagFriends(Friends);
                        aC.setTotalUserFollowing(0);
                        aC.setUsersFollowings(null);
                        aC.setCommentNumber(0);
                        aC.setStartComments(null);
                        TaskLoadImage taskLoadImage = new TaskLoadImage();
                        taskLoadImage.execute();
                        break;
                }
                break;
            case R.id.btnprevious:
                switch (vitri) {
                    case 1:
                        if (Fragment_Newfeed_step1.ok == true) {
                            btnnext.setEnabled(true);
                            Glide.with(getBaseContext()).load(status.getListImg().get(0)).centerCrop().into(fr1.getImgMain());
                        }
                        vitri--;
                        viewPager.setCurrentItem(vitri);
                        btnprevious.setText("");
                        break;
                    case 2:
                        vitri--;
                        viewPager.setCurrentItem(vitri);
                        btnnext.setText("NEXT");
                        break;
                }
                break;
        }
    }

    public class TaskLoadImage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... v) {

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API image = retrofit.create(API.class);
            for (int i = 0; i < fr1.getPath().size(); i++) {
                Bitmap bitmap = getImageBitmapFromUrl((fr1.getPath().get(i)));
                final String f = Utils.bitmapToString(bitmap);
                UploadImage uploadImage = new UploadImage(Config.APP_KEY, 1, ".jpeg", f);
                Call<String> call1 = image.uploadImage(uploadImage);
                call1.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            JSONObject img = new JSONObject(Utils.convertJson(response.body()));
                            String t = img.getString("Data");
                            Log.e(TAG_NAME, "link img" + " " + t);
                            DS.add("http://hippocampus.vn" + t);
                            Log.e(TAG_NAME, "xem thu " + DS.get(DS.size() - 1));
                            if (DS.size() == fr1.getPath().size()) {
                                aC.setImageURLs(DS);
                                TaskUpLoad tf = new TaskUpLoad();
                                tf.execute();
                            }


                        } catch (JSONException e) {
                            Log.e(TAG_NAME, "Lỗi json");
                            e.printStackTrace();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void strings) {
            super.onPostExecute(strings);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


    public class TaskUpLoad extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... cases) {

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final API upCaseAPI = retrofit.create(API.class);


            Log.e(TAG_NAME, "aC" + Config.APP_KEY + "\n" +
                    Config.FOR_TEST + "\n" +
                    Config.USER_ID + "\n" +
                    aC.getTitle() + "\n" +
                    aC.getImageURLs() + "\n" +
                    aC.getTagFriends() + "\n" +
                    aC.getFieldId() + "\n" +
                    aC.getDescription());

            AddNewCase addNewCase = new AddNewCase(Config.APP_KEY,
                    Config.FOR_TEST,
                    Config.USER_ID,
                    aC.getTitle(),
                    aC.getImageURLs(),
                    aC.getTagFriends(),
                    aC.getFieldId(),
                    aC.getDescription());


            Call<String> call = upCaseAPI.addNewCase(addNewCase);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        JSONObject json = new JSONObject(Utils.convertJson(response.body()));
                        Log.e(TAG_NAME, json.toString());
                        int code = json.getInt("errCode");
                        Log.e(TAG_NAME, code + "");
                        if (code == 200) {
                            aC.setCaseId(json.getString("Data"));
                            Log.e(TAG_NAME, "DATA: " + json.getString("Data"));


                        } else {
                            Log.e(TAG_NAME, "Loooooooi");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progressDialog.dismiss();
            Toast.makeText(getBaseContext(), "UPLOAD THANH CÔNG", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.putExtra("parcelable", status);
            intent.putExtra("doituong", aC);
            startActivity(intent);
            finish();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private class TaskGetListFrend extends AsyncTask<Void, Void, ArrayList<UserFriend>> {

        @Override
        protected ArrayList<UserFriend> doInBackground(Void... integers) {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final API getFriend = retrofit.create(API.class);
            Log.e(TAG_NAME, "USERIDFRIEND : " + Config.USER_ID);
            GetFriendList getFriendList = new GetFriendList(Config.APP_KEY, Config.FOR_TEST, Config.USER_ID);


            Call<String> call = getFriend.getFriendList(getFriendList);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        JSONObject json = new JSONObject(Utils.convertJson(response.body()));
                        Log.e(TAG_NAME, json.toString());
                        int code = json.getInt("errCode");
                        Log.e(TAG_NAME, "code friend : " + code + "");
                        if (code == 200) {
                            progressDialog.dismiss();
                            Log.e(TAG_NAME, "DATA friend: " + json.getString("Data"));
                            JSONArray data = json.getJSONArray("Data");
                            Log.e(TAG_NAME, "Sizzz : " + data.length());

                            Log.e(TAG_NAME, "Sizzz : " + listFriend.size());
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject object = (JSONObject) data.get(i);
                                User userFriend = new User();
                                userFriend.setUserId(object.getInt("UserId"));
                                userFriend.setEmail(object.getString("Email"));
                                userFriend.setFullname(object.getString("Fullname"));
                                listFriendname.add("#" + object.getString("Fullname"));
                                userFriend.setAvatar(object.getString("Avatar"));
                                listFriend.add(userFriend);
                                Log.e(TAG_NAME, "UserId : " + userFriend.getUserId());
                                Log.e(TAG_NAME, "Email : " + userFriend.getEmail());
                                Log.e(TAG_NAME, "Avatar : " + userFriend.getAvatar());
                                Log.e(TAG_NAME, "Fullname : " + userFriend.getFullname());
                                Log.e(TAG_NAME, "Sizzz : " + listFriend.size());

                            }
                            init();


                        } else {
                            Log.e(TAG_NAME, "loi danh sach ban be");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(ArrayList<UserFriend> strings) {
            super.onPostExecute(strings);
        }
    }

    private String getTimeSystem() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        return datetime;
    }

//    public static Bitmap getBitmap(String filePath) {
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(filePath, options);
//
//        Boolean scaleByHeight = Math.abs(options.outHeight - 100) >= Math
//                .abs(options.outWidth - 100);
//        if (options.outHeight * options.outWidth * 2 >= 16384) {
//            double sampleSize = scaleByHeight
//                    ? options.outHeight / 100
//                    : options.outWidth / 100;
//            options.inSampleSize =
//                    (int) Math.pow(2d, Math.floor(
//                            Math.log(sampleSize) / Math.log(2d)));
//        }
//        options.inJustDecodeBounds = false;
//        options.inTempStorage = new byte[512];
//        Bitmap output = BitmapFactory.decodeFile(filePath, options);
//        return output;
//    }


    public static Bitmap getImageBitmapFromUrl(String imageFilePath) {
        try {
            BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
            Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
            return bmp;
        } catch (Exception e) {
        }

        return null;
    }


    private ArrayList<String> TachDanhSachBan(String s) {
        ArrayList<String> list = new ArrayList<>();
        Log.e(TAG_NAME, "Chuoi ban dau la : " + s);
        String[] words = s.split("\\s");
        for (int i = 0; i < words.length; i++) {
            Log.e(TAG_NAME, "K************");
            Log.e(TAG_NAME, "choi la :" + words[i]);
            Log.e(TAG_NAME, "Do dai :" + words[i].length() + "");

            Log.e(TAG_NAME, "Trim :" + words[i].trim());
            Log.e(TAG_NAME, "Do dai sau Trim :" + words[i].trim().length());
            try {
                String s1 = words[i].trim().substring(1, (words[i].length() - 1));
                Log.e(TAG_NAME, "S :" + s1);
                Log.e(TAG_NAME, "Do dai sau cuoi :" + s1.length());
                list.add(s1);
            } catch (Exception e) {
            }


        }
        return list;
    }

    private User checkFriend(String name) {
        for (int i = 0; i < listFriend.size(); i++) {
            if (listFriend.get(i).getFullname().equals(name)) {
                return listFriend.get(i);
            }
        }
        return null;
    }
}
