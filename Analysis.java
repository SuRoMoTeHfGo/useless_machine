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
		iPod = new AudioPlayer(70);
		// this.sounds = sounds;
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
			int value = getRandomVal(0, 8);
			switch (value) {
				case 1 :
					classicPush();
					break;

				case 2:
					fastPush();
					break;

				case 3:
					slowPush();
					break;

				case 4:
					peekPush();
					break;

				case 5:
					dodgePush();
					break;

				case 6:
					delayPush();
					break;
					
				case 7:
					cenaPush();
					break;
					
				case 8:
					driveTest();
					break;
					
				default:
					classicPush();
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
	private void analyzeSounds() {
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
	
	/* 
	* Different functions for the robot
	*/
	private void classicPush() throws Exception {
		executor.moveArm(-100, 200, false);
		executor.moveArm(0, 200, false);
	}
	
	private void slowPush() throws Exception {
		executor.moveArm(-70, 300, false);
		executor.moveArm(-100, 100, false);
		executor.moveArm(0, 100, false);
	}
	
	private void fastPush() throws Exception {
		executor.moveArm(-100, 350, true);
		executor.sleep(250);
		executor.moveArm(0, 400, false);
	}
	
	private void peekPush() throws Exception {
		executor.moveArm(-30, 350, false);
		executor.sleep(1750);
		executor.moveArm(0, 350, false);
		executor.sleep(750);
		executor.moveArm(-100, 350, true);
		executor.sleep(250);
		executor.moveArm(0, 400, false);
	}
	
	private void delayPush() throws Exception {
		executor.sleep(1000);
		executor.moveArm(-30, 350, false);
		executor.sleep(1500);
		executor.moveArm(-100, 40, false);
		executor.moveArm(0, 350, false);
	}
	
	private void dodge(long ms) throws Exception {
		executor.sleep(ms);
		executor.moveArm(-90, 350, true);
		executor.moveLever(35, 300);
		executor.moveArm(0, 200, false);
		executor.moveLever(70, 25);
	}
	
	private void dodgePush() throws Exception {
		dodge(750);
		peekPush();
	}
	
	private void driveTest() throws Exception {
		executor.drive(250, -250, 500);
		classicPush();
	}
	
	private void cenaPush() throws Exception {
		iPod.setSound("cena.wav");
		iPod.getSound();
		executor.sleep(1550);
		dodge(750);
		dodge(500);
		dodge(250);
		dodge(100);
		dodge(50);
		classicPush();
	}

}//class