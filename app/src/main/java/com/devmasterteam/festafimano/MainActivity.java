package com.devmasterteam.festafimano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViweHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViweHolder.textToday = findViewById(R.id.text_today);
        this.mViweHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViweHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViweHolder.buttonConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button_confirm){
            Intent intent = new Intent(this,DestailsActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
