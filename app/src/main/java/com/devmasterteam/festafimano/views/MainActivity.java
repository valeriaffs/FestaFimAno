package com.devmasterteam.festafimano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devmasterteam.festafimano.R;
import com.devmasterteam.festafimano.constats.FimDeAnoConstants;
import com.devmasterteam.festafimano.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViweHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViweHolder.textToday = findViewById(R.id.text_today);
        this.mViweHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViweHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViweHolder.buttonConfirm.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);
        this.verifyPreferences();
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
