package DMB.PRJ.MusicBack.dao;

import java.util.List;

import DMB.PRJ.MusicBack.dto.Song;

public interface SongDAO {
	List<Song> listAllSongs();
	List<Song> listAlbumSongs(String album, String artist);
	Song get(String artist, String album, int track);
	boolean add(Song s);
	boolean update(Song s);
	
	String latestSongName();
	String latestSongArtist();
	String latestSongAlbum();
	String latestSongNumber();
	String trendingSongName();
	String trendingSongArtist();
	String trendingSongAlbum();
	String trendingSongNumber();
}
