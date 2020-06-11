package com.example.skripsiadi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PINActivity extends AppCompatActivity {
    private static String TAG = PINActivity.class.getSimpleName();

    TextView tvUsername;
    EditText etPassword;
    FirebaseAuth mFirebaseAuth;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_i_n);
        tvUsername = findViewById(R.id.TV_username);
        etPassword = findViewById(R.id.ET_password);
        mFirebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("dataSiswa");

        tvUsername.setText(getIntent().getStringExtra("hasilscan"));

        final String ID = tvUsername.getText().toString();
        final String PASSWORD = etPassword.getText().toString();

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Method ini digunakan untuk awalan suatu text, akan di baca atau dideteksi oleh TextWatcher
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Method ini digunakan untuk pemrosesan, suatu text sedang di baca atau dideteksi oleh TextWatcher.

            }

            @Override
            public void afterTextChanged(final Editable s) {
                //Method ini digunakan untuk akhiran ,ketika text telah berhasil di baca atau dideteksi oleh TextWatcher.
                if (s.toString().length() >= 6) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("dataSiswa")
                            .orderByChild("ID")
                            .equalTo(ID)
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Log.d(TAG, "Data: " + dataSnapshot.getChildren().iterator().next());
                                    Log.d(TAG, "Data (no iterator): " + dataSnapshot);
                                    DataSiswa dataSiswa = dataSnapshot.getChildren().iterator().next().getValue(DataSiswa.class);

                                    if (dataSiswa != null) {
                                        if (s.toString().equals(dataSiswa.PASSWORD)) {
                                            Toast.makeText(PINActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(PINActivity.this, HomeActivity.class);
                                            intent.putExtra("user", dataSiswa);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(PINActivity.this, "PIN Anda Salah !", Toast.LENGTH_SHORT).show();
                                        }

                                        Log.d(TAG, "Typed password: " + s.toString());
                                        Log.d(TAG, "Expected: " + dataSiswa.PASSWORD);
                                    } else {
                                        Toast.makeText(PINActivity.this, "Tidak Ada Data !", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                } else {
                }
            }
        });
    }
}
