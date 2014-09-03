package me.xvronny.web.dakon.model;

import java.util.ArrayList;
import java.util.List;

public class Pit {
	
	protected final Integer index;
	protected final Player player;
	protected final List<Stone> stones;
	
	public Pit(final Player player, final int index) {
		this.player = player;
		this.index = index;
		this.stones = new ArrayList<Stone>();
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getIndex() {
		return this.index;
	}

	public String getId() {
		return String.format("%s/%d",this.player.getName(), this.index);
	}
	
	public List<Stone> getStones() {
		return this.stones;
	}
	
	public boolean isEmpty() {
		return this.stones.isEmpty();
	}
	
	public void addStone(Stone stone) {
		this.stones.add(stone);
	}
	
	public List<Stone> removeAllStones() {
		List<Stone> clone = new ArrayList<>(this.stones);
		this.stones.clear();
		return clone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) 
			return false;
		if (!(obj instanceof Pit))
			return false;
		Pit that = (Pit) obj;
		return this.getId().equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

}
