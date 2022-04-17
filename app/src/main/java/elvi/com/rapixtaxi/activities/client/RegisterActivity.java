package elvi.com.rapixtaxi.activities.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import elvi.com.rapixtaxi.activities.driver.MapDriverActivity;
import elvi.com.rapixtaxi.activities.driver.RegisterDriverActivity;
import elvi.com.rapixtaxi.includes.MyToolbar;
import elvi.com.rapixtaxi.models.Client;
import elvi.com.rapixtaxi.providers.AuthProvider;
import elvi.com.rapixtaxi.providers.ClientProvider;

public class RegisterActivity extends AppCompatActivity {

    //SharedPreferences mPref;
    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;


    //instanciar
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;

 // AlerDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // boton atras
        MyToolbar.show(this, "REGISTROS DE USUARIOS", true);

        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();



        //mensajes en la pantalla
       // mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);



        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
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
        String password = mTextInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if (password.length() >= 6) {
                register(name, email, password);


            } else {
                Toast.makeText(this, "La contraseña debe de tener minimo 6 caracteres", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void register(String name, String email, String password){
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Client client = new Client(id, name, email);
                    create(client);


                } else {
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    void create (Client client){
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful()){
                     Intent intent = new Intent(RegisterActivity.this, MapClientActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(RegisterActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
    /*
    void saveUser(String id, String name, String email) {
        String selecteUser = mPref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        // estructura de almacenamiento
        if (selecteUser.equals("driver")) {
            mDatabase.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else if (selecteUser.equals("client")) {
            mDatabase.child("User").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
   */
}

