package part03;

/**
 * This is an interface for the AudioFile class which has the following methods
 * Javadoc for these methods can be found in the AudioFile class
 * 
 * @author Corey Lynch - 40363992
 * @version V1.0
 *
 */
public interface iAudio {
	public int getCode();

	public String getTitle();

	public int getDuration();

	public String getDataSource();

	public int getPlayCount();

	public void registerPlay();

	public String toString();
}
