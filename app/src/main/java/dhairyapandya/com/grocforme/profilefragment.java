package dhairyapandya.com.grocforme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ResourceBundle;


public class profilefragment extends Fragment {

    public profilefragment() {
        // Required empty public constructor
    }
    String naam;
//    String userid;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
    Button About,tandc,Editprofile,Theme;
    TextView Name;
    //    Button Logout,verifibut;
//    Button Resetpassword;
//    TextView verification;
    FirebaseAuth auth;
    ImageView Profilepic;
    StorageReference storageReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profilefragment, container, false);
//return inflater.inflate(R.layout.fragment_profilefragment, container, false);
//        Logout=view.findViewById(R.id.logout);
//        verification = view.findViewById(R.id.verifyemail);
//        verifibut = view.findViewById(R.id.verify);
//        userid=auth.getCurrentUser().getUid();
        auth = FirebaseAuth.getInstance();
//        fStore=FirebaseFirestore.getInstance();








//        Resetpassword = view.findViewById(R.id.resetpassword);
        About=view.findViewById(R.id.about);
        tandc=view.findViewById(R.id.termsandconditions);
        Theme=view.findViewById(R.id.theme);
        Editprofile=view.findViewById(R.id.editprofile);
        Name=view.findViewById(R.id.namedisplay);
        Profilepic=view.findViewById(R.id.imageView);
        storageReference= FirebaseStorage.getInstance().getReference();


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
Bundle bungle = getArguments();
//        String msg=bundle.getString("message");
//Name.setText(msg);
//        Name.setText(this.getArguments().getString("message"));

        //Code for changing the theme
        Theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),changingtheme.class));
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
                startActivity(new Intent(getContext(),profilepage.class));
            }
        });

        //Code for Terms and Conditions
        tandc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),termsandconditions.class));
            }
        });
        //Code for about
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),aboutscreen.class));
            }
        });

        //        Code For reseting the password
//        Resetpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), resetpassword.class));
//            }
//        });

        return view;
    }
}