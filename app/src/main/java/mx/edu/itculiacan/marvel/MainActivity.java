package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void pantallaBuscarPersonaje(View view) {
        Intent pantallaPersonaje= new Intent(this,Descripcion.class);
        startActivity(pantallaPersonaje); //Cambia de pantalla
    }

    public void pantallaBuscarComics(View view) {
        Intent pantallaComics= new Intent(this,Comics.class);
        startActivity(pantallaComics); //Cambia de pantalla
    }
}
