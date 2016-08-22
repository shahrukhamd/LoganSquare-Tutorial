package com.shahrukhamd.logansquare_tutorial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);

        parseJson();
    }

    void parseJson() {
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                StringBuilder sb = new StringBuilder();

                try {
                    URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                        QuakeModel model = LoganSquare.parse(urlConnection.getInputStream(), QuakeModel.class);

                        for(QuakeModel.Features f: model.features){
                            sb.append("Magnitude: ").append(f.properties.mag).append("\n")
                                    .append("Place: ").append(f.properties.place).append("\n")
                                    .append("ID: ").append(f.quake_id).append("\n")
                                    .append("URL: ").append(f.properties.url).append("\n")
                                    .append("Longitude: ").append(f.geometry.coordinates[0]).append("\n")
                                    .append("Latitude: ").append(f.geometry.coordinates[1]).append("\n")
                                    .append("------------------\n");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return sb.toString();
            }

            @Override
            protected void onPostExecute(String text) {
                super.onPostExecute(text);
                textView.setText(text);
            }
        }.execute();
    }
}