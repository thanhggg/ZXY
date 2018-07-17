package com.gggstudio.chuctet.smstet.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gggstudio.chuctet.smstet.GreetingsActivity;
import com.gggstudio.chuctet.smstet.db.CategoryCache;
import com.gggstudio.chuctet.smstet.db.CategoryObject;

import java.util.ArrayList;


public class AdapterCategorys extends BaseAdapter {
    private int resouce;
    private Context context = null;
    private LayoutInflater inflater;
    private ArrayList<CategoryObject> arr = null;

    public AdapterCategorys(Context context, int resouce, ArrayList<CategoryObject> list) {
        this.context = context;
        this.resouce = resouce;
        inflater = LayoutInflater.from(context);
        this.arr = list;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public CategoryObject getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CategoryCache viewCache;
        CategoryObject Category = arr.get(position);

        if (view == null) {
            view = inflater.inflate(resouce, null);
            viewCache = new CategoryCache(view);
            view.setTag(viewCache);
        } else {
            view = convertView;
            viewCache = (CategoryCache) view.getTag();
        }

        Log.e("", "" + arr.get(position).getName_category());


        final String mStringImg = Category.getImage_category();

        ImageView mImageView = viewCache.getImageView(resouce);

        int imgDrawerble = context.getResources().getIdentifier(mStringImg, "drawable", context.getPackageName());


        mImageView.setImageResource(imgDrawerble);
        mImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(context, GreetingsActivity.class);
                intent.putExtra("id_category", arr.get(position).getId_category());
                intent.putExtra("mStringImg", mStringImg);
                context.startActivity(intent);
            }
        });

        return view;
    }


}
