package elvi.com.rapixtaxi.activities.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import elvi.com.rapixtaxi.R;

import elvi.com.rapixtaxi.activities.client.RegisterActivity;
import elvi.com.rapixtaxi.includes.MyToolbar;

import elvi.com.rapixtaxi.models.Driver;
import elvi.com.rapixtaxi.providers.AuthProvider;

import elvi.com.rapixtaxi.providers.DriverProvider;

public class RegisterDriverActivity extends AppCompatActivity {


    AuthProvider mAuthProvider;
    DriverProvider mDriverProvider;


    //instanciar
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputVehiculeBrand;
    TextInputEditText mTextInputVehiculePlate;



    // AlerDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);

        // boton atras
        MyToolbar.show(this, "REGISTROS DE CONDUCTOR", true);

        mAuthProvider = new AuthProvider();
        mDriverProvider = new DriverProvider();

        // llamados de botones
        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputVehiculeBrand = findViewById(R.id.textInputVehiculeBrand);
        mTextInputVehiculePlate = findViewById(R.id.textInputVehiculePlate);
        mTextInputPassword = findViewById(R.id.textInputPassword);


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegister();
            }
        });
    }

    void clickRegister() {
        String name = mTextInputName.getText().toString();
        String email = mTextInputEmail.getText().toString();
        String vehiculeBrand = mTextInputVehiculeBrand.getText().toString();
        String vehiculePlate = mTextInputVehiculePlate.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !vehiculeBrand.isEmpty() && !vehiculePlate.isEmpty()) {
            if (password.length() >= 6) {
                register(name, email, password, vehiculeBrand, vehiculePlate);


            } else {
                Toast.makeText(this, "La contraseña debe de tener minimo 6 caracteres", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void register(String name, String email, String password, String vehiculeBrand, String vehiculePlate){
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Driver driver = new Driver(id, name, email, vehiculeBrand, vehiculePlate);
                    create(driver);


                } else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    void create (Driver driver){
        mDriverProvider.create(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   // Toast.makeText(RegisterDriverActivity.this, "El Registro fue un exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterDriverActivity.this, MapDriverActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
