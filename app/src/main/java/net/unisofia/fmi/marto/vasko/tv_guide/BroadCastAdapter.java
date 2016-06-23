package net.unisofia.fmi.marto.vasko.tv_guide;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by vasko on 23.06.16.
 */
public class BroadCastAdapter extends ArrayAdapter<BroadCast> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<BroadCast> data = null;

    public BroadCastAdapter(Context context, int layoutResourceId, ArrayList<BroadCast> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        BroadCastHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BroadCastHolder();
            holder.time = (TextView) row.findViewById(R.id.time);
            holder.telecast = (TextView) row.findViewById(R.id.telecast);

            row.setTag(holder);
        } else {
            holder = (BroadCastHolder) row.getTag();
        }

        BroadCast broadCast = data.get(position);
        holder.time.setText(broadCast.time);
        holder.telecast.setText(broadCast.telecast);

        return row;
    }

    static class BroadCastHolder {
        public TextView time;
        public TextView telecast;
    }
}