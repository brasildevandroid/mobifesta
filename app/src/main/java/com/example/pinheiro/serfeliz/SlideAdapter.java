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

            R.drawable.moedadeouro,
            R.drawable.ic_reembolso,
            R.drawable.slide3,
            R.drawable.background_imagem_nula

    };

    public String[] slide_headings = {

            "MOEDAS!",
            "REEMBOLSO GARANTIDO!",
            "LOJA VIRTUAL!",
            ""
    };

    public String[] slide_descs = {

            "as moedas de ouro são uma grande oportunidade na hora de você economizar para fazer á sua festa,junte moedas em cada compra e troque por afiliados,cada compra que seus afiliados realizam na plataforma gera descontos pra você,acumule e troque por produtos e serviços" ,
                    "nós queremos que você se divirta organizando sua festa,por isso garantimos todos os produtos ou serviços adquiridos em nossa plataforma com nossos fornecedores,mais lembre-se,os pagamentos deverão sempre serem efetuados aqui dentro da plataforma,não reembolsamos pagamentos efetuados fora do aplicativo!",
                    "chega de perder tempo com inbox,aqui você já vê os preços de produtos ou serviços e se gostar faça contato com o fornecedor e o contrate!",
            ""
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
         TextView slideHeading = (TextView) view.findViewById(R.id.slide_titulo);
         TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

         slideImageView.setImageResource(slide_images[position]);
         slideHeading.setText(slide_headings[position]);
         slideDescription.setText(slide_descs[position]);

         container.addView(view);

         return view;
      }

      @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((RelativeLayout)object) ;

      }
    }