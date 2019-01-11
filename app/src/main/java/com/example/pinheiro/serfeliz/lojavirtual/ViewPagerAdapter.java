package com.example.pinheiro.serfeliz.lojavirtual;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pinheiro.serfeliz.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private Integer [] images =
                {R.drawable.imagem_1,R.drawable.imagem_2,R.drawable.imagem_3,R.drawable.imagem_4,R.drawable.imagem_5};


    public List<String> fotosLista;

    public ViewPagerAdapter(Context context,List<String>fotos){

        this.context = context;
        this.fotosLista = fotos;

    }


    @Override
    public int getCount() {
        return fotosLista.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object ;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =  layoutInflater.inflate(R.layout.item_detalhe_fornecedor,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.img_Foto_Fornecedor);
        // imageView.setImageResource(images[position]);

        String fotos= String.valueOf(fotosLista.size());
        Log.d("TAG",">>>>>>>>>" +fotos);




        Glide.with(imageView)
                .load(fotosLista.get(position))
                .into(imageView);



        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object){

        ViewPager vp = (ViewPager)container;
        View view = (View) object;
        vp.removeView(view);
    }
}
