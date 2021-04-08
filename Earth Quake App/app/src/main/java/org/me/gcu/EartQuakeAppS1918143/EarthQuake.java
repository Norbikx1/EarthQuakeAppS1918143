/*
Earth Quake App
Norbert Bednarski
s1918143
 */

package org.me.gcu.EartQuakeAppS1918143;

import java.util.Comparator;

//Class that defines the earth quake object.
public class EarthQuake {



        private String title;
        private String link;
        private String description;
        private String PubDate;
        private String latitude;
        private String longitude;
        public String magnitude;
        private Float depth;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getLink() {
            return link;
        }
        public void setLink(String link) {
            this.link = link;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getPubDate() {
            return PubDate;
        }
        public void setPubDate(String PubDate) {
            this.PubDate = PubDate;
        }

        public String getLatitude() {
        return latitude;
          }
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() { return longitude; }
        public void setLongitude(String longitude) {
        this.longitude = longitude;
         }

        public String getMagnitude() {
        return magnitude;
    }
        public void setMagnitude(String magnitude) { this.magnitude = magnitude;

    }


        @Override
        public String toString() {
            return "EarthQuake [title=" + title + "/n" + ", link=" + link + "/n" + ", description="
                    + description + "/n" + ", PubDate=" + PubDate + "/n" + ", Latitude " + latitude +  "/n" +", Longitude" + longitude +"]";
        }

        //Comparator for longiude.
        public static Comparator<EarthQuake> longComparator = new Comparator<EarthQuake>(){


            @Override
            public int compare(EarthQuake o1, EarthQuake o2) {
                float floatLong1 = Float.parseFloat(o1.getLongitude());
                float floatLong2 = Float.parseFloat(o2.getLongitude());
                return (floatLong2  < floatLong1 ? -1:
                        (floatLong2 == floatLong1? 0:1 ));
            }
        };
    //Comparator for latitude of an earthquake
    public static Comparator<EarthQuake> latComparator = new Comparator<EarthQuake>(){


        @Override
        public int compare(EarthQuake o1, EarthQuake o2) {
            float floatLat1 = Float.parseFloat(o1.getLatitude());
            float floatLat2 = Float.parseFloat(o2.getLatitude());
            return (floatLat2  < floatLat1 ? -1:
                    (floatLat2 == floatLat1? 0:1 ));
        }
    };
    //Comparator for magnitude of an earthquake
    public static Comparator<EarthQuake> magnitudeComaparator = new Comparator<EarthQuake>(){


        @Override
        public int compare(EarthQuake o1, EarthQuake o2) {
            float floatMag1 = Float.parseFloat(o1.title.substring(25,28));
            float floatMag2 = Float.parseFloat(o2.title.substring(25,28));
            return (floatMag2  < floatMag1 ? -1:
                    (floatMag2 == floatMag1? 0:1 ));
        }
    };
    //Comparator for depth of an earthquake
    public static Comparator<EarthQuake> depthComparator = new Comparator<EarthQuake>(){


        @Override
        public int compare(EarthQuake o1, EarthQuake o2) {
            String[] Depth1 = o1.description.split(";");
            String[] Depth2 = o2.description.split(";");
            String depth1 = Depth1[3];
            String depth2 = Depth2[3];

            String subDepth1 = depth1.substring(8,10);
            String subDepth2 = depth2.substring(8,10);


            float floatDepth1 = Float.parseFloat(subDepth1) ;
            float floatDepth2 = Float.parseFloat(subDepth2);
            return (floatDepth2  < floatDepth1 ? -1:
                    (floatDepth2 == floatDepth1? 0:1 ));
        }
    };



}


