package com.example.puissance111;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Connexion extends AppCompatActivity {

    EditText editPassword, editMail;
    Button bouton_connexion;

    private View.OnClickListener bouton_connexion_listener = new View.OnClickListener() {

        public void onClick(View v) {


            String email = editMail.getText().toString();
            String password = editPassword.getText().toString();


            if (TextUtils.isEmpty(email))
            {
                editMail.setError("Il faut renseigner l'adresse e-mail");
                return;
            }
            signIn(email,password);
        }
    };

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        editMail = findViewById(R.id.editMail);
        editPassword = findViewById(R.id.editPassword);
        bouton_connexion = findViewById(R.id.buttonConnexion);
        bouton_connexion.setOnClickListener(bouton_connexion_listener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Connexion.this, "Connecté", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Connexion.this, "Erreur - Mot de passe ou identifiant incorrect.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void reload() {

    }

    private void updateUI(FirebaseUser user) {

    }
}