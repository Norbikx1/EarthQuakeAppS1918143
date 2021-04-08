
/*
Earth Quake App
Norbert Bednarski
s1918143
 */

package org.me.gcu.EartQuakeAppS1918143;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {



    private String result = "";
    //Url to RSS Feed
    private String URL = "http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";

    ArrayList<EarthQuake> arrayOfQuakes = null;

    private ListView quakesList;
    private earthQuakesAdapter eAdapter;

    private static MainActivity instance;
    private GoogleMap mMap;
    boolean date;




    @Override

    //On create method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadXML downloadXML = new DownloadXML();

        downloadXML.execute();


        //Reference to ListView
        quakesList = (ListView)findViewById(R.id.EarthQuakeListView);

        //Get Reference to date from and date to
        TextView dateFromTxt = (TextView)findViewById(R.id.dateFromTxt);
        TextView dateToTxt = (TextView)findViewById(R.id.dateToTxt);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new createMap());

        instance = this;



        dateFromTxt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){


                date= false;
                DialogFragment timePicker = new dateRangeFragment();
                timePicker.show(getSupportFragmentManager(), "date picker");


            }




            });




        dateToTxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                date= true;
                DialogFragment timePicker = new dateRangeFragment();
                timePicker.show(getSupportFragmentManager(), "date picker");

            }



        });


    } // End of onCreate



    public void onDateSet(DatePicker view, int year, int month, int day ) {

        if (date == false){

            TextView dateFromTxt = (TextView) findViewById(R.id.dateFromTxt);
            String strDate;


            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            Date date = c.getTime();

            SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
            strDate = formatDate.format(date);


            dateFromTxt.setText(strDate);




        }

        if (date == true){

            TextView dateToTxt = (TextView) findViewById(R.id.dateToTxt);
            String strDate;


            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            Date date = c.getTime();

            SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
            strDate = formatDate.format(date);


            dateToTxt.setText(strDate);

        }

    }


    //Method that sets an adapter to a view.
    public void setAdapter(ArrayList<EarthQuake> arrayOfQuakes){

        eAdapter = new earthQuakesAdapter(MainActivity.this, -1, arrayOfQuakes );

        //After execution it calls the adapter to display the data
        quakesList.setAdapter(eAdapter);
    }



    //Download the XML file to allows extract the data from it.
    private class DownloadXML extends AsyncTask<Void, Void, Void> {

        //Downlaods the xml file in background
        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                GetXML.GetXMLFromURL("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml", openFileOutput("MhSeismology.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        //After the file is ready pull parse the details from it and add to an array.
        @Override
        public void onPostExecute(Void result){
            //creates array of earth quakes from the xml file.
            arrayOfQuakes = (ArrayList<EarthQuake>)EarthQuakesXmlPullParser.getEarthQuakesFromFile(MainActivity.this);
            //set the adapter and link it to the list view.
            setAdapter(arrayOfQuakes);

            Log.i("EarthQuakes", "adapter size = "+ eAdapter.getCount());

            //Link the spinner
            Spinner spinnerChoice = (Spinner) findViewById(R.id.spinner);

            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new spinnerSort (arrayOfQuakes, findViewById(R.id.dateFromTxt), findViewById(R.id.dateToTxt)));
            //spinnerChoice.setOnItemSelectedListener(new spinnerSort(arrayOfQuakes));

        }


    }




    //Return the instance on MainActivity
    public static MainActivity getInstance(){
        return instance;
    }
}