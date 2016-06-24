package net.unisofia.fmi.marto.vasko.tv_guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class ChannelListActivity extends AppCompatActivity {

    final static TVChannel channelData[] = new TVChannel[]
            {
                    new TVChannel("BTV", R.drawable.btv, 12),
                    new TVChannel("NovaTV", R.drawable.nova, 28),
                    new TVChannel("Канал 1", R.drawable.bnt, 21),
                    new TVChannel("Discovery", R.drawable.discovery, 10),
                    new TVChannel("National Geographic", R.drawable.nat, 27),
                    new TVChannel("Animal Planet", R.drawable.animal, 65),
                    new TVChannel("TLC", R.drawable.tlc, 178),
                    new TVChannel("Travel TV", R.drawable.travel, 163)
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_channel_list);

        setChannels();
    }

    private void setChannels() {

        final ChannelListActivity ctx = this;

        TVChannelAdapter adapter = new TVChannelAdapter(this, R.layout.channel_item_row, channelData);

        ListView listView = (ListView) findViewById(R.id.channelListView);
        View header = getLayoutInflater().inflate(R.layout.header_row, null);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ctx.showDetails(position);
            }
        });

    }

    private void showDetails(int id) {

        Intent intent = new Intent(this, ChannelInfoActivity.class);
        intent.putExtra("id", channelData[id - 1].id);
        intent.putExtra("channel_name", channelData[id - 1].title);
        startActivity(intent);
    }


}
