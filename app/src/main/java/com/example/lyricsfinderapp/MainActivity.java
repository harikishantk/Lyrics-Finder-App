package com.example.lyricsfinderapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText edtTextSongName;
    private EditText edtTextArtistName;
    private Button button;
    private TextView lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTextSongName = findViewById(R.id.edtTextSongName);
        edtTextArtistName = findViewById(R.id.edtTextArtistName);
        button = findViewById(R.id.button);
        lyrics = findViewById(R.id.lyrics);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Button is tapped now!",Toast.LENGTH_SHORT).show();
                String url = "https://api.lyrics.ovh/v1/" + edtTextArtistName.getText().toString() + "/" + edtTextSongName.getText().toString();
                RequestQueue Requestqueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            lyrics.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Wrong Artist/Song Name, Please Try Again!",Toast.LENGTH_SHORT).show();
                    }
                });

                Requestqueue.add(jsonObjectRequest);
            }
        });



    }
}