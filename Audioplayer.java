/*************************************************************
	Class Audioplayer for lejos project, "the Useless machine"
	This class has responsibility for playing audio files
	By developers Elias, Kristoffer, Ole Kristian and Haakon
**************************************************************/

//Lejos classes
import lejos.hardware.Brick;
import lejos.hardware.port.Port;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.*;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.robotics.pathfinding.Path;
import lejos.hardware.Sounds.*;
import lejos.hardware.Audio.*;
import lejos.remote.ev3.*;
import lejos.hardware.Sound;

//Java classes
import java.io.File;

public class Audioplayer {
	private int masterVolume;
	File file;
	String filename;

	// Constructor
	public Audioplayer(int masterVolume) {
		this.masterVolume = masterVolume;
		Sound.setVolume(masterVolume);
	}
	public static int generateSound(File file) throws Exception {
		return Sound.playSample(file, 50);
	}

	public void getSound() {
		Runnable task = new Runnable() {
			public void run() {

				try {
					file = new File(filename);
					int wavfilelength = generateSound(file);
				} catch (Exception e){
					System.out.println(e);
				}
			}
		};
		new Thread(task).start();
	}
	
	public void setSound(String name) {
		filename = name;
	}

}