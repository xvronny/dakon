package me.xvronny.web.dakon.controller.move;

import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Stone;

public class MoveStone extends Move {

	private final Pit origin;
	private final Pit destination;
	private final Stone stone;
	
	public MoveStone(Stone stone, Pit origin, Pit destination) {
		super("move");
		this.stone = stone;
		this.origin = origin;
		this.destination = destination;
	}
	
	public Stone getStone() {
		return stone;
	}
	
	public Pit getOrigin() {
		return origin;
	}
	
	public Pit getDestination() {
		return destination;
	}
	
}
