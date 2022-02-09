package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class editprofile extends AppCompatActivity {
    public static final String TAG="TAG";
    EditText Name,Email,Mobile;
Button Save;
ImageView Profilepic;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
FirebaseUser user;
StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Name=findViewById(R.id.ename);
        Email=findViewById(R.id.eemailaddress);
        Mobile=findViewById(R.id.emobilenumber);
        Save=findViewById(R.id.save);
        Profilepic=findViewById(R.id.profilepicture);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        user=fAuth.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference();

        //for Image reference
        StorageReference profileRef=storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/Groc_for_me_profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(Profilepic);
            }




        });

        Intent data= getIntent();
        String fullName = data.getStringExtra("fullname");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");

        Name.setText(fullName);
        Email.setText(email);
        Mobile.setText(phone);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.getText().toString().isEmpty() ||Email.getText().toString().isEmpty() || Mobile.getText().toString().isEmpty()){
                    Toast.makeText(editprofile.this, "Empty Field", Toast.LENGTH_SHORT).show();
                    return;
                }
                String Editedemail=Email.getText().toString();
                user.updateEmail(Editedemail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef=fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited=new HashMap<>();
                        edited.put("Mail ID",Editedemail);
                        edited.put("Mobile Number",Mobile.getText().toString());
                        edited.put("Name",Name.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(editprofile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),profilepage.class));
                                finish();
                            }
                        });


                        Toast.makeText(editprofile.this, "Email changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(editprofile.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        Profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(editprofile.this, "Profile Pic clicked", Toast.LENGTH_SHORT).show();
                                Intent openGalleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1212);
            }
        });
        Log.d(TAG,"onCreate : "+fullName+" "+email+" "+phone);






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1212)
        {
            if(resultCode== Activity.RESULT_OK){
                Uri imageUri=data.getData();

              //this is the risky code

//                Intent i = new Intent(this, profilepage.class);
//                i.putExtra("imagePath", imageUri);
//                startActivity(i);
                //Risky code ends
//                Uri selectedImage = data.getData();

//                Intent i = new Intent(this, profilepage.class);
//                i.putExtra("imagePath", selectedImage);
                Profilepic.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
//                startActivity(i);
//                Uri uri = data.getData();
//                Intent intent=new Intent(editprofile.this,profilepage.class);
//                intent.putExtra("imageUri", uri.toString());
//                startActivity(intent);
//                Uri selectedImage = data.getData();
////                Intent intent = new Intent(this, profilepage.class);
//                i.putExtra("imagePath", selectedImage);
//                startActivity(i);

//                    Intent intent =new Intent(editprofile.this,profilepage.class);
//                    intent.putExtra("xyz",Profilepic.);
//                    startActivity(intent);

//                Intent i = new Intent(this, profilepage.class);
//                Bitmap b=imageUri; // your bitmap
//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                b.compress(Bitmap.CompressFormat.PNG, 50, bs);
//                i.putExtra("profileimage", bs.toByteArray());
//                startActivity(i);

//                Intent intent= new Intent(this, profilepage.class);
////                intent.putExtra("image", Profilepic.getAvatar_id());
//                startActivity(intent);


//                Intent i = new Intent(this, profilepage.class);
//                Bitmap b; // your bitmap
//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
////                bs.compress(Bitmap.CompressFormat.PNG, 50, bs);
//                i.putExtra("byteArray", bs.toByteArray());
//                startActivity(i);

            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        //logic to upload image to firebase storage
        StorageReference fileRef =storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/Groc_for_me_profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(profilepage.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(Profilepic);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(editprofile.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }



}