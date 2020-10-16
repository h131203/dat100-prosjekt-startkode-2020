package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];
		for (double d: da) {
			if (d < min) {
				min = d;
			}
		}
		return min;

		// TODO - SLUT

	}
//returnerner en tabell med alle breddegradene (latitudes) fra gpspunkente i tabellen gpspoints
	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < latitudes.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longitudes = new double[gpspoints.length];
		for (int i = 0; i < longitudes.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;
		
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		// se formel i oppgaveteksten...

		// TODO - START

		latitude1 = gpspoint1.getLatitude() * (Math.PI/180);
		latitude2 = gpspoint2.getLatitude() * (Math.PI/180);
		double differanseLat = latitude2-latitude1;
		longitude1 = gpspoint1.getLongitude() * (Math.PI/180);
		longitude2 = gpspoint2.getLongitude() * (Math.PI/180);
		double differanseLong = longitude2 - longitude1;
		
		double a = ((Math.sin(differanseLat / 2)) * (Math.sin(differanseLat / 2))) + Math.cos(latitude1)
		* Math.cos(latitude2) * ((Math.sin(differanseLong / 2)) * (Math.sin(differanseLong / 2)));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d = R * c;
		return d;

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		secs = gpspoint2.getTime() - gpspoint1.getTime(); //tiden fra punkt 1 til punkt 2
		double distance = distance(gpspoint1, gpspoint2);
		speed = (distance/secs) *3.6; //kmh
		
		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		int hours = secs / 3600;
		int minutes = (secs % 3600) / 60;
		int seconds = secs % 60;
		String tid = String.format("%02d"+TIMESEP+"%02d"+TIMESEP+"%02d", hours, minutes, seconds);
		timestr = String.format("%10s", tid);
		return timestr;
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10; 

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		 //.2f => floating point (double) setter 2 desimaler
		//str = String.format("%"+TEXTWIDTH+".2f", d);
		//str.replace(',','.'); //feiler.. ?
		
	  	//return str;
	
	 		d = d*100;
	 		d = Math.round(d);
	 		d = (d/100);
	 		String s = Double.toString(d);
	 		return str = String.format("%10s", s);
	 	

		// TODO - SLUTT
		
	}
}
