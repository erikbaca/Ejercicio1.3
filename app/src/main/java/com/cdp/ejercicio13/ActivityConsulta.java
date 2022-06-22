package com.cdp.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.ejercicio13.Procesos.SQLiteConexion;
import com.cdp.ejercicio13.Procesos.personas;

public class ActivityConsulta extends AppCompatActivity {


    SQLiteConexion conexion;
    EditText id, nombres, apellidos, edad, correo, direccion;
    Button btnconsulta, btneliminar, btnactualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        inicio();

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarPersona();
            }
        });

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


                Toast.makeText(getApplicationContext(),"Consultado con exito :)> ",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No hay datos, favor verifique :( ",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext()," Upps, ha ocurrido un problema :( !!",Toast.LENGTH_LONG).show();
        }

    }

    private void Eliminar()
    {

    }

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
}