package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            url = new URL(requestUrl);

            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)
            URLConnection uc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            StringBuilder s = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                // Appending the Line to StringBuilder
                s.append(line);
            }
            in.close();

            // Make StirngBuilder to String
            jsonResponse = s.toString();



                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */
            // Create an array to access each element
            JSONObject reader = new JSONObject(jsonResponse);
            JSONArray features = reader.getJSONArray("features");

            for (int i = 0; i < features.length(); i++ ) {
                JSONObject p = features.getJSONObject(i).getJSONObject("properties");
                double mag = p.getDouble("mag");
                String place = p.getString("place");
                long time = p.getLong("time");
                String eurl = p.getString("url");

                // Assign values to earthquake

                Earthquake earthquake = new Earthquake(mag, place, time, eurl);
                earthquakes.add(earthquake);

            }

            // Return the list of earthquakes
            return earthquakes;

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
