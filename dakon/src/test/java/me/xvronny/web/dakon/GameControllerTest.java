package me.xvronny.web.dakon;

import me.xvronny.web.dakon.controller.GameController;
import me.xvronny.web.dakon.controller.move.AbstractMove;
import me.xvronny.web.dakon.controller.move.MoveStone;
import me.xvronny.web.dakon.controller.move.SwitchPlayer;
import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameControllerTest {

    private Player player1;
    private Player player2;
    private Board board;
    private GameController controller;

    @Before
    public void before() {
        player1 = new Player("Zoe");
        player2 = new Player("Emma");
        board = new Board(player1, player2);
        controller = new GameController(board);
    }

    @Test
    public void getMovesForTheChosenPit_firstMove() {

        board.setCurrentPlayer(player1);

        Pit pit = board.getPitById("Zoe_0").get();
        List<AbstractMove> moves = controller.getMovesForChosenPit(pit);

        // On first move, it should return 6 MoveStones but no SwitchPlayer
        assertEquals(6, moves.size());
        assertTrue(moves.stream()
                .allMatch(move -> move instanceof MoveStone));

        // End result should be that selected pit will be empty and
        // any of the following pit should have 7 stones
        assertTrue(pit.isEmpty());
        assertEquals(7, board.getAdjacent(pit).getStones().size());
        assertEquals(1, board.getStore(player1).getStones().size());

    }

}
