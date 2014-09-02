package me.xvronny.web.dakon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.LubangMenggali;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.model.Stone;

public class Game {
	
	private final Board board;
	private final List<Player> players;
	private Player current;
	
	public Game(Player playerA, Player playerB) {
		this.players = Arrays.asList(playerA, playerB);
		this.board = new Board(playerA, playerB);
		this.current = playerA;
	}
	
	public boolean isFinished() {
		return board.isPlayerFinished(current);
	}
	
	
	public void moveStonesFrom(Pit pit) {
		if (pit instanceof LubangMenggali) {
			throw new IllegalArgumentException("Can't move stones from LubangMenggali");
		}
		Pit current = pit;
		// iterate through all stones
		List<Stone> stones = pit.removeAllStones();
		// distribute stones in a loop
		while (stones.size() > 0) {
			Stone rolling = stones.remove(0);
			// determine the recipient of the
			Pit recipient = board.getNext(current);
			if ((recipient instanceof LubangMenggali) && 
					(!recipient.getPlayer().equals(this.current))) {
				// skip forward for the opponent's lubang 
				recipient = board.getNext(recipient);
			}
			// put rolling stone into recipient
			recipient.addStone(rolling);
			// check whether this is the last stone
			if (stones.size() == 0) {
				boolean ownSide = recipient.getPlayer().equals(this.current);
				boolean onLubang = recipient instanceof LubangMenggali;
				// first case :: if the stone fell into an empty pit on his own side
				if (ownSide && !onLubang && recipient.getStones().size() == 1) {
					// capture own pit and opposing pit
				}
				// second case :: if the last stone fell into player's own lubang 
				else if (ownSide && onLubang) {
					// do nothing (have another turn)
				}
				// default case :: if the last stone fell into enemy territory or
				// on to a non empty pit
				else {
					switchCurrentPlayer();
				}
			}
		}
		
	}
	
	private void captureOppositePit(Pit recipient) {
		Pit opposite = board.getOpposite(recipient);
	}
	
	private void switchCurrentPlayer() {
		int index = players.indexOf(current);
		current =  (index == 0) ? players.get(1) : players.get(0);
	}

}
