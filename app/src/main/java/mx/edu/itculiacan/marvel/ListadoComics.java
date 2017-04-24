package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListadoComics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_comics);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
           // String j = (String) b.get("nomPer");
            ArrayList<String> lista = (ArrayList<String>)b.get("listaComics");
            ListView listado = (ListView) findViewById(R.id.lstComics);
            ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lista);
            listado.setAdapter(adapter);

        }
    }

}
