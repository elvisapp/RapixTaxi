package elvi.com.rapixtaxi.activities.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import elvi.com.rapixtaxi.R;
import elvi.com.rapixtaxi.activities.MainActivity;
import elvi.com.rapixtaxi.activities.client.MapClientActivity;
import elvi.com.rapixtaxi.providers.AuthProvider;

public class MapDriverActivity extends AppCompatActivity implements OnMapReadyCallback {
    private  GoogleMap mMap;
    private  SupportMapFragment mMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);

    mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mMapFragment.getMapAsync( this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}




