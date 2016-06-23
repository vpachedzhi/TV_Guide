package net.unisofia.fmi.marto.vasko.tv_guide;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChannelInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<String> categories = new ArrayList<>();

    private static final String BaseURL = "http://tv.dir.bg/tv_channel.php";
    private String channelCode;

    // only for today for now
    private static final int offset = 0;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_channel_info);
        Integer id = (Integer) getIntent().getExtras().get("id");
        Spinner spinner = (Spinner) findViewById(R.id.spinnerWeek);
        spinner.setOnItemSelectedListener(this);
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(1);


    }

    private class ParseURL extends AsyncTask<String, Void, ArrayList<BroadCast>> {


        @Override
        protected ArrayList<BroadCast> doInBackground(String... strings) {

            return getSchedule(offset);
        }

        @Override
        protected void onPostExecute(ArrayList<BroadCast> result) {

            StringBuilder sb = new StringBuilder();
            for (BroadCast bc : result){
                sb.append(bc.time);
                sb.append(" ");
                sb.append(bc.telecast);
            }
        }
    }

    private void update(int position) {

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        update(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void initializeDays() {


    }


}
