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
import lejos.robotics.pathfinding.Path;
import lejos.hardware.Sounds.*;
import lejos.hardware.Audio.*;
import lejos.remote.ev3.*;
;import lejos.hardware.Sound;

//Java classes
import java.io.File;
public class Audioplayer{
// Object variables
	private int masterVolume;
	File file;
// Constructor
	public Audioplayer(int masterVolume){
		this.masterVolume = masterVolume;
		file = new File("MegaMan.wav");
	}
	public int generateSound(File sound)throws Exception{
		return Sound.playSample(sound,50);
	}
	public void setVolume()throws Exception{
		Sound.setVolume(100);
	}
	private void getSound()throws Exception{
		Runnable task = new Runnable() {//thread playing music
		public void run() {
			try{
				int wavfilelength = generateSound(file);
				} catch (Exception e){

					}
				}
			};
			new Thread(task).start();
	}

}

