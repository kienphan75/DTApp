package com.example.nam.dtapp.view.activity.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nam.dtapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 12/26/2016.
 */
public class UpdateProfileActivity extends AppCompatActivity {
    private static final String TAG_NAME = "UpdateProfileActivity";
    @BindView(R.id.updateprofile_object)
    Spinner spObject;
    private String listType[] = {"Bác sĩ/ Bác sĩ nội trú","Giảng viên y khoa","Sinh viên y khoa","Chuyên viên y tế khác"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        ButterKnife.bind(this);

        initView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        return true;
    }

    public void initView(){

//


        spObject.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listType));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }
}
class ConcernFieldAdapter extends RecyclerView.Adapter<ConcernFieldAdapter.TypeViewHolder> {
    private ArrayList<String> listConcernField;
    private Context mContext;
    public boolean checkBox [] = new boolean[3];
    public ConcernFieldAdapter(Context context, ArrayList<String> listConcernField) {
        this.listConcernField = listConcernField;
        this.mContext = context;
    }
    public void newData(ArrayList<String> data){
        this.listConcernField = data;
        notifyDataSetChanged();
    }
    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_concern_field, parent, false);
        TypeViewHolder viewHolder = new TypeViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return listConcernField.size();
    }

    @Override
    public void onBindViewHolder(final TypeViewHolder holder, final int position) {
        holder.tvField.setText(listConcernField.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cbField.setChecked(!holder.cbField.isChecked());
                checkBox[position] = holder.cbField.isChecked();

            }
        });
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvField)
        TextView tvField;
        @BindView(R.id.cbField)
        CheckBox cbField;
        public TypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
