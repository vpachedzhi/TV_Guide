package net.unisofia.fmi.marto.vasko.tv_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChannelInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_channel_info);

        final TextView textView = new TextView(this);
        textView.setTextSize(20);
        Intent intent = getIntent();
        //textView.setText(intent.getExtras().get("id").toString());
        textView.setText(test());
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
    }

    public String test(){

        StringBuilder sb = new StringBuilder();
        try {
            Document doc = Jsoup
                    .connect("http://tv.dir.bg/tv_channel.php?id=12")
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .get();

            Elements elements = doc.select("#events > li > a");

            List<String> programme = new ArrayList<>();

            for (Element el : elements) {
                String time = el.child(0).ownText();
                String telecast = el.child(1).ownText();
                programme.add(time + " " + telecast);
            }


            for (String str : programme) {
                sb.append(str);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        Calendar now = Calendar.getInstance();
//        Integer d = now.get(Calendar.DAY_OF_MONTH);
//        String day = d.toString();
//        Integer m = now.get(Calendar.MONTH);
//        String month = m.toString();
//        month = m < 10 ? "0" + month : month;
//        System.out.println(day+"."+month);
        return sb.toString();
    }
}
