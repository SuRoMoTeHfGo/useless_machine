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
	private SoundReader ears;
	private AudioPlayer iPod;
	private Commands executor;
	private Outcomes pusher;
	private Random randomVal;

	public Analysis(PressureReader leverStatus, UltrasonicReader eyes, SoundReader ears, AudioPlayer iPod, Commands executor, Outcomes pusher) {
		this.leverStatus = leverStatus;
		this.eyes = eyes;
		this.ears = ears;
		this.iPod = iPod;
		this.executor = executor;
		this.pusher = pusher;
	}
	
	//generates a random value within decired interval, momentarily accecible by other classes
	public int getRandomVal(int min, int max) {
		randomVal = new Random();
		return randomVal.nextInt(max - min) + min;
	}

	/*
	*The method below processes the fact that the lever has been hit
	*It's intended to make the robot seem like it takes rushed decitions
	*/
	
	private void analyzePressure() throws Exception {
		
		if(leverStatus.toggled()) {
			//switch case is designed to generate a number of seemingly random outcomes
			int value = getRandomVal(0, 24);
			switch (value) {
				case 1 :
					pusher.fastDodgePush();
					break;

				case 2:
					pusher.fastPush();
					break;

				case 3:
					pusher.slowPush();
					break;

				case 4:
					pusher.peekPush();
					break;

				case 5:
					pusher.dodgePush();
					break;

				case 6:
					pusher.delayPush();
					break;
					
				case 7:
					pusher.cenaPush();
					break;
					
				case 8:
					pusher.longDelayPush();
					break;
					
				default:
					pusher.classicPush();
					break;
			}//switch
		}
	}//void

	/*The method analyseSpace uses data from the Ultrasonic sensor
	*It registeres wether someone is attempting to hit the lever
	*At certain outcomes, decided by a random variable, the robot will hide the lever
	*The first 5 lever hits are supposed to go as normal, therefore a counter is placed in the method AnalyzePressure()
	*/
	private void analyzeSpace() throws Exception {

		if(eyes.registered() && getRandomVal(0, 300000) < 2){
			if (getRandomVal(0, 2) > 0.5) {
				executor.moveLever(0, 200);
			} else {
				executor.drive(1200, -1200, 300);
			}
		} else {
			executor.moveLever(70, 200);
		}

	}

	/*The method analyzeSounds is supposed to be the robot's ears
	*Desired reactions to certain sound levels could be playing a track from the class AudioPlayer
	*/
	private void analyzeSounds() throws Exception {
		while (true) {
			System.out.println(ears.getValue());
			if (ears.triggered()) {
				pusher.classicPush();
				System.out.println("Limit hit");
			}
		}
	}

	public void init() throws Exception {
		executor.moveLever(70, 200);
	}
	
	//init method that calls the other methods
	public void chooseOutcome() throws Exception {
		analyzePressure();
		// analyzeSpace();
		// analyzeSounds();
	}//void

}//class