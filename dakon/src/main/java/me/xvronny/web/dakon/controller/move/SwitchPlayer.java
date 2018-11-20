package me.xvronny.web.dakon.controller.move;

import lombok.Getter;
import me.xvronny.web.dakon.model.Player;

/**
 * Move indicating time to switch players.
 */
@Getter
public class SwitchPlayer extends AbstractMove {

	/**
	 * The player in control *after* the shift change.
	 */
	private final Player currentPlayer;

	public SwitchPlayer(final Player currentPlayer) {
		super("switch");
		this.currentPlayer = currentPlayer;
	}
}

