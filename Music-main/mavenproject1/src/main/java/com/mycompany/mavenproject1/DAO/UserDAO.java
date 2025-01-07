package DMB.PRJ.MusicBack.dao;

import java.util.List;

import DMB.PRJ.MusicBack.dto.User;

public interface UserDAO {
	List<User> list();
	User get(String email);
	boolean add(User u);
	boolean update(User u);
	String loggedUser();
	String loggedUserRole();
}
