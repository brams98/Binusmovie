package com.example.tb_laota.Binusmovie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


public class Adapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader=AppController.getmInstance().getmImageLoader();
    public Adapter(Activity activity,List<Item> items){
        this.activity=activity;
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.custom_layout,null);
        }
        if(imageLoader==null)
            imageLoader=AppController.getmInstance().getmImageLoader();
            NetworkImageView imageView= (NetworkImageView) convertView.findViewById(R.id.image_view);
            TextView title= (TextView) convertView.findViewById(R.id.tv_title);
            TextView rate= (TextView) convertView.findViewById(R.id.tv_rate);
            TextView genre= (TextView) convertView.findViewById(R.id.tv_genre);
            TextView year= (TextView) convertView.findViewById(R.id.tv_year);
            Button btn= (Button) convertView.findViewById(R.id.btnn);
            //getting data for row
            Item item=items.get(position);
            imageView.setImageUrl(item.getImage(), imageLoader);
            //title
            title.setText(item.getTitle());
            //rate
            rate.setText(String.valueOf(item.getRate()));
            String genreStr="";
            for(String str: item.getGenre()){
                genreStr +=str + ",";
            }
            genreStr = genreStr.length() >0 ? genreStr.substring(0, genreStr.length() - 2) : genreStr;
            genre.setText(genreStr);
            //year
            year.setText(String.valueOf(item.getYear()));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        return convertView;
    }
}
