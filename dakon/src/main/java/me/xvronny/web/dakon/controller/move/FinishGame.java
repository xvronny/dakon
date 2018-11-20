package me.xvronny.web.dakon.controller.move;

import lombok.Getter;
import me.xvronny.web.dakon.model.Player;

/**
 * Move that signifies that someone is winning.
 */
@Getter
public class FinishGame extends AbstractMove {

	private final Player winningPlayer;

	public FinishGame(final Player winningPlayer) {
		super("finish");
		this.winningPlayer = winningPlayer;
	}
}
