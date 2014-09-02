package me.xvronny.web.dakon.controller;

import java.util.List;

import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.LubangMenggali;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.model.Stone;

public class Game {
	
	private final Board board;
	
	public Game(final Board board) {
		this.board = board;
	}
	
	public boolean isFinished() {
		return board.isPlayerFinished(board.getCurrentPlayer());
	}
	
	public void moveStonesFrom(Pit chosenPit) {
		if (chosenPit instanceof LubangMenggali) {
			throw new IllegalArgumentException("Can't move stones from LubangMenggali");
		}
		Player currentPlayer = board.getCurrentPlayer();
		Pit current = chosenPit;
		// iterate through all stones
		List<Stone> stones = chosenPit.removeAllStones();
		// distribute stones in a loop
		while (stones.size() > 0) {
			Stone rolling = stones.remove(0);
			// determine the recipient of the
			Pit recipient = board.getNext(current);
			if ((recipient instanceof LubangMenggali) && 
					(!recipient.getPlayer().equals(currentPlayer))) {
				// skip forward for the opponent's lubang 
				recipient = board.getNext(recipient);
			}
			// put rolling stone into recipient
			recipient.addStone(rolling);
			// check whether this is the last stone
			if (stones.size() == 0) {
				boolean ownSide = recipient.getPlayer().equals(currentPlayer);
				boolean onLubang = recipient instanceof LubangMenggali;
				// first case :: if the stone fell into an empty pit on his own side
				if (ownSide && !onLubang && recipient.getStones().size() == 1) {
					// capture own pit and opposing pit
					Pit opposite = board.getOpposite(recipient);
					LubangMenggali lubang = board.getLubangForPlayer(board.getCurrentPlayer());
					lubang.addAllStones(recipient.removeAllStones());
					lubang.addAllStones(opposite.removeAllStones());
				}
				// second case :: if the last stone fell into player's own lubang 
				else if (ownSide && onLubang) {
					// do nothing (have another turn)
				}
				// default case :: if the last stone fell into enemy territory or
				// on to a non empty pit
				else {
					// TODO switchCurrentPlayer();
				}
			}
		}
		
	}

}
