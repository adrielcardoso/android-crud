package adrielcardoso.com.br.aulaphoto;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    RangoDAO rangoDAO;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Banco(this);
        setContentView(R.layout.activity_main);
        rangoDAO = new RangoDAO(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_cart:
                // search action

                Intent create = new Intent(this, CreateActivity.class);

                startActivityForResult(create, 2);

                return true;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            mapFragment.getMapAsync(this);
        }
    }

    public void setListView(GoogleMap map)
    {

        Cursor data = rangoDAO.selectAll();

        if(data.getCount() != 0){

            data.moveToFirst();

            while (!data.isAfterLast()) {

                String lat = data.getString(data.getColumnIndexOrThrow(RangoDAO.lat));
                String log = data.getString(data.getColumnIndexOrThrow(RangoDAO.lon));
                String title = data.getString(data.getColumnIndexOrThrow(RangoDAO.descricao));

                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(Float.parseFloat(lat), Float.parseFloat(log)))
                        .title(title));
                data.moveToNext();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.i("CALL", "CHAMANDO");
        setListView(googleMap);
    }
}
