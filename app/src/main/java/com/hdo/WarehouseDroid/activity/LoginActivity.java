package com.hdo.WarehouseDroid.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.LoginUser;
import com.hdo.WarehouseDroid.bean.User;
import com.hdo.WarehouseDroid.utils.NetworkUtils;
import com.hdo.WarehouseDroid.utils.SpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


/**
 * author 张健银
 * version 1.0
 * description 引导页
 * create 2017-09-06
 * modified by 吴小雪 on 2017/12/14 实现登录
 *
 */

public class LoginActivity extends BaseActivity {

    private Context mContext;
    private EditText loginUserName;
    private EditText loginPassword;
    //记住密码
    private CheckBox autoLogin;
    //忘记密码
    private TextView backRegister;
    private Button btnLogin;
    TextView toolbarButton;
    TextView toolbarTitle;

    private String userName;
    private String password;
    private ProgressDialog dialog;
    private String TAG = "LoginActivity";

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }



    @Override
    public void initView() {
        mContext = LoginActivity.this;
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        toolbarButton = (TextView)findViewById(R.id.toolbar_button);
        btnLogin = (Button) findViewById(R.id.btn_login);
        loginUserName = (EditText) findViewById(R.id.login_userName);
        loginPassword = (EditText) findViewById(R.id.login_Password);
        autoLogin = (CheckBox) findViewById(R.id.auto_login);
        backRegister = (TextView) findViewById(R.id.back_register);
    }

    @Override
    public void initData() {
    }

    @Override
    public void setView() {
        toolbarTitle.setText("登录");
        toolbarButton.setVisibility(View.GONE);
        //SpUtils调用getBoolean()方法获取autoLogin键值，默认为false
        autoLogin.setChecked(SpUtils.getBoolean(this, "autoLogin", false));
        //判断是否选择记住密码
        if (autoLogin.isChecked()) {
            User user = SpUtils.getUser(LoginActivity.this);
            if (user.getPhone().length() > 1) {
                loginUserName.setText(user.getNumber());
                loginPassword.setText(user.getPassword());
                boolean fromChangePsw = SpUtils.getBoolean(this, "fromChangePsw", false);
                if (fromChangePsw){
                    SpUtils.putBoolean(this,"fromChangePsw",false);
                    loginPassword.setText("");
                }else {
                    login();
                }

            }
        }
        //点击登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //点击忘记密码弹出提示
        backRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "请咨询相关人员重置密码！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //判断登录信息是否正确
    public void login() {
        userName = loginUserName.getText().toString().trim();
        password = loginPassword.getText().toString().trim();
        if (userName.isEmpty()) {
            Toast.makeText(mContext, "输入用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(mContext, "输入密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            dialog = ProgressDialog.show(this, "登录", "登录中，请等待...", false, true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Call call = NetworkUtils.getInstance().login(userName,password);
                    try {
                        Response response = call.execute();
                        String result = response.body().string();
                        Log.e(TAG, "run: " + result);

                        LoginUser user = new Gson().fromJson(result, LoginUser.class);
                        if(user.getCode().equals("200")){
                            user.getData().setPassword(password);
                            SpUtils.putUser(LoginActivity.this, user.getData());
                            Message msg = new Message();
                            msg.what = 0;
                            String netPsw = user.getData().getPassword();
                            Log.e(TAG,"netPsw:" + netPsw + "oldpsw:" + password);
                            Log.e(TAG,"token值：" + user.getData().getToken());
                            handler.sendMessage(msg);
                        }else if(user.getCode().equals("102")){
                            handler.sendEmptyMessage(1);
                        }
                        else if(user.getCode().equals("103")){
                            handler.sendEmptyMessage(2);
                        }

                    } catch (IOException e) {
//                        e.printStackTrace();
                        Log.e("",e.getMessage()+e.getCause());
                    }
                }
            }).start();
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    dialog.dismiss();
                    Toast.makeText(mContext, "登录成功！", Toast.LENGTH_SHORT).show();
                    final String token = (String) message.obj;
                    //转译token字符
//                    Log.e(TAG, "handleMessage: "+URLEncoder.encode(token));
                    //将token保存到本地
                    SpUtils.putBoolean(LoginActivity.this, "autoLogin", autoLogin.isChecked());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;

                case 1:
                    dialog.dismiss();
                    Toast.makeText(mContext, "登录失败，用户不存在", Toast.LENGTH_SHORT).show();

                    break;
                case 2:
                    dialog.dismiss();
                    Toast.makeText(mContext, "登录失败，密码错误！", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }


}
