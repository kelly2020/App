package com.wondersgroup.smile.school.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 友盟第三方登陆 qq 使用精简版不需要进行授权 直接可以获取用户信息
 */

public class LoginUmeng extends AppCompatActivity {
    UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_umeng);

        Button weChat = (Button) findViewById(R.id.weChat);
        Button qq = (Button) findViewById(R.id.qqBtn);
        mShareAPI = UMShareAPI.get(LoginUmeng.this);

        weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI mShareAPI = UMShareAPI.get(LoginUmeng.this);
                mShareAPI.doOauthVerify(LoginUmeng.this, SHARE_MEDIA.WEIXIN, umAuthListener);

                mShareAPI.getPlatformInfo(LoginUmeng.this, SHARE_MEDIA.WEIXIN, umAuthListener);
            }
        });

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mShareAPI.getPlatformInfo(LoginUmeng.this, SHARE_MEDIA.QQ, umAuthListener);


            }
        });
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            Log.e("data====", "data.toString()" + data.toString());
            String unionid = data.get("unionid");
            String name = data.get("screen_name");
            String image_url = data.get("profile_image_url");

            Log.d("data====", "dunionid==" + unionid);
            Log.d("data====", "name==" + name);
            Log.d("data====", "profile_image_url==" + image_url);

            if (data != null) {
                Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
