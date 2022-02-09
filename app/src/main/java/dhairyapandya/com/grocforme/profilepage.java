package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import io.grpc.Context;

public class profilepage extends AppCompatActivity {
    TextView Name,Email,Mobilenumber,Verification;
    String userid;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
Button Resetpassword,Logout,Verifibut,Editprofile;
ImageView Profilepicture,Trial;
StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);
        Verification=findViewById(R.id.verifyemail);
        Verifibut=findViewById(R.id.verify);
        Name=findViewById(R.id.nameview);
//        Trial=findViewById(R.id.imageView2);
        Email=findViewById(R.id.emailview);
        Mobilenumber=findViewById(R.id.Mobilenumberview);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userid=fAuth.getCurrentUser().getUid();
        Resetpassword=findViewById(R.id.resetpassword);
        Logout=findViewById(R.id.logout);
        Editprofile=findViewById(R.id.editprofile);
        Profilepicture=findViewById(R.id.profilepicture);
        storageReference= FirebaseStorage.getInstance().getReference();


//Pasting image to the imageview from edit profile activvity

//        String imagePath = getIntent().getStringExtra("imagePath");
//        Profilepicture.setImageURI(Uri.parse(imagePath));


//Retreving image from storage
        StorageReference profileRef=storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/Groc_for_me_profile.jpg");
profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
    @Override
    public void onSuccess(Uri uri) {
        Picasso.get().load(uri).into(Profilepicture);
    }
});

//        Intent intent = getIntent();
//        String image_path= intent.getStringExtra("imagePath");
//        Uri fileUri = Uri.parse(image_path);
//        Profilepicture.setImageURI(fileUri);

//        Imageview iv_photo=(ImageView)findViewById(R.id.iv_photo);
//        Bundle extras = getIntent().getExtras();
//        Uri myUri = Uri.parse(extras.getString("imageUri"));
//        Profilepicture.setImageURI(myUri);


        //Code for Log out
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), logoutsplashscreen.class));
                finish();
            }
//
        });

//        This is the trial code






        //Code to retreve the data from the firestore
        DocumentReference documentReference=fStore.collection("users").document(userid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Mobilenumber.setText(documentSnapshot.getString("Mobile Number"));
                    Email.setText(documentSnapshot.getString("Mail ID"));
                    Name.setText(documentSnapshot.getString("Name"));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Info not found", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to fetch the data", Toast.LENGTH_SHORT).show();
            }
        });


        //For sending the display name to the profile fragment
//        FragmentManager fragmentmanager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentmanager.beginTransaction();
//        Bundle bundle =new Bundle();
//        bundle.putString("message",Name.getText().toString());
//        Fragment ProfileFrag=new profilefragment();
//        ProfileFrag.setArguments(bundle);


        //        Code For reseting the password
        Resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), resetpassword.class));
            }
        });



        //Code for Email Verification Button
        //        Code to verify the email address
        if (!fAuth.getCurrentUser().isEmailVerified()) {  //if email is not verified
            Verifibut.setVisibility(View.VISIBLE);
            Verification.setVisibility(View.VISIBLE);
        }
        Verifibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Verification Email Sent", Toast.LENGTH_SHORT).show();
                        Verification.setVisibility(View.GONE);
                        Verifibut.setVisibility(View.GONE);
                    }
                });
            }
        });

        //Code for edit profile
        Editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(view.getContext(),editprofile.class);
                i.putExtra("fullname",Name.getText().toString());
                i.putExtra("email",Email.getText().toString());
                i.putExtra("phone",Mobilenumber.getText().toString());
                startActivity(i);
            }
        });
    }




}