package DMB.PRJ.MusicBack.dao;

import java.util.List;

import DMB.PRJ.MusicBack.dto.Cart;

public interface CartDAO {
	boolean add(Cart c);
	boolean update(Cart c);
	boolean delete(Cart c);
	Cart get(String email, String path);
	Cart getActive(String email, String path);
	List<Cart> listBoughtSongs(String email);
	List<Cart> listBoughtAlbums(String email);
	List<Cart> listSongs(String email);
	List<Cart> listAlbums(String email);
	int price(String datefrom, String dateto);
	int priceTill(String path, String datefrom, String dateto);
	int numberSold(String path, String datefrom, String dateto);
}
