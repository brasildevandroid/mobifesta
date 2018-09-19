package com.example.pinheiro.serfeliz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {

        this.context = context;
    }

    public int[] slide_images = {

            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3

    };

    public String[] slide_headings = {

            "EAT",
            "SLEEP",
            "CODE"
    };

    public String[] slide_descs = {

            "estou na tela 1 e estou bem" +
                    "estou na tela 2 e estou bem" +
                    "estou na tela 3 e estou bem"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==
                (RelativeLayout) o;
     }

     @Override
    public Object instantiateItem(ViewGroup container,int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

         ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
       //  TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
         TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

         slideImageView.setImageResource(slide_images[position]);
       //  slideHeading.setText(slide_headings[position]);
    //    slideDescription.setText(slide_descs[position]);

         container.addView(view);

         return view;
      }

      @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((RelativeLayout)object) ;

      }
    }