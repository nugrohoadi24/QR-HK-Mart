package com.example.skripsiadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skripsiadi.databinding.ActivityBayarBinding;
import com.example.skripsiadi.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BayarActivity extends AppCompatActivity {
    private static String TAG = BayarActivity.class.getSimpleName();

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private ActivityBayarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBayarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            final DataSiswa dataSiswa = (DataSiswa) getIntent().getParcelableExtra("user");

            db.child("dataTemporary").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final String idTrans = dataSnapshot.getChildren().iterator().next().getKey();
                    dataSnapshot.getRef()
                            .child(idTrans)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                                    final ArrayList<String> idItem = new ArrayList<>();
                                    final ArrayList<DataTemporary> temporaries = new ArrayList<>();
                                    int num = 0;
                                    while (iterator.hasNext()) {
                                        num++;
                                        DataSnapshot temp = iterator.next();
                                        idItem.add(temp.getKey());
                                        DataTemporary temporary = temp.getValue(DataTemporary.class);
                                        if (temporary != null) {
                                            temporaries.add(temporary);
                                            showDataTemp(num, temporary);
                                        }
                                    }

                                    int total = 0;
                                    // ini for each
                                    for (DataTemporary t : temporaries) {
                                        total += t.TOTALITEM;
                                    }
                                    binding.TVTotal.setText(String.valueOf(total));

                                    binding.BTNBayar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            HashMap<String, Object> map = new HashMap<>();
                                            for (int i = 0; i < temporaries.size(); i++) {
                                                map.put(idItem.get(i), temporaries.get(i));
                                            }

                                            updateDataTransaksi(dataSiswa, map, idTrans);
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void showDataTemp(int number, DataTemporary dataTemporary) {

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        row.setWeightSum(1f);

        TextView TVNomor = new TextView((this));
        TVNomor.setText(String.valueOf(number));

        TextView tvNama = new TextView(this);
        tvNama.setText(dataTemporary.BRAND);

        TextView TVTampilID = new TextView(this);
        TVTampilID.setText(String.valueOf(dataTemporary.NETSALES));

        TextView TVTampilAlamat = new TextView(this);
        TVTampilAlamat.setText(dataTemporary.QTY_TERJUAL);

        TextView TVTampilSaldo = new TextView(this);
        TVTampilSaldo.setText(String.valueOf(dataTemporary.TOTALITEM));

        row.addView(TVNomor);
        row.addView(tvNama);
        row.addView(TVTampilID);
        row.addView(TVTampilAlamat);
        row.addView(TVTampilSaldo);
        binding.TLDataTemporary.addView(row);

    }

    private void updateDataTransaksi(final DataSiswa dataSiswa, final Map<String, Object> map, final String idTrans) {
        db.child("dataTransaksiQR")
                .child(dataSiswa.ID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, Object> transaksi = new HashMap<>();
                        transaksi.put(idTrans, map);

                        if (dataSnapshot.exists()) {
                            dataSnapshot.getRef().updateChildren(transaksi);
                        } else {
                            dataSnapshot.getRef().setValue(transaksi);
                        }
                        Log.d(TAG,"exists?"+dataSnapshot.exists());
                        Log.d(TAG,"exists?"+dataSnapshot.getRef().toString());

                        db.child("dataSiswa")
                                .orderByChild("ID")
                                .equalTo(dataSiswa.ID)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        dataSiswa.SALDO = String.valueOf(Integer.parseInt(dataSiswa.SALDO) - Integer.parseInt(binding.TVTotal.getText().toString()));
                                        HashMap<String, Object> mapSiswa = new HashMap<>();
                                        mapSiswa.put(dataSnapshot.getChildren().iterator().next().getKey(), dataSiswa);
                                        db.child("dataSiswa")
                                                .orderByChild("ID")
                                                .equalTo(dataSiswa.ID)
                                                .getRef().updateChildren(mapSiswa);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                        Toast.makeText(BayarActivity.this, "updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
