package com.example.as.finalavplayer;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.as.finalavplayer.All_Adapters.SongAdapter;
import com.example.as.finalavplayer.All_Handlers.Song;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Fragment_Songs_section extends Fragment {
    private ArrayList<Song> songList;
    ListView songView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__songs_section, container, false);
        songList = new ArrayList<>();
        //get songs from device
        getSongList();

        songView = (ListView) view.findViewById(R.id.song_List);

        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        SongAdapter songAdt = new SongAdapter(getContext(), songList);
        songView.setAdapter(songAdt);
        return view;
    }

    public void getSongList() {

        ContentResolver musicResolver = getActivity().getContentResolver();

        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;


        //     Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);


        //iterate over results if valid
        if (musicCursor != null && musicCursor.moveToFirst()) {
            //get columns

            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int durationId = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            int column_index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);

            //    int art = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);

            while (musicCursor.moveToNext()) {

                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                long duration = musicCursor.getLong(durationId);
                //   Long albumId = musicCursor.getLong(art);

                String pathId = musicCursor.getString(column_index);

                MediaMetadataRetriever metaRetriver = new MediaMetadataRetriever();
                metaRetriver.setDataSource(pathId);

                byte[] art;
                Bitmap songImage = null;
                try {
                    art = metaRetriver.getEmbeddedPicture();
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 2;
                    songImage = BitmapFactory.decodeByteArray(art, 0, art.length, opt);
                } catch (Exception e) {
                    //  imgAlbumArt.setBackgroundColor(Color.GRAY);
                }

                //       String iconPath = albumCursor.getString(coverId);

                songList.add(new Song(thisId, thisTitle, songImage, thisArtist, duration));
            }

        }
    }

}
