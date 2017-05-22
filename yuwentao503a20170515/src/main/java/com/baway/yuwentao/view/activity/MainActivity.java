package com.baway.yuwentao.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yuwentao.R;
import com.baway.yuwentao.mondel.bean.Bean;
import com.baway.yuwentao.mondel.utils.BaseUiListener;
import com.baway.yuwentao.sqlite.SqliteDao;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * 类用途 :主页面
 * 类的描述:本类是为了引出SlidingMenu而创建的
 * 作者 : 郁文涛
 * 时间 : 2017/5/15 9:02
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private BaseUiListener mIUiListener;
    private static final String TAG = "BaseUiListenerl";
    private static final String APP_ID = "1106162650";
    private Tencent mTencent;
    private Button button;
    private SlidingMenu menu;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    private SqliteDao dao;
    private ImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new SqliteDao(MainActivity.this);
        mTencent = Tencent.createInstance(APP_ID, MainActivity.this);
        sp = getSharedPreferences("shade", MODE_PRIVATE);
        editor = sp.edit();

        initData();
        initView();
        options = new ImageOptions.Builder()
                .setCircular(true)
                .build();
        boolean haha = sp.getBoolean("haha", false);
        if (haha){
            List<Bean> list = dao.queryDB();
            x.image().bind(imageView,list.get(0).getImagename(),options);
            textView.setText(list.get(0).getName());
        }

    }

    private void initData() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);

        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.item_slidingmenu);

    }


    private void initView() {
        imageView = (ImageView) findViewById(R.id.item_image);
        textView = (TextView) findViewById(R.id.item_text);
        button = (Button) findViewById(R.id.button_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });
        //点击切换
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putBoolean("haha",true);
                editor.commit();
                mIUiListener = new BaseUiListener(MainActivity.this, imageView, textView, dao);
                mTencent.login(MainActivity.this, "all", mIUiListener);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        BaseUiListener baseUiListener = new BaseUiListener();
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, baseUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
