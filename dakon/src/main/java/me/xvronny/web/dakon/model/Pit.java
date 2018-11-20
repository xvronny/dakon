package me.xvronny.web.dakon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Pit where stones can be moved into or taken from by the players.
 */
@EqualsAndHashCode
@Getter
public class Pit extends Store {

	/**
	 * Position of the pit on the side side of the board, counter clockwise.
	 */
	protected int index;

	public Pit(final Player player, final int index) {
		super(player);
		this.index = index;
		this.id = String.format("%s_%d",this.player.getName(), this.index);
	}

	public void initializePit(final int nbStones) {
		for (int i=0; i < nbStones; i++) {
			this.addStone(Stone.newInstance());
		}
	}

	public List<Stone> popAllStones() {
		List<Stone> removedStones = new ArrayList<>(this.stones);
		this.stones.clear();
		return removedStones;
	}

}

