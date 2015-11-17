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
	int count = 1;
	private void analyzePressure() throws Exception {
		if(leverStatus.toggled()) {
			//switch case is designed to generate a number of seemingly random outcomes
			
			switch (count) {
			case 1 :
				classicPush();
				count++;
				break;

			case 2:
				fastPush();
				count++;
				break;

			case 3:
				slowPush();
				count++;
				break;

			case 4:
				peekPush();
				count++;
				break;

			case 5:
				dodge();
				count++;
				break;

			default:
				classicPush();
				count = 1;
				break;
			}//switch
		} else {
			executor.moveArm(0, 200, false);
		}
	}//void

	/*The method analyseSpace uses data from the Ultrasonic sensor
	*It registeres wether someone is attempting to hit the lever
	*At certain outcomes, decided by a random variable, the robot will hide the lever
	*The first 5 lever hits are supposed to go as normal, therefore a counter is placed in the method AnalyzePressure()
	*/
	private void analyzeSpace() throws Exception {

		if(eyes.registered() && getRandomVal(0, 3000) < 2){
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
	
	/* 
	* Different functions for the robot
	*/
	private void classicPush() throws Exception {
		executor.moveArm(-100, 200, false);
	}
	
	private void slowPush() throws Exception {
		executor.moveArm(-100, 100, false);
	}
	
	private void fastPush() throws Exception {
		executor.moveArm(-100, 400, false);
	}
	
	private void peekPush() throws Exception {
		executor.moveArm(-30, 75, false);
		executor.moveArm(0, 200, false);
		executor.moveArm(-100, 250, false);
	}
	
	private void dodge() throws Exception {
		executor.moveArm(-100, 100, true);
		executor.moveLever(0, 0);
	}

}//class