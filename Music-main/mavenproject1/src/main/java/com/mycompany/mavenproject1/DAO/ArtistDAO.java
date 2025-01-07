package DMB.PRJ.MusicBack.dao;

import java.util.List;

import DMB.PRJ.MusicBack.dto.Artist;

public interface ArtistDAO {
	List<Artist> listAllArtists();
	List<Artist> listActiveArtists();
	Artist get(String name);
	boolean add(Artist a);
	boolean update(Artist a);
	boolean delete(Artist a);
	
	String topArtistPic();
	String topArtistBio();
	String topArtist();
}
