package com.example.nam.dtapp.view.activity.start;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.User;
import com.example.nam.dtapp.model.modelAPI.Login;
import com.example.nam.dtapp.service.API;
import com.example.nam.dtapp.view.activity.home.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG_NAME = "LoginActivity";
    @BindView(R.id.email)
    EditText edEmail;
    @BindView(R.id.password)
    EditText edPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnLinkToRegisterScreen)
    Button BtnRegister;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
        BtnRegister.setOnClickListener(this);
        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin: {
                new Waitting().execute();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API loginAPI = retrofit.create(API.class);
                Login login = new Login(Config.APP_KEY, Config.FOR_TEST, edEmail.getText() + "", edPassword.getText() + "");
                Call<String> call = loginAPI.login(login);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.e(TAG_NAME,"Login success: "+response.body());
                        try {
                            JSONObject json  = new JSONObject(response.body());
                            int code = json.getInt("errCode");
                            Log.e(TAG_NAME,"ErroCode : "+code);
                            if (code==200){
                                progressDialog.dismiss();
                                JSONObject jsonObject=json.getJSONObject("Data");
                                Config.USER_ID=jsonObject.getInt("UserId");
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this,"Sai thông tin !",Toast.LENGTH_LONG).show();
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


                break;
            }
            case R.id.btnLinkToRegisterScreen: {
                Intent intent = new Intent(this, RegisterManFieldActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    class Waitting extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... strings) {

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void integer) {
            super.onPostExecute(integer);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
