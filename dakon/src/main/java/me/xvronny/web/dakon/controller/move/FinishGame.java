package me.xvronny.web.dakon.controller.move;

import me.xvronny.web.dakon.model.Player;

public class FinishGame extends Move {
	
	private Player winningPlayer;
	
	public FinishGame(final Player winningPlayer) {
		super("finish");
		this.winningPlayer = winningPlayer;
	}
	
	public Player getWinningPlayer() {
		return winningPlayer;
	}

}
