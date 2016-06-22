package net.unisofia.fmi.marto.vasko.tv_guide;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vasko on 22.06.16.
 */
public class TVChannelAdapter extends ArrayAdapter<TVChannel> {

    Context context;
    int layoutResourceId;
    TVChannel data[] = null;

    public TVChannelAdapter(Context context, int layoutResourceId, TVChannel[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        TVChannelHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TVChannelHolder();
            holder.title = (TextView)row.findViewById(R.id.title);
            holder.icon = (ImageView)row.findViewById(R.id.icon);

            row.setTag(holder);
        }
        else
        {
            holder = (TVChannelHolder)row.getTag();
        }

        TVChannel tvChannel = data[position];
        holder.title.setText(tvChannel.title);
        holder.icon.setImageResource(tvChannel.icon);

        return row;
    }

    static  class  TVChannelHolder{
        public TextView title;
        public ImageView icon;
    }
}
