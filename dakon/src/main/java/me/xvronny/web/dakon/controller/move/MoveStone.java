package me.xvronny.web.dakon.controller.move;

import lombok.Getter;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Stone;
import me.xvronny.web.dakon.model.Store;

/**
 * Normal move indicating stone movement from one pit to another or to the Store.
 */
@Getter
public class MoveStone extends AbstractMove {

	/**
	 * Where the stone came from.
	 */
	private final Pit origin;

	/**
	 * Where the stone is being moved into.
	 */
	private final Store destination;

	/**
	 * Stone being moved around.
	 */
	private final Stone stone;

	public MoveStone(final Pit origin, final Store destination, final Stone stone) {
		super("move");
		this.stone = stone;
		this.origin = origin;
		this.destination = destination;
	}
}
