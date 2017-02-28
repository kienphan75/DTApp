package com.example.nam.dtapp.view.activity.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.nam.dtapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 12/25/2016.
 */
public class RegisterConcernIDActivity extends AppCompatActivity {
    @BindView(R.id.register_concern)
    RecyclerView recycleConcern;

    @BindView(R.id.concern_next)Button next;
    ConcernFieldAdapter concernFieldAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_concernid);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final ArrayList<String > list = new ArrayList<>();
        list.add("Chuyên ngành 1");
        list.add("Chuyên ngành 2");
        list.add("Chuyên ngành 3");
        concernFieldAdapter = new ConcernFieldAdapter(getApplicationContext(),list);
        recycleConcern.setAdapter(concernFieldAdapter);
        recycleConcern.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = getIntent().getStringExtra("key");
                for(int i=0;i<concernFieldAdapter.checkBox.length;i++) {
                    if(concernFieldAdapter.checkBox[i]==true)
                        s+="_"+i;

                }
                Intent t = new Intent(getApplicationContext(),RegisterActivity.class);
                t.putExtra("key",s);
                startActivity(t);
            }
        });
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
