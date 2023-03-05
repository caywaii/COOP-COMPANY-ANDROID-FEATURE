package com.carylsantiago.spcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Accstatus extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textViewShareCapital, textViewSavingDeposit, textViewTimeDeposit;
    TextView textViewLoans, textViewPatronageRefund, textViewDividends;
    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accstatus);
        //COLOR OF STATUS BAR
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.headerViolet)));
        //ACCOUNTS ID
        textViewShareCapital = findViewById(R.id.sharecapital);
        textViewSavingDeposit = findViewById(R.id.savingdeposit);
        textViewTimeDeposit = findViewById(R.id.timedeposit);
        textViewLoans = findViewById(R.id.loans);
        textViewPatronageRefund = findViewById(R.id.patronageref);
        textViewDividends = findViewById(R.id.dividends);
        buttonBack = findViewById(R.id.back);

        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://spcbph.tech/android/accdetails.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sharecapital = jsonObject.getString("sharecapital");
                            String savingdeposit = jsonObject.getString("savingdeposit");
                            String loans = jsonObject.getString("loans");
                            String timedeposit = jsonObject.getString("timedeposit");
                            String dividends = jsonObject.getString("dividends");
                            String patref = jsonObject.getString("patref");

                           textViewShareCapital.setText(sharecapital);
                           textViewSavingDeposit.setText(savingdeposit);
                           textViewLoans.setText(loans);
                           textViewTimeDeposit.setText(timedeposit);
                           textViewDividends.setText(dividends);
                           textViewPatronageRefund.setText(patref);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();


            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id", sharedPreferences.getString("id", ""));


                return paramV;
            }
        };
        queue.add(stringRequest);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accstatus.this, MainActivity.class));
                finish();
            }
        });
    }
}