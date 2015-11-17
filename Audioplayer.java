/*************************************************************
	Class Audioplayer for lejos project, "the Useless machine"
	This class has responsibility for playing audio files
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.Brick;
import lejos.hardware.port.Port;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.Sound;

//Java classes
import java.io.File;

public class AudioPlayer{
	
	public AudioPlayer() {
		Sound.setVolume(100);
	}
	
	public static int createSound(File file) throws Exception {
		return Sound.playSample(file, 50);
	}

	public void startSound() {
		Runnable task = new Runnable() {
			public void run() {
				try {
					File file = new File("music.wav");
					int wavfilelength = createSound(file);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		};
		new Thread(task).start();
	}
}

