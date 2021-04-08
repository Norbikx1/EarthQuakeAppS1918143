/*
Earth Quake App
Norbert Bednarski
s1918143
 */
package org.me.gcu.EartQuakeAppS1918143;

import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


//Custom Adapter with the purpose to store the list of earth quakes after parsing them from xml file.
public class earthQuakesAdapter extends ArrayAdapter<EarthQuake> {



    public earthQuakesAdapter(Context ctx, int textViewResourceId, List<EarthQuake> quakes) {
        super(ctx, textViewResourceId, quakes);



    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        ConstraintLayout row = (ConstraintLayout)convertView;
        MainActivity mainActivity = MainActivity.getInstance();
        Log.i("EarthQuakes", "getView pos = " + pos);
        if(null == row){
            //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (ConstraintLayout)inflater.inflate(R.layout.earthquakeslayout, null);
        }

        //Get our View References

        TextView locationTxt = (TextView)row.findViewById(R.id.locationTxt);
        TextView magTxt = (TextView)row.findViewById(R.id.magTxt);
        TextView linkTxt = (TextView)row.findViewById(R.id.linkTxt);
        TextView descriptionTxt = (TextView)row.findViewById(R.id.descriptionTxt);
        TextView pubDateTxt = (TextView)row.findViewById(R.id.pubDateTxt);
        TextView latitudeTxt = (TextView)row.findViewById(R.id.latitudeTxt);
        TextView longitudeTxt = (TextView)row.findViewById(R.id.longitudeTxt);

        float latitude = Float.parseFloat(getItem(pos).getLatitude());
        float longitude = Float.parseFloat(getItem(pos).getLongitude());


        //Reference the buttons
        Button showDetails = row.findViewById(R.id.showDetails);
        Button mapBtn = row.findViewById(R.id.mapBtn);

        //Get the title to extract the magnitude
        String title = getItem(pos).getTitle();
        //Extract the magnitude from title
        String subMgt = title.substring(25,28);

        //Variable to store the magnitde of earth quake
        double magnitude = Double.parseDouble(subMgt);

        String descStr = getItem(pos).getDescription();
        String[] strArray = descStr.split(";");


        locationTxt.setText(strArray[1].substring(1));
        magTxt.setText("Magnitude: " + magnitude);


        //Set the colour of strength
        if(magnitude < 1){

            magTxt.setTextColor(Color.GREEN);
        }
        if(magnitude > 1){

            magTxt.setTextColor(Color.rgb(235, 155, 50));
        }
        if(magnitude > 3){

            magTxt.setTextColor(Color.RED);
        }


        //Plot the markers on the map.
        float color = 0;
        if(magnitude < 1){

            color = BitmapDescriptorFactory.HUE_GREEN;
        }
        if(magnitude > 1){

            color = BitmapDescriptorFactory.HUE_ORANGE;
        }
        if(magnitude > 3){

            color = BitmapDescriptorFactory.HUE_RED;
        }


        //Create marker on the map
        createMap.getInstance().addMarker(latitude, longitude , magnitude, color );

        //Set on click listener for showDetails button.
        showDetails.setOnClickListener(new View.OnClickListener() {

            int buttonPress = 1;
        public void onClick(View v){
            // If the button is pressed shows more details
                if(buttonPress == 1){
                    descriptionTxt.setText("Description: "+ getItem(pos).getDescription());
                    linkTxt.setText("Link: " + getItem(pos).getLink());
                    pubDateTxt.setText(" Publication Date: "+ getItem(pos).getPubDate());
                    latitudeTxt.setText("Latitude: " + getItem(pos).getLatitude());
                    longitudeTxt.setText("Longitude: " + getItem(pos).getLongitude());

                    buttonPress = 2;
                    descriptionTxt.setVisibility(View.VISIBLE);
                    linkTxt.setVisibility(View.VISIBLE);
                    pubDateTxt.setVisibility(View.VISIBLE);
                    latitudeTxt.setVisibility(View.VISIBLE);
                    longitudeTxt.setVisibility(View.VISIBLE);

                    showDetails.setText("Hide Details");
            }

            //If the button is pressed second time it hides the details
            else{
                buttonPress = 1;
                showDetails.setText("Show Details");
                descriptionTxt.setVisibility(View.GONE);
                linkTxt.setVisibility(View.GONE);
                pubDateTxt.setVisibility(View.GONE);
                latitudeTxt.setVisibility(View.GONE);
                longitudeTxt.setVisibility(View.GONE);

            }
        }
        });


        //Zoom marker click method.
        mapBtn.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                createMap.getInstance().zoomMarker(latitude, longitude);


            }
        });


        return row;

    }
}