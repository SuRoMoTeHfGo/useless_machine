/*************************************************************
	Main class for lejos project, "the Useless machine"
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.sensor.NXTColorSensor; // Importing color sensos class
import lejos.hardware.Brick;// Importing brick class
import lejos.hardware.port.Port;// Importing Port class
import lejos.hardware.BrickFinder; //Importing bringfinder class
import lejos.hardware.ev3.EV3;// Importing EV3 class
import lejos.hardware.Keys;// Importing keys class
import lejos.hardware.sensor.SensorModes;// Importing sensormodes class
import lejos.robotics.SampleProvider;// Importing SampleProvider class
import lejos.hardware.sensor.*;// Importing all sensor classes

public class Main {

	public static void main(String[] args) throws Exception {

		Brick brick = BrickFinder.getDefault();
		// Port s1 = brick.getPort("S1"); // soundsensor
		Port s3 = brick.getPort("S3"); // ultrasonicsensor
		Port s4 = brick.getPort("S4"); // touchsensor

		// SoundReader sounds = new SoundReader(s1);
		UltrasonicReader eyes = new UltrasonicReader(s3);
		PressureReader leverStatus = new PressureReader(s4);
		Commands executor = new Commands();
		AudioPlayer iPod = new AudioPlayer(100);
		Outcomes pusher = new Outcomes(executor, iPod);
		Analysis brain = new Analysis(leverStatus, eyes, iPod, executor,pusher); //(leverStatus, eyes, sounds, iPod, executor);


		// Initialize program
		brain.init();

		/*We might consider a while loop with changing terms, as a way of ending the program sequence
		*At this point the only desired functionality is for the program to loop infinitely
		*/
		while(true) {
			brain.chooseOutcome();
		}
	}//void main
}//class