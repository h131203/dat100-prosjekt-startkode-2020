package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static final int MARGIN = 50;
	private static final int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {

		// get segments speeds from the GPS computer object		
		double[] speeds = gpscomputer.speeds();

		int x = MARGIN,y;

		// TODO - START
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		  int windowHeight = 2 * MARGIN + BARHEIGHT;
	        int d = 0;
	        double maxSpeed, averageSpeed;
	    

	        maxSpeed = GPSUtils.findMax(speeds);
	        averageSpeed = gpscomputer.averageSpeed();

	        setColor(0,0,255);

	        for (int i = 0; i < N; i++) {
	            GPSPoint gpspoint1 = gpspoints[i];
	            GPSPoint gpspoint2 = gpspoints[i+1];

	            //Skalerer hoeyde med x = BARHEIGHT/maxSpeed
	            int height = (int) (GPSUtils.speed(gpspoint1, gpspoint2) * (BARHEIGHT / maxSpeed));
	            int minY = windowHeight - MARGIN - height;
	            int minX = MARGIN + d;

	            if (height < 0) {
	                height = 0;
	            }

	            drawRectangle(minX , minY, 0, height);
	            d += 2;
	            pause((gpspoint2.getTime() - gpspoint1.getTime()) * 1000 / timescaling);
	        }
		
		
	
		// TODO - SLUTT
	}
}
