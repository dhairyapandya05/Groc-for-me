package dhairyapandya.com.grocforme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {
        holder.Name.setText(model.getName());
        holder.Price.setText(model.getPrice());
        holder.Quantity.setText(model.getQuantity());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
        holder.Grade.setText(model.getGrade());
        holder.Wholeseller.setText(model.getWholeseller());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1550)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText purl=myview.findViewById(R.id.uimgurl);
                final EditText Name=myview.findViewById(R.id.uname);
                final EditText Price=myview.findViewById(R.id.uPrice);
                final EditText Quantity=myview.findViewById(R.id.uquantity);
                final EditText Grade=myview.findViewById(R.id.Graded);
                final EditText Wholeseller=myview.findViewById(R.id.Wholesellerd);
                Button submit=myview.findViewById(R.id.usubmit);

                purl.setText(model.getPurl());
                Name.setText(model.getName());
                Price.setText(model.getPrice());
                Quantity.setText(model.getQuantity());
                Grade.setText(model.getGrade());
                Wholeseller.setText(model.getWholeseller());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("purl",purl.getText().toString());
                        map.put("Name",Name.getText().toString());
                        map.put("Price",Price.getText().toString());
                        map.put("Quantity",Quantity.getText().toString());
                        map.put("Grade",Grade.getText().toString());
                        map.put("Wholeseller",Wholeseller.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(holder.getAdapterPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });
//
//
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView Name,Price,Quantity,Grade,Wholeseller;
        ImageView edit,delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            Name=(TextView)itemView.findViewById(R.id.nametext);
            Price=(TextView)itemView.findViewById(R.id.coursetext);
            Quantity=(TextView)itemView.findViewById(R.id.emailtext);
            Grade=(TextView)itemView.findViewById(R.id.gradeno);
            Wholeseller=(TextView)itemView.findViewById(R.id.wholesalername);
            edit=(ImageView)itemView.findViewById(R.id.edit);
            delete=(ImageView)itemView.findViewById(R.id.delete);

        }
    }
}
