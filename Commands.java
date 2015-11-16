/*
*class Commands for lejos project, "the Useless machine"
*/
import java.io.*;

import lejos.hardware.motor.*; // Importing all motor classes
import lejos.hardware.lcd.*; // Importing all LCD screen classes
import lejos.hardware.*; // Importing all hardware classes
import lejos.hardware.port.Port; // Importing Port class
import lejos.hardware.ev3.EV3; // Importing EV3 class
import lejos.robotics.SampleProvider; // Importing SampleProvider class
import lejos.hardware.sensor.*; // Importing all sensor classes
import lejos.robotics.navigation.DifferentialPilot; // Importing DifferentialPilot class

public class Commands{

		public void drive(int speed) throws Exception {
			/* This method drives the robot left and then right (back to the starting position) */

			// Motor.C

			Motor.C.setSpeed(speed);
			Motor.C.forward(); // Drive one direction
			Thread.sleep(1000);
			Motor.C.backward(); // Drive other direction
			Thread.sleep(1000);
		}

		public void moveArm(int rotation,int motorspeed) {
			/* This method moves the "arm" up and then down again */

			// Motor.B

			Motor.B.setSpeed(motorspeed);
			Motor.B.rotateTo(rotation); // Top: rotateTo(-90)
		}

		public void moveLever(int rotation) {
			/* This test moved the lever up and down again */

			// Motor.D

			Motor.D.setSpeed(200); // Value for motorspeeed?
			Motor.D.rotateTo(rotation); // Top: rotateTo(70)
		}



}//class