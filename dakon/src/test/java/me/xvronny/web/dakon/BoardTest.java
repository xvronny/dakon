package me.xvronny.web.dakon;

import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.model.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Player player1;
    private Player player2;
    private Board testBoard;

    @Before
    public void before() {
        player1 = new Player("Zoe");
        player2 = new Player("Emma");
        testBoard = new Board(player1, player2);
    }

    @Test
    public void isCompleted_initialBoard() {
        assertFalse(testBoard.isCompleted());
    }

    @Test
    public void isCompleted_cleanedBoard() {
        Player targetPlayer = testBoard.getPlayers().get(0);
        testBoard.getAllPits().stream()
                .filter(pit -> pit.getPlayer().equals(targetPlayer))
                .forEach(pit -> {
                    pit.popAllStones();
                });

        assertTrue(testBoard.isCompleted());
    }

    @Test
    public void getNextDestination_includesCurrentPlayersStorage() {

        testBoard.setCurrentPlayer(player1);

        Optional<Pit> pit = testBoard.getPitById("Zoe_5");
        Store next = testBoard.getNextDestination(pit.get());
        assertEquals("Zoe_Store", next.getId());

        next = testBoard.getNextDestination(next);
        assertEquals("Emma_0", next.getId());

        next = testBoard.getNextDestination(next);
        assertEquals("Emma_1", next.getId());
    }


    @Test
    public void getNextDestination_excludesOpposingPlayersStorage() {

        testBoard.setCurrentPlayer(player2);

        Optional<Pit> pit = testBoard.getPitById("Zoe_5");
        Store next = testBoard.getNextDestination(pit.get());
        assertEquals("Emma_0", next.getId());

        next = testBoard.getNextDestination(next);
        assertEquals("Emma_1", next.getId());

    }
}