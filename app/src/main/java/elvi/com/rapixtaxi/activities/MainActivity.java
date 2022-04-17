package elvi.com.rapixtaxi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import elvi.com.rapixtaxi.R;
import elvi.com.rapixtaxi.activities.client.MapClientActivity;
import elvi.com.rapixtaxi.activities.driver.MapDriverActivity;

public class MainActivity extends AppCompatActivity {

    Button mButtonIACliente;
    Button mButtonIADriver;

    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();

        mButtonIACliente = findViewById(R.id.btnIACliente);
        mButtonIADriver = findViewById(R.id.btnIAnDriver);


        mButtonIACliente.setOnClickListener((view) ->{
            editor.putString( "user", "client");
            editor.apply();
            goToSelectAuth();

        });

        mButtonIADriver.setOnClickListener((view) ->{
            editor.putString( "user", "driver");
            editor.apply();
            goToSelectAuth();

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
           String user = mPref.getString( "user", "");
            if (user.equals("client")){
                Intent intent = new Intent(MainActivity.this, MapClientActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(MainActivity.this, MapDriverActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}