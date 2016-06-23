package net.unisofia.fmi.marto.vasko.tv_guide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChannelInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_channel_info);
        TextView textView = new TextView(this);
        Bundle extras = getIntent().getExtras();

        String channelCode = extras.get("id").toString();
        // get offset from extra

    }


}
