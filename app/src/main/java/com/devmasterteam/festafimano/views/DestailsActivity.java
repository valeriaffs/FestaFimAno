package com.devmasterteam.festafimano.views;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.devmasterteam.festafimano.R;
import com.devmasterteam.festafimano.constats.FimDeAnoConstants;
import com.devmasterteam.festafimano.util.SecurityPreferences;


public class DestailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destails);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_background);

        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDateFromActivity();
    }

    private void loadDateFromActivity() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if(presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){
                this.mViewHolder.checkParticipate.setChecked(true);
            }else{
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.check_participate){
            if(mViewHolder.checkParticipate.isChecked()){
                mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE,FimDeAnoConstants.CONFIRMED_WILL_GO);
            }else{
                mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE,FimDeAnoConstants.CONFIRMED_WONT_GO);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkParticipate;
    }
}
