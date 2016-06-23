package net.unisofia.fmi.marto.vasko.tv_guide;

import android.os.AsyncTask;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Filipoff on 23.6.2016 г..
 */
public class ChannelInfoGetter {

    private static final String BaseURL = "http://tv.dir.bg/tv_channel.php";
    private String channelCode;
    private TextView textview;

    // only for today for now
    private static final int offset = 0;

    private class BroadCast {
        public String time;
        public String telecast;
    }

    public ChannelInfoGetter(TextView v){
        this.textview = v;
    }

    private ArrayList<BroadCast> getSchedule(int offset){

        ArrayList<BroadCast> schedule = new ArrayList<>();

        String channelURL = BaseURL + "?id=" + channelCode + "&dd=" +getDate(offset);

        try {
            Document doc = Jsoup
                    .connect(channelURL)
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                    .get();

            Elements elements = doc.select("#events > li > a");


            for (Element el : elements) {
                BroadCast bc = new BroadCast();
                bc.time = el.child(0).ownText();
                bc.telecast = el.child(1).ownText();
                schedule.add(bc);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return schedule;

    }

    private String getDate(int offset){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, offset);
        Integer d = now.get(Calendar.DAY_OF_MONTH);
        String day = d.toString();
        Integer m = now.get(Calendar.MONTH) + 1;
        String month = m.toString();
        month = m < 10 ? "0" + month : month;
        return day + "." + month;
    }

    private class ParseURL extends AsyncTask<String, Void, ArrayList<BroadCast>> {

        private TextView view;

        public ParseURL(TextView v) {
            this.view = v;
        }

        @Override
        protected ArrayList<BroadCast> doInBackground(String... strings) {

            return getSchedule(0);
        }

        @Override
        protected void onPostExecute(ArrayList<BroadCast> result) {

            view.setTextSize(20);
            StringBuilder sb = new StringBuilder();
            for (BroadCast bc : result){
                sb.append(bc.time);
                sb.append(" ");
                sb.append(bc.telecast);
            }
            view.setText(sb.toString());

            RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.content);
            layout.addView(view);
        }
    }

    public void execute(){
        new ParseURL(textview).execute();
    }
}
