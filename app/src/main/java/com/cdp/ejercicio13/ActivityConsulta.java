package com.cdp.ejercicio13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.ejercicio13.Procesos.SQLiteConexion;
import com.cdp.ejercicio13.Procesos.personas;

import java.util.function.Function;

public class ActivityConsulta extends AppCompatActivity {


    SQLiteConexion conexion;
    EditText id, nombres, apellidos, edad, correo, direccion;
    Button btnconsulta, btneliminar, btnactualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        inicio();

        // Boton de consultar informacion
        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                BuscarPersona();
            }
        });



        //Boton Eliminar
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityConsulta.this);
                builder.setMessage("Esta seguro de eliminar el Registro?")
                                .setTitle("ATENCION");

                        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                eliminarRegistro();
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Toast.makeText(getApplicationContext(), "No se elimino el Registro !!", Toast.LENGTH_LONG).show();

                            }
                        }).show();

            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRegistro();
            }
        });

    }

    //Eliminar Registro
    private void eliminarRegistro()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        /* Parametros de BUSQUEDA de la sentencia SELECT*/
        String [] params = {id.getText().toString()};

        db.delete(personas.tablaPersonas, personas.id+"=?", params);

        Toast.makeText(getApplicationContext(),"Se ha eliminado el Registro !! ",Toast.LENGTH_LONG).show();

        limpiar2();
        db.close();

    }

    // Metodo para actualizar informacion de la persona
    private void actualizarRegistro()
    {
        try
        {
            //Creamos la conexion
            SQLiteDatabase db = conexion.getWritableDatabase();
            /* Parametros de BUSQUEDA de la sentencia SELECT*/
            String [] params = {id.getText().toString()};
            ContentValues values = new ContentValues();
            values.put(personas.nombres, nombres.getText().toString());
            values.put(personas.apellidos, apellidos.getText().toString());
            values.put(personas.edad, edad.getText().toString());
            values.put(personas.correo, correo.getText().toString());
            values.put(personas.direccion, direccion.getText().toString());

            //Se encargara del proceso de Actualizacion.
            db.update(personas.tablaPersonas,values,personas.id+"=?",params);
            Toast.makeText(getApplicationContext(), "El Registro ha sido actualizado!!!", Toast.LENGTH_LONG).show();
            limpiar2();
            db.close();
        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "No se pudo actualizar el Registro :(", Toast.LENGTH_LONG).show();
        }
    }


    private void BuscarPersona()
    {
        try
        {
            SQLiteDatabase db = conexion.getWritableDatabase();
            /* Parametros de BUSQUEDA de la sentencia SELECT*/
            String [] params = {id.getText().toString()};

            /* Campos a retornar  de la sentencia SELECT*/
            String [] fields = { personas.nombres,
                    personas.apellidos,
                    personas.edad,
                    personas.correo,
                    personas.direccion};

            String WhereCondition = personas.id + "=?";

            Cursor cdata = db.query(personas.tablaPersonas,
                    fields,
                    WhereCondition,params,null,null,null);

            cdata.moveToFirst();

            if(cdata.getCount() > 0 )
            {
                nombres.setText(cdata.getString(0));
                apellidos.setText(cdata.getString(1));
                edad.setText(cdata.getString(2));
                correo.setText(cdata.getString(3));
                direccion.setText(cdata.getString(4));


                Toast.makeText(getApplicationContext(),"Consultado con exito !! ",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No hay informacion, favor verifique :( ",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext()," Upps, ha ocurrido un problema !!",Toast.LENGTH_LONG).show();
        }

    }


    // Funcion para extraer los id
    private void inicio()
    {
        conexion = new SQLiteConexion(this, personas.NameDatabase, null, 1);

        btnconsulta = (Button) findViewById(R.id.btnbuscar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);

        id = (EditText) findViewById(R.id.txtcid);
        nombres = (EditText) findViewById(R.id.txtcnombres);
        apellidos = (EditText) findViewById(R.id.txtcapellidos);
        edad = (EditText) findViewById(R.id.txtcedad);
        correo = (EditText) findViewById(R.id.txtccorreo);
        direccion = (EditText) findViewById(R.id.txtcdireccion);

    }

    private void limpiar2()
    {
        id.setText("");
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }



}