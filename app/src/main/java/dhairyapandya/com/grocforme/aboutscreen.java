package dhairyapandya.com.grocforme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

public class aboutscreen extends AppCompatActivity {
Toolbar mtoolbarr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutscreen);
//        toolbarr=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About");
//        Toolbar toolbarr = (Toolbar) findViewById(R.id.toolbar);
//        TextView mTitle = (TextView) toolbarr.findViewById(R.id.about);
//
//        setSupportActionBar(toolbarr);
//        mTitle.setText(toolbarr.getTitle());
//Toolbar toolbarr =findViewById(R.id.toolbar);
////        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setSupportActionBar(binding.toolbar);
//        toolbarr =findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
//        setSupportActionBar(toolbarr);
//        mtoolbarr = findViewById(R.id.customtoolbar);
//        TextView mToolbarCustomTitle = findViewById(R.id.toolbar_title);
//                setSupportActionBar(Toolbar customtoolbar);

    }
}