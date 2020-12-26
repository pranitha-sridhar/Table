package com.example.table;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterTitle extends RecyclerView.Adapter<AdapterTitle.holder> {
    List<cell> list;
    Context context;
    cell cells;

    public AdapterTitle(Context context, List<cell> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardcell, parent, false);
        AdapterTitle.holder vH = new AdapterTitle.holder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        cells = list.get(position);
        holder.editText.setText(cells.getData());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class holder extends RecyclerView.ViewHolder {
        EditText editText;

        public holder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.edit);


        }
    }
}