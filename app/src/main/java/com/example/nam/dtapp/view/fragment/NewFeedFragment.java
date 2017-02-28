package com.example.nam.dtapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.Case;
import com.example.nam.dtapp.model.StartComments;
import com.example.nam.dtapp.model.Status;
import com.example.nam.dtapp.model.User;
import com.example.nam.dtapp.model.modelAPI.GetCasesList;
import com.example.nam.dtapp.service.API;
import com.example.nam.dtapp.view.activity.home.HomeActivity;
import com.example.nam.dtapp.view.activity.start.LoginActivity;
import com.example.nam.dtapp.view.adapter.HomeRVAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NAM on 12/11/2016.
 */

public class NewFeedFragment extends Fragment {
    public static final String TAG_NAME = "NewFeedFragment";
    @BindView(R.id.rv_newfeed)
    RecyclerView mRecyclerView;
    static Status newFeed;

    Case aCase;



    private HomeRVAdapter.NewFeedAdapter adapter;
    private ArrayList<Status> listNewFeed= new ArrayList<>();
    private ArrayList<Case> listCase= new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View v =inflater.inflate(R.layout.fragment_newfeed, container, false);
        ButterKnife.bind(this,v);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API loginAPI = retrofit.create(API.class);
        final GetCasesList getCasesList = new GetCasesList(Config.APP_KEY, Config.FOR_TEST,10,3,3,4);
        Call<String> call = loginAPI.getCasesList(getCasesList);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.v(TAG_NAME,"Login success: "+response.body());

                try {
                    JSONObject json  = new JSONObject(response.body());
                    int code = json.getInt("errCode");
                    if (code==200){
                        JSONObject data = json.getJSONObject("Data");

                        JSONArray array= data.getJSONArray("CaseList");
                        Log.v(TAG_NAME,array.toString());


//                        Case aCase= new Case();
//                        aCase.setCaseId(data.getInt("CaseId"));
//                        aCase.setFieldId(data.getInt("FieldId"));
//                        aCase.setTitle(data.getString("Title"));
//                        JSONArray array= data.getJSONArray("ImageURLs");
//                        ArrayList<String> list= new ArrayList<String>();
//                        for(int i=0;i<array.length();i++){
//                            list.add((String) array.get(i));
//                        }
//                        aCase.setImageURLs(list);
//                        aCase.setDescription(data.getString("Description"));
//                        aCase.setCreateDate(data.getString("CreateDate"));
//                        JSONArray array1=data.getJSONArray("TagFriends");
//                        ArrayList<User> list1= new ArrayList<User>();
//                        for(int i=0;i<array1.length();i++){
//                            JSONObject object =array1.getJSONObject(i);
//                            User user= new User(object.getInt("UserId"),object.getString("Fullname"),object.getString("Email"));
//                            list1.add(user);
//                        }
//                        aCase.setTagFriends(list1);
//                        aCase.setTotalUserFollowing(data.getInt("TotalUserFollowing"));
//                        JSONArray array2=data.getJSONArray("UsersFollowing");
//                        ArrayList<User> list2= new ArrayList<User>();
//                        for(int i=0;i<array2.length();i++){
//                            JSONObject object =array2.getJSONObject(i);
//                            User user= new User(object.getInt("UserId"),object.getString("Fullname"),object.getString("Email"));
//                            list2.add(user);
//                        }
//                        aCase.setCommentNumber(data.getInt("CommentNumber"));
//                        JSONArray array3=data.getJSONArray("StartComments");
//                        ArrayList<StartComments> list3= new ArrayList<StartComments>();
//                        for(int i=0;i<array3.length();i++){
//                            JSONObject object =array3.getJSONObject(i);
//                            StartComments startComments= new StartComments(object.getInt("CommentId"),object.getInt("UserId"),object.getString("Content"),object.getString("ImageUrl"),
//                                    object.getString("CreatedTime"),object.getBoolean("IsDeleted"));
//                            list3.add(startComments);
//                        }
//                        aCase.setStartComments(list3);



//                        Log.e(TAG_NAME,aCase.getCaseId()+"\n"+
//                                aCase.getCommentNumber()+"\n"+
//                                aCase.getCreateDate()+"\n"+
//                                aCase.getDescription()+"\n"+
//                                aCase.getTitle()+"\n"+
//                                aCase.getCreateDate()+"\n"+
//                                aCase.getCaseId()+"\n"+
//                                aCase.getCaseId()+"\n"+
//                                aCase.getCaseId()+"\n"+
//                                aCase.getCaseId()+"\n"
//                        );


                    }
                    else{
                        Toast.makeText(getActivity(),"Không thành công",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG_NAME, "Fail login: " + t.getLocalizedMessage());

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeRVAdapter.NewFeedAdapter(getContext(), listCase);
//        newFeed = getArguments().getParcelable("data");
        aCase= getArguments().getParcelable("dataCase");
        mRecyclerView.setAdapter(adapter);
//        if(newFeed!=null){
//            Toast.makeText(getActivity().getBaseContext(),"Them moi",Toast.LENGTH_SHORT).show();
//            listNewFeed.add(newFeed);
//            adapter.notifyDataSetChanged();
////            mRecyclerView.invalidate();
//        }

        if(aCase!=null){
            Toast.makeText(getActivity().getBaseContext(),"Them moi",Toast.LENGTH_SHORT).show();
            listCase.add(aCase);
            adapter.notifyDataSetChanged();
        }

        return v;
    }

    public static NewFeedFragment getInstance(){
        NewFeedFragment f = new NewFeedFragment();
        Bundle b = new Bundle();
        // set something
        f.setArguments(b);
        return f;
    }





}