import java.io.*;

import lejos.hardware.motor.*; // Importing all motor classes
import lejos.hardware.lcd.*; // Importing all LCD screen classes
import lejos.hardware.*; // Importing all hardware classes
import lejos.hardware.port.Port; // Importing Port class
import lejos.hardware.ev3.EV3; // Importing EV3 class
import lejos.robotics.SampleProvider; // Importing SampleProvider class
import lejos.hardware.sensor.*; // Importing all sensor classes
import lejos.robotics.navigation.DifferentialPilot; // Importing DifferentialPilot class


public class Main{
	public static void main (String[] arg) throws Exception  {

		colorTest();

	}

	public static void driveTest() throws Exception {
		/* This test drives the robot left and then right (back to the starting position) */

		// Motor.C

		Motor.C.setSpeed(200); // Value for motorspeeed?
		Motor.C.forward(); // Drive one direction
		Thread.sleep(1000);
		Motor.C.backward(); // Drive other direction
		Thread.sleep(1000);
	}

	public static void moveArmTest(int value) {
		/* This test moves the "arm" up and then down again */

		// Motor.B

		Motor.B.setSpeed(200); // Value for motorspeeed?
		Motor.B.rotateTo(value); // Top: rotateTo(-90)
	}

	public static void moveLeverTest(int value) {
		/* This test moved the lever up and down again */

		// Motor.D

		Motor.D.setSpeed(200); // Value for motorspeeed?
		Motor.D.rotateTo(value); // Top: rotateTo(70)
	}

	public static void ultrasonicTest() {
		/* This test logs the values of the ultrasonic sensor */

		Brick brick = BrickFinder.getDefault(); // Register brick
		Port s3 = brick.getPort("S3"); // Register EV3-uttrasonicsensor port
		EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(s3); // Register EV3-uttrasonicsensor
		SampleProvider ultrasonicLeser = ultrasonicSensor.getDistanceMode(); // Register sample provider for EV3-uttrasonicsensor
		float[] ultrasonicSample = new float[ultrasonicLeser.sampleSize()]; // Register float table for EV3-uttrasonicsensor sample values

		String filename = "ultrasonicOutput.txt"; // Filename of output file
		File file = new File(filename); // Register as file
		String output = "";

		for (int i = 0; i < 100; i++) {
			ultrasonicLeser.fetchSample(ultrasonicSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table

			output += ultrasonicSample[0] + "\n";
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			// Create new file if no file exsist
			if ( !file.exists() ) {
				file.createNewFile();
			}

			writer.write(output); // Writes value to file

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void colorTest() {
		/* This test logs the values of the color sensor */

		Brick brick = BrickFinder.getDefault(); // Register brick
		Port s4 = brick.getPort("S4"); // Register EV3-uttrasonicsensor port
		EV3ColorSensor colorSensor = new EV3ColorSensor(s4); // Register EV3-uttrasonicsensor
		SampleProvider colorLeser = colorSensor.getRGBMode(); // Register sample provider for EV3-uttrasonicsensor
		float[] colorSample = new float[colorLeser.sampleSize()]; // Register float table for EV3-uttrasonicsensor sample values

		String filename = "colorOutput.txt"; // Filename of output file
		File file = new File(filename); // Register as file
		String output = "";

		for (int i = 0; i < 100; i++) {
			colorLeser.fetchSample(colorSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table
			output += colorSample[0] + "\n";
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			// Create new file if no file exsist
			if ( !file.exists() ) {
				file.createNewFile();
			}

			writer.write(output); // Writes value to file

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
