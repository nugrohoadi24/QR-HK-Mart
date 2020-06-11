package com.example.skripsiadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.skripsiadi.databinding.ActivityHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            final DataSiswa dataSiswa = (DataSiswa) getIntent().getParcelableExtra("user");

            if (dataSiswa!=null) {
                binding.TVTampilNama.setText(dataSiswa.NAMA);
                binding.TVTampilID.setText(dataSiswa.ID);
                binding.TVTampilAlamat.setText(dataSiswa.ALAMAT);
                binding.TVTampilSaldo.setText(dataSiswa.SALDO);

            binding.BTNRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RiwayatActivity.class);
                intent.putExtra("user", dataSiswa);
                startActivity(intent);
                }
            });

            binding.BTNBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BayarActivity.class);
                intent.putExtra("user", dataSiswa);
                startActivity(intent);
            }
        });

            }
        }
    }


}
