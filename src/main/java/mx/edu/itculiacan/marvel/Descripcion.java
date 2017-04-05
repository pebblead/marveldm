package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

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
        String url="http://gateway.marvel.com/v1/public/characters?ts=1&apikey=136dea6ba6b37b0cedda9b6b16d9b469&hash=c9fc6223d7a05a3f32fe2b4c2cbcb25c";
        RequestParams params = new RequestParams();
        params.put("nameStartsWith",personaje.getText());
        ///
        client.get(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){


                    datosEnPantalla(obtDatosJSON(new String(responseBody)));
                    //System.out.println(new String(responseBody));
                    Toast.makeText(Descripcion.this,"SEGUN SI: "+ statusCode,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Descripcion.this,"Falla numero: "+ statusCode,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void datosEnPantalla(String[] datosPersonaje) {
        Toast.makeText(Descripcion.this,datosPersonaje[0],Toast.LENGTH_SHORT).show();
          //  ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
           // listado.setAdapter(adapter);

    }

    public String[] obtDatosJSON(String response) {
        ArrayList<String> listado = new ArrayList<String>();
        String match[]=new String[3];
        try {

            JSONObject jObject = new JSONObject(response);
            JSONObject objData = new JSONObject(jObject.getString("data"));
            JSONArray objResults= new JSONArray(objData.getString("results"));
            JSONObject primerMatch= objResults.getJSONObject(0);

            String nombrePersonaje= primerMatch.getString("name");
            String descripcionPersonaje= primerMatch.getString("description");
            JSONObject objImgPersonaje=new JSONObject(primerMatch.getString("thumbnail"));
            String imgPersonaje= objImgPersonaje.getString("path")+"."+objImgPersonaje.getString("extension");

           System.out.println(nombrePersonaje+" "+descripcionPersonaje+" "+imgPersonaje);
            match[0]=nombrePersonaje;
            match[1]=descripcionPersonaje;
            match[2]=imgPersonaje;


          /*  JSONArray objetoResults = jObject.getJSONArray("results");
            String texto;
            for(int i=0;i<objetoResults.length();i++){
                /*texto=jsonArray.getJSONObject(i).getString("results")+" "+
                        jsonArray.getJSONObject(i).getString("")+" "+
                        jsonArray.getJSONObject(i).getString("")+" ";
                JSONObject nombreObject = new JSONObject(objetoResults.getJSONObject(i).getString("name"));
                texto=nombreObject.getString("first")+" "+nombreObject.getString("last");
                listado.add(texto);


            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;

    }


}
