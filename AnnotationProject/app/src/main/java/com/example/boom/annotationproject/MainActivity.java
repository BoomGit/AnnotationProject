package com.example.boom.annotationproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boom.annotationproject.annotation.BindId;
import com.example.boom.annotationproject.annotation.OnClick;
import com.example.boom.annotationproject.annotation.MyAnnotation;

public class MainActivity extends Activity {
    @BindId(R.id.tv)
    TextView tv;
    @OnClick(R.id.tv)
    void show(View v){
        Toast.makeText(this, "自定义注解成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAnnotation.init(this);
        tv.setText("自定义注解");
    }
}
