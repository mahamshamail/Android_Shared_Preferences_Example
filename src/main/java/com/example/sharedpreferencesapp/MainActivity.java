package com.example.sharedpreferencesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "SP-EXAMPLE";

    // 1a. create a reference to Shared Preferens
    private SharedPreferences prefs;

    // 1b. give your shared preferences name
    private static final String PREFERENCES_NAME = "EXAMPLESP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. initialize your refernce to shared prefs
        this.prefs = this.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveButtonPressed(View view) {
        // 3. SAVE the name to SP
        SharedPreferences.Editor prefEditor = prefs.edit();

        // 3a.  Get the name from the Edit Text
        EditText etName = (EditText)findViewById(R.id.etName);
        String name = etName.getText().toString();

        Log.d(TAG, "Name in textbox: " + name);


        // 3b.  Save it to shared prefs

        // 3c. create a key-value pair to store your name
        prefEditor.putString("username",name);

        // 3d. commit the changes (commit = actually do the save = press the save button)
        prefEditor.apply();

        // 4. We are now finished!
        Log.d(TAG, "Name saved!");

        TextView tvResults = (TextView) findViewById(R.id.tvResults);
        tvResults.setText("Name saved to shared prefs.");

    }

    public void getButtonPressed(View view) {
        // 3. FETCH the name from SP
        // 3a. Tell SP which key you want to retrieve

        // 1st parmeter = the key that you want to fetch
        // 2nd parameter = the value you want to assign IN CASE this key does not exist
            // - What value should nameFromSP have if "username" key is not found in SP?
        String nameFromSP = this.prefs.getString("username","");
        // OPTION 2: You can just do prefs.___, you don't need the "this"
        // String nameFromSP = prefs.getString("username", "");

        Log.d(this.TAG, "Name in SP is: " + nameFromSP);

        // 3b. Display that key in the user interface
        TextView tvResults = (TextView) findViewById(R.id.tvResults);
        tvResults.setText("Name in SP is: " + nameFromSP);


    }
}