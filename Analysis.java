/*
*class Analysis for lejos project, "the Useless machine"
*/

import java.util.Random;

public class Analysis{
	private ColorReader leverStatus;
	private UltrasonicReader eyes;
	private SoundReader sounds;
	private Audioplayer iPod;
	private Random randomVal;

	public Analysis(ColorReader leverStatus,UltrasonicReader eyes, SoundReader sounds,Audioplayer iPod){
		this.leverStatus = leverStatus;
		this.eyes = eyes;
		this.sounds = sounds;
		this.iPod = iPod;
	}

	public int getRandomVal(int min, int max){
		randomVal = new Random();
		return randomVal.nextInt(max-min+1)+min;
	}


}//class