package com.itsjustfaiq.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etLink;
    EditText etAlamat;
    EditText etStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAlamat = findViewById(R.id.etAlamat);
        etLink = findViewById(R.id.etLink);
        etStatus = findViewById(R.id.etStatus);
    }

    public void openBrowser(View view){
        String link = "http://" + etLink.getText().toString();

        Uri webpage = Uri.parse(link);
        Intent intentBrowser = new Intent(Intent.ACTION_VIEW, webpage);

        if(intentBrowser.resolveActivity(getPackageManager()) != null){
            startActivity(intentBrowser);
        } else{
            Log.d("ImplicitIntents","Can't handle request!");
            Toast.makeText(MainActivity.this,"failure opening browser!", Toast.LENGTH_SHORT).show();
        }
    }

    public void openMaps(View view){
        String location = etAlamat.getText().toString();

        Uri urilocation = Uri.parse("geo:0,0?q=" + location);
        Intent intentMaps = new Intent(Intent.ACTION_VIEW, urilocation);

        if(intentMaps.resolveActivity(getPackageManager()) != null){
            startActivity(intentMaps);
        } else{
            Log.d("ImplicitIntents","Can't handle request!");
            Toast.makeText(MainActivity.this,"failure opening maps!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shareStatus(View view){
        String status = etStatus.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(status)
                .startChooser();
    }
}
