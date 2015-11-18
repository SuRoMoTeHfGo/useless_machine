/*************************************************************
	Class Outcomes for lejos project, "the Useless Machine"
	This class handles various functional outcomes for the useless machine
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


public class Outcomes{
	private Commands executor;
	private AudioPlayer iPod;

	//Constructor
	public Outcomes(Commands executor, AudioPlayer iPod){
		this.executor = executor;
		this.iPod = iPod;
	}

	
	//Method classicPush is the "normal" hit lever outcome
	public void classicPush() throws Exception {
		executor.moveArm(-100, 200, false);
		executor.moveArm(0, 200, false);
	}

	//Methods slowPush and fastPush, push the lever at different speeds
	public void slowPush() throws Exception {
		executor.moveArm(-70, 300, false);
		executor.moveArm(-100, 100, false);
		executor.moveArm(0, 100, false);
	}
	public void fastPush() throws Exception {
		iPod.setSound("wah.wav");
		executor.moveArm(-100, 350, true);
		executor.sleep(125);
		iPod.getSound();
		executor.sleep(125);
		executor.moveArm(0, 400, false);
	}

	//The robot peeks first, then retires. After a delay, it hits the lever
	public void peekPush() throws Exception {
		iPod.setSound("jump.wav");
		iPod.getSound();
		executor.moveArm(-30, 350, false);
		executor.sleep(1750);
		executor.moveArm(0, 350, false);
		executor.sleep(750);
		fastPush();
	}

	//In method delayPush lever is pushed after a delay
	public void delayPush() throws Exception {
		executor.sleep(1000);
		executor.moveArm(-30, 350, false);
		executor.sleep(1500);
		iPod.setSound("coin.wav");
		executor.moveArm(-100, 40, true);
		executor.sleep(1500);
		iPod.getSound();
		executor.sleep(250);
		executor.moveArm(0, 350, false);
	}
	//The lever is pushed after a longer delay, to make it seem like the user has won.
	public void longDelayPush() throws Exception {
		executor.sleep(3000);
		executor.moveArm(-30, 350, false);
		executor.sleep(1500);
		executor.moveArm(-100, 40, false);
		executor.moveArm(0, 350, false);
	}

	//In methods dodgePush and fastDodgePush, the bot is unable to hit the lever, as it hides the lever from itself
	public void dodgePush() throws Exception {
		dodge(750);
		peekPush();
	}
	public void fastDodgePush() throws Exception {
		dodge(150);
		sleep(400);
		fastPush();
	}
	

	//In method cenaPush the robot goes into a "crazy mode", playing music while doing several attempts to hit the lever
	public void cenaPush() throws Exception {
		//play John Cena song
		iPod.setSound("cena.wav");
		iPod.getSound();

		executor.sleep(1550);//wait for 1,5 seconds
		//dodge a few times so the bot can't hit the lever
		dodge(750);
		dodge(100);
		dodge(50);
		dodge(10);
		dodge(0);
		dodge(0);
		//eventually it hits the lever
		classicPush();
	}

	/*
		*Private methods
		*/

	//Method  dodge is exclusively used locally by other methods
	private void dodge(long ms) throws Exception {
		executor.sleep(ms);
		executor.moveArm(-90, 350, true);
		executor.moveLever(35, 300);
		executor.moveArm(0, 200, false);
		executor.moveLever(70, 25);
	}

}//class
