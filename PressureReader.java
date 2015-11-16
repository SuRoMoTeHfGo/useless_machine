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

public class PressureReader{
/*
*needs update! Touchsensor is to be used instead
*/
	private float[] touchSample;
	private EV3TouchSensor touchSensor;
	private SampleProvider touchReader;
    private Port port;
	private double value = 0.01;
	boolean black = false;
//contstructor
	public PressureReader(Port port)
	{
		this.port = port;
	}

	//returns color sample for debugging
	public double getSample()throws Exception{
		return value;
	}

//method checks wether the touch sensor has been hit or not
	public boolean toggled()throws Exception{
		touchSensor = new EV3TouchSensor(port);
		touchReader = touchSensor;
		touchSample = new float[touchReader.sampleSize()]; // Register float table for EV3-touchsensor sample values		colorReader.fetchSample(colorSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table
		touchReader.fetchSample(touchSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table

		return touchSample[0] > 0;

	}
}//class