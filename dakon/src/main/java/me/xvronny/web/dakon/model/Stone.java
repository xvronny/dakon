package me.xvronny.web.dakon.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Representation of the movable stones in the gameplay.
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Stone {

	/**
	 * Identifier for the movable stones. Not strictly required but useful for debugging.
	 */
	private String uuid;

	/**
	 * Simple static method used during board initialization.
	 * @return newInstance
	 */
	public static Stone newInstance() {
		return new Stone(UUID.randomUUID().toString());
	}

}