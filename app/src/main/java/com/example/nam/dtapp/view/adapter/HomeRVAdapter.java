package com.example.nam.dtapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nam.dtapp.R;
import com.example.nam.dtapp.model.Case;
import com.example.nam.dtapp.model.ImageController;

import com.example.nam.dtapp.model.Status;
import com.example.nam.dtapp.view.activity.comment.CommentActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Lớp chứa các adapter của recyclerView trên activity home
 * Created by NAM on 12/11/2016.
 */

public class HomeRVAdapter {
    public static class NewFeedAdapter extends RecyclerView.Adapter<NewFeedAdapter.NewFeedHolder> {
        private ArrayList<Status> listTypes;
        private ArrayList<Case> listCase;
        private Context mContext;
        Adapter_ListImage adapter_listImage;


        public ArrayList<String> getImages(){
            ArrayList<String> list= new ArrayList<>();
            list.add("http://www.w3schools.com/css/trolltunga.jpg");
            list.add("http://www.w3schools.com/css/trolltunga.jpg");
            list.add("http://www.w3schools.com/css/trolltunga.jpg");
            list.add("http://www.w3schools.com/css/trolltunga.jpg");
            list.add("http://www.w3schools.com/css/trolltunga.jpg");
            return list;
        }



        public NewFeedAdapter(Context mContext,ArrayList<Case> listCase) {
            this.listCase = listCase;
            this.mContext = mContext;
        }

        @Override
        public NewFeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_new_feed, parent, false);
            NewFeedHolder viewHolder = new NewFeedHolder(view);
            return viewHolder;
        }

        @Override
        public int getItemCount() {
            return listCase.size();
        }

        @Override
        public void onBindViewHolder(final NewFeedHolder holder, final int position) {
            holder.recyclerViewImage.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            adapter_listImage = new Adapter_ListImage(mContext, listCase.get(position).getImageURLs() );
            holder.recyclerViewImage.setAdapter(adapter_listImage);
            Glide.with(mContext).load(listCase.get(position).getImageURLs().get(0)).into(holder.img_avatar);
            adapter_listImage.notifyDataSetChanged();
            holder.img_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent= new Intent(mContext, CommentActivity.class);
//
//                    intent.putExtra("listanh",listTypes.get(position).getListImg());
//                    intent.putExtra("name","VIETTELL");
//                    intent.putExtra("avatar",listTypes.get(position).getListImg().get(0));
//                    intent.putExtra("status","XIN CHÀO CÁC BẠN");
//                    mContext.startActivity(intent);
                }
            });
            holder.img_menuitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showmenu(holder.img_menuitem);
                }
            });


        }

        class NewFeedHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.listImage) RecyclerView recyclerViewImage;
            @BindView(R.id.img_comment) ImageView img_comment;
            @BindView(R.id.img_like) ImageView img_like;
            @BindView(R.id.img_menuitem) ImageView img_menuitem;
            @BindView(R.id.img_avatar_st) CircleImageView img_avatar;

            public NewFeedHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

            }
        }
        public void showmenu(View view){
            PopupMenu p=new PopupMenu(mContext,view);
            MenuInflater inflater=p.getMenuInflater();
            inflater.inflate(R.menu.menu,p.getMenu());
            p.setOnMenuItemClickListener(new ClickMenu());
            p.show();
        }
        public class ClickMenu implements PopupMenu.OnMenuItemClickListener {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.setting:
                        Toast.makeText(mContext,"Setting",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        }


    }

    public static class Adapter_ListImage extends RecyclerView.Adapter<Adapter_ListImage.Holder_Image> {

        Context c;
        ArrayList<String> anh = new ArrayList<>();

        public Adapter_ListImage(Context c, ArrayList<String> anh) {
            this.c = c;
            this.anh = anh;
        }

        @Override
        public Holder_Image onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(c).inflate(R.layout.custom_list_image, parent, false);
            Holder_Image holder_image = new Holder_Image(v);
            return holder_image;
        }

        @Override
        public void onBindViewHolder(Holder_Image holder, int position) {
            Glide.with((c)).load(anh.get(position)).into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return anh.size();
        }

        public class Holder_Image extends RecyclerView.ViewHolder {
            @BindView(R.id.thumbnail) ImageView imageView;

            public Holder_Image(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
                ButterKnife.bind(itemView);
            }
        }

    }

    public static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
        Context context;
        ArrayList<String> imagePaths;
        ImageController mainController;

        public ImageAdapter(Context context, ImageController mainController, ArrayList<String> imagePaths) {
            this.context = context;
            this.mainController = mainController;
            this.imagePaths = imagePaths;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final String imagePath = imagePaths.get(position);
            System.out.println(imagePath);
            Picasso
                    .with(context)
                    .load(new File(imagePath))
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainController.setImgMain(imagePath);
                }
            });
        }

        public void changePath(ArrayList<String> imagePaths) {
            this.imagePaths = imagePaths;
            mainController.setImgMain(imagePaths.get(0));

            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return imagePaths.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.img_item);
            }
        }
    }

}
