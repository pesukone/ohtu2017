package statistics.matcher;

import java.util.ArrayList;

public class QueryBuilder {
	private ArrayList<Matcher> query;
	
	public QueryBuilder() {
		this.query = new ArrayList<>();
	}
	
	public QueryBuilder playsIn(String team) {
		this.query.add(new PlaysIn(team));
		return this;
	}
	
	public QueryBuilder hasAtLeast(int value, String category) {
		this.query.add(new HasAtLeast(value, category));
		return this;
	}
	
	public QueryBuilder hasFewerThan(int value, String category) {
		this.query.add(new HasFewerThan(value, category));
		return this;
	}
	
	public QueryBuilder oneOf(Matcher... matchers) {
		this.query.add(new Or(matchers));
		return this;
	}
	
	public Matcher build() {
		Matcher[] operands = new Matcher[this.query.size()];
		operands = this.query.toArray(operands);
		this.query = new ArrayList();
		return new And(operands);
	}
}
