package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {
	private int value;
	private String fieldName;
	
	public HasFewerThan(int value, String category) {
		this.value = value;
		this.fieldName = "get" + Character.toUpperCase(category.charAt(0)) + category.substring(1, category.length());
	}
	
	@Override
	public boolean matches(Player p) {
		try {
			Method method = p.getClass().getMethod(this.fieldName);
			int playersValue = (Integer) method.invoke(p);
			return playersValue < this.value;
		} catch (Exception e) {
			System.out.println(e);
			throw new IllegalStateException("Player does not have field " + this.fieldName.substring(3, this.fieldName.length()).toLowerCase());
		}
	}
}
