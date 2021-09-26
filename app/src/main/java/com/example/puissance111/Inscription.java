package com.example.puissance111;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Inscription extends AppCompatActivity {

    EditText editNom, editPrenom, editMdp, editEmail, editDate;
    Button bouton_inscrire;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;


    private View.OnClickListener bouton_inscrire_listener = new View.OnClickListener() {

        public void onClick(View v) {


            String email = editEmail.getText().toString();
            String password = editMdp.getText().toString();


            if (TextUtils.isEmpty(email))
            {
                editEmail.setError("Il faut renseigner l'adresse e-mail");
                return;
            }
            createAccount(email,password);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        editNom = findViewById(R.id.editNom);
        editPrenom = findViewById(R.id.editPrenom);
        editMdp = findViewById(R.id.editMdp);
        editEmail = findViewById(R.id.editEmail);
        editDate = findViewById(R.id.editDate);
        bouton_inscrire = findViewById(R.id.buttonInscrire);
        bouton_inscrire.setOnClickListener(bouton_inscrire_listener);




    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Inscription.this, "Inscription reussie", Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Inscription.this, "Erreur - Le compte n'a pas été créé", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }


    private void updateUI(FirebaseUser user) {

    }

}



/* Pour Victor, dans la principale

    public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }
 */