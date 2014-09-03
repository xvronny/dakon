package me.xvronny.web.dakon.controller.move;

import me.xvronny.web.dakon.model.Player;

public class SwitchPlayer extends Move {
	
	private final Player currentPlayer;
	
	public SwitchPlayer(final Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

}
