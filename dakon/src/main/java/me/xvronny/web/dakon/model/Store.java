package me.xvronny.web.dakon.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The pit store on either side of the board. Can receive stones but could not let stones be taken from it.
 */
@EqualsAndHashCode
@Getter
public class Store {

	protected String id;
	protected Player player;
	protected List<Stone> stones;

	public Store(final Player player) {
		this.player = player;
		this.stones = new ArrayList<>();
		this.id = String.format("%s_%s",this.player.getName(), getClass().getSimpleName());
	}

	public void addStone(final Stone stone) {
		this.stones.add(stone);
	}

	public boolean isEmpty() {
		return this.stones.isEmpty();
	}

}
