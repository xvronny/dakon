package me.xvronny.web.dakon.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import me.xvronny.web.dakon.controller.move.CapturePit;
import me.xvronny.web.dakon.controller.move.FinishGame;
import me.xvronny.web.dakon.controller.move.AbstractMove;
import me.xvronny.web.dakon.controller.move.MoveStone;
import me.xvronny.web.dakon.controller.move.SwitchPlayer;
import me.xvronny.web.dakon.model.*;
import me.xvronny.web.dakon.model.Store;

/**
 * Game Controller keeps track of business logic procedures to change the model state
 * and keeping track of UI updates.
 */
@AllArgsConstructor
public class GameController {

	/**
	 * The board in use for the game.
	 */
	private Board board;

	/**
	 * Returns all the UI updates to be done in the web pages.
	 *
	 * @param chosenPit
	 * @return gameMoves
	 */
	public List<AbstractMove> getMovesForChosenPit(final Pit chosenPit) {
		List<AbstractMove> moves = new ArrayList<>();

		// iterate through all stones
		List<Stone> stones = chosenPit.popAllStones();
		Store destination = board.getNextDestination(chosenPit);
		;

		while (stones.size() > 0) {

			// put rolling stone into recipient
			Stone rolling = stones.remove(0);
			destination.addStone(rolling);
			moves.add(new MoveStone(chosenPit, destination, rolling));

			if (stones.size() == 0) {
				moves.addAll(getMovesForLastStone(destination));
			}

			// move to the next pit on the same side.
			destination = board.getNextDestination(destination);
		}

		// check if the next stone is in the store.
		return moves;
	}

	/**
	 * Special procedure for last stone being moved on the board on 1 go.
	 *
	 * @param finalDestination
	 * @return gameMoves
	 */
	public List<AbstractMove> getMovesForLastStone(final Store finalDestination) {
		List<AbstractMove> extras = new ArrayList<>();

		Player currentPlayer = board.getCurrentPlayer();
		boolean ownSide = finalDestination.getPlayer().equals(currentPlayer);
		boolean normalPit = finalDestination instanceof Pit;

		// If the stone fell into an empty pit on his own side
		if (ownSide && normalPit && finalDestination.getStones().size() == 1) {

			// capture own pit and opposing pit
			Pit finalPit = (Pit) finalDestination;
			Stone ownStone = finalPit.popAllStones().get(0);

			Pit oppositePit = board.getOpposite(finalPit);
			List<Stone> capturedStones = oppositePit.popAllStones();

			Store targetStorage = board.getStore(currentPlayer);
			targetStorage.addStone(ownStone);

			capturedStones.stream().forEach(stone -> targetStorage.addStone(stone));
			extras.add(new CapturePit(finalPit, ownStone, oppositePit, capturedStones, targetStorage));


		} else if (ownSide && !normalPit) {
			// second case :: if the last stone fell into player's own store do nothing

		} else if (board.isCompleted()) {
			// check whether the game is over
			extras.addAll(getMovesForBoardCompletion());

		} else {
			// default case :: if the last stone fell into enemy territory or
			// on to a non empty pit. This is executed on the last stone so
			// there won't be any effect to the loop.
			Player opposingPlayer = board.getOpposingPlayer(currentPlayer);
			board.setCurrentPlayer(opposingPlayer);

			extras.add(new SwitchPlayer(opposingPlayer));
		}

		return extras;
	}

	/**
	 * Special moves for when the game is wrapping up because one of the side is completely empty.
	 *
	 * @return gameMoves
	 */
	public List<AbstractMove> getMovesForBoardCompletion() {
		List<AbstractMove> finish = new ArrayList<>();

		board.getAllPits().stream().forEach(pit -> {
			Store store = board.getStore(pit.getPlayer());
			pit.popAllStones().stream().forEach(stone -> {
				finish.add(new MoveStone(pit, store, stone));
			});

		});

		finish.add(new FinishGame(board.getWinningPlayer()));
		return finish;

	}
}