package com.lrs.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "click add menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(this, "click remove menu", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        setContentView(R.layout.first_layout);

        // 查找按钮，并添加事件，使用 Toast
        Button button = findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,
                        "You click this button!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonIntent = findViewById(R.id.button_intent);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显式intent
                // 在FirstActivity这个活动的基础上打开SecondActivity
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("userId", 123567);
                startActivity(intent);
            }
        });

        findViewById(R.id.button_intent2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.lrs.activitytest.ACTION_START");
                        intent.addCategory("com.lrs.activitytest.MY_CATEGORY");
                        startActivity(intent);

                    }
                });

        findViewById(R.id.button_intent3)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.anitama.cn/"));
                        startActivity(intent);
                    }
                });

        // activity 销毁
        Button destoryButton = findViewById(R.id.destory);
        destoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstActivity.this.finish();
            }
        });

    }
}
