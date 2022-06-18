package com.cdp.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.ejercicio13.Procesos.SQLiteConexion;
import com.cdp.ejercicio13.Procesos.personas;

public class MainActivity extends AppCompatActivity {

    Button btnagregar;
    EditText txtnombres, txtapellidos, txtedad, txtcorreo, txtdireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Casteo de los elementos en la interfaz
        elementos();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();
            }
        });

    }

    private void elementos()
    {
        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        txtedad = (EditText) findViewById(R.id.txtedad);
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtdireccion = (EditText) findViewById(R.id.txtdireccion);

        btnagregar = (Button) findViewById(R.id.btnagregar);
    }


    private void AgregarPersona()
    {
        // Creamos la conexion e insercion a la BDD
        SQLiteConexion conexion = new SQLiteConexion(this, personas.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put(personas.nombres, txtnombres.getText().toString());
        datos.put(personas.apellidos, txtapellidos.getText().toString());
        datos.put(personas.edad, txtedad.getText().toString());
        datos.put(personas.correo, txtcorreo.getText().toString());
        datos.put(personas.direccion, txtdireccion.getText().toString());

    long resultado = db.insert(personas.tablaPersonas, personas.id, datos);

        Toast.makeText(getApplicationContext(), "Registro Ingresado con Exito :)", Toast.LENGTH_LONG).show();

        db.close();

        limpiar();
    }


    private void limpiar()
    {
        txtnombres.setText("");
        txtapellidos.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");
    }
}