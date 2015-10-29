/*
*class ColorReader for lejos project, "the Useless machine"
*this class processes of data from the color sensor
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

public class ColorReader{

	private float[] colorSample;
	private EV3ColorSensor colorSensor;
	private SampleProvider colorReader;
    private Port port;
	private double value = 0.01;
	boolean black = false;
//contstructor
	public ColorReader(Port port)
	{
		this.port = port;
	}

	//returns color sample
	public double getSample()throws Exception{
		return value;
	}

//returns status as either black or not black, which translates to lever is hit or not hit.
	public boolean getStatus()throws Exception{
		colorSensor = new EV3ColorSensor(port);
		colorReader = colorSensor.getMode("RGB");
		colorSample = new float[colorReader.sampleSize()];
		colorReader.fetchSample(colorSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table

		return colorSample[0] > value;

	}
}//class