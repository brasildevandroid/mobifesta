package com.example.pinheiro.serfeliz;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import pojos.DadosPessoaisProfissional;
import util.MaskEditUtil;

/**
 *
 */
public class FragmentCadastroPessoaJuridica extends android.support.v4.app.Fragment {

    protected AlertDialog alerta;
    Button btnEnviarCadastroPessoaJuridica;

    AlertDialog.Builder builder;

    Dialog dialog;
    private static final int PICK_IMAGE  = 123;

    DadosPessoaisProfissional dadosPessoaisProfissional;

    StorageReference mStorageRef;
    EditText edtNome;
    EditText edtSobreNome;
    EditText edtEmail;
    EditText edtCelular;
    EditText  edtCpf;
    EditText edtIdentidade;


    EditText edtRazaoSocial;
    EditText edtInscricaoEstadual;
    EditText edtNomeFantasia;
    EditText edtCnpj;

    String urlProfissionalCpf;
    String urlCnpjMei;

    String urlProfissionalIdentidade;
    String urlProfissionalResidencia;
    FirebaseStorage mStorage;

    EditText edtCep;
    EditText edtRua;
    EditText edtNumero;
    EditText edtBairro;
    EditText edtReferencia;
    EditText edtComplemento;
    EditText edtCidade;
    EditText edtEstado;
    Button btnCpfProfissional;
    Button btnIdentidadeProfissional;
    Button btnResidenciaProfissional;
    Button btnCnpjMei;

    List<String> documentos;


    ImageView imgCpfCarregado;
    ImageView imgIdentidadeCarregado;
    ImageView imgResidenciaCarregado;
    ImageView imgCnpjCarregado;


    public FragmentCadastroPessoaJuridica() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.fragment_cadastro_pessoa_juridica,container,false);

        // dados pessoais

        edtNome = (EditText) view.findViewById(R.id.edt_Nome_Juridica_Profissional);
        edtSobreNome = (EditText) view.findViewById(R.id.edt_Sobrenome_Juridica_Profissional);
        edtCpf = (EditText) view.findViewById(R.id.edt_Cpf_Juridica_Profissional);
        edtIdentidade = (EditText) view.findViewById(R.id.edt_Identidade_Juridica_Profissional);
        edtCelular = (EditText) view.findViewById(R.id.edt_Celular_Juridica_Profissional);
        edtEmail = (EditText) view.findViewById(R.id.edt_Email_Juridica_Profissional);


        // dados da empresa
        edtCnpj = (EditText) view.findViewById(R.id.edt_Cnpj_Juridica_Profissional);
        edtRazaoSocial = (EditText) view.findViewById(R.id.edt_Razao_Social_Juridica_Profissional);
        edtNomeFantasia = (EditText) view.findViewById(R.id.edt_Nome_Fantasia_Juridica_Profissional);
        edtInscricaoEstadual = (EditText) view.findViewById(R.id.edt_InscEstadual_Juridica_Profissional);



        // dados do endereço

        edtCep =    (EditText) view.findViewById(R.id.edt_Cep_Juridica_Profissional);
        edtRua =    (EditText) view.findViewById(R.id.edt_Rua_Juridica_Profissional);
        edtNumero = (EditText) view.findViewById(R.id.edt_Numero_Juridica_Profissional);
        edtBairro = (EditText) view.findViewById(R.id.edt_Bairro_Juridica_Profissional);
        edtComplemento = (EditText) view.findViewById(R.id.edt_Complemento_Juridica_Profissional);
        edtCidade = (EditText) view.findViewById(R.id.edt_Cidade_Juridica_Profissional);
        edtEstado = (EditText) view.findViewById(R.id.edt_Estado_Juridica_Profissional);

        edtCep.addTextChangedListener(MaskEditUtil.mask(edtCep,MaskEditUtil.FORMAT_CEP));
        edtCelular.addTextChangedListener(MaskEditUtil.mask(edtCelular,MaskEditUtil.FORMAT_FONE));
        edtCpf.addTextChangedListener(MaskEditUtil.mask(edtCpf,MaskEditUtil.FORMAT_CPF));
        edtIdentidade.addTextChangedListener(MaskEditUtil.mask(edtIdentidade,MaskEditUtil.FORMAT_IDENTIDADE));

        btnCpfProfissional = (Button) view.findViewById(R.id.btn_Cpf_Juridica_Profissional);
        btnIdentidadeProfissional = (Button) view.findViewById(R.id.btn_Identidade_Juridica_Profissional);
        btnResidenciaProfissional = (Button) view.findViewById(R.id.btn_Residencia_Juridica_Profissional);
        btnCnpjMei = (Button) view.findViewById(R.id.btn_Cnpj_Juridica_Profissional);


        imgCpfCarregado        = (ImageView) view.findViewById(R.id.img_Cpf_Carregado_Juridica);
        imgIdentidadeCarregado = (ImageView) view.findViewById(R.id.img_Identidade_Carregado_Juridica);
        imgResidenciaCarregado = (ImageView) view.findViewById(R.id.img_Residencia_Carregado_Juridica);
        imgCnpjCarregado  = (ImageView)view.findViewById(R.id.img_Cnpj_Carregado_Juridica);

        btnEnviarCadastroPessoaJuridica =
                     (Button) view.findViewById(R.id.btn_Enviar_Cadastro_Juridica_Profissional);

        btnCpfProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlProfissionalCpf = "cpf";

                Toast.makeText(getActivity(),"fui clicado",Toast.LENGTH_SHORT).show();

                obterFotoGaleria();

            }
        });


        btnIdentidadeProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlProfissionalIdentidade = "identidade";

                obterFotoGaleria();
            }
        });


        btnResidenciaProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlProfissionalResidencia = "residencia";

                obterFotoGaleria();
            }
        });

        btnCnpjMei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlProfissionalCpf = "cnpj";

                Toast.makeText(getActivity(),"fui clicado",Toast.LENGTH_SHORT).show();

                obterFotoGaleria();

            }
        });






        dadosPessoaisProfissional = new DadosPessoaisProfissional();

        mStorage = FirebaseStorage.getInstance();

        mStorageRef = mStorage.getReferenceFromUrl("gs://serfeliz-4d215.appspot.com").child("images");


        btnEnviarCadastroPessoaJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (urlProfissionalCpf == null
                        || urlProfissionalCpf.equals("cpf")){


                    Toast.makeText(getActivity(),"por favor anexe a foto do seu c.p.f",Toast.LENGTH_SHORT).show();

                }else if (urlProfissionalIdentidade == null
                        || urlProfissionalIdentidade.equals("cpf")){

                    Toast.makeText(getActivity(),"por favor anexe a foto da sua identidade",Toast.LENGTH_SHORT).show();

                }else if (urlProfissionalResidencia == null
                        || urlProfissionalResidencia.equals("cpf")){

                    Toast.makeText(getActivity(),"por favor anexe a foto seu comprovante de residência",Toast.LENGTH_SHORT).show();


                }else if (urlCnpjMei == null
                        || urlCnpjMei.equals("cnpj")){

                    Toast.makeText(getActivity(),"por favor anexe a foto do  seu Cnpj ou Mei",Toast.LENGTH_SHORT).show();

                }

                else {

                    validaCampos();

                }


            }
        });

        return view;


    }

    private void setDialog(boolean show){
        builder = new AlertDialog.Builder(getActivity());
        //View view = getLayoutInflater().inflate(R.layout.progress);
        builder.setView(R.layout.carregando);
        dialog  = builder.create();

        dialog.show();
        /*
        if (show)dialog.show();
        else dialog.dismiss();

        */
    }


    public void alertCadastroPessoaJuridica(){

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //define o titulo
        //builder.setTitle("Coleta enviada com sucesso!");

        builder.setMessage("Deseja revisar os dados antes de enviar para ánalise ?");


        //define um botão como positivo
        builder.setNegativeButton("revisar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {



                /*
                EnviaColeta.enviaCadastroColeta();
                alerta.dismiss();


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_tela_principal,new FragmentHome());
                ft.commit();

                */

                alerta.dismiss();
            }
        });


        //define um botão como positivo
        builder.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                /*
                EnviaColeta.enviaCadastroColeta();
                alerta.dismiss();


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_tela_principal,new FragmentHome());
                ft.commit();

                */

                getActivity().startActivity(new Intent(getActivity(),TelaProfissionalTeste.class));
                alerta.dismiss();
            }
        });





        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }


    private void validaCampos() {

        String nome = edtNome.getText().toString();
        String sobreNome = edtSobreNome.getText().toString();
        String email = edtEmail.getText().toString();
        String celular = edtCelular.getText().toString();
        String cpf = edtCpf.getText().toString();
        String identidade = edtIdentidade.getText().toString();



        String cep = edtCep.getText().toString();
        String rua = edtRua.getText().toString();
        String bairro = edtBairro.getText().toString();
        String numero = edtNumero.getText().toString();
        String complemento = edtComplemento.getText().toString();
        String cidade  = edtCidade.getText().toString();
        String estado = edtEstado.getText().toString();



        boolean ok = true;

        if (isCampoVazio(nome)) {

            edtNome.setError("dados incorretos");
            edtNome.requestFocus();

            ok = false;
        }

        if (isCampoVazio(sobreNome)) {

            edtNome.setError("dados incorretos");
            edtNome.requestFocus();

            ok = false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {


            edtEmail.setError("dados incorretos");
            edtEmail.requestFocus();
            ok = false;
        }

        if (isCampoVazio(celular) ||
                celular.length() < 11) {

            edtCelular.setError("dados incorretos");
            edtCelular.requestFocus();
            ok = false;
        }

        if (isCampoVazio(identidade) ||
                celular.length() < 9) {

            edtIdentidade.setError("dados incorretos");
            edtIdentidade.requestFocus();
            ok = false;
        }

        if (isCampoVazio(cpf) ||
                celular.length() < 14) {

            edtCpf.setError("dados incorretos");
            edtCpf.requestFocus();
            ok = false;
        }


        if (isCampoVazio(cep) ||
                cep.length() < 9) {


            edtCep.setError("dados incorretos");
            edtCep.requestFocus();

            ok = false;
        }

        if (isCampoVazio(rua)) {

            edtRua.setError("dados incorretos");
            edtRua.requestFocus();
            ok = false;
        }

        if (isCampoVazio(numero)) {

            edtNumero.setError("dados incorretos");
            edtNumero.requestFocus();
            ok = false;
        }

        if (isCampoVazio(bairro)) {

            edtBairro.setError("dados incorretos");
            edtBairro.requestFocus();
            ok = false;
        }

        if (isCampoVazio(complemento)) {

            edtComplemento.setError("dados incorretos");
            edtComplemento.requestFocus();
            ok = false;
        }

        if (isCampoVazio(cidade)) {

            edtCidade.setError("dados incorretos");
            edtCidade.requestFocus();
            ok = false;
        }

        if (isCampoVazio(estado)) {

            edtEstado.setError("dados incorretos");
            edtEstado.requestFocus();
            ok = false;
        }



        if (ok) {


            alertCadastroPessoaJuridica();

            /*
            enviaColeta(

                     nome,email,celular,cep,rua,bairro,numero,cidade,estado,referencia);
*/

/*
            EnviaColeta.recebeDadosColeta(
                    nome, email, celular, cep, rua, bairro, numero, referencia, complemento, horario, sobreNome);

            criaCadastroCliente(nome,email,celular,sobreNome);

            criaEnderecoCliente(cep,rua,bairro,numero,referencia,complemento);
            alertColetaCriada();

*/
        }

    }


    private static boolean isCampoVazio(String valor) {


        boolean resultado = (TextUtils.isEmpty(valor) ||
                valor.trim().isEmpty());

        return resultado;


    }

    private void obterFotoGaleria() {

        Intent galerry  = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(galerry,PICK_IMAGE);



/*
        String nomeFoto = "qualquer nome";

        caminhoFoto =
                new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES),
                nomeFoto);

        Intent it =
                new Intent(
                        Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        it.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(caminhoFoto));
        startActivityForResult(it,PICK_IMAGE);

*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        setDialog(true);

        Toast.makeText(getActivity(),"entrei no resultado",Toast.LENGTH_SHORT).show();

        if (resultCode == getActivity().RESULT_OK
                && requestCode == PICK_IMAGE){

            Toast.makeText(getActivity(),"o resultado é positivo",Toast.LENGTH_SHORT).show();

            Uri uri = data.getData();

            final StorageReference filepatch = mStorageRef.child(uri.getLastPathSegment());




            UploadTask uploadTask = filepatch.putFile(uri);

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }

            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    taskSnapshot.getStorage()
                            .getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Uri imagemRecebida = uri;

                                    String anexoDocumento;
                                    anexoDocumento = imagemRecebida.toString();

                                    if (urlProfissionalCpf.equals("cpf")){

                                        urlProfissionalCpf = anexoDocumento;

                                        imgCpfCarregado.setVisibility(View.VISIBLE);

                                        salvaDocumentos(urlProfissionalCpf);


                                    }else if (urlProfissionalIdentidade.equalsIgnoreCase("identidade")){

                                        Toast.makeText(getActivity(),"identidade foi carregada" ,Toast.LENGTH_SHORT).show();

                                        urlProfissionalIdentidade = anexoDocumento;
                                        imgIdentidadeCarregado.setVisibility(View.VISIBLE);
                                        salvaDocumentos(urlProfissionalIdentidade);

                                    }else if (urlProfissionalResidencia.equals("residencia")){

                                        urlProfissionalResidencia = anexoDocumento;

                                        imgResidenciaCarregado.setVisibility(View.VISIBLE);
                                        salvaDocumentos(urlProfissionalResidencia);

                                        Toast.makeText(getActivity(),"comprovante residência foi carregada" ,Toast.LENGTH_SHORT).show();

                                    }else if (urlCnpjMei.equals("cnpj")){

                                        urlCnpjMei = anexoDocumento;

                                        imgCnpjCarregado.setVisibility(View.VISIBLE);
                                        salvaDocumentos(urlProfissionalResidencia);

                                    }




                                }
                            });

                    //Picasso.with(getActivity()).load(imagemRecebida).fit().centerCrop().into(imageView);
                }
            });

        }

    }

    private void salvaDocumentos(String anexo) {


        Toast.makeText(getActivity(),"imagem foi salva",Toast.LENGTH_SHORT).show();

        dialog.dismiss();




    }


}
