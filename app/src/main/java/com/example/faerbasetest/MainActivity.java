package com.example.faerbasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText password, email;
    private Button avtor, regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.password);
        email = findViewById(R.id.mail);

        avtor = findViewById(R.id.avtor);
        regist = findViewById(R.id.registor);

        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                }else {

                }
            }
        };

        avtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signing(email.getText().toString(),password.getText().toString());
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration(email.getText().toString(),password.getText().toString());
            }
        }); 

    }
    private void Signing(final String email, String password){
      mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()) {
                  Toast.makeText(MainActivity.this, "Авторизация успешна", Toast.LENGTH_LONG).show();
              }else {
                  Toast.makeText(MainActivity.this, "Авторизация провалена", Toast.LENGTH_LONG).show();
              }
          }
      });
    }
    private void Registration(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Регистрация успешна", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Регистрация провалена", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
