package com.example.nam.dtapp.view.activity.gallary;//package com.example.nam.dtapp.view.activity.gallary;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.example.nam.dtapp.R;
//
//import java.util.ArrayList;
//
///**
// * Created by Phan Trung Kien on 1/10/2017.
// */
//
//public class Activity_Test_Gallery extends AppCompatActivity implements View.OnClickListener{
//    private LinearLayout lnrImages;
//    private Button btnAddPhots;
//    private Button btnSaveImages;
//    private ArrayList<String> imagesPathList;
//    private Bitmap yourbitmap;
//    private Bitmap resized;
//    private final int PICK_IMAGE_MULTIPLE =1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_gallery);
//        lnrImages = (LinearLayout)findViewById(R.id.lnrImages);
//        btnAddPhots = (Button)findViewById(R.id.btnAddPhots);
//        btnSaveImages = (Button)findViewById(R.id.btnSaveImages);
//        btnAddPhots.setOnClickListener(this);
//        btnSaveImages.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnAddPhots:
//                Intent intent = new Intent(Activity_Test_Gallery.this,CustomPhotoGalleryActivity.class);
//                startActivityForResult(intent,PICK_IMAGE_MULTIPLE);
//                break;
//            case R.id.btnSaveImages:
//                if(imagesPathList !=null){
//                    if(imagesPathList.size()>1) {
//                        Toast.makeText(Activity_Test_Gallery.this, imagesPathList.size() + " no of images are selected", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(Activity_Test_Gallery.this, imagesPathList.size() + " no of image are selected", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(Activity_Test_Gallery.this," no images are selected", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if(requestCode == PICK_IMAGE_MULTIPLE){
//                imagesPathList = new ArrayList<String>();
//                String[] imagesPath = data.getStringExtra("data").split("\\|");
//                try{
//                    lnrImages.removeAllViews();
//                }catch (Throwable e){
//                    e.printStackTrace();
//                }
//                for (int i=0;i<imagesPath.length;i++){
//                    imagesPathList.add(imagesPath[i]);
//                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
//                    ImageView imageView = new ImageView(this);
//                    imageView.setImageBitmap(yourbitmap);
//                    imageView.setAdjustViewBounds(true);
//                    lnrImages.addView(imageView);
//                }
//            }
//        }
//
//    }
//}
