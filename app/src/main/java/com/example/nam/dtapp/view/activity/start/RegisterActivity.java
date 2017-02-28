package com.example.nam.dtapp.view.activity.start;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.RegisterUser;
import com.example.nam.dtapp.model.modelAPI.UploadImage;
import com.example.nam.dtapp.service.API;
import com.example.nam.dtapp.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG_NAME = "ACTIVITY_REGISTER";
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edFullname)
    EditText edFullname;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.edRePassword)
    EditText edRePassword;
    @BindView(R.id.edWorkplace)
    EditText edWorkplace;
    @BindView(R.id.edPhone)
    EditText edPhone;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.btnReg)
    Button btnReg;
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final String TAG = "ACTIVITY_REGISTER";
    private boolean pickimage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initButton();


    }

    public void loadImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            Log.e(TAG_NAME,uri+"");
            try {
                pickimage = true;
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Log.e(TAG_NAME,bitmap.toString());
                img.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initButton() {
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        final RegisterUser registerUser = new RegisterUser();
        registerUser.setAppKey(Config.APP_KEY);
        registerUser.setForTest(0);
        registerUser.setEmail(edEmail.getText().toString());
        registerUser.setPassword(edPassword.getText().toString());
        registerUser.setFullname(edFullname.getText().toString());
        registerUser.setPhone(edPhone.getText().toString());
        registerUser.setWorkplace(edWorkplace.getText().toString());
        String s[] = getIntent().getStringExtra("key").split("_");
        registerUser.setObjectType(Integer.parseInt(s[0]));
        registerUser.setMainFieldId(Integer.parseInt(s[1]));
        registerUser.setAvatar("");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int k = 2; k < s.length; k++) {
            arrayList.add(Integer.parseInt((s[k])));
        }
        registerUser.setConcernFieldsId(arrayList);
        if (registerUser.getEmail().length() == 0 ||
                registerUser.getFullname().length() == 0 ||
                registerUser.getPassword().length() == 0 ||
                registerUser.getPhone().length() == 0 ||
                registerUser.getWorkplace().length() == 0 ||
                !(edPassword.getText() + "").equals(edRePassword.getText() + "")
                ) {
            Toast.makeText(getApplicationContext(), "Chưa nhập đúng thông tin!", Toast.LENGTH_SHORT).show();
            return;

        }

        if(!pickimage) {
            Toast.makeText(getApplicationContext(),"Bạn chưa chọn avatar",Toast.LENGTH_SHORT).show();
            return;
        }

        Log.e(TAG_NAME,
                registerUser.getAppKey() + "\n" +
                        registerUser.getForTest() + "\n" +
                        registerUser.getEmail() + "\n" +
                        registerUser.getPassword() + "\n" +
                        registerUser.getFullname() + "\n" +
                        registerUser.getPhone() + "\n" +
                        registerUser.getWorkplace() + "\n" +
                        registerUser.getAvatar() + "\n" +
                        registerUser.getConcernFieldsId() + "\n" +
                        registerUser.getMainFieldId() + "\n" +
                        registerUser.getObjectType()

        );
        /*
        Gủi ảnh lên
         */

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API image = retrofit.create(API.class);

        String f = Utils.bitmapToString(((BitmapDrawable) img.getDrawable()).getBitmap());
        UploadImage uploadImage = new UploadImage(Config.APP_KEY, 0, ".jpeg", f);
        Call<String> call = image.uploadImage(uploadImage);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, response.body().toString());
                Log.i(TAG,Utils.convertJson(response.body()));
                try {
                    JSONObject avatar = new JSONObject(Utils.convertJson(response.body()));
                    String t  = avatar.getString("Data");
                    Log.e(TAG,"link avatar"+" "+t);
                    registerUser.setAvatar("http://hippocampus.vn"+t);
                    Log.e(TAG_NAME,
                            registerUser.getAppKey() + "\n" +
                                    registerUser.getForTest() + "\n" +
                                    registerUser.getEmail() + "\n" +
                                    registerUser.getPassword() + "\n" +
                                    registerUser.getFullname() + "\n" +
                                    registerUser.getPhone() + "\n" +
                                    registerUser.getWorkplace() + "\n" +
                                    registerUser.getAvatar() + "\n" +
                                    registerUser.getConcernFieldsId() + "\n" +
                                    registerUser.getMainFieldId() + "\n" +
                                    registerUser.getObjectType()

                    );
                } catch (JSONException e) {
                    Log.e(TAG,"Lỗi json");
                    e.printStackTrace();
                    return ;
                }

                /*
                Lấy ảnh về
                 */
                API regAPI = retrofit.create(API.class);
                Call<String> call2 = regAPI.registerUser(registerUser);
                call2.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call3, Response<String> response) {
                        Log.e(TAG_NAME, "Login success: " + response.message() + "," + response.body() + "," + response.errorBody());
                        try {
                            JSONObject o = new JSONObject(response.body());
                            String avatar = o.getJSONObject("Data").getString("Avatar");
                            Log.e(TAG_NAME, "Login: " + avatar);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(RegisterActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call3, Throwable t) {
                        Log.e(TAG_NAME, "Fail login: " + t.getLocalizedMessage());


                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(TAG, t.getLocalizedMessage());
            }
        });

    }


}
