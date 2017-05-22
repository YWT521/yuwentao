package com.baway.yuwentao.mondel.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.yuwentao.sqlite.SqliteDao;
import com.baway.yuwentao.view.activity.MainActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 类用途 :utils类
 * 类的用处:用来实现第三方登录
 * 作者 : 郁文涛
 * 时间 : 2017/5/15 9:34
 */

public class BaseUiListener implements IUiListener {
    private ImageView imageView;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private static final String TAG = "BaseUiListenerl";
    private static final String APP_ID = "1106162650";
    private Tencent mTencent;
    private Context context;
    private TextView textView;
    private ImageOptions options;
    private SqliteDao dao;

    public BaseUiListener() {

    }

    public BaseUiListener(Context context, ImageView imageView, TextView textView,SqliteDao dao) {
        this.context = context;
        this.imageView = imageView;
        this.textView = textView;
        this.dao=dao;
        options = new ImageOptions.Builder().setCircular(true)
                .build();
    }

    @Override
    public void onComplete(Object o) {
        mTencent = Tencent.createInstance(APP_ID, context);
        Toast.makeText(context, "授权成功", Toast.LENGTH_SHORT).show();
        JSONObject obj = (JSONObject) o;
        try {
            String openID = obj.getString("openid");
            String accessToken = obj.getString("access_token");
            String expires = obj.getString("expires_in");
            mTencent.setOpenId(openID);
            mTencent.setAccessToken(accessToken, expires);
            QQToken qqToken = mTencent.getQQToken();
            mUserInfo = new UserInfo(context, qqToken);
            mUserInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object response) {
                    JSONObject object = (JSONObject) response;
                    try {

                        String figureurl_2 = object.getString("figureurl_qq_2");
                        String nickname = object.getString("nickname");
                        dao.insert(figureurl_2,nickname);

                        x.image().bind(imageView, figureurl_2, options);
                        textView.setText(nickname);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.e(TAG, "登录成功" + response.toString());
                }

                @Override
                public void onError(UiError uiError) {
                    Log.e(TAG, "登录失败" + uiError.toString());
                }

                @Override
                public void onCancel() {
                    Log.e(TAG, "登录取消");

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }
}
