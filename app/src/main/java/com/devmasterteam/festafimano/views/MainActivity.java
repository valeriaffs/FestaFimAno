package com.devmasterteam.festafimano.views;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devmasterteam.festafimano.R;
import com.devmasterteam.festafimano.constats.FimDeAnoConstants;
import com.devmasterteam.festafimano.util.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViweHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_background);

        this.mViweHolder.textToday = findViewById(R.id.text_today);
        this.mViweHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViweHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViweHolder.buttonConfirm.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViweHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s",String.valueOf(this.getDaysLeftToEndOfYear()),getString(R.string.dias));
        this.mViweHolder.textDaysLeft.setText(daysLeft);
    }

    private void verifyPreferences() {
        String preference = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        if(preference.equals("")){
            this.mViweHolder.buttonConfirm.setText(R.string.nao_confirmado);
        }
        else if(preference.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
            this.mViweHolder.buttonConfirm.setText(R.string.sim);
        }
        else if(preference.equals(FimDeAnoConstants.CONFIRMED_WONT_GO)) {
            this.mViweHolder.buttonConfirm.setText(R.string.nao);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.verifyPreferences();
    }

    private int getDaysLeftToEndOfYear(){
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecember31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDecember31 - today;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button_confirm){
            String presence = mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

            Intent intent = new Intent(this,DestailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE,presence);
            startActivity(intent);
        }
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
