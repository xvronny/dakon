package me.xvronny.web.dakon.model;

import java.util.List;

public class LubangMenggali extends Pit {

	public LubangMenggali(Player player) {
		super(player);
	}
	
	public void addAllStones(List<Stone> stones) {
		this.stones.addAll(stones);
	}

}
