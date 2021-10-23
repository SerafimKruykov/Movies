package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class DetailsActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    String posterUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        requestQueue = Volley.newRequestQueue(this);


        ImageView posterImageView = findViewById(R.id.posterImageView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView yearTextView = findViewById(R.id.yearTextView);
        TextView countryTextView = findViewById(R.id.countryTextView);
        TextView directorTextView = findViewById(R.id.directorTextView);
        TextView actorsTextView = findViewById(R.id.actorsTextView);


        Intent intent = getIntent();
        if (intent != null) {



            String url = "https://www.omdbapi.com/?apikey=f7976d64&i=" + intent.getStringExtra("id");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                titleTextView.setText(response.getString("Title"));
                                yearTextView.setText(response.getString("Year"));
                                countryTextView.setText(response.getString("Country"));
                                directorTextView.setText(response.getString("Director"));
                                actorsTextView.setText(response.getString("Actors"));
                                posterUrl = response.getString("Poster");
                                Picasso.get().load(posterUrl).fit().centerInside().into(posterImageView);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            requestQueue.add(request);


        }

    }
}
