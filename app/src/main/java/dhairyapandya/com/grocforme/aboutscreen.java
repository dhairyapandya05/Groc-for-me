package dhairyapandya.com.grocforme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class aboutscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutscreen);
        getSupportActionBar().setTitle("About");
    }
}