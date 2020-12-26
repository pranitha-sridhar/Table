package com.example.table;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TableLayout stk;
//ConnectionClass conn;
List<cell> cells;
int rows=2,column=2,height=50;
Button addr,addc,save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        addr=findViewById(R.id.addrow);
        addc=findViewById(R.id.addcol);
        save=findViewById(R.id.save);
        final RecyclerView recyclerView=findViewById(R.id.recycle1);

        //conn=new ConnectionClass();



        cells=new ArrayList<>();
       cells.add(new cell("","0","0"));
        for(int i=1;i<=(column);i++){
            cells.add(new cell("Column "+i,"0",""+i+1));
        }

        for(int i=1;i<=(rows)*(column+1);i++){
            if(i%(column+1)==1)cells.add(new cell(""+(i/(column+1)+1),""+i+1,"0"));
            else if(i%(column+1)==0)cells.add(new cell("",""+i+1,""+column+1));
           else cells.add(new cell("",""+i/(column+1)+1,""+i%(column+1)));
        }
        AdapterTitle adapter=new AdapterTitle(getApplicationContext(),cells);
        recyclerView.setLayoutManager(new GridLayoutManager(this,column+1));
        recyclerView.setAdapter(adapter);

        /*cells.clear();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Table");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String row=  ds.getKey();
                    String column= ds.child(row).getKey();
                    String msg= (String) ds.child(row).child(column).getValue();
                    cells.add(new cell(msg,row,column));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Adapter adapter1=new Adapter(getApplicationContext(),cells);
        recyclerView.setLayoutManager(new GridLayoutManager(this,column+1));
        recyclerView.setAdapter(adapter1);
       /* adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                cell cell=cells.get(position);
                int row=cell.getRow();
                int column=cell.getColumn();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Table");
                String s=cell.getData();
                databaseReference.child(String.valueOf(row)).child(String.valueOf(column)).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rows++;
                cells.add(new cell(""+rows,"1","1"));
                for(int i=1;i<=column;i++){
                    cells.add(new cell("",""+i/(column+1)+1,"1"));
                }
                Adapter adapter=new Adapter(getApplicationContext(),cells);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),column+1));
                recyclerView.setAdapter(adapter);
            }
        });

        addc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column++;
                cells.clear();
                cells.add(new cell("","1","1"));
                for(int i=1;i<=(column);i++){
                    cells.add(new cell("Column "+i,"1","1"));
                }

                for(int i=1;i<=(rows)*(column+1);i++){
                    if(i%(column+1)==1)cells.add(new cell(""+(i/(column+1)+1),"1","1"));
                    else cells.add(new cell("",""+i/(column+1)+1,"1"));
                }
                Adapter adapter=new Adapter(getApplicationContext(),cells);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),column+1));
                recyclerView.setAdapter(adapter);
            }
        });


        /*stk = (TableLayout) findViewById(R.id.tablemain);
        TableRow tbrow = new TableRow(this);
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.title_cell);
        textView.setLayoutParams(new TableRow.LayoutParams(80, 100));
        tbrow.addView(textView);
        for(int j=1;j<=column;j++){
            TextView tvi = new TextView(this);
            tvi.setText("Column "+j);
            tvi.setLayoutParams(new TableRow.LayoutParams(250, 100));
            tvi.setBackgroundResource(R.drawable.title_cell);
            tbrow.addView(tvi);
        }
        stk.addView(tbrow);
        for(int i=1;i<=rows;i++){
            TableRow tbrowi = new TableRow(this);
            TextView tv = new TextView(this);
            tv.setText("  "+i);
            tv.setBackgroundResource(R.drawable.title_cell);
            tv.setLayoutParams(new TableRow.LayoutParams(80, 100));
            tbrowi.addView(tv);
            for(int j=1;j<=column;j++){
                EditText tvi = new EditText(this);
                tvi.setBackgroundResource(R.drawable.cell);
                tvi.setLayoutParams(new TableRow.LayoutParams(250, 100));
                tbrowi.addView(tvi);
            }
            stk.addView(tbrowi);
        }

        addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rows++;
                    TableRow tbrowi = new TableRow(getApplicationContext());
                    TextView tv = new TextView(getApplicationContext());
                    tv.setText("  " + rows);
                    tv.setBackgroundResource(R.drawable.title_cell);
                    tv.setLayoutParams(new TableRow.LayoutParams(80, 100));
                    tbrowi.addView(tv);
                    for (int j = 1; j <= column; j++) {
                        EditText tvi = new EditText(getApplicationContext());
                        tvi.setBackgroundResource(R.drawable.cell);
                        tvi.setLayoutParams(new TableRow.LayoutParams(250, 100));
                        tbrowi.addView(tvi);
                    }
                    stk.addView(tbrowi);
                }

        });
        addc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                column++;
                    stk.removeAllViewsInLayout();
                TableRow tbrow = new TableRow(getApplicationContext());
                TextView textView = new TextView(getApplicationContext());
                textView.setBackgroundResource(R.drawable.title_cell);
                textView.setLayoutParams(new TableRow.LayoutParams(80, 100));
                tbrow.addView(textView);
                for(int j=1;j<=column;j++){
                    TextView tvi = new TextView(getApplicationContext());
                    tvi.setText("Column "+j);
                    tvi.setLayoutParams(new TableRow.LayoutParams(250, 100));
                    tvi.setBackgroundResource(R.drawable.title_cell);
                    tbrow.addView(tvi);
                }
                stk.addView(tbrow);
                for(int i=1;i<=rows;i++){
                    TableRow tbrowi = new TableRow(getApplicationContext());
                    TextView tv = new TextView(getApplicationContext());
                    tv.setText("  "+i);
                    tv.setBackgroundResource(R.drawable.title_cell);
                    tv.setLayoutParams(new TableRow.LayoutParams(80, 100));
                    tbrowi.addView(tv);
                    for(int j=1;j<=column;j++){
                        EditText tvi = new EditText(getApplicationContext());
                        tvi.setBackgroundResource(R.drawable.cell);
                        tvi.setLayoutParams(new TableRow.LayoutParams(250, 100));
                        tbrowi.addView(tvi);
                    }
                    stk.addView(tbrowi);
                }
            }
        });
        <ScrollView
        android:id="@+id/scrollView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="15dp">

        <HorizontalScrollView
            android:id="@+id/horiscroll"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <LinearLayout
                android:id="@+id/layout"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">

                <TableLayout
                    android:id="@+id/tablemain"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />

            </LinearLayout>
        </HorizontalScrollView>

    </ScrollView>
         */
    }


}