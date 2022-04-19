package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import dhairyapandya.com.grocforme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
//    TextView verification;
    Button Logout;
//    Button verifibut, resetpassword;
    FirebaseAuth auth;
    BottomNavigationView BNV;
    TabLayout tabLayout;
    ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

//        setContentView(binding.getRoot());
//        replaceFragment(new homefragment());
//        auth = FirebaseAuth.getInstance();

        //Triallllllllllllllllllllllllllll Codeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
        BNV=findViewById(R.id.bottomnav);

        BNV.setSelectedItemId(R.id.homebtn);
        BNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.stocksbtn:
                        startActivity(new Intent(getApplicationContext(),stocksbnm.class));
                        overridePendingTransition(0,0);
//                                BNV.setSelectedItemId(R.id.stocksbtn);

                        return true;

                    case R.id.homebtn:
//                        startActivity(new Intent(getApplicationContext(),home.class));
//                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profilebtn:
                        startActivity(new Intent(getApplicationContext(),profilebnm.class));
                        overridePendingTransition(0,0);
//                        BNV.setSelectedItemId(R.id.profilebtn);

                        return true;



                }
                return false;
            }
        });

        //Triallllllllllllllllllllllllllll Codeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee


//        Logout = findViewById(R.id.logout);
//        resetpassword = findViewById(R.id.resetpassword);
//        verification = findViewById(R.id.verifyemail);
//        verifibut = findViewById(R.id.verify);


//        Code For reseting the password
//        resetpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), resetpassword.class));
//            }
//        });


        //Code to verify the email address
//        if (!auth.getCurrentUser().isEmailVerified()) {
//            verifibut.setVisibility(View.VISIBLE);
//            verification.setVisibility(View.VISIBLE);
//        }
//        verifibut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(MainActivity.this, "VerificationEmailSent", Toast.LENGTH_SHORT).show();
//                        verification.setVisibility(View.GONE);
//                        verifibut.setVisibility(View.GONE);
//                    }
//                });
//            }
//        });

////        Code for Logout
//        Logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), login.class));
//                finish();
//            }
//        });


        //Changing the Fragments Code
//        binding.btmnvgview.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home:
//                    replaceFragment(new homefragment());
//                    break;
//                case R.id.stocks:
//                    replaceFragment(new stocksfragment());
//                    break;
//                case R.id.profile:
//                    replaceFragment(new profilefragment());
//                    break;
//            }
//            return true;
//        });

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//
////add tab items with title..
//        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
//        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        tabLayout = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);



        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                        String tabTitles[] = new String[]{"Friends", "Suggested Friends"};
//                        tab.setText("Tab " + (position + 1));
                        if(position==0)
                        tab.setText("Completed");
                        if(position==1)
                            tab.setText("Pending");
                    }
                }).attach();

    }

//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTraction = getSupportFragmentManager().beginTransaction();
//        fragmentTraction.replace(R.id.frameLayout, fragment);
//        fragmentTraction.commit();
//    }
}