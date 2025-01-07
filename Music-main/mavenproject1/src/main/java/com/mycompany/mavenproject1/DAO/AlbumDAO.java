package DMB.PRJ.MusicBack.dao;

import java.util.List;

import DMB.PRJ.MusicBack.dto.Album;

public interface AlbumDAO {
	List<Album> listAllAlbums();
	List<Album> listGenreAlbums(String genre);
	List<Album> listArtistAlbums(String artist);
	List<Album> listActiveAlbums();
	List<Album> listLangAlbums(String lang);
	Album get(String artist, String album);
	boolean add(Album a);
	boolean update(Album a);
	boolean delete(Album a);
	
	String trendingAlbumName();
	String trendingAlbumPic();
	String trendingAlbumArtist();
	
	String latestAlbumName();
	String latestAlbumPic();
	String latestAlbumArtist();
}
