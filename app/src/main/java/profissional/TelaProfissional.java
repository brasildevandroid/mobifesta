package profissional;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pinheiro.serfeliz.R;

public class TelaProfissional extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profissional_geral:
                    mTextMessage.setText(R.string.titulo_profissional_Painel);
                    return true;
                case R.id.navigation_profissional_pedidos:
                    mTextMessage.setText(R.string.titulo_profissional_Pedidos);
                    return true;
                case R.id.navigation_profissional_financeiro:
                    mTextMessage.setText(R.string.titulo_profissional_Financeiro);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_profissional);

         mTextMessage = (TextView) findViewById(R.id.message_profissional);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigati);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
