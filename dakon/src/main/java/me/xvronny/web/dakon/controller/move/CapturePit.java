package me.xvronny.web.dakon.controller.move;

import java.util.List;

import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Stone;

public class CapturePit extends Move {
	
	private final Pit finalPit;
	private final Pit capturedPit;
	private final List<Stone> captives;
	
	public CapturePit(Pit finalPit, Pit capturedPit, List<Stone> captives) {
		this.finalPit = finalPit;
		this.capturedPit = capturedPit;
		this.captives = captives;
	}
	
	public List<Stone> getCaptives() {
		return captives;
	}
	
	public Pit getFinalPit() {
		return finalPit;
	}
	
	public Pit getCapturedPit() {
		return capturedPit;
	}

}
