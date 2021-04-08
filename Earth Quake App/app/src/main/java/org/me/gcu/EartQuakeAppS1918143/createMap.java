/*
Earth Quake App
Norbert Bednarski
s1918143
 */

package org.me.gcu.EartQuakeAppS1918143;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class createMap extends AppCompatActivity implements OnMapReadyCallback {
    private static createMap instance;
    private GoogleMap mMap;

    //Method called when the map is ready to be used.
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        instance = this;
    }



    //Add the marker to the map.
    public void addMarker(float Latitude, float Longitude, double magnitude, float color){
        LatLng pin = new LatLng(Latitude, Longitude);

        mMap.addMarker(new MarkerOptions()
                .position(pin)
                .icon(BitmapDescriptorFactory.defaultMarker(color))
                .title(String.valueOf(magnitude)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pin, 6));


    }
    //Zooms to the marker selected.
    public void zoomMarker(float Latitude, float Longitude){
        LatLng pin = new LatLng(Latitude, Longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pin, 10));


    }
    public static createMap getInstance(){
        return instance;
    }
}
