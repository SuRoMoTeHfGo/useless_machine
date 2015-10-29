/*
*class Analysis for lejos project, "the Useless machine"
*/


public class Analysis{
	private ColorReader leverStatus;
	private UltrasonicReader eyes;
	private SoundReader sounds;
	private Audioplayer iPod;
	public Analysis(ColorReader leverStatus,UltrasonicReader eyes, SoundReader sounds,Audioplayer iPod){
		this.leverStatus = leverStatus;
		this.eyes = eyes;
		this.sounds = sounds;
		this.iPod = iPod;
	}
	public void initialize(){

	}//initialize
}//class