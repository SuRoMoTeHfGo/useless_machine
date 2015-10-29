/*
*Main class for lejos project, "the Useless machine"
*By developers Elias, Kristoffer, Ole and Håkon
*/
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
	public static void main(String[]arg)throws Exception{
		Brick brick = BrickFinder.getDefault();
		Port s1 = brick.getPort("S1"); // colorsensor
		Port s2 = brick.getPort("S2"); // ultrasonicsensor
		Port s3 = brick.getPort("S3"); // soundsensor

		ColorReader leverStatus = new ColorReader(s1);
		UltrasonicReader eyes = new UltrasonicReader(s2);
		SoundReader sounds = new SoundReader(s3);
		Audioplayer iPod = new Audioplayer();
		Commands executor = new Commands(brain);
		Analysis brain = new Analysis(leverStatus,eyes,sounds,iPod);

	}//void main
}//class