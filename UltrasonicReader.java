/*
*class UltrasonicReader for lejos project, "the Useless Machine"
*this class processes data from the ultrasonic sensor
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
public class UltrasonicReader{

	private EV3UltrasonicSensor ultrasonicSensor;
	private SampleProvider ultrasonicReader;
    private Port port;
    private float[] ultrasonicSample;

	public UltrasonicReader(Port port)
	{
		this.port = port;
	}
	//this method provides the status of the airspace directly above the useless machine
	public boolean triggered(){
		ultrasonicSensor = new EV3UltrasonicSensor(port); // Register EV3-uttrasonicsensor
		ultrasonicReader = ultrasonicSensor.getDistanceMode(); // Register sample provider for EV3-uttrasonicsensor
		ultrasonicSample = new float[ultrasonicReader.sampleSize()]; // Register float table for EV3-uttrasonicsensor sample values
		ultrasonicReader.fetchSample(ultrasonicSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table


		return ultrasonicSample[0] < 0.13;
	}
}