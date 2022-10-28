package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import android.app.SearchManager;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class stocksbnm extends AppCompatActivity {
    BottomNavigationView BNV;
    RecyclerView recview;
    myadapter adapter;
    FloatingActionButton fb;
//SwipeRefreshLayout Refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocksbnm);
//Floating action button
        fb=findViewById(R.id.fadd);
//        Refresh=findViewById(R.id.refresh);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),adddata.class));
            }
        });





        //Coding Code
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), model.class)
                        .build();

        adapter=new myadapter(options);
        recview.setAdapter(adapter);
        //Coding Code


        BNV=findViewById(R.id.bottomnav);
//        BNV=findViewById(R.id.bottomnav);
        //Set home selected
        BNV.setSelectedItemId(R.id.stocksbtn);
        BNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.stocksbtn:
//                        startActivity(new Intent(getApplicationContext(),stocks.class));
//                        overridePendingTransition(0,0);
                        return true;

                    case R.id.homebtn:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
//                                BNV.setSelectedItemId(R.id.homebtn);

                        return true;

                    case R.id.profilebtn:
                        startActivity(new Intent(getApplicationContext(),profilebnm.class));
                        overridePendingTransition(0,0);
//                                BNV.setSelectedItemId(R.id.profilebtn);

                        return true;



                }
                return false;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students").orderByChild("Name").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        adapter=new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }


}