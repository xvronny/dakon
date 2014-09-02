package me.xvronny.web.dakon.model;

import java.util.List;

public class LubangMenggali extends Pit {

	public LubangMenggali(final Player player) {
		super(player, Board.PIT_PER_PLAYER);
	}
	
	public void addAllStones(List<Stone> stones) {
		this.stones.addAll(stones);
	}

}
