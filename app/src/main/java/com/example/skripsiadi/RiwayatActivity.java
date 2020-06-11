package com.example.skripsiadi;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skripsiadi.databinding.ActivityRiwayatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity {
    private static String TAG = RiwayatActivity.class.getSimpleName();

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private ActivityRiwayatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRiwayatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            final DataSiswa dataSiswa = (DataSiswa) getIntent().getParcelableExtra("user");
            fetchTransactionData(dataSiswa.ID);
        }
    }

    private void fetchTransactionData(String idSiswa) {
        // getting available transaction id by id siswa
        db.child("dataTransaksiQR")
                .child(idSiswa)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final ArrayList<DataTransaksiQR> transactions = new ArrayList<>();
                        // get transaction
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // transaction id
                            String idTrans = snapshot.getKey();
                            Log.d(TAG, "transaction id: " + idTrans);

                            // get item
                            for (DataSnapshot item: snapshot.getChildren()) {
                                DataTransaksiQR transaksiQR = item.getValue(DataTransaksiQR.class);
                                Log.d(TAG, "items: " + transaksiQR.toString());
                                transactions.add(transaksiQR);
                            }
                        }
                        showDataTrans(transactions);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void showDataTrans(List<DataTransaksiQR> transactions) {
        for (int index = 0; index < transactions.size(); index++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            row.setWeightSum(1f);

            TextView TVNomor = new TextView((this));
            TVNomor.setText(String.valueOf(transactions.get(0).IDTRANSAKSI));

            TextView tvNama = new TextView(this);
            tvNama.setText(transactions.get(index).BRAND);

            TextView TVTampilID = new TextView(this);
            TVTampilID.setText(String.valueOf(transactions.get(index).NETSALES));

            TextView TVTampilAlamat = new TextView(this);
            TVTampilAlamat.setText(transactions.get(index).QTY_TERJUAL);

            TextView TVTampilSaldo = new TextView(this);
            TVTampilSaldo.setText(String.valueOf(transactions.get(index).TOTALITEM));

            row.addView(TVNomor);
            row.addView(tvNama);
            row.addView(TVTampilID);
            row.addView(TVTampilAlamat);
            row.addView(TVTampilSaldo);
            binding.TLDataTransaksi.addView(row);
        }
    }
}
