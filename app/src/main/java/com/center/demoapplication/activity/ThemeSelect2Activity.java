package com.center.demoapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.center.demoapplication.R;
import com.center.demoapplication.utils.Colorful;
import com.center.demoapplication.view.ViewGroupSetter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_theme_select2)
public class ThemeSelect2Activity extends AppCompatActivity {

    @ViewInject(R.id.listview)
    ListView listView;

    boolean isNight = false ;
    Colorful mColorful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_theme_select2);
        x.view().inject(this);

        // 为ListView设置要修改的属性,在这里没有对ListView本身的属性做修改
        ViewGroupSetter listViewSetter = new ViewGroupSetter(listView, 0);
// 绑定ListView的Item View中的news_title视图，在换肤时修改它的text_color属性
//        listViewSetter.childViewTextColor(R.id.news_title, R.attr.text_color);
// 构建Colorful对象
        mColorful = new Colorful.Builder(this)
                .backgroundDrawable(R.id.root_view, R.attr.root_view_bg) // 设置view的背景图片
                .backgroundColor(R.id.change_btn, R.attr.btn_bg) // 设置按钮的背景色
                .textColor(R.id.textview, R.attr.text_color) // 设置文本颜色
                .setter(listViewSetter)           // 手动设置setter
                .create();
    }

    // 切换主题
    private void changeThemeWithColorful() {
        if (!isNight) {
            mColorful.setTheme(R.style.DayTheme);
        } else {
            mColorful.setTheme(R.style.NightTheme);
        }
        isNight = !isNight;
    }
}
