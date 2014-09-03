package me.xvronny.web.dakon.controller.move;

import java.util.List;

import me.xvronny.web.dakon.model.Lubang;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Stone;

public class CapturePit extends Move {
	
	private final Pit ownPit;
	private final Stone ownStone;
	private final Pit capturedPit;
	private final List<Stone> capturedStones;
	private final Lubang lubang;
	
	public CapturePit(Pit ownPit, Stone ownStone, Pit capturedPit, List<Stone> capturedStones, Lubang lubang) {
		super("capture");
		this.ownPit = ownPit;
		this.lubang = lubang;
		this.ownStone = ownStone;
		this.capturedPit = capturedPit;
		this.capturedStones = capturedStones;
	}
	
	public Pit getOwnPit() {
		return ownPit;
	}
	
	public Stone getOwnStone() {
		return ownStone;
	}
	
	public Pit getCapturedPit() {
		return capturedPit;
	}
	
	public List<Stone> getCapturedStones() {
		return capturedStones;
	}
	
	public Lubang getLubang() {
		return lubang;
	}

}
