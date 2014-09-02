package me.xvronny.web.dakon.model;

public class Player {
	
	private final String name;

	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (!(obj instanceof Player))
			return false;
		Player that = (Player) obj;
		return this.name.equals(that.name);
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

}
