package net.unisofia.fmi.marto.vasko.tv_guide;

import android.widget.ImageView;

/**
 * Created by vasko on 22.06.16.
 */
public class TVChannel {

    public String title;
    public int icon;
    public int id;

    public TVChannel(String title, int icon, int id) {
        this.title = title;
        this.icon = icon;
        this.id = id;
    }
}
