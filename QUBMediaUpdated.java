package part03;

import audio.AudioPlayer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import console.Console;
import java.util.concurrent.TimeUnit;

/**
 * This is an application class that prompts the user with a menu and uses an
 * AudioManager instance to handle audioFiles
 * 
 * @author Corey Lynch - 40363992
 * @version V2.0
 *
 */
public class QUBMediaUpdated {

	private static AudioPlayer player = new AudioPlayer();
	private static AudioManager am = new AudioManager(player);
	private static Console con = new Console(true);
	private static String menuTitle = "QUB Media Player";
	private static String[] menuOptions = { "Display All Files", "Load Audio File", "Delete Audio", "Play Audio File",
			"Display Top 10", "Exit" };

	public static void main(String[] args) {

		// Setup
		con.setSize(1000, 500);
		con.setVisible(true);
		con.setTitle(menuTitle);
		con.setBgColour(Color.BLACK);
		con.setColour(Color.WHITE);
		con.setFont(new Font("Courier", Font.BOLD, 20));

		ImageIcon cushion = new ImageIcon("Images\\Cushion.png");// a plain black image printed before the album cover
																	// so the media player will appear
																	// visually centred

		int choice = getUserChoice();

		while (choice != 6)// will keep prompting the user for input until option 6 is entered which will
							// exit the program
		{
			int selectedTrack = 0;// initialising the variable outside switch to avoid problems with scope

			switch (choice) {

			/**
			 * This case displays the code, title, duration and file path of each audioFile
			 * in audioData
			 */
			case 1:
				if (am.getAllData()[0] != null) {
					con.println("<Code> , <Title>, <Duration>, <Data Source>");
					for (int i = 0; i < am.getAllData().length; i++) {
						if (am.getAllData()[i] != null) {
							con.println(am.getAllData()[i]);
						}
					}
				} else {
					con.println("No audio files to display.");
				}
				break;

			/**
			 * This case prompts the user for the title, duration and file path of a .wav
			 * file in order to instantiate a new audioFile
			 * 
			 */
			case 2:
				con.println("Enter the title of the new audio: ");
				String title = con.readLn();
				con.println("Enter the artist of the new audio: ");
				String artist = con.readLn();
				con.println("Enter the file path of the album cover (.png): ");
				String filename = con.readLn();
				ImageIcon cover = new ImageIcon(filename);
				Album album = new Album(title, artist, cover);

				int z = 1;
				int duration = 0;
				do {
					con.println("Enter the duration of the new audio: ");
					try {
						String strDuration = con.readLn();
						duration = Integer.parseInt(strDuration);
						z = 2;
					} catch (Exception e) {
						con.println("You must enter an integer.");
					} // keeps looping if the user doesn't enter an integer and promts them to do so
				} while (z == 1);

				con.println("Enter the file path of the new audio (.wav): ");
				String dataSource = con.readLn();
				AlbumTrack at = new AlbumTrack(title, duration, dataSource, album);
				am.loadAudio(at);
				break;

			/**
			 * This case prompts the user for the code of an audioFile and deletes it if it
			 * exists
			 */
			case 3:
				int y = 1;// temporary variable that allows us to escape the do-while loop if the user
							// behaves correctly (enters and integer)
				do {
					con.println("Enter the code of an audio file you wish to delete.");
					try {
						String strSelectedTrack = con.readLn();
						selectedTrack = Integer.parseInt(strSelectedTrack);
						y = 2;
					} catch (Exception e) {
						con.println("You must enter an integer.");
					} // keeps looping if the user doesn't enter an integer and promts them to do so
				} while (y == 1);
				am.deleteAudio(selectedTrack);// delete the selected audioFile
				break;

			/**
			 * This case prompts the user for the code of an audioFile and plays it if it
			 * exists. Also gives the user a message if the file they selected could be
			 * played or not
			 */
			case 4:
				int x = 1;// temporary variable that allows us to escape the do-while loop if the user
							// behaves correctly (enters and integer)
				do {
					con.println("Enter the code of an audio file you wish to play.");
					try {
						String strSelectedTrack = con.readLn();
						selectedTrack = Integer.parseInt(strSelectedTrack);
						x = 2;
					} catch (Exception e) {
						con.println("You must enter an integer.");
					} // keeps looping if the user doesn't enter an integer and promts them to do so
				} while (x == 1);
				con.println(am.play(selectedTrack));// play the selected audioFile

				// setting up a new console window
				Console temp = new Console(true);
				temp.setTitle("Now playing: ");
				temp.setSize(750, 500);
				temp.setVisible(true);
				temp.setBgColour(Color.BLACK);
				temp.setColour(Color.WHITE);
				temp.setFont(new Font("Courier", Font.BOLD, 20));

				// getting info about the audioFile currently playing
				Album currentAlbum = am.getAudioData().get(selectedTrack - 1).getAlbum();
				temp.print(cushion);
				temp.println(currentAlbum.getCover());
				printSpaces(16, temp);
				temp.println(currentAlbum.toString());
				int songLength = am.getAudioData().get(selectedTrack - 1).getDuration();
				printSpaces(6, temp);

				// a 'duration elapsed' bar will be gradually printed out as the user listens to
				// a song
				// time past will be symbolised by a '*' each of which represents 1% of the
				// song's length
				// e.g. if the song is 100 seconds long, a '*' will be printed out every second
				int wait = songLength * 10;// formula to find 1% of the song's length, rounded to the
															// nearest integer
				temp.println("Duration Elapsed (each * is 1% of song duration): ");
				for (int i = 0; i < 100; i++) {
					try {
						TimeUnit.MILLISECONDS.sleep(wait);
						temp.print("*");
						if(i % 25 ==0) {
							temp.print("\n");
						}
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
				System.out.println("\n");

				// if the user reaches the end of a song the message below will be printed to
				// let them know
				// this message will be left up for a second before the console closes
				temp.println("Song finished");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				break;

			/**
			 * This case displays a string of existing audioFile titles, sorted by playCount
			 * in descending order (most played audioFile appears at the top)
			 */
			case 5:
				con.print(am.topTen());
				break;

			/**
			 * This is the default case which ensures any input other than integers 1->6 are
			 * not accepted by the system and prompts the user to select another option
			 */
			default:
				con.println("Invalid option: " + choice);
				break;
			}

			choice = getUserChoice();// gets the user's choice once the selected case has been executed
		}
	}

	/**
	 * prints out a menu on the console window for the user
	 */
	public static void display() {
		con.println(menuTitle);
		for (int i = 0; i < menuTitle.length(); i++) {
			con.print("+");
		}
		con.println("");
		for (int option = 1; option <= menuOptions.length; option++) {
			con.println(option + ". " + menuOptions[option - 1]);
		}
	}

	/**
	 * displays the menu to the user then prompts them for an integer response if
	 * anything but an integer in input, it will ask the user to try again
	 * 
	 * @return - the user's selected integer
	 */
	public static int getUserChoice() {
		boolean ok = false;
		int choice = 0;
		do {
			display();
			con.print("Enter Selection: ");
			String strChoice = con.readLn();
			try {
				choice = Integer.parseInt(strChoice);
				ok = true;
			} catch (Exception ex) {
				con.println("Bad Input: " + strChoice + ". Try again.");
			}
		} while (!ok);
		return choice;

	}

	/**
	 * prints a number of spaces to a console window, to make the media player seem
	 * visually centred
	 * 
	 * @param spaces - the number of spaces to be printed to the console
	 * @param con    - the console window the spaces need to be printed in
	 */
	public static void printSpaces(int spaces, Console con) {
		for (int i = 0; i < spaces; i++) {
			con.print(" ");
		}
	}

}
