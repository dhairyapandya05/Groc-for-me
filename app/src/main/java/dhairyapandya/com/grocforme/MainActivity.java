package dhairyapandya.com.grocforme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import dhairyapandya.com.grocforme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
//    TextView verification;
    Button Logout;
//    Button verifibut, resetpassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        replaceFragment(new homefragment());
        auth = FirebaseAuth.getInstance();
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
        binding.btmnvgview.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new homefragment());
                    break;
                case R.id.stocks:
                    replaceFragment(new stocksfragment());
                    break;
                case R.id.profile:
                    replaceFragment(new profilefragment());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTraction = getSupportFragmentManager().beginTransaction();
        fragmentTraction.replace(R.id.frameLayout, fragment);
        fragmentTraction.commit();
    }
}