package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login extends AppCompatActivity {
//    public static final int GOOGLE_SIGN_IN_CODE = 10005;
//    int status=1;//1 gor signing in with google 0 for signing in with register
    EditText username, password;
    TextView forgotpassword;
    Button signupbutton, register;
    SignInButton btn;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;
//    GoogleSignInOptions gso;
//    GoogleSignInClient signInClient;
public static final int GOOGLE_CODE = 10005;
    GoogleSignInClient mGoogleSignInClient;
//    @Override
//    public void onStart() {
////        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if (currentUser != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        signupbutton = findViewById(R.id.SignupButton);
        forgotpassword=findViewById(R.id.forgotpassword);
        register = findViewById(R.id.REGISTER);
        firebaseAuth=FirebaseAuth.getInstance();
        reset_alert = new AlertDialog.Builder(this);
        inflater=this.getLayoutInflater();
        btn=findViewById(R.id.signinwalla);




//Google sign in
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });










//google font setting in the button

//register Button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });

        //Signupbutton
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extract the data
                //validate the data

                if (username.getText().toString().isEmpty()) {
                    username.setError("Username is missing");
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Password is missing");
                    return;
                }

                //make users entry in the application
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //login is sucessful
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();//So that user never jumps to login activity again when he press on back button
//                        status=0;
//                        Intent i = new Intent(login.this,profilefragment.class);
//                        i.putExtra("STATUS",status);
//                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


//        code for forgot password

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start alert dialog
                View view = inflater.inflate(R.layout.reset_pop,null);
                reset_alert.setTitle("Reset Forgot Password ?").setMessage("Enter Your Email to get password reset link.")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //validate the email Address
                        EditText email = view.findViewById(R.id.reset_email_pop);
                        if(email.getText().toString().isEmpty())
                        {
                            email.setError("Required Field");
                            return;
                        }

                        //set the reset link
                        firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this, "Reset Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Cancel",null).setView(view).create().show();
            }
        });
        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("405537836325-k77po960k26eli8807d3uh62fqprbqhv.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//        signInClient = GoogleSignIn.getClient(this, gso);
//
//        GoogleSignInAccount signInAccount=GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount!=null){
//            Toast.makeText(this, "User Already logged in", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this,MainActivity.class));
//        }
//        signinwallabtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent sign =signInClient.getSignInIntent();
//                startActivityForResult(sign,GOOGLE_SIGN_IN_CODE);
//            }
//        });

    }

    //Code for google sign in
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, GOOGLE_CODE);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == GOOGLE_CODE) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
////                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
////                Log.w(TAG, "Google sign in failed", e);
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
////                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
////                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
////                            Log.w(TAG, "signInWithCredential:failure", task.getException());
////                            updateUI(null);
//                            Toast.makeText(login.this, "hello it failed", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }


    //Code to directly send user to home page once user has logged in the application

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
//            status=0;
//            Intent i = new Intent(login.this,profilefragment.class);
//            i.putExtra("STATUS",status);
//            startActivity(i);
        }
    }



//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == GOOGLE_SIGN_IN_CODE) {
//            Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount signInAcc =signInTask.getResult(ApiException.class);
//                AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(),null);
//                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(getApplicationContext(), "Your Google Account is Connected to our Application", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
//
//                Toast.makeText(this, "Your Google Account is Connected to our Application", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this,MainActivity.class));
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }}
}