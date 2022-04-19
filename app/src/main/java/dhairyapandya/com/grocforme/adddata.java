package dhairyapandya.com.grocforme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity {
    EditText Name,Price,Quantity,purl,Grade,Wholeseller;
    Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        Name=(EditText)findViewById(R.id.Name);
        Price=(EditText)findViewById(R.id.Price);
        Quantity=(EditText)findViewById(R.id.Quantity);
        Grade=(EditText)findViewById(R.id.Grade);
        Wholeseller=(EditText)findViewById(R.id.Wholeseller);
        purl=(EditText)findViewById(R.id.purl);

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }
    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("Name",Name.getText().toString());
        map.put("Price",Price.getText().toString());
        map.put("Quantity",Quantity.getText().toString());
        map.put("Grade",Grade.getText().toString());
        map.put("Wholeseller",Wholeseller.getText().toString());
        map.put("purl",purl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Name.setText("");
                        Price.setText("");
                        Quantity.setText("");
                        Grade.setText("");
                        Quantity.setText("");
                        purl.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}