package com.center.demoapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.center.demoapplication.R;
import com.center.demoapplication.base.BaseActivity;
import com.center.demoapplication.utils.ColorUiUtil;
import com.center.demoapplication.utils.SharedPreferencesMgr;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_theme_select)
public class ThemeSelectActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_theme_select);
        x.view().inject(this);


    }

    @Event(value = {R.id.btn,R.id.btn_start})
    private void event(View view){
        switch (view.getId()){
            case R.id.btn:
               setTheme();
                Toast.makeText(this,"aaa",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_start:
                startActivity(new Intent(this,ThemeSelect2Activity.class));
                break;
        }
    }

    private void setTheme(){
        if(SharedPreferencesMgr.getInt("theme", 0) == 1) {
            SharedPreferencesMgr.setInt("theme", 0);
            setTheme(R.style.theme_1);
        } else {
            SharedPreferencesMgr.setInt("theme", 1);
            setTheme(R.style.theme_2);
        }
        View rootView = getWindow().getDecorView();
        ColorUiUtil.changeTheme(rootView,getTheme());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
