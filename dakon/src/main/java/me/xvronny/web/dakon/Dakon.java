package me.xvronny.web.dakon;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import me.xvronny.web.dakon.controller.Game;
import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Lubang;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.util.JsonTransformer;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * Dakon is the East Javanese name for the Mancala/Lubang Menggali game.
 * "Lubang Menggali" itself means "Hole-Digging" in Indonesian.
 * 
 * @see <a
 *      href="https://play.google.com/store/apps/details?id=com.amegoo.dakon&hl=en">Dakon
 *      Game</a>
 * @see <a href="https://www.youtube.com/watch?v=k4drX3HOnMg">How To Play
 *      Mancala</a>
 * 
 * @author ronnyhendrawan
 *
 */
public class Dakon {

	private static final Map<String, Object> properties = new HashMap<String, Object>();

	private static Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("brand", "Dakon");
		properties.put("title", "Lubang Menggali");
		properties.put("source", "https://github.com/xvronny/dakon");
		properties.put("homepage", "http://xvronny.me/");
		return properties;
	}

	public static void main(String[] args) {

		// static file declaration
		staticFileLocation("/static");

		// load homepage template
		get("/", (request, response) -> {
			return new ModelAndView(getProperties(), "home.ftl");
		}, new FreeMarkerEngine());

		// create new board
		get("/dakon", (request, response) -> {
			
			String nameA = request.params("playerA");
			if (nameA == null) nameA = "PlayerA";
			String nameB = request.params("playerB");
			if (nameB == null) nameB = "PlayerB";
			
			Board board = new Board(new Player(nameA), new Player(nameB));
			request.session(true).attribute("board", board);
			
			JsonTransformer transformer = new JsonTransformer();
			
			Map<String, Object> attributes = getProperties();
			attributes.put("board", board);
			attributes.put("currentPlayer", transformer.render(board.getCurrentPlayer()));
			attributes.put("topPlayer", transformer.render(board.getPlayers().get(0)));

			return new ModelAndView(attributes, "board.ftl");
		
		}, new FreeMarkerEngine());

		// load saved board elsewhere
		post("/dakon", (request, response) -> {
			
			String content = request.queryParams("fileContentField");
			
			JsonTransformer transformer = new JsonTransformer();
			Board board = transformer.parse(content, Board.class);
			
			if (board == null) halt(400, "Malformed fileContentField : "+content);
			request.session().attribute("board", board);

			Map<String, Object> attributes = getProperties();
			attributes.put("board", board);
			attributes.put("currentPlayer", transformer.render(board.getCurrentPlayer()));
			attributes.put("topPlayer", transformer.render(board.getPlayers().get(0)));

			return new ModelAndView(attributes, "board.ftl");
		
		}, new FreeMarkerEngine());

		// read current board as a json
		get("/board", (request, response) -> {
			// forcing old browsers to go by unknown content type
			response.header("Content-Type", "text/dakon");
			response.header("Content-Disposition", "attachment; filename=dakon.json");
			// return board as plaintext json
			return request.session().attribute("board");
		}, new JsonTransformer());

		// move pieces of the board
		post("/board", "application/json", (request, response) -> {
			Session session = request.session(true);
			Board board = session.attribute("board");
			Game game = new Game(board);
			// parse request body
			Pit chosenPit = board.getPitById(request.body());
			if (chosenPit == null) halt(400, "Malformed pit id : "+request.body());
			if (!chosenPit.getPlayer().equals(board.getCurrentPlayer()))
				halt(400, "Illegal move on opponent pit id : "+request.body());
			if (chosenPit instanceof Lubang)
				halt(400, "Illegal move on Lubang : "+request.body());
			// return list of all move
			return game.executeStep(chosenPit);
		}, new JsonTransformer());

	}

}
