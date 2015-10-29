/*
*class SoundReader for lejos project, "the Useless Machine"
*this class processes data from the sound sensor
*/
import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;

public class SoundReader{

	private SampleProvider soundReader;
	private	NXTSoundSensor soundSensor;
	private float[] soundSample;
	private Port port;
	private int value;
	private Brick brick = BrickFinder.getDefault();
	boolean volume = false;

	public SoundReader(Port port){
		this.port = port;
	}
	public int getValue(){
		return value;
	}//getVaule

	public boolean getStatus(){
		return readSample();
	}

	//reads sample
	private boolean readSample(){
		soundSample = new float[soundReader.sampleSize()];
		soundSensor = new NXTSoundSensor(port);
		soundReader = soundSensor.getDBAMode();
		soundReader.fetchSample(soundSample, 0);

		return soundSample[0] > 0.5;
	}

}