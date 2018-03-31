package com.lrs.activitytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int userId = getIntent().getIntExtra("userId", 0);

        Log.d(TAG, "onCreate: " + userId);

        this.editText = findViewById(R.id.edit_text);

        Button button = findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                Toast.makeText(SecondActivity.this,input,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
