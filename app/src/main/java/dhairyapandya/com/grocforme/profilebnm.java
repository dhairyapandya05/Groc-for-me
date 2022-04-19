package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class profilebnm extends AppCompatActivity {
    String naam;
    //    String userid;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
    Button About,tandc,Editprofile,Theme,Chatbot;
    TextView Name;
    //    Button Logout,verifibut;
//    Button Resetpassword;
//    TextView verification;
    FirebaseAuth auth;
    ImageView Profilepic;
    StorageReference storageReference;
    BottomNavigationView BNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilebnm);
        auth = FirebaseAuth.getInstance();
//        fStore=FirebaseFirestore.getInstance();








//        Resetpassword = view.findViewById(R.id.resetpassword);
        About=findViewById(R.id.about);
        tandc=findViewById(R.id.termsandconditions);
        Theme=findViewById(R.id.theme);
        BNV=findViewById(R.id.bottomnav);
        Editprofile=findViewById(R.id.editprofile);
        Name=findViewById(R.id.namedisplay);
        Profilepic=findViewById(R.id.imageView);
//        Chatbot=findViewById(R.id.chatbot);
        storageReference= FirebaseStorage.getInstance().getReference();
//Chatbot code
//        Chatbot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotourl("https://console.dialogflow.com/api-client/demo/embedded/1dff2cf6-ffda-4e89-8f1c-b01ed7a75b9a");
//            }
//
//
//        });

        //Display the Image of the user
        //for Image reference
        StorageReference profileRef=storageReference.child("users/"+auth.getCurrentUser().getUid()+"/Groc_for_me_profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(Profilepic);
            }
        });
//

//to get the Name of the user
//        Bundle bungle = getArguments();
//        String msg=bundle.getString("message");
//Name.setText(msg);
//        Name.setText(this.getArguments().getString("message"));

        //Code for changing the theme
        Theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),changingtheme.class));
            }
        });

//        Code to verify the email address
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
//                        Toast.makeText(getContext(), "Verification Email Sent", Toast.LENGTH_SHORT).show();
//                        verification.setVisibility(View.GONE);
//                        verifibut.setVisibility(View.GONE);
//                    }
//                });
//            }
//        });

//        Code for Logout
//        Intent i=Intent.getIntent();

//code for log out
//        Logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getContext(), logoutsplashscreen.class));
//                getActivity().finish();
//            }
////
//        });


        //Code for profile
        Editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),profilepage.class));
            }
        });

        //Code for Terms and Conditions
        tandc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),termsandconditions.class));
            }
        });
        //Code for about
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),aboutscreen.class));
            }
        });

        //        Code For reseting the password
//        Resetpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), resetpassword.class));
//            }
//        });
//        auth = FirebaseAuth.getInstance();
        //Set home selected
        BNV.setSelectedItemId(R.id.profilebtn);
        BNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.stocksbtn:
                        startActivity(new Intent(getApplicationContext(),stocksbnm.class));
                        overridePendingTransition(0,0);
//                        BNV.setSelectedItemId(R.id.stocksbtn);
                        return true;

                    case R.id.homebtn:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
//                        BNV.setSelectedItemId(R.id.homebtn);
                        return true;

                    case R.id.profilebtn:
//                        startActivity(new Intent(getApplicationContext(),profile.class));
//                        overridePendingTransition(0,0);
                        return true;



                }
                return false;
            }
        });
//
//        //Code for Terms and Conditions
//        tandc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),termsandconditions.class));
//            }
//        });
//        //Code for about
//        About.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),aboutscreen.class));
//            }
//        });
//
//        //Code for profile
//        Editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),profilepage.class));
//            }
//        });
////        Bundle bungle = getArguments();
//        //Code for changing the theme
//        Theme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),changingtheme.class));
//            }
//        });

        //Display the Image of the user
        //for Image reference
//        StorageReference profileRef=storageReference.child("users/"+auth.getCurrentUser().getUid()+"/Groc_for_me_profile.jpg");
//        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).into(Profilepic);
//            }
//        });

    }
    private void gotourl(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}