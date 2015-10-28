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



	}

	public static void driveTest() {
		/* This test drives the robot left and then right (back to the starting position) */

		Motor driveMotor = Motor.A; // Check correct motor

		driveMotor.setSpeed(200); // Value for motorspeeed?
		driveMotor.forward(); // Drive one direction
		Thread.sleep(100);
		driveMotor.backward(); // Drive other direction
		Thread.sleep(100);
	}

	public static void moveArmTest() {
		/* This test moves the "arm" up and then down again */

		Motor armMotor = Motor.B // Check correct motor

		armMotor.setSpeed(200); // Value for motorspeeed?
		armMotor.rotateTo(360); // Value for rotation at up-position?
		armMotor.rotateTo(180); // Value for rotation at down-position?
	}

	public static void moveLeverTest() {
		/* This test moved the lever up and down again */

		Motor armMotor = Motor.C // Check correct motor

		armMotor.setSpeed(200); // Value for motorspeeed?
		armMotor.rotateTo(360); // Value for rotation at up-position?
		armMotor.rotateTo(180); // Value for rotation at down-position?
	}

	public static void ultrasonicTest() {
		/* This test logs the values of the ultrasonic sensor */

		Brick brick = BrickFinder.getDefault(); // Register brick
		Port s1 = brick.getPort("S1"); // Register EV3-uttrasonicsensor port
		EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(s1); // Register EV3-uttrasonicsensor
		SampleProvider ultrasonicLeser = ultrasonicSensor.getDistanceMode(); // Register sample provider for EV3-uttrasonicsensor
		float[] ultrasonicSample = new float[ultrasonicLeser.sampleSize()]; // Register float table for EV3-uttrasonicsensor sample values

		String filename = "ultrasonicOutput.txt"; // Filename of output file
		File file = new File(filename); // Register as file

		for (int i = 0; i < 100; i++) {
			ultrasonicLeser.fetchSample(ultrasonicSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
				// Create new file if no file exsist
            	if ( !file.exists() ) {
                	file.createNewFile();
            	}

                writer.write(Float.toString(ultrasonicSample[0])); // Writes value to file

        	} catch (IOException e) {
            	System.out.println(e.getMessage());
        	}
		}

	}

	public static void colorTest() {
		/* This test logs the values of the color sensor */

		Brick brick = BrickFinder.getDefault(); // Register brick
		Port s2 = brick.getPort("S2"); // Register EV3-uttrasonicsensor port
		EV3ColorSensor colorSensor = new EV3ColorSensor(s2); // Register EV3-uttrasonicsensor
		SampleProvider colorLeser = colorSensor; // Register sample provider for EV3-uttrasonicsensor
		float[] colorSample = new float[colorLeser.sampleSize()]; // Register float table for EV3-uttrasonicsensor sample values

		String filename = "colorOutput.txt"; // Filename of output file
		File file = new File(filename); // Register as file

		for (int i = 0; i < 100; i++) {
			colorLeser.fetchSample(colorSample, 0); // Save values to first position of the EV3-uttrasonicsensor float table

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
				// Create new file if no file exsist
            	if ( !file.exists() ) {
                	file.createNewFile();
            	}

                writer.write(Float.toString(colorSample[0])); // Writes value to file

        	} catch (IOException e) {
            	System.out.println(e.getMessage());
        	}
		}
	}
}
