package part03;

public class AlbumTrack extends AudioFile{
	private Album record;
	
	public AlbumTrack(String title, int duration, String dataSource, Album record) {
		super(title, duration, dataSource);
		this.record = record;
	}
	
	public String toString() {
		String str = "";
		str += this.getCode() + ", ";
		str +=  this.getTitle() + ", ";
		str += this.getDuration() + ", ";
		str += this.getDataSource();
		return str;
	}
	
	public Album getAlbum() {
		return this.record;
	}
}
