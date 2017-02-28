package com.example.nam.dtapp.view.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.util.RecyclerItemClickListener;
import com.example.nam.dtapp.view.adapter.SearchAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Phan Trung Kien on 2/18/2017.
 */

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private SearchView searchView;
    SearchAdapter adapter;
    RecyclerView recyclerView;
    ImageView img_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        searchView= (SearchView) findViewById(R.id.search_view_search);

        img_back= (ImageView) findViewById(R.id.toolbar_back);
        recyclerView= (RecyclerView) findViewById(R.id.recycel_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        adapter= new SearchAdapter(getBaseContext(),getList());
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"back",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getBaseContext(),getList().get(position),Toast.LENGTH_SHORT).show();
                searchView.setQuery(getList().get(position), false);
                searchView.clearFocus();
            }

        }));


    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public ArrayList<String> getList() {
        ArrayList<String> list= new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");
        list.add("Item 6");
        list.add("Item 7");
        list.add("Item 8");
        list.add("Item 9");
        list.add("Item 10");
        list.add("Item 11");
        list.add("Item 12");
        return list;
    }


}
