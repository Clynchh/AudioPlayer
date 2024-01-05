package part03;

/**
 * This class represents an audio file which will be managed by the audio
 * manager
 * 
 * @author Corey Lynch - 40363992
 * @version V1.0
 *
 */
public class AudioFile {
	private int code;
	static private int nextCode = 1;
	private String title;
	private int duration;
	private String dataSource;
	private int playCount = 0;

	/**
	 * Constructor for the AudioFile class
	 * 
	 * @param title      - title of the audioFile
	 * @param duration   - duration of the audioFile
	 * @param dataSource - file path of the audioFile
	 */
	public AudioFile(String title, int duration, String dataSource) {
		this.title = title;
		this.duration = duration;
		this.dataSource = dataSource;
		this.code = nextCode;// each new instance of an audioFile will have a code that is one greater than
								// the last
		nextCode++;
	}

	/**
	 * gives an audioFile's contents as a string
	 * 
	 * @return - a string of an audioFile's code, title, duration and file path
	 */
	public String toString() {
		String str = "";
		str += this.code + ", ";
		str += this.title + ", ";
		str += this.duration + ", ";
		str += this.dataSource;
		return str;
	}

	/**
	 * increments an audioFile's playCount when it is played
	 */
	public void registerPlay() {
		playCount++;
	}

	/**
	 * @return - an audioFile's code
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * @return - an audioFile's title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @return - an audioFile's duration
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * @return - an audioFile's file path
	 */
	public String getDataSource() {
		return this.dataSource;
	}

	/**
	 * @return - an audioFile's playCount
	 */
	public int getPlayCount() {
		return playCount;
	}
}
