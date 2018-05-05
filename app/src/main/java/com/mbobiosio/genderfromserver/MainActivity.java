package com.mbobiosio.genderfromserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    private static final String ID = "identifier";
    private static final String NAME = "name";
    private static final String API_URL = "https://api.myjson.com/bins/12kqlm";
    private BackGroundTask backGroundTask;
    Spinner genders;
    ArrayList<DataObject> genderList = new ArrayList<DataObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        doGenders();
    }

    public void doGenders() {
        List<NameValuePair> apiParams = new ArrayList<NameValuePair>(0);
        apiParams.add(new BasicNameValuePair("call", "genders"));
        backGroundTask = new BackGroundTask(API_URL, "GET", apiParams);

                try {
                    JSONObject countryJSON = backGroundTask.execute().get();
                    // Getting Array of genders


                    JSONObject data = countryJSON.getJSONObject("data");
                    JSONArray content = data.getJSONArray("content");


                    // looping through All gender
                    for (int i = 0; i < content.length(); i++) {

                        JSONObject c = content.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(ID);
                        String name = c.getString(NAME);

                        // add Country
                        genderList.add(new DataObject(id, name.toUpperCase()));
                    }

                    // bind adapter to spinner
                    genders = findViewById(R.id.genders);
                    SpinnerAdapter cAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, genderList);
                    genders.setAdapter(cAdapter);

                    genders.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            DataObject selectedGender = genderList.get(position);
                            Toast.makeText(MainActivity.this, "selected: " + selectedGender.getName() + "with id number of: " + id, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }

                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
    }

