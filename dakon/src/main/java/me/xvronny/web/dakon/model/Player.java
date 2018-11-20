package me.xvronny.web.dakon.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Representation of the movable stones in the gameplay.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Player {

	/**
	 * Identifier of the player displayed in the Web UI.
	 */
	private final String name;

}
