package me.xvronny.web.dakon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Board {
	
	public static final int PIT_PER_PLAYER = 6;
	public static final int STONES_PER_PIT = 6;
	
	// layout of the pits on the board :
	//       [ 0] [ 1] [ 2] [ 3] [ 4] [ 5] { 6P} 
	// {13P} [12] [11] [10] [ 9] [ 8] [ 7]
	private final List<Pit> pits;
	
	public Board(Player playerA, Player playerB) {
		this.pits = new ArrayList<Pit>();
		for (Player player : Arrays.asList(playerA, playerB)) {
			for (int i = 0; i < PIT_PER_PLAYER; i++) {
				Pit pit = new Pit(player, i);
				for (int j = 0; j < STONES_PER_PIT; j++) {
					pit.addStone(new Stone(UUID.randomUUID().toString()));
				}
				this.pits.add(pit);
			}
			this.pits.add(new LubangMenggali(player));
		}
	}
	
	
	public Pit getNext(Pit pit) {
		if (pit == null || !this.pits.contains(pit)) {
			throw new IllegalArgumentException("Input parameter pit is null or not found");
		}
		int index = this.pits.indexOf(pit);
		if (index == this.pits.size() - 1) {
			return this.pits.get(0);
		}
		return this.pits.get(index + 1);
	}
	
	public Pit getOpposite(Pit pit) {
		if (pit == null || !this.pits.contains(pit)) {
			throw new IllegalArgumentException("Input parameter pit is null or not found");
		}
		int index = this.pits.indexOf(pit);
		//       [ 0] [ 1] [ 2] [ 3] [ 4] [ 5] { 6P} 
		// {13P} [12] [11] [10] [ 9] [ 8] [ 7]
		int opposant = (2 * PIT_PER_PLAYER) - index;
		return this.pits.get(opposant);
	}
	
	public LubangMenggali getLubangForPlayer(Player player) {
		int index = (this.pits.get(0).getPlayer().equals(player)) ? 
				PIT_PER_PLAYER : (2 * PIT_PER_PLAYER) + 1;
		return (LubangMenggali) this.pits.get(index);
	}
	
	public boolean isPlayerFinished(Player player) {
		int startIndex = (this.pits.get(0).getPlayer().equals(player)) ? 0 : PIT_PER_PLAYER + 1;
		for (int i = startIndex; i < PIT_PER_PLAYER + 1; i++) {
			if (!pits.get(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public List<Pit> getPits() {
		return this.pits;
	}
	
	public List<Stone> getAllStones() {
		List<Stone> stones = new ArrayList<>();
		for (Pit pit : this.pits) {
			stones.addAll(pit.getStones());
		}
		return stones;
	}
	
}
