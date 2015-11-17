/*************************************************************
	Main class for lejos project, "the Useless machine"
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.Brick;
import lejos.hardware.port.Port;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;

public class Main{

	public static void main(String[] args ) throws Exception {

		Brick brick = BrickFinder.getDefault();
		// Port s1 = brick.getPort("S1"); // soundsensor
		Port s3 = brick.getPort("S3"); // ultrasonicsensor
		Port s4 = brick.getPort("S4"); // touchsensor

		PressureReader leverStatus = new PressureReader(s4);
		UltrasonicReader eyes = new UltrasonicReader(s3);
		// SoundReader sounds = new SoundReader(s1);
		Audioplayer iPod = new Audioplayer();
		Commands executor = new Commands();
		Analysis brain = new Analysis(leverStatus,eyes,iPod,executor); //sounds

		/*We might consider a while loop with changing terms, as a way of ending the program sequence
		*At this point the only desired functionality is for the program to loop infinitely
		*/
		while(true){
			brain.chooseOutcome();
		}
	}//void main
}//class