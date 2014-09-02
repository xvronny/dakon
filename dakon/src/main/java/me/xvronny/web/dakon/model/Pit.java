package me.xvronny.web.dakon.model;

import java.util.ArrayList;
import java.util.List;

public class Pit {
	
	protected final Player player;
	protected final List<Stone> stones;
	
	public Pit(final Player player) {
		this.player = player;
		this.stones = new ArrayList<Stone>();
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

	public Player getPlayer() {
		return this.player;
	}

}
