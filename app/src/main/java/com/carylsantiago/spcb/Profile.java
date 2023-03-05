package com.carylsantiago.spcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textViewName, textViewMemberID, textViewBio;
    TextView textViewUsername, textViewPassword;
    TextView textViewStreet, textViewBarangay, textViewCity, textViewProvince, textViewZipCode;
    TextView textViewBirthdate, textViewMarital, textViewOccupation, textViewContact;
    ImageView imageViewUpdate, ImageViewUpdateBio, imageViewDeleteBio;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //HEADERS
        textViewName = findViewById(R.id.membername);
        textViewMemberID = findViewById(R.id.membercode);
        textViewBio = findViewById(R.id.bio);
        //USERNAME AND PASSWORD
        textViewUsername = findViewById(R.id.username);
        textViewPassword = findViewById(R.id.password);
        //PLACES
        textViewStreet = findViewById(R.id.street);
        textViewBarangay = findViewById(R.id.barangay);
        textViewCity = findViewById(R.id.city);
        textViewProvince = findViewById(R.id.province);
        textViewZipCode = findViewById(R.id.zipcode);
        //OTHER INFORMATION
        textViewContact = findViewById(R.id.contactnumber);
        textViewBirthdate = findViewById(R.id.birthdate);
        textViewMarital = findViewById(R.id.maritalstatus);
        textViewOccupation = findViewById(R.id.occupation);
        imageViewUpdate = findViewById(R.id.updatepassword);
        ImageViewUpdateBio = findViewById(R.id.updatebio);
        imageViewDeleteBio = findViewById(R.id.deletebio);
        //BUTTON
        buttonBack = findViewById(R.id.back);

        //COLOR OF STATUS BAR
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.headerViolet)));
        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://spcbph.tech/android/profile.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textViewName.setText(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String fname = jsonObject.getString("fname");
                            String id = jsonObject.getString("id");
                            String bio = jsonObject.getString("bio");
                            String lname = jsonObject.getString("lname");
                            String username = jsonObject.getString("username");
                            String password = jsonObject.getString("password");
                            String street = jsonObject.getString("street");
                            String barangay = jsonObject.getString("barangay");
                            String city = jsonObject.getString("city");
                            String province = jsonObject.getString("province");
                            String zipcode = jsonObject.getString("zipcode");
                            String contact = jsonObject.getString("contact");
                            String birthdate = jsonObject.getString("birthdate");
                            String marital = jsonObject.getString("marital");
                            String occupation = jsonObject.getString("occupation");

                            //NAMING HEADER FORMAT
                            String headerName = fname + " " + lname;
                            textViewName.setText(headerName);
                            textViewMemberID.setText(id);
                            //BIO

                            textViewBio.setText(bio);
                            //USERNAME AND PASSWORD
                            textViewUsername.setText(username);
                            textViewPassword.setText(password);
                            //PLACES
                            textViewStreet.setText(street);
                            textViewBarangay.setText(barangay);
                            textViewCity.setText(city);
                            textViewProvince.setText(province);
                            textViewZipCode.setText(zipcode);
                            //INFORMATION
                            textViewContact.setText(contact);
                            textViewBirthdate.setText(birthdate);
                            textViewMarital.setText(marital);
                            textViewOccupation.setText(occupation);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                textViewName.setText(error.getLocalizedMessage());

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id", sharedPreferences.getString("id", ""));


                return paramV;
            }
        };
        queue.add(stringRequest);

        imageViewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, UpdatePassword.class));
                finish();
            }
        });


        ImageViewUpdateBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, UpdateBio.class));
                finish();
            }
        });
        imageViewDeleteBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewBio.setText(null);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://spcbph.tech/android/updatebio.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    Toast.makeText(Profile.this, response, Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("bio", textViewBio.getText().toString());
                                    editor.apply();

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
                            paramV.put("bio",textViewBio.getText().toString());
                            return paramV;
                        }
                    };
                    queue.add(stringRequest);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MainActivity.class));
                finish();
            }
        });
    }

}