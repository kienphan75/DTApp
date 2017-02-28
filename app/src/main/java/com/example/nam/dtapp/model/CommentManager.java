package com.example.nam.dtapp.model;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nam.dtapp.R;


import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 12/16/2016.
 */

public class CommentManager {
    private static final int ID = 836127827;
    public static ArrayList<Comment> listComment=new ArrayList<Comment>();
    private Context context;
    private AlphaAnimation alphaAnim;
    private boolean isStart=true;

    public CommentManager (Context context) {
        this.context = context;
        alphaAnim=new AlphaAnimation(0.0F, 1.0F);
        alphaAnim.setDuration(500);
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isStart=false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isStart=true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void addContact(LinearLayout viewParent, String name, String avatar, String time, String content){
        TableRow rowView;
        int currentRow=1;
        Comment comment=new Comment(name,avatar,time,content);
        rowView =new TableRow(context);
        rowView.setWeightSum(1);
        rowView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rowView.setId(ID + currentRow);
        rowView.setGravity(Gravity.LEFT);

        final View commentView= View.inflate(context, R.layout.item_comment, null);
        commentView.setTag(comment);
        TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight=1;
        commentView.setLayoutParams(layoutParams);
        commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isStart) {
                    return;
                }
                isStart = false;

                view.startAnimation(alphaAnim);
                if (mListener == null) {
                    return;
                }
                mListener.onCalling((Comment) view.getTag());
            }
        });
        commentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mListener.onEditContact(CommentManager.this, view);
                return true;
            }
        });

        ImageView img_avatar= (ImageView) commentView.findViewById(R.id.img_avtarcomment);
        TextView tv_name= (TextView) commentView.findViewById(R.id.tv_namecomment);
        TextView tv_time= (TextView) commentView.findViewById(R.id.tv_timecomment);
        TextView tv_content= (TextView) commentView.findViewById(R.id.tv_contentcomment);

        Glide.with((context)).load(avatar).into(img_avatar);
        tv_name.setText(name);
        tv_time.setText(time);
        tv_content.setText(content);

        rowView.addView(commentView);
        viewParent.addView(rowView);

        listComment.add(comment);
    }

    public ArrayList<Comment> getListComment() {
        return listComment;
    }




    private OnCallListener mListener;
    public void setOnCallListener(OnCallListener event){
        mListener = event;
    }
    public interface OnCallListener{
        void onCalling(Comment comment);
        void onEditContact(CommentManager commentManager, View view);
    }
}
