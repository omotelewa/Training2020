package marian.revature.com.dao;

import java.io.IOException;
import java.util.List;

import marian.revature.com.models.AccountDetails;
import marian.revature.com.models.User;

public interface UserDao {

	public List<User> getAllUsers() throws IOException;
	public List<User> getAllUsersByRole(String role) throws IOException;
	public User getUserbyId(int id) throws IOException;
	public User getUserbyUsername(String username) throws IOException;
	public User getUserbyUsernameAndPassword(String username, String password) throws IOException;
	public boolean updateUser(User user) throws Exception;
	public boolean deleteUser(User user);
	User createUser(User user);
	AccountDetails createBankAccount();
	
}
