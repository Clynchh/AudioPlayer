package part03;

import audio.AudioPlayer;
import java.util.ArrayList;

/**
 * This class represents an audio manager to be used in QUB media
 * 
 * @author Corey Lynch - 40363992
 * @version V1.0
 *
 */
public class AudioManager {
	private ArrayList<AlbumTrack> audioData = new ArrayList<AlbumTrack>();// an array list of albumTracks
	private AudioPlayer player;

	/**
	 * Constructor for the AudioManager class
	 * 
	 * @param player - an AudioPlayer reference
	 */
	public AudioManager(AudioPlayer player) {
		setPlayer(player);
	}

	/**
	 * Add an albumTrack into the audioData array list
	 * 
	 * @param af - an albumTrack reference
	 */
	public void loadAudio(AlbumTrack at) {
		audioData.add(at);
	}

	/**
	 * Delete a file using an albumTrack code
	 * 
	 * @param code - code of an albumTrack to be deleted
	 */
	public void deleteAudio(int code) {
		if (code > audioData.size()) {
			System.out.println("No file with code " + code + " exists.");
		}
		for (int i = 0; i < audioData.size(); i++) {
			if (code == audioData.get(i).getCode()) {
				audioData.remove(i);
				System.out.println("OK - Deleted Track " + (i + 1));
			}
		} // search through every albumTrack in audioData and if the codes match, delete
			// that file
	}

	/**
	 * Get the information from all albumTracks and add them to an array
	 * 
	 * @return - an array of albumTrack info as strings
	 */
	public String[] getAllData() {
		String[] list = new String[15];// user can enter 15 albumTracks at the most
		for (int i = 0; i < audioData.size(); i++) {
			list[i] = audioData.get(i).toString();
		}
		return list;
	}

	/**
	 * Play a file using an albumTrack reference
	 * 
	 * @param af - an albumTrack reference
	 * @return - a string telling the user if their file could be played or not
	 */
	public String play(AlbumTrack at) {
		String str = player.playFile(at.getDataSource());
		at.registerPlay();
		if (str == "OK") {
			str = "File " + at.getTitle() + " is playing";
		} else {
			str = "Couldn't play file " + at.getTitle();
		}
		return str + "\n\n";
	}

	/**
	 * Play a file using an albumTrack code by getting its corresponding albumTrack
	 * reference
	 * 
	 * @param code - code of an albumTrack entered by the user
	 * @return - a string telling the user if their file could be played or not
	 */
	public String play(int code) {
		if (code > audioData.size()) {
			return "No file with code " + code + " exists.";
		}
		String str = "";
		for (int i = 0; i < audioData.size(); i++) {
			if (code == audioData.get(i).getCode()) {
				str += play(audioData.get(i));
				return str;
			}
		} // search through every albumTrack in audioData and if the codes match, play
			// that
			// file
		return "";
	}

	/**
	 * Bubble sort an array list of (10 or less) albumTracks by playCount
	 * 
	 * @return - a string of albumTracks by playCount descending
	 */
	public String topTen() {
		String str = "";
		ArrayList<AlbumTrack> copy = audioData;// create a copy of audioData so its contents are not affected by the
												// topTen method
		// bubble sort
		int swaps;
		do {
			swaps = 0;
			for (int i = 0; i < (copy.size() - 1); i++) {
				int j = i + 1;
				if (copy.get(i).getPlayCount() < copy.get(j).getPlayCount()) {
					AlbumTrack tmp = copy.get(i);
					copy.set(i, copy.get(j));
					copy.set((j), tmp);
					swaps++;
				}
			}
		} while (swaps > 0);
		// end of bubble sort
		int dataLength;
		if (copy.size() < 10) {
			dataLength = copy.size();// if the length of audioData is less than 10, don't loop any further than that
										// length
			if (dataLength == 0) {
				return "No audio files to display.";
			}
		} else {
			dataLength = 10;
		}
		for (int i = 0; i < dataLength; i++) {
			str += "Track " + copy.get(i).getCode() + ": ";
			str += copy.get(i).getTitle() + " ";
			str += "Plays: " + copy.get(i).getPlayCount() + "\n";
		} // adds the code, title and playCount of each albumTrack in the sorted audio
			// data
			// to a string
		return str;
	}

	/**
	 * @return - an arrayList of albumTracks
	 */
	public ArrayList<AlbumTrack> getAudioData() {
		return audioData;
	}

	/**
	 * Used to assign an AudioPlayer to the AudioManager
	 * 
	 * @param p - an AudioPlayer reference
	 */
	public void setPlayer(AudioPlayer p) {
		if (p != null) {
			this.player = p;
		}
	}
}
