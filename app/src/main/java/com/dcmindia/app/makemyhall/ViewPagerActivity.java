package com.dcmindia.app.makemyhall;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abu Osama on 04-07-2017.
 */

public class ViewPagerActivity extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    //private List<SliderUtils> sliderimg;
    private ImageLoader imageLoader;
    private Integer[] images={R.drawable.sliderimg,R.drawable.sliderimg2};
    //private Integer [] images={R.drawable.cr,R.drawable.background};
    ImageView imageView;
   // ArrayList<Listnew> halllist;


    public ViewPagerActivity(Context context) {

        //this.sliderimg=sliderimg;
        this.context = context;
       // layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {

//        if(halllist != null){
//            return halllist.size();
//        }
//        return 0;

       return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.customactivity,container,false);

       // SliderUtils utils=sliderimg.get(position);

        //Listnew get = halllist.get(position);

         imageView= (ImageView) view.findViewById(R.id.sliderviewimage);
         imageView.setImageResource(images[position]);

//        Glide.with(context).load(get.getHallimage())
//                .placeholder(R.drawable.sliderimg)
//                .error(R.drawable.sliderimg).into(imageView);

        //Glide.with(context).load(get.getHallimage()).into(imageView);

        //imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();

        //container.addView(view);
//
//        imageLoader.get(utils.getSliderImageUrl(),ImageLoader.getImageListener(imageView,R.mipmap.ic_launcher,
//                android.R.drawable.ic_menu_report_image));
        ViewPager vp= (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp= (ViewPager) container;
        View view= (View) object;
        vp.addView(view);
    }
}
