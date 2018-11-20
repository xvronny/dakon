package me.xvronny.web.dakon.controller.move;

import lombok.Builder;
import lombok.Getter;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Stone;
import me.xvronny.web.dakon.model.Store;

import java.util.List;

/**
 * Move for capturing the opponent pit.
 */
@Builder
@Getter
public class CapturePit extends AbstractMove {

	/**
	 * The final pit where we encounter empty pit.
	 */
	private final Pit ownPit;

	/**
	 * The stone moving to ownPit, will be moved to target.
	 */
	private final Stone ownStone;

	/**
	 * Opponent's pit which is the counterpart of ownPit.
	 */
	private final Pit capturedPit;

	/**
	 * All stones which were located in capturedPit.
	 */
	private final List<Stone> capturedStones;

	/**
	 * Destination of ownStone and captureStones.
	 */
	private final Store target;

	public CapturePit(final Pit ownPit, final Stone ownStone,
					  final Pit capturedPit, final List<Stone> capturedStones,
					  final Store target) {
		super("capture");
		this.ownPit = ownPit;
		this.ownStone = ownStone;
		this.capturedPit = capturedPit;
		this.capturedStones = capturedStones;
		this.target = target;
	}

}
