package com.carylsantiago.spcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //COLOR OF STATUS BAR
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.headerViolet)));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.spcbmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.menuProfile:
                Toast.makeText(this, "This is your Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Profile.class));
                finish();
                return true;
            case R.id.menuAccStatus:
                Toast.makeText(this, "This is your Account Status", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Accstatus.class));
                finish();
                return true;

            case R.id.menuLogout:
                Toast.makeText(this, "Log Out Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                return true;



        }
        return super.onOptionsItemSelected(item);
    }

}