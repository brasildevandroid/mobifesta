package com.example.pinheiro.serfeliz;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class AdapterCadastrosPendentes extends RecyclerView.Adapter<AdapterCadastrosPendentes.MeuViewHolder> {


    private List<DadosGeraisProfissionalFisica>listaDadosProfissionais;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onMensagemCliente(int position);
        void onLigacaoCliente(int position);
        void onAlterarCadastroPendente(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterCadastrosPendentes(List<DadosGeraisProfissionalFisica> dados){

        listaDadosProfissionais = dados;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView nome;
        public TextView sobrenome;
        public TextView email;
        public TextView identidade;
        public TextView cpf;
        public TextView celular;


        public TextView rua;
        public TextView numero;
        public TextView bairro;
        public TextView cep;
        public TextView cidade;
        public TextView estado;



        public Button alterarStatusColeta;
        public ImageView mLigarCliente;
        public ImageView mMensagemCliente;


        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.txt_Nome_Pendente);
            sobrenome = (TextView) itemView.findViewById(R.id.txt_Sobrenome_Pendente);
            email = (TextView) itemView.findViewById(R.id.txt_Email_Pendente);
            celular = (TextView) itemView.findViewById(R.id.txt_Celular_Pendente);
            cpf = (TextView) itemView.findViewById(R.id.txt_Cpf_Pendente);
            identidade = (TextView)itemView.findViewById(R.id.txt_Identidade_Pendente);



            rua = (TextView)itemView.findViewById(R.id.txt_Rua_Pendente);
            numero = (TextView)itemView.findViewById(R.id.txt_Numero_Pendente);
            bairro = (TextView)itemView.findViewById(R.id.txt_Bairro_Pendente);
            cep = (TextView) itemView.findViewById(R.id.txt_Cep_Pendente);
            cidade = (TextView) itemView.findViewById(R.id.txt_Cidade_Pendente);
            estado = (TextView) itemView.findViewById(R.id.txt_Estado_Pendente);




              mMensagemCliente = (ImageView) itemView.findViewById(R.id.coleta_mensagem_admin);
              mLigarCliente = (ImageView) itemView.findViewById(R.id.coleta_ligar_admin);
              alterarStatusColeta = (Button) itemView.findViewById(R.id.coletas_alterar_status);


            mMensagemCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onMensagemCliente(position);

                        }

                    }
                }
            });



            mLigarCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onLigacaoCliente(position);
                        }

                    }
                }
            });


            alterarStatusColeta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onAlterarCadastroPendente(position);
                        }

                    }
                }
            });


        }



    }



    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cadastros_pendentes,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCadastrosPendentes.MeuViewHolder holder, int position) {

        DadosGeraisProfissionalFisica dados = listaDadosProfissionais.get(position);


        holder.nome.setText(dados.getNome());
        holder.sobrenome.setText(dados.getSobrenome());
        holder.email.setText(dados.getEmail());
        holder.identidade.setText(dados.getIdentidade());
        holder.cpf.setText(dados.getCpf());
        holder.celular.setText(dados.getCelular());

        holder.cep.setText(dados.getCep());
        holder.rua.setText(dados.getRua());
        holder.numero.setText(dados.getNumero());
        holder.bairro.setText(dados.getBairro());
        holder.numero.setText(dados.getNumero());
        holder.cidade.setText(dados.getCidade());
        holder.estado.setText(dados.getEstado());

    }


    @Override
    public int getItemCount() {

        return listaDadosProfissionais.size();

      }



}
