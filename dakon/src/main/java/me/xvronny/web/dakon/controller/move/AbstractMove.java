package me.xvronny.web.dakon.controller.move;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Abstract class for all classes representing UI view updates.
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class AbstractMove {

	/**
	 * Move name, identifies type of game movement.
	 */
	private final String name;

}
