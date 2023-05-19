package com.example.control;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {

    private List<Artist> artistList;
    private Activity context;

    public ArtistList(Activity context, List<Artist> artistList){
        super(context, R.layout.list_layout, artistList);
        this.context = context;
        this.artistList = artistList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);


        TextView email = listViewItem.findViewById(R.id.email);
        TextView privilege = listViewItem.findViewById(R.id.privilege);
//        TextView password = listViewItem.findViewById(R.id.password);

        Artist artist = artistList.get(position);

        email.setText(artist.getEmail());
        privilege.setText(artist.getPrivilege());
//        password.setText(artist.getPassword());

        return listViewItem;
    }

}
