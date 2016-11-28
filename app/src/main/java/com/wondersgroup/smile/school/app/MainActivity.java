package com.wondersgroup.smile.school.app;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

/**
 * 精简版  第三方分享
 */

public class MainActivity extends AppCompatActivity {
    private String path = "http://www.shsmile.com.cn/smile/app/loadDownloadPage.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button qqBtn = (Button) findViewById(R.id.qqBtn);
        Button qqZone = (Button) findViewById(R.id.qqZoneBtn);
        Button weChat = (Button) findViewById(R.id.weChat);
        Button weChatCirlce = (Button) findViewById(R.id.weChatCircle);

       final UMImage image = new UMImage(MainActivity.this, "http://dev.umeng.com/images/tab2_1.png");


        qqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QQ)
                        .withTitle("")
                        .withText("内容")
                        .withMedia(image)
                        .withTargetUrl(path)
                        .setCallback(umShareListener)
                        .share();
            }
        });

        qqZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                        .withTitle("title")
                        .withText("——来自友盟分享面板")
                        .withMedia(new UMImage(MainActivity.this, "http://dev.umeng.com/images/tab2_1.png"))
                        .withTargetUrl("https://wsq.umeng.com/")
                        .setCallback(umShareListener)
                        .share();
            }
        });

        weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                        .withTitle("title")
                        .withText("——来自友盟分享面板")
                        .withMedia(new UMImage(MainActivity.this, "http://dev.umeng.com/images/tab2_1.png"))
                        .withTargetUrl("https://wsq.umeng.com/")
                        .setCallback(umShareListener)
                        .share();
            }
        });

        weChatCirlce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withTitle("title")
                        .withText("——来自友盟分享面板")
                        .withMedia(new UMImage(MainActivity.this, "http://dev.umeng.com/images/tab2_1.png"))
                        .withTargetUrl("https://wsq.umeng.com/")
                        .setCallback(umShareListener)
                        .share();
            }
        });
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}





    //========================qq 第三方登陆=============================================================================
//    private void qqLogin() {
//        //如果session无效，就开始登录
//        if (null != mTencent && !mTencent.isSessionValid()) {
//            //开始qq授权登录
//            mTencent.login(LoginActivity.this, scope, loginListener);
//        }
//    }

//    private void initQQData() {
        //初始化qq主操作对象
//        mTencent = Tencent.createInstance(Config.QQ_APP_ID, this);
        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
//        scope = "all";

//        loginListener = new IUiListener() {
//
//            @Override
//            public void onError(UiError arg0) {
//                // TODO Auto-generated method stub
//            }
//
//            /**
//             * 返回json数据样例
//             *
//             * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
//             * "pf":"desktop_m_qq-10000144-android-2002-",
//             * "query_authority_cost":448,
//             * "authority_cost":-136792089,
//             * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
//             * "expires_in":7776000,
//             * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
//             * "msg":"",
//             * "access_token":"A2455F491478233529D0106D2CE6EB45",
//             * "login_cost":499}
//             */
//            @Override
//            public void onComplete(Object value) {
//                // TODO Auto-generated method stub
//
//                if (value == null) {
//                    return;
//                }
//
//                try {
//                    JSONObject jo = (JSONObject) value;
//
//                    int ret = jo.getInt("ret");
//
//                    System.out.println("json=" + String.valueOf(jo));
//
//                    if (ret == 0) {
//                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
//
//                        String openID = jo.getString("openid");
//                        String accessToken = jo.getString("access_token");
//                        String expires = jo.getString("expires_in");
//
//                        ToastUtils.showToast(LoginActivity.this, "accessToken=" + accessToken);
//
//                        mTencent.setOpenId(openID);
//                        mTencent.setAccessToken(accessToken, expires);
//
//                        userInfo = new UserInfo(LoginActivity.this, mTencent.getQQToken());
//                        userInfo.getUserInfo(userInfoListener);
//
//                        //通过获取到的access_token 来获取unionId 进行登陆请求
//                        new QQLoginTask().execute("https://graph.qq.com/oauth2.0/me?access_token=" + accessToken + "&unionid=1");
//                    }
//
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
//
//            }
//
//            @Override
//            public void onCancel() {
//                // TODO Auto-generated method stub
//
//            }
//        };
//
//        userInfoListener = new IUiListener() {
//
//            @Override
//            public void onError(UiError arg0) {
//                // TODO Auto-generated method stub
//
//            }
//
//            /**
//             * 返回用户信息样例
//             *
//             * {"is_yellow_year_vip":"0","ret":0,
//             * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
//             * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
//             * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
//             * "city":"黄冈","
//             * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
//             * "vip":"0","level":"0",
//             * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
//             * "province":"湖北",
//             * "is_yellow_vip":"0","gender":"男",
//             * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
//             */
//            @Override
//            public void onComplete(Object arg0) {
//                // TODO Auto-generated method stub
//                if (arg0 == null) {
//                    return;
//                }
//                try {
//                    JSONObject jo = (JSONObject) arg0;
//                    int ret = jo.getInt("ret");
//                    System.out.println("json=" + String.valueOf(jo));
//                    qqUserName = jo.getString("nickname");
//                    qqUserImage = jo.getString("figureurl_qq_2");
//
//                    ToastUtils.showToast(LoginActivity.this, "qqUserImage======" + qqUserImage);
//
//                    Toast.makeText(LoginActivity.this, "你好，" + qqUserName, Toast.LENGTH_LONG).show();
//
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
//
//
//            }
//
//            @Override
//            public void onCancel() {
//                // TODO Auto-generated method stub
//
//            }
//        };
//    }


