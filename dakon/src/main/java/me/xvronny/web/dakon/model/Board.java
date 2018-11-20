package me.xvronny.web.dakon.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Representation of the board in play. Each board has two sides, one for each player.
 */
public class Board {

	/**
	 * The board size is the number of pits and stones in each of them.
	 */
	public static final int BOARD_SIZE = 6;

	/**
	 * Mapping of player to all the pits belonging to that player.
	 */
	private Map<Player, List<Pit>> pits;

	/**
	 * Stores on either side of the board.
	 */
	private Store storeA;
	private Store storeB;

	/**
	 * Board has to remember which player has to move next.
	 */
	private Player currentPlayer;

	public Board(final Player playerA, final Player playerB) {

		this.storeA = new Store(playerA);
		this.storeB = new Store(playerB);

		this.pits = new HashMap<>();
		pits.put(playerA, initializePitsFor(playerA));
		pits.put(playerB, initializePitsFor(playerB));

		// randomly select current player
		int index = (int) Math.round(Math.random());
		this.currentPlayer = getPlayers().get(index);

	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(final Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	// Game mechanics

	public boolean isCompleted() {
		return this.pits.values()
				.stream()
				.anyMatch(side -> {
					return side.stream().allMatch(Pit::isEmpty);
				});
	}

	public Player getWinningPlayer() {
		return Arrays.asList(storeA, storeB).stream()
				.max(Comparator.comparing(
						store -> store.getStones().size())
				).get().getPlayer();
	}

	public Player getFirstPlayer() {
		return storeA.getPlayer();
	}

	public List<Player> getPlayers() {
		return Arrays.asList(storeA.getPlayer(), storeB.getPlayer());
	}

	public Optional<Pit> getPitById(final String pitId) {
		return this.pits.values().stream()
				.flatMap(List::stream)
				.filter(p -> p.getId().equals(pitId))
				.findFirst();
	}

	public List<Pit> getAllPits() {
		return this.pits.values().stream()
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public List<Store> getAllStorable() {
		List<Store> storables = new ArrayList<>();
		Arrays.asList(storeA, storeB).stream().forEach(store -> {
			this.pits.get(store.getPlayer()).stream()
					.forEach(pit -> storables.add(pit));
			storables.add(store);
		});
		return storables;
	}

	public Store getStore(final Player player) {
		// If current player is player A, we return playerB
		if (player.equals(storeA.getPlayer())) {
			return storeA;
		}
		return storeB;
	}

	public Player getOpposingPlayer(final Player player) {
		// If current player is player A, we return playerB
		if (player.equals(storeA.getPlayer())) {
			return storeB.getPlayer();
		}
		return storeA.getPlayer();
	}

	public Pit getOpposite(final Pit pit) {
		Player opposition = getOpposingPlayer(pit.getPlayer());
		int complementIndex = BOARD_SIZE - pit.getIndex() - 1;
		return this.pits.get(opposition).get(complementIndex);
	}

	public Pit getAdjacent(final Pit pit) {

		int index = pit.getIndex() + 1;
		if (index < BOARD_SIZE) {
			List<Pit> playerPits = this.pits.get(pit.getPlayer());
			return playerPits.get(index);
		}

		return null;
	}

	public Store getNextDestination(Store storable) {

		Player player = storable.getPlayer();
		if (storable instanceof Pit) {
			Store destination = getAdjacent((Pit) storable);
			// In case the pit has an adjacent pit, this is the easiest case.
			if (destination != null) {
				return destination;
			}
			// Return the store next in line if it belongs the current player.
			if (player.equals(getCurrentPlayer())) {
				return getStore(player);
			}
		}

		// Otherwise, if storable is actually a store at either end of the board,
		// return the first pit of the opposing player of the current target player.
		Player opposing = getOpposingPlayer(player);
		return this.pits.get(opposing).get(0);
	}

	private List<Pit> initializePitsFor(Player player) {
		List<Pit> pits = new ArrayList<Pit>();

		// Initialize pits for each player's side
		for (int i = 0; i < BOARD_SIZE; i++) {
			Pit pit = new Pit(player, i);
			pit.initializePit(BOARD_SIZE);
			pits.add(pit);
		}

		return pits;
	}

}
