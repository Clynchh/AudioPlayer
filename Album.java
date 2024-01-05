package part03;

import javax.swing.ImageIcon;

public class Album {
	private String title;
	private String artist;
	private ImageIcon cover;
	
	public Album(String title, String artist, ImageIcon cover) {
		this.title = title;
		this.artist = artist;
		this.cover = cover;		
	}
	
	public String toString() {
		String str = "";
		str += getTitle() + " by ";
		str += getArtist();
		return str;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public ImageIcon getCover() {
		return this.cover;
	}
	
}
