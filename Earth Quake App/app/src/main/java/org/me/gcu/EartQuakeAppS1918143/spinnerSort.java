
/*
Earth Quake App
Norbert Bednarski
s1918143
 */

package org.me.gcu.EartQuakeAppS1918143;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class spinnerSort implements AdapterView.OnItemSelectedListener {



private ArrayList<EarthQuake> arrayOfQuakes;
private TextView DateFrom;
private TextView DateTo;

    public spinnerSort(ArrayList<EarthQuake> ArrayOfQuakes, TextView dateFromTxt, TextView dateToTxt) {arrayOfQuakes = ArrayOfQuakes;
    DateFrom = dateFromTxt ;  DateTo = dateToTxt; }


    //Check which option has been selected from the spinner view and then sorts the array using a comparator.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Show all earth quakes
        if(position == 0){



        }
        //Show the most southern earth quakes
        if(position == 1){


            Collections.sort(arrayOfQuakes, EarthQuake.latComparator);
            Collections.reverse(arrayOfQuakes);
        }
        //Show the most northern earth quakes
        if(position == 2){
            Collections.sort(arrayOfQuakes, EarthQuake.latComparator);
        }
        //Show the most Eastern earth quakes
        if(position == 3){


            Collections.sort(arrayOfQuakes, EarthQuake.longComparator);
        }
        //Show the most western earth quakes
        if(position == 4){
            Collections.sort(arrayOfQuakes, EarthQuake.longComparator);
            Collections.reverse(arrayOfQuakes);


        }
        //Show the highest magnitude earth quakes
        if(position == 5){
            Collections.sort(arrayOfQuakes, EarthQuake.magnitudeComaparator);

        }
        //Show the deepest earth quakes
        if(position == 6){
            Collections.sort(arrayOfQuakes, EarthQuake.depthComparator);

        }
        //Show the shallowest earth quakes
        if(position == 7){
            Collections.sort(arrayOfQuakes, EarthQuake.depthComparator);
            Collections.reverse(arrayOfQuakes);

        }


        MainActivity.getInstance().setAdapter(arrayOfQuakes);

        getDates();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


        getDates();
    }

    //Function that is used to display only earth quakes between a specific time range. Creates a new array then adds the earth quakes to it
    //then sets the adapter to display an array with earth quakes between a specific time range
    public void getDates(){
        ArrayList dateRangeEarthQuakes = new ArrayList<EarthQuake>();
        String dateFrom = DateFrom.getText().toString();
        String dateTo = DateTo.getText().toString();




        //Check if the textviews are set to the default value.
        if(dateFrom.equals("Select Date From") && dateTo.equals("Select Date To")){

            MainActivity.getInstance().setAdapter(arrayOfQuakes);
            Log.d("Test" , " Test" + dateFrom + dateTo);









        }
        //If the textviews are set to a specific date, iterate through the array of all earth quakes and find earth quakes
        //between the specific time range and adds them to a new array.
        else{
            try{

                for(EarthQuake earthQuake: arrayOfQuakes){

                    String strDate = earthQuake.getPubDate().substring(5);

                    try{
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

                        Date dateFromObject = dateFormat.parse(dateFrom);
                        Date dateToObject = dateFormat.parse(dateTo);
                        Date earthQuakeDate = dateFormat.parse(strDate);

                        if(earthQuakeDate.after(dateFromObject) && earthQuakeDate.before(dateToObject)){
                            dateRangeEarthQuakes.add(earthQuake);

                        }

                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }


                }
                //Sets the adapter to use new array with earth quakes between the specific time range.
                MainActivity.getInstance().setAdapter(dateRangeEarthQuakes);

                if(dateRangeEarthQuakes.isEmpty()){

                    DialogFragment dialog = new popUP();
                    dialog.show(MainActivity.getInstance().getSupportFragmentManager(), "MyDialogFragmentTag");
                }
            }

            catch(Exception ex){
                ex.printStackTrace();
            }

        }


    }
}



