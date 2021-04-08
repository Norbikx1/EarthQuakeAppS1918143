/*
Earth Quake App
Norbert Bednarski
s1918143
 */

package org.me.gcu.EartQuakeAppS1918143;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Class used to parse the earth quakes from the xml file
public class EarthQuakesXmlPullParser {

	static final String KEY_ITEM = "item";
	static final String KEY_TITLE = "title";
	static final String KEY_DESCRIPTION = "description";
	static final String KEY_LINK = "link";
	static final String KEY_PUBDATE = "pubDate";
	static final String KEY_LAT = "geo:lat";
	static final String KEY_LONG = "geo:long";



	public static List<EarthQuake> getEarthQuakesFromFile(Context ctx) {

		// A list of earth quakes
		List<EarthQuake> earthQuakes;
		earthQuakes = new ArrayList<EarthQuake>();

		//Temporary holder for current earthquake
		EarthQuake curEarthQuake = null;
		//Temporary holder for text while parsing.
		String curText = "";

		try {

			//Get the pull parser and factory.
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			//Starts the input stream and reader.
			FileInputStream fis = ctx.openFileInput("MhSeismology.xml");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));


			xpp.setInput(reader);


			int eventType = xpp.getEventType();

			//Loops through the xml file.
			while (eventType != XmlPullParser.END_DOCUMENT) {

				String tagname = xpp.getName();


				switch (eventType) {
					//Defines the start tag of the document
				case XmlPullParser.START_TAG: if (tagname.equals(KEY_TITLE)) {

						curEarthQuake = new EarthQuake();
					}
					break;

				case XmlPullParser.TEXT:

					curText = xpp.getText();
					break;
					//Defines the end tag of the document
				case XmlPullParser.END_TAG: if (tagname.equals("item")) {

						earthQuakes.add(curEarthQuake);
					}
					else if (tagname.equalsIgnoreCase(KEY_TITLE)) {

						curEarthQuake.setTitle(curText);
					}
					else if (tagname.equalsIgnoreCase(KEY_LINK)) {

						curEarthQuake.setLink(curText);
					}
					else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {

						curEarthQuake.setDescription(curText);
					}
					else if (tagname.equalsIgnoreCase(KEY_PUBDATE)) {

						curEarthQuake.setPubDate(curText);
					}
					else if (tagname.equalsIgnoreCase(KEY_LAT)) {

						curEarthQuake.setLatitude(curText);
					}
					else if (tagname.equalsIgnoreCase(KEY_LONG)) {

						curEarthQuake.setLongitude(curText);
					}

					break;

				default:
					break;
				}
				//Moves to the next iteration.
				eventType = xpp.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Returns the list with all the earth quakes from xml file.
		return earthQuakes;

	}
}
