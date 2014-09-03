package me.xvronny.web.dakon.model;

import java.util.List;

public class Lubang extends Pit {

	public Lubang(final Player player) {
		super(player, Board.PIT_PER_PLAYER);
	}
	
	public void addAllStones(List<Stone> stones) {
		this.stones.addAll(stones);
	}

}
