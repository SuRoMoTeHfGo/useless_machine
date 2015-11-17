/*************************************************************
	Class Analysis for lejos project, "the Useless machine"
	This class analyzes data from all the sensors
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

//Java classes
import java.util.Random;

public class Analysis {
	private PressureReader leverStatus;
	private UltrasonicReader eyes;
	private SoundReader sounds;
	private AudioPlayer iPod;
	private Commands executor;
	private Random randomVal;
	private int counter;

	public Analysis(PressureReader leverStatus, UltrasonicReader eyes, AudioPlayer iPod, Commands executor) { //(PressureReader leverStatus, UltrasonicReader eyes, SoundReader sounds, Audioplayer iPod, Commands executor)
		this.leverStatus = leverStatus;
		this.eyes = eyes;
		this.iPod = iPod;
		this.executor = executor;
		// this.sounds = sounds;
	}
	
	//generates a random value within decired interval, momentarily accecible by other classes
	public int getRandomVal(int min, int max) {
		randomVal = new Random();
		return randomVal.nextInt(max - min + 1) + min;
	}

	/*
	*The method below processes the fact that the lever has been hit
	*It's intended to make the robot seem like it takes rushed decitions
	*/
	private void analyzePressure() throws Exception {
		if(leverStatus.toggled()) {
			//switch case is designed to generate a number of seemingly random outcomes
			switch (getRandomVal(1, 30)) {
			case 1 :
				executor.moveArm(-30, 40);
				break;

			case 2 :
				executor.moveArm(-30, 40);//illustrates that the robot takes a "peek"
				executor.sleep(600);//the robot waits for a brief moment
				executor.moveArm(-100, 200);//the robot hits the lever eventually
				break;

			case 3 :
				executor.moveArm(-100, 200);//this must happen while the lever is down
				executor.moveLever(70, 0);//
				executor.moveArm(-100, 100);//this is supposed to happen after the lever is back up, after a timed delay
				break;

			case 4 :
				executor.moveLever(70, 200);//the intended outcome is to hide the lever, drive away, and pick up the lever after a timed delay
				executor.drive(100);//
				break;
				
			case 5 ://something with the AudioPlayer should be placed here
				break;
				
			case 6 :
				executor.sleep(2000);//the robot is supposed to wait for a few seconds before it hits the lever
				executor.moveArm(-100, 200);
				break;

			default :
				executor.moveArm(-100, 200); //the standard quick hit
				break;
			}//switch
		} else {
			executor.moveArm(0, 200);
		}
	}//void

	/*The method analyseSpace uses data from the Ultrasonic sensor
	*It registeres wether someone is attempting to hit the lever
	*At certain outcomes, decided by a random variable, the robot will hide the lever
	*The first 5 lever hits are supposed to go as normal, therefore a counter is placed in the method AnalyzePressure()
	*/
	private void analyzeSpace() throws Exception {

		if(eyes.registered() && getRandomVal(0, 10) < 2){
			if (getRandomVal(0, 2) > 0.5) {
				executor.moveLever(0, 0);
			} else {
				executor.drive(300);
			}
		} else {
			executor.moveLever(70, 0);
		}

	}

	/*The method analyzeSounds is supposed to be the robot's ears
	*Desired reactions to certain sound levels could be playing a track from the class AudioPlayer
	*/
	private void analyzeSounds() {
	}

	//init method that calls the other methods
	public void chooseOutcome() throws Exception {
		analyzePressure();
		analyzeSpace();
		analyzeSounds();
	}//void

}//class