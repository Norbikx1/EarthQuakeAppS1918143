/*
Earth Quake App
Norbert Bednarski
s1918143
 */

package org.me.gcu.EartQuakeAppS1918143;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.util.Log;
//Class for downloading a file.
public class GetXML {

    private static String myTag = "MhSeismology";

    static final int POST_PROGRESS = 1;

    //Download the xml file from the url.
    public static void GetXMLFromURL(String URL, FileOutputStream fos) {
        try {

            URL url = new URL(URL); //URL of the file
            //Used to calculate the time taken to download the xml file.
            long startTime = System.currentTimeMillis();
            //Open connection to the URL
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.i(myTag, "InputStream and BufferedInputStream ready");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            Log.i(myTag, "FileOutputStream and BufferedOutputStream ready");
            byte data[] = new byte[1024];
            int count;
            while ((count = bis.read(data)) != -1) {

                bos.write(data, 0, count);
            }
            bos.flush();
            bos.close();
            //Shows the time taken to download the xml file in ms
            Log.d(myTag, "Downloaded in "
                    + ((System.currentTimeMillis() - startTime))
                    + " ms");
        } catch (IOException e) {
            Log.d(myTag, "Error: " + e);
        }
    }
}
