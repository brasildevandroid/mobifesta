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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import pojos.DadosDocumentoProfissional;
import pojos.DadosEnderecoProfissional;
import pojos.DadosPessoaisProfissional;
import util.MaskEditUtil;

/**
 *
 */
public class FragmentCadastroPessoaFisica extends android.support.v4.app.Fragment {

    protected AlertDialog alerta;
    Button btnEnviarCadastroPessoaFisica;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseStorage mStorage;
    FirebaseFirestore mFirestore;


    AlertDialog.Builder builder;

    Dialog dialog;
    private static final int PICK_IMAGE  = 123;

    DadosPessoaisProfissional dadosPessoaisProfissional;
    DadosEnderecoProfissional dadosEnderecoProfissional;
    DadosDocumentoProfissional dadosDocumentoProfissional;

    StorageReference mStorageRef;
    EditText edtNome;
    EditText edtSobreNome;
    EditText edtEmail;
    EditText edtCelular;
    EditText  edtCpf;
    EditText edtIdentidade;

    String urlProfissionalCpf;
    String urlProfissionalIdentidade;
    String urlProfissionalResidencia;

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

    List<String> documentos;


    ImageView imgCpfCarregado;
    ImageView imgIdentidadeCarregado;
    ImageView imgResidenciaCarregado;


    public FragmentCadastroPessoaFisica() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cadastro_pessoa_fisica,container,false);


        mAuth = FirebaseAuth.getInstance();


        if (mAuth != null){


            mUser = mAuth.getCurrentUser();



        }


        // dados pessoais

        edtNome = (EditText) view.findViewById(R.id.edt_Nome_Fisica_Profissional);
        edtSobreNome = (EditText) view.findViewById(R.id.edt_Sobrenome_Fisica_Profissional);
        edtCpf = (EditText) view.findViewById(R.id.edt_Cpf_Fisica_Profissional);
        edtIdentidade = (EditText) view.findViewById(R.id.edt_Identidade_Fisica_Profissional);
        edtCelular = (EditText) view.findViewById(R.id.edt_Celular_Fisica_Profissional);
        edtEmail = (EditText) view.findViewById(R.id.edt_Email_Fisica_Profissional);



        edtNome.setText("Luiz");
        edtSobreNome.setText("pinheiro de Jesus");
        edtCpf.setText("072726081633");
        edtIdentidade.setText("219152550");
        edtCelular.setText("9678374700");
        edtEmail.setText("luizcox1@hotmail.com");

        // dados do endereço

        edtCep =    (EditText) view.findViewById(R.id.edt_Cep_Fisica_Profissional);
        edtRua =    (EditText) view.findViewById(R.id.edt_Rua_Fisica_Profissional);
        edtNumero = (EditText) view.findViewById(R.id.edt_Numero_Fisica_Profissional);
        edtBairro = (EditText) view.findViewById(R.id.edt_Bairro_Fisica_Profissional);
        edtComplemento = (EditText) view.findViewById(R.id.edt_Complemento_Fisica_Profissional);
        edtCidade = (EditText) view.findViewById(R.id.edt_Cidade_Fisica_Profissional);
        edtEstado = (EditText) view.findViewById(R.id.edt_Estado_Fisica_Profissional);


        edtCep.addTextChangedListener(MaskEditUtil.mask(edtCep,MaskEditUtil.FORMAT_CEP));
        edtCelular.addTextChangedListener(MaskEditUtil.mask(edtCelular,MaskEditUtil.FORMAT_FONE));
        edtCpf.addTextChangedListener(MaskEditUtil.mask(edtCpf,MaskEditUtil.FORMAT_CPF));
        edtIdentidade.addTextChangedListener(MaskEditUtil.mask(edtIdentidade,MaskEditUtil.FORMAT_IDENTIDADE));

        edtCep.setText("219152550");
        edtRua.setText("rua algustina");
        edtNumero.setText("600");
        edtBairro.setText("campo grande");
        edtComplemento.setText("casa");
        edtCidade.setText("rio de janeiro");
        edtEstado.setText("rj");

        btnCpfProfissional = (Button) view.findViewById(R.id.btn_Cpf_Fisica_Profissional);
        btnIdentidadeProfissional = (Button) view.findViewById(R.id.btn_Identidade_Fisica_Profissional);
        btnResidenciaProfissional = (Button) view.findViewById(R.id.btn_Residencia_Fisica_Profissional);

        imgCpfCarregado        = (ImageView) view.findViewById(R.id.img_Cpf_Carregado);
        imgIdentidadeCarregado = (ImageView) view.findViewById(R.id.img_Identidade_Carregado);
        imgResidenciaCarregado = (ImageView) view.findViewById(R.id.img_Residencia_Carregado);
        btnEnviarCadastroPessoaFisica = (Button) view.findViewById(R.id.btn_Enviar_Cadastro_Fisica_Profissional);




        btnCpfProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urlProfissionalCpf = "cpf";


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


        dadosPessoaisProfissional = new DadosPessoaisProfissional();
        dadosEnderecoProfissional = new DadosEnderecoProfissional();
        dadosDocumentoProfissional = new DadosDocumentoProfissional();


        mStorage = FirebaseStorage.getInstance();

        mStorageRef = mStorage.getReferenceFromUrl("gs://serfeliz-4d215.appspot.com").child("images");


        btnEnviarCadastroPessoaFisica.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                validaCampos();
/*


                if (urlProfissionalCpf == null
                         || urlProfissionalCpf.equals("cpf")){


                    Toast.makeText(getActivity(),"por favor anexo a foto do seu c.p.f",Toast.LENGTH_SHORT).show();

                }else if (urlProfissionalIdentidade == null
                        || urlProfissionalIdentidade.equals("cpf")){

                    Toast.makeText(getActivity(),"por favor anexo a foto do sua identidade",Toast.LENGTH_SHORT).show();

                }else if (urlProfissionalResidencia == null
                        || urlProfissionalResidencia.equals("cpf")){

                    Toast.makeText(getActivity(),"por favor anexo seu comprovante de residência",Toast.LENGTH_SHORT).show();


                }else {

                    validaCampos();

                }
*/

            }
        });

        return view;


    }




    public void alertCadastroPessoaFisica(final DadosGeraisProfissionalFisica dadosGerais){

        final DadosGeraisProfissionalFisica dadosGeraisPessoa = dadosGerais;
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //define o titulo
        //builder.setTitle("Coleta enviada com sucesso!");

        builder.setTitle("Só um aviso!");


        builder.setMessage("Se Desejar conferir os dados pra ver se está certinho so clicar em revisar,caso contrário clique em confimar para enviar o seu cadastro!");


        //define um botão como positivo
        builder.setNegativeButton("revisar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {




                alerta.dismiss();
            }
        });


        //define um botão como positivo
        builder.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {


                setSalvandoCadastro();

                finalizarCadastro(dadosGeraisPessoa);

/*
                getActivity().startActivity(new Intent(getActivity(),TelaProfissionalTeste.class));
                alerta.dismiss();
  */
            }
        });



        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }


    private void finalizarCadastro(DadosGeraisProfissionalFisica dadosGerais) {

        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("plataforma")
                .document("profissional")
                .collection("pendentes")
                .document(mAuth.getUid())
                .set(dadosGerais)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        dialog.dismiss();

                        startActivity(new Intent(getContext(),MainActivity.class));
                        getActivity().finish();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(),"ocorreu um erro ao gravar os dados",Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void validaCampos() {

        DadosGeraisProfissionalFisica
                    pessoaFisica = new DadosGeraisProfissionalFisica();

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
/*

        boolean ok = true;

        if (isCampoVazio(nome)) {

            edtNome.setError("dados incorretos");
            edtNome.requestFocus();

            ok = false;
        }

        if (isCampoVazio(sobreNome)) {

            edtSobreNome.setError("dados incorretos");
            edtSobreNome.requestFocus();

            ok = false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {


            edtEmail.setError("dados incorretos");
            edtEmail.requestFocus();
            ok = false;
        }

        if (isCampoVazio(celular) ||
                celular.length() < 11){

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
                cpf.length() < 14) {

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


*/


       // if (ok) {

            pessoaFisica.setNome(nome);
            pessoaFisica.setSobrenome(sobreNome);
            pessoaFisica.setCelular(celular);
            pessoaFisica.setEmail(email);
            pessoaFisica.setCpf(cpf);
            pessoaFisica.setIdentidade(identidade);


            pessoaFisica.setCep(cep);
            pessoaFisica.setRua(rua);
            pessoaFisica.setNumero(numero);
            pessoaFisica.setBairro(bairro);
            pessoaFisica.setCidade(cidade);
            pessoaFisica.setEstado(estado);

/*
            pessoaFisica.setUrlCpf(urlProfissionalCpf);
            pessoaFisica.setUrlIdentidade(urlProfissionalIdentidade);
            pessoaFisica.setUrlEndereco(urlProfissionalResidencia);
*/
            pessoaFisica.setStatus("pendente");


            alertCadastroPessoaFisica(pessoaFisica);

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
      //  }//

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



        if (resultCode == getActivity().RESULT_OK
                && requestCode == PICK_IMAGE){


            Uri uri = data.getData();

            String fotoPerfilUsuario;
            fotoPerfilUsuario = uri.toString();



            setDialog(true,fotoPerfilUsuario);
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

                                   }




                                }
                            });

                    //Picasso.with(getActivity()).load(imagemRecebida).fit().centerCrop().into(imageView);
                }
            });

        }

    }

    private void setDialog(boolean show,String urlPhoto){

        String url = urlPhoto;

        builder = new AlertDialog.Builder(getActivity());
        //View view = getLayoutInflater().inflate(R.layout.progress);
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.carregando, null);

        ImageView image = (ImageView) view.findViewById(R.id.image_Carregando);

        Glide.with(getContext()).load(url).into(image);

        builder.setView(view);
        dialog  = builder.create();

        dialog.show();
        /*
        if (show)dialog.show();
        else dialog.dismiss();

        */
    }

    public void setSalvandoCadastro(){



        builder = new AlertDialog.Builder(getActivity());
        //View view = getLayoutInflater().inflate(R.layout.progress);
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.carregando_cadastro, null);

        builder.setView(view);
        dialog  = builder.create();

        dialog.show();
        /*
        if (show)dialog.show();
        else dialog.dismiss();

        */


    }

    private void salvaDocumentos(String anexo) {


        Toast.makeText(getActivity(),"imagem foi salva",Toast.LENGTH_SHORT).show();

        dialog.dismiss();




    }


}
