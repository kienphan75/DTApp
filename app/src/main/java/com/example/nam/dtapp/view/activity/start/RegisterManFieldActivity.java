package com.example.nam.dtapp.view.activity.start;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.nam.dtapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Chon cong viec va nganh
 */

public class RegisterManFieldActivity extends Activity {
    public final String TAG="RegisterManFieldActivity";
    @BindView(R.id.register_subject)
    RecyclerView rcvSubject;
    AdapterObjectType adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_subject);
        ButterKnife.bind(this);
        String [] listOb ={"  Bác sĩ/ Bác sĩ nội trú",
                           "  Giảng viên y khoa",
                           "  Sinh viên y khoa",
                           "  Chuyên viên y tế khác"};
        AdapterObjectType adapterObjectType = new AdapterObjectType(this,listOb);
        rcvSubject.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcvSubject.setAdapter(adapterObjectType);

    }
}
class AdapterObjectType extends RecyclerView.Adapter<AdapterObjectType.HolderManfeild>{
    String [] listOb;
    Context mContext;
    public AdapterObjectType(Context c,String[] listOb){
        mContext = c;
        this.listOb = listOb;
    }
    @Override
    public HolderManfeild onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_register_objecttype,parent,false);
        HolderManfeild holderManfeild = new HolderManfeild(v);
        return holderManfeild;
    }

    @Override
    public void onBindViewHolder(final HolderManfeild holder, final int position) {
        holder.obButton.setText(listOb[position]);
        holder.obButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.listField.getVisibility()==View.VISIBLE) {
                    holder.listField.setVisibility(View.GONE);
                    holder.more.setBackgroundResource(R.drawable.ic_more_up_24dp);
                }
                else {
                    holder.more.setBackgroundResource(R.drawable.ic_more_down_24dp);
                    holder.listField.setVisibility(View.VISIBLE);
                }

            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.listField.getVisibility()==View.VISIBLE) {
                    holder.listField.setVisibility(View.GONE);
                    holder.more.setBackgroundResource(R.drawable.ic_more_up_24dp);
                }
                else {
                    holder.more.setBackgroundResource(R.drawable.ic_more_down_24dp);
                    holder.listField.setVisibility(View.VISIBLE);
                }
            }
        });
        final String nganh [] = new String[]{"Nganh 1","Nganh 2","Nganh 3"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (mContext,android.R.layout.simple_list_item_1,nganh);
        holder.listField.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(holder.listField);
        holder.listField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String s=position+"_"+i;

                Intent intent = new Intent(mContext,RegisterConcernIDActivity.class);
                intent.putExtra("key",s);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOb.length;
    }

    class HolderManfeild extends RecyclerView.ViewHolder{
        @BindView(R.id.objecttype_moredown)
        Button more;
        @BindView(R.id.objecttype_ObjectType)
        Button obButton;
        @BindView(R.id.objecttype_list)
        ListView listField;

        public HolderManfeild(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            listField.setVisibility(View.GONE);

        }

    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

