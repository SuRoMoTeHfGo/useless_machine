/*************************************************************
	Class Commands for lejos project, "the Useless machine"
	This class operates with all of the robot's physical movements
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.utility.*;
import lejos.hardware.motor.*; // Importing all motor classes
import lejos.hardware.lcd.*; // Importing all LCD screen classes
import lejos.hardware.*; // Importing all hardware classes
import lejos.hardware.port.Port; // Importing Port class
import lejos.hardware.ev3.EV3; // Importing EV3 class
import lejos.robotics.SampleProvider; // Importing SampleProvider class
import lejos.hardware.sensor.*; // Importing all sensor classes
import lejos.robotics.navigation.DifferentialPilot; // Importing DifferentialPilot class

//Java classes
import java.io.*;

public class Commands {

	public void drive(int rotateTo, int rotateNext, int speed) throws Exception {
		/* This method drives the robot left and then right (back to the starting position) */

		// Motor.C

		Motor.C.setSpeed(speed);
		Motor.C.rotateTo(rotateTo, false); // Drive one direction
		Motor.C.rotateTo(rotateNext, false); // Drive one direction
		Motor.C.rotateTo(0, false); // Drive other direction
	}

	public void moveArm(int rotation, int motorspeed, boolean immediateReturn) throws Exception {
		/* This method moves the "arm" up and then down again */
		//We should consider the option of letting the lever return back to place at a slower pace, or even take a pause at the top
		// Motor.B

		Motor.B.setSpeed(motorspeed);
		Motor.B.rotateTo(rotation, immediateReturn); // Top: rotateTo(-90)
	}

	public void moveLever(int rotation, int motorspeed) throws Exception {
		/* This method moves the lever up and down again */
		//It should be considered an option to switch the lever down, and leave it there for a timed delay...
		//motor D
		Motor.D.setSpeed(motorspeed); // Value for motorspeeed?
		Motor.D.rotateTo(rotation); // Top: rotateTo(70)
	}

	public void sleep (long ms) throws Exception {
		//the method sleep is to give the robot a timed delay befor it hits the lever.
		Delay.msDelay(ms);
	}


}//class