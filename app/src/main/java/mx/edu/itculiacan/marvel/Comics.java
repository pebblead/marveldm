package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Comics extends AppCompatActivity {
    //ListView listado = (ListView) findViewById(R.id.lstComics);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);
        ListView listado = (ListView) findViewById(R.id.lstComics);
    }
    public void pantallaListadoComics(View view) {
        buscarComics();
        Intent pantallaListado= new Intent(this,ListadoComics.class);
        startActivity(pantallaListado); //Cambia de pantalla
    }

    private void buscarComics() {
        EditText personajeC  =(EditText) findViewById(R.id.txtPerComics);
        Toast.makeText(this, personajeC.getText().toString(), Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://gateway.marvel.com/v1/public/characters?ts=1&apikey=136dea6ba6b37b0cedda9b6b16d9b469&hash=c9fc6223d7a05a3f32fe2b4c2cbcb25c";
        RequestParams params = new RequestParams();
        params.put("nameStartsWith",personajeC.getText());
        ///
        client.get(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){


                    datosEnPantallaC(obtDatosJSONC(new String(responseBody)));
                    //System.out.println(new String(responseBody));
                    Toast.makeText(Comics.this,"SEGUN SI: "+ statusCode,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Comics.this,"Falla numero: "+ statusCode,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void datosEnPantallaC(ArrayList<String> lista) {
       Toast.makeText(Comics.this,"datosen",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ListadoComics.class);
        i.putExtra("listaComics", lista );
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);

        //ListView listado = (ListView) findViewById(R.id.lstComics);
        //listado.setAdapter(adapter);
         startActivity(i);
    }

    public ArrayList<String> obtDatosJSONC(String response) {
        ArrayList<String> listado = new ArrayList<String>();
        String texto="";

        try {

            JSONObject objComics=new JSONObject(((new JSONArray((new JSONObject((new JSONObject(response).getString("data"))).getString("results")))).getJSONObject(0)).getString("comics"));
            String ncomics=objComics.getString("available");
            JSONArray objItemsComics= new JSONArray(objComics.getString("items"));
            System.out.println(objItemsComics);



            for(int i=0;i<objItemsComics.length();i++){
                try {
                    texto=objItemsComics.getJSONObject(i).getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listado.add(texto);
            }

            Toast.makeText(this, listado.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;

    }

}
