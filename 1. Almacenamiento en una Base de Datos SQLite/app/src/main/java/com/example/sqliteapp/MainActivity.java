package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id== R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void alta(View v){
        AdminSQLite admin = new AdminSQLite(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo",cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        bd.insert("articulos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        Toast.makeText(this,"Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v){
        AdminSQLite admin = new AdminSQLite(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        Cursor fila = bd.rawQuery("select descripcion, precio from articulos where codigo=" + cod,null);
        if(fila.moveToFirst()){
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void consultapordescripcion(View v){
        AdminSQLite admin = new AdminSQLite(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = et2.getText().toString();
        Cursor fila = bd.rawQuery("select codigo, precio from articulos where descripcion='" + descri +"'",null);
        if(fila.moveToFirst()){
            et1.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe un artículo con dicha descripcion", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View v){
        AdminSQLite admin = new AdminSQLite(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        int cant = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        if(cant==1)
            Toast.makeText(this,"Se borró el artículo con dicho código", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v){
        AdminSQLite admin = new AdminSQLite(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri= et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo",cod);
        registro.put("descripcion",descri);
        registro.put("precio",pre);
        int cant = bd.update("articulos", registro,"codigo=" + cod, null);
        bd.close();
        if(cant==1)
            Toast.makeText(this,"Se modificaron los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No existe un artículo con el código ingresado", Toast.LENGTH_SHORT).show();
    }


}
