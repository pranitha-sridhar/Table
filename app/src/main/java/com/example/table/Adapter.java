package com.example.table;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLException;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    List<cell> list;
    Context context;
    String row,column;
    ConnectionClass connectionClass;
    String message,s;
    cell cells;
    private OnItemClickListener mlistener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mlistener = listener;
    }

    public Adapter(Context context,List<cell> list) {
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardcell, parent, false);
        Adapter.holder vH = new Adapter.holder(view,new MyTextWatcher(),mlistener);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        cells=list.get(position);
        holder.editText.setText(cells.getData());
        message=cells.getData();
        holder.myTextWatcher.updatePosition(holder.getAdapterPosition());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public class holder extends RecyclerView.ViewHolder {
        EditText editText;
        MyTextWatcher myTextWatcher;
        public holder(@NonNull View itemView, MyTextWatcher textWatcher,final OnItemClickListener listener) {
            super(itemView);
            editText=itemView.findViewById(R.id.edit);
            this.myTextWatcher=textWatcher;
            MyTextWatcher watcher=new MyTextWatcher();
            editText.addTextChangedListener(watcher);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        listener.onItemClick(position, v);
                    }
                }
            });
        }
    }

    public class MyTextWatcher implements TextWatcher {

        EditText editText;
        private int position;

        public MyTextWatcher() {

        }

        public void updatePosition(int position){
            this.position=position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            cells=list.get(position);
            s=charSequence.toString();

            cells.setData(s);

        }

        @Override
        public void afterTextChanged(Editable editable) {

            row=cells.getRow();
            column=cells.getColumn();
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Table");
            //cell cell=new cell(s,row,column);
            //String childid=databaseReference.push().getKey();
            databaseReference.child(row).child(column).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
