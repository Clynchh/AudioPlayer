package part03;

import audio.AudioPlayer;

/**
 * This is an interface for the AudioManager class which has the following
 * methods Javadoc for these methods can be found in the AudioManager class
 * 
 * @author Corey Lynch - 40363992
 * @version V1.0
 *
 */
public interface iManager {
	public void loadAudio(AudioFile af);

	public void deleteAudio(int index);

	public String[] getAllData();

	public String play(AudioFile af);

	public String play(int audioId);

	public String topTen();

	public void setPlayer(AudioPlayer p);
}
