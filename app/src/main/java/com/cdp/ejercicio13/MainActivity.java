package com.cdp.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.ejercicio13.Procesos.SQLiteConexion;
import com.cdp.ejercicio13.Procesos.personas;

public class MainActivity extends AppCompatActivity {

    Button btnagregar, btnsiguientepagina;
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

        btnsiguientepagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityConsulta.class));
            }
        });

    }


    private void elementos()
    {
        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtcapellidos);
        txtedad = (EditText) findViewById(R.id.txtedad);
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtdireccion = (EditText) findViewById(R.id.txtdireccion);

        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnsiguientepagina = (Button) findViewById(R.id.btnsiguientepagina);
    }

    private void AgregarPersona()
    {
        // Creamos la conexion e insercion a la BDD
        SQLiteConexion conexion = new SQLiteConexion(this, personas.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();


        try {
            String resu = "";
            //tenemos que comprobar que se haya seleccionado alguna operacion a realizar.

                if (txtnombres.getText().toString().isEmpty()) {
                    resu = "Upps!!, ingrese el nombre del registro";
                    Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
                } else {
                    if (txtapellidos.getText().toString().isEmpty()) {
                        resu = "Upps!!, ingrese el apellido";
                        Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
                    } else {
                        if (txtedad.getText().toString().isEmpty()) {
                            resu = "Favor, ingrese una nota :)!";
                            Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
                        } else {
                            if (txtcorreo.getText().toString().isEmpty())
                            {
                                resu = "Favor, ingrese un correo :)!";
                                Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
                            }
                            else{
                                if (txtdireccion.getText().toString().isEmpty())
                                {
                                    resu="Favor, ingrese una direccion :)!";
                                    Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
                                }else{

                            ContentValues datos = new ContentValues();
                            datos.put(personas.nombres, txtnombres.getText().toString());
                            datos.put(personas.apellidos, txtapellidos.getText().toString());
                            datos.put(personas.edad, txtedad.getText().toString());
                            datos.put(personas.correo, txtcorreo.getText().toString());
                            datos.put(personas.direccion, txtdireccion.getText().toString());
                            Long resultado = db.insert(personas.tablaPersonas, personas.id, datos);

                            // Creamos un Toas para que muestre en pantalla que se ha salvado correctamente el contacto
                            Toast.makeText(getApplicationContext(), " Registro (" + resultado.toString() + ") Ingresado Correctamente :)", Toast.LENGTH_LONG).show();

                            db.close();
                            limpiar();
                                }

                            }
                        }
                    }
                }
        } catch (Exception e) {
            System.out.println("Error!! Exception: " + e);
        }

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