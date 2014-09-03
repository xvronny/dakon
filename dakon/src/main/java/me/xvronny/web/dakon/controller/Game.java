package me.xvronny.web.dakon.controller;

import java.util.ArrayList;
import java.util.List;

import me.xvronny.web.dakon.controller.move.CapturePit;
import me.xvronny.web.dakon.controller.move.FinishGame;
import me.xvronny.web.dakon.controller.move.Move;
import me.xvronny.web.dakon.controller.move.MoveStone;
import me.xvronny.web.dakon.controller.move.SwitchPlayer;
import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Lubang;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.model.Stone;

public class Game {
	
	private final Board board;
	
	public Game(final Board board) {
		this.board = board;
	}
	
	public List<Move> executeStep(Pit chosenPit) {
		if (chosenPit instanceof Lubang) {
			throw new IllegalArgumentException("Can't move stones from LubangMenggali");
		}
		// initialize variables
		List<Move> moves = new ArrayList<>();
		Player currentPlayer = board.getCurrentPlayer();
		Pit currentPit = chosenPit;
		// iterate through all stones
		List<Stone> stones = chosenPit.removeAllStones();
		// distribute stones in a loop
		while (stones.size() > 0) {
			Stone rolling = stones.remove(0);
			// determine the recipient of the
			currentPit = board.getNext(currentPit);
			if ((currentPit instanceof Lubang) && 
					(!currentPit.getPlayer().equals(currentPlayer))) {
				// skip forward for the opponent's lubang 
				currentPit = board.getNext(currentPit);
			}
			// put rolling stone into recipient
			currentPit.addStone(rolling);
			moves.add(new MoveStone(rolling, chosenPit, currentPit));
			// check whether this is the last stone
			if (stones.size() == 0) {
				boolean ownSide = currentPit.getPlayer().equals(currentPlayer);
				boolean onLubang = currentPit instanceof Lubang;
				// first case :: if the stone fell into an empty pit on his own side
				if (ownSide && !onLubang && currentPit.getStones().size() == 1) {
					// capture own pit and opposing pit
					Pit oppositePit = board.getOpposite(currentPit);
					Lubang lubang = board.getLubangForCurrentPlayer();
					Stone ownStone = currentPit.removeAllStones().get(0);
					lubang.addStone(ownStone);
					List<Stone> capturedStones = oppositePit.removeAllStones();
					lubang.addAllStones(capturedStones);
					moves.add(new CapturePit(currentPit, ownStone, oppositePit, capturedStones, lubang));
					
				}
				// second case :: if the last stone fell into player's own lubang 
				else if (ownSide && onLubang) {
					// do nothing (have another turn)
				}
				// check whether the current player has won the game
				else if (board.isFinished()) {
					moves.add(new FinishGame(board.getCurrentPlayer()));
				}
				// default case :: if the last stone fell into enemy territory or
				// on to a non empty pit. This is executed on the last stone so 
				// there won't be any effect to the loop.
				else {
					board.switchCurrentPlayer();
					moves.add(new SwitchPlayer(board.getCurrentPlayer()));
				}
			}
		}
		
		// return changes to the UI
		return moves;
		
	}

}
