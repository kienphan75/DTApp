package com.example.nam.dtapp.view.activity.comment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nam.dtapp.R;
import com.example.nam.dtapp.model.Comment;
import com.example.nam.dtapp.model.CommentManager;
import com.example.nam.dtapp.view.adapter.HomeRVAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity implements CommentManager.OnCallListener, View.OnClickListener {

    public static CommentManager contactMgr;
    private LinearLayout lnParent;
    private CircleImageView img_avatar;
    private ImageView img_imagecomment, img_likecomment, img_icon, img_send;
    private TextView tv_name, tv_time, tv_status, tv_coutlike, tv_countcomment;
    String name, time, ct_status, image;
    String avatar;
    int coutlike, coutcomment;
    EditText edt_comment;
    boolean checklike = false;
    RecyclerView recyclerView;
    HomeRVAdapter.Adapter_ListImage adapter;
    ArrayList<String> anh = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = getIntent().getStringExtra("name");
        avatar = getIntent().getStringExtra("avatar");
        ct_status = getIntent().getStringExtra("status");
        image = getIntent().getStringExtra("image");
        anh = getIntent().getStringArrayListExtra("listanh");
        coutlike = getIntent().getIntExtra("like", 0);
        coutcomment = getIntent().getIntExtra("commnent", 0);
        recyclerView = (RecyclerView) findViewById(R.id.recycellist_anh_comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new HomeRVAdapter.Adapter_ListImage(getBaseContext(), anh);
        recyclerView.setAdapter(adapter);


        img_avatar = (CircleImageView) findViewById(R.id.img_avtar);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_status = (TextView) findViewById(R.id.tv_stcomment);
        tv_coutlike = (TextView) findViewById(R.id.tv_countlike);
        tv_countcomment = (TextView) findViewById(R.id.tv_countcommnet);
        img_icon = (ImageView) findViewById(R.id.img_icon);
        img_likecomment = (ImageView) findViewById(R.id.img_likecomment);
        img_send = (ImageView) findViewById(R.id.img_send);
        edt_comment = (EditText) findViewById(R.id.edt_comment);
        edt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (avatar != "") {
            Glide.with((this)).load(avatar).into(img_avatar);
        } else {
            img_avatar.getResources().getDrawable(R.drawable.photo);
        }
        tv_name.setText(name);
        tv_status.setText(ct_status);
        tv_coutlike.setText(coutlike + " người thích điều này.");
        tv_countcomment.setText("Có " + coutcomment + " bình luân. Để bảo đảm tính riêng tư của người gửi, bạn chỉ xem được bình luận của bạn bè chung.");

        img_send.setOnClickListener(this);
        img_icon.setOnClickListener(this);

        initViews();
    }


    private void initViews() {

        contactMgr = new CommentManager(this);
        contactMgr.setOnCallListener(this);
        lnParent = (LinearLayout) findViewById(R.id.ln_parent);
        contactMgr.addContact(lnParent, "Sơn Tùng", "http://www.w3schools.com/css/trolltunga.jpg", "bạn thật tuyệt", "sssss");
        contactMgr.addContact(lnParent, "Sơn Tùng", "http://www.w3schools.com/css/trolltunga.jpg", "bạn thật tuyệt", "sssss");
        contactMgr.addContact(lnParent, "Sơn Tùng", "http://www.w3schools.com/css/trolltunga.jpg", "bạn thật tuyệt", "sssss");
        contactMgr.addContact(lnParent, "Sơn Tùng", "http://www.w3schools.com/css/trolltunga.jpg", "bạn thật tuyệt", "sssss");

    }


    @Override
    public void onCalling(Comment comment) {

    }

    @Override
    public void onEditContact(CommentManager commentManager, View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_send:
                String ed = edt_comment.getText().toString();
                contactMgr.addContact(lnParent, "Sơn Tùng", "http://www.w3schools.com/css/trolltunga.jpg", "bạn thật tuyệt", ed);
                edt_comment.setText("");
                break;
            case R.id.img_likecomment:
                if (checklike == false) {
                    img_likecomment.setImageResource(R.drawable.ic_star_border_black_24dp);
                    checklike = true;
                } else {
                    img_likecomment.setImageResource(R.drawable.ic_star_border_black_24dp);
                    checklike = false;
                }
                break;
            case R.id.img_icon:
                break;
        }
    }
}
