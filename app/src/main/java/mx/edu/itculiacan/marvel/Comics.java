package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Comics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);
    }
    public void pantallaListadoComics(View view) {
        Intent pantallaListado= new Intent(this,ListadoComics.class);
        startActivity(pantallaListado); //Cambia de pantalla
    }
}
