package daoOperations;

import java.io.IOException;
import java.util.List;

import marian.revature.com.models.CustomerDetails;

public interface CustomerDao {

	public List<CustomerDetails> getUser() throws IOException;
	public CustomerDetails getUserbyId(int id) throws IOException;
	public CustomerDetails getUserbyUsername(String username) throws IOException;
	public void updateUser(CustomerDetails user);
	public void deleteUser(CustomerDetails user);
	CustomerDetails createUser();
	
}
