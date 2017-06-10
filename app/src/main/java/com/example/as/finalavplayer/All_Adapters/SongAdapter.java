package com.example.as.finalavplayer.All_Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.as.finalavplayer.All_Handlers.Song;
import com.example.as.finalavplayer.R;

import java.util.ArrayList;


public class SongAdapter extends BaseAdapter {

    //song list and layout
    private ArrayList<Song> songs = new ArrayList<>();
    private LayoutInflater inflater;
    Context context;

    public SongAdapter(Context context, ArrayList<Song> songs) {
        this.songs = songs;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.song_list_row, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        Song currentListData = (Song) getItem(position);

        myViewHolder.title.setText(currentListData.getTitle());
        myViewHolder.artist.setText(currentListData.getArtist());

        long duration = currentListData.getDuration() / 1000;

        int min = (int) duration / 60;
        int sec = (int) duration % 60;

        String time = "0:0";

        if (sec < 10) {
            time = min + ":0" + sec;
        }
        if (min < 10) {
            time = "0" + min + ":" + sec;
        }
        if (sec < 10 && min < 10) {
            time = "0" + min + ":0" + sec;
        }

        myViewHolder.duration.setText(time);
        Bitmap bitmap = currentListData.getIcon();

        if(bitmap == null){
            myViewHolder.imageView.setBackgroundColor(Color.RED);
        }else{
            myViewHolder.imageView.setImageBitmap(currentListData.getIcon());

        }



        return convertView;

    }

    private class MyViewHolder {

        TextView title, artist, duration;
        ImageView imageView;


        public MyViewHolder(View Item) {
            title = (TextView) Item.findViewById(R.id.song_title);
            artist = (TextView) Item.findViewById(R.id.song_artist);
            duration = (TextView) Item.findViewById(R.id.song_duration);
            imageView = (ImageView) Item.findViewById(R.id.songIcon);


        }

    }
}