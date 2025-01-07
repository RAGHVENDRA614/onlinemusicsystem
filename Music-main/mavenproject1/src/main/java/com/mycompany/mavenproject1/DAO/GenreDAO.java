package DMB.PRJ.MusicBack.dao;

import java.util.List;

import DMB.PRJ.MusicBack.dto.Genre;

public interface GenreDAO {
	List<Genre> listActiveGenres();
	List<Genre> listAllGenres();
	Genre get(String name);
	boolean add(Genre g);
	boolean update(Genre g);
	boolean delete(Genre g);
	
	String topGenrePic();
	String topGenreDescription();
	String topGenre();
}
