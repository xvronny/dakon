package me.xvronny.web.dakon.controller.move;

/**
 * a trace of board state change, to be used for UI changes
 * @author ronnyhendrawan
 *
 */
public class Move {

	protected final String name;
	
	protected Move(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
