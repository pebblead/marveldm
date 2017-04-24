package mx.edu.itculiacan.marvel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DetallePersonaje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_personaje);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get("nomPer");
            EditText nombre = (EditText) findViewById(R.id.txtNombre);
            //EditText desc = (EditText) findViewById(R.id.txtDescripcion);
            EditText desc2 = (EditText) findViewById(R.id.txtMultiDes);
            EditText comics = (EditText) findViewById(R.id.txtNComics);
            ImageView imgPer=(ImageView)findViewById(R.id.imgPersonaje);
            nombre.setText(j);
           // desc.setText((String) b.get("desc"));
            desc2.setText((String) b.get("desc"));
            comics.setText((String) b.get("com"));

            String urlimg = (String) b.get("img");
            Picasso.with(this).load(urlimg).into(imgPer);

        }
          /*  URL newurl = null;
            try {
                newurl = new URL(urlimg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap mIcon_val = null;
            try {
                mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imper=(ImageView) findViewById(R.id.imgPersonaje);
            imper.setImageBitmap(mIcon_val);
        }*/


    }



}
