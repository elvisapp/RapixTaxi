package elvi.com.rapixtaxi.activities.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import elvi.com.rapixtaxi.R;
import elvi.com.rapixtaxi.activities.MainActivity;
import elvi.com.rapixtaxi.providers.AuthProvider;

public class MapClientActivity extends AppCompatActivity {

    Button mButtonLogout;

    AuthProvider mAuthProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_client);

        mButtonLogout = findViewById(R.id.btnLogout);
        mAuthProvider = new AuthProvider();

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mAuthProvider.logout();
                Intent intent = new Intent( MapClientActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}