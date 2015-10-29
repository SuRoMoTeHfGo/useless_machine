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
	private Brick brick = BrickFinder.getDefault();
    private Port port;
	private int value;
	boolean black = false;
//contstructor
	public ColorReader(Port port)
	{
		this.port = port;
	}

	//returns color sample
	public int getSample()throws Exception{
		return value;
	}
//returns status as either black or not black, which translates to lever is hit or not hit.
	public boolean getStatus()throws Exception{

			readSample();

			while(!black){
			colorReader.fetchSample(colorSample, 0);

			if (colorSample[0]*100 > value){   //check black value
				return false;
			} else {
				black = true;

			}//else
		}
			return true;
	}

	private void readSample()throws Exception{
		colorSensor = new EV3ColorSensor(port);
		colorReader = colorSensor.getMode("RGB");
		colorSample = new float[colorReader.sampleSize()];

		for (int i = 0; i<100; i++){
					colorReader.fetchSample(colorSample, 0);
					value += colorSample[0]*100;
		}
		value = value/100 +5;
	}
}//class