package msd.com.vaccinebook;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    private static final int EDIT_REQUEST = 1;
    private GoogleMap mMap;
    public static String m_markerMapTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();

        //LatLng klalit = new LatLng(m_Latitude, m_Longitude);


        LatLng klalit = new LatLng(32.0768983, 34.7784425);
        LatLng maccabi = new LatLng(31.9097676, 34.8054703);
        LatLng mauchedet = new LatLng(32.0232222, 34.7736739);

//        mGoogleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.addMarker(new MarkerOptions()
                .position(klalit)
                .title("כללית-אהרונוב")
                .snippet("תל אביב, זמנהוף 42 טל. 03-6214100")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.syr1)));

        mMap.addMarker(new MarkerOptions()
                .position(maccabi)
                .title("מכבי-מרכז המכונים")
                .snippet("רחובות, שושן אריה 1 טל. 08-6866700")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.syr1)));

        mMap.addMarker(new MarkerOptions()
                .position(mauchedet)
                .title("מאוחדת-שירות אחיות")
                .snippet("חולון, שד' קוגל 45 טל. 03-5021414")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.syr1)));

        mMap.setOnInfoWindowClickListener(this);


//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                // Getting view from the layout file info_window_layout
//                View v = getLayoutInflater().inflate(R.layout.activity_maps, null);
//
//                TextView title = (TextView) v.findViewById(R.id.title_view);
//                title.setText(getTitle());
//
//                return false;
//            }
//        });


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String markerTitle = marker.getTitle();
        String markerSnippet = marker.getSnippet();

//        Intent mapsDataIntent=new Intent(getApplicationContext(),AddVaccineActivity.class);
//        mapsDataIntent.putExtra("markerTitle", markerTitle);
//        mapsDataIntent.putExtra("markerSnippet", markerSnippet);
//        mapsDataIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(mapsDataIntent);


//        Intent markerDataIntent = new Intent(this, AddVaccineActivity.class);
//        markerDataIntent.putExtra("markerTitle", markerTitle);
//        markerDataIntent.putExtra("markerSnippet", markerSnippet);
//        startActivity(markerDataIntent);
//        Toast.makeText(this, "Info window clicked",
//        Toast.LENGTH_SHORT).show();
    }




    public void setUpMap() {

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    void getCurrentLocation()
    {
        Location myLocation  = mMap.getMyLocation();
        if(myLocation!=null)
        {
            double dLatitude = myLocation.getLatitude();
            double dLongitude = myLocation.getLongitude();
            mMap.addMarker(new MarkerOptions().position(new LatLng(dLatitude, dLongitude))
                    .title("My Location").icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 8));

        }
        else
        {
            Toast.makeText(this, "Unable to fetch the current location", Toast.LENGTH_SHORT).show();
        }
    }



/////////////////////////////////////////////////////////
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//    /////////////////////////////////////////////////////////////////////////
}
