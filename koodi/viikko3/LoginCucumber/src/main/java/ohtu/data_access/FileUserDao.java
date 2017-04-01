package ohtu.data_access;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import ohtu.domain.User;

public class FileUserDao implements UserDao {
	private String filename;
	
	public FileUserDao(String filename) throws IOException {
		this.filename = filename;
		
		new File(filename).createNewFile();
		writeUsers(new ArrayList());
	}
	
	@Override
	public List<User> listAll() {
		ArrayList<User> users = readUsers();
		
		return users;
	}
	
	@Override
	public User findByName(String name) {
		ArrayList<User> users = readUsers();
		
		for (User user : users) {
			if (user.getUsername().equals(name)) {
				return user;
			}
		}
		
		return null;
	}
	
	@Override
	public void add(User user) {
		ArrayList<User> users = readUsers();
		
		users.add(user);
		
		writeUsers(users);
	}
	
	private ArrayList<User> readUsers() {
		ArrayList<User> users;
		
		try {
			FileInputStream fileIn = new FileInputStream(this.filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return users;
	}
	
	private void writeUsers(ArrayList<User> users) {
		try {
			FileOutputStream fileOut = new FileOutputStream(this.filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(users);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
