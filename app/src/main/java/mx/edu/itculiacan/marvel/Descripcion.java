package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

//import cz.msebera.android.httpclient.Header;
public class Descripcion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
    }

    public void pantallaDetallePersonaje(View view) {
        buscarPersonaje();
        Intent pantallaDetalles= new Intent(this,DetallePersonaje.class);
        startActivity(pantallaDetalles); //Cambia de pantalla
    }

    private void buscarPersonaje() {
        EditText personaje  =(EditText) findViewById(R.id.txtPersonaje);

        Toast.makeText(this, personaje.getText().toString(), Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://gateway.marvel.com/v1/public/characters?ts=1&apikey=136dea6ba6b37b0cedda9b6b16d9b469&hash=c9fc6223d7a05a3f32fe2b4c2cbcb25c&nameStartsWith=Spider";
        RequestParams params = new RequestParams();
        params.put("results",100);
        ///
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    //CargaLista(obtDatosJSON(new String(responseBody)));
                    Toast.makeText(Descripcion.this,"SEGUN SI: "+ statusCode,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Descripcion.this,"Falla numero: "+ statusCode,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
