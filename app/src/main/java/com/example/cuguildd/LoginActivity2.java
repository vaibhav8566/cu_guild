package com.example.cuguildd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity2 extends AppCompatActivity {
    Button mLoginBtn;
    EditText mEmail,mPassword;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mEmail=findViewById(R.id.giveEmail);
        mPassword=findViewById(R.id.givepassword);
        progressBar=findViewById(R.id.progressBar1);
        fAuth=FirebaseAuth.getInstance();
        mLoginBtn=findViewById(R.id.login);
        mCreateBtn=findViewById(R.id.signuppage);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    mPassword.setError("Password should have atleast 6 letters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //authenticating user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(fAuth.getCurrentUser().isEmailVerified()){
                                Toast.makeText(LoginActivity2.this, "Going in", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                            }else{
                                Toast.makeText(LoginActivity2.this, "Please click on the verification link sent to you", Toast.LENGTH_LONG).show();
                            }



                        }else{
                            Toast.makeText(LoginActivity2.this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}