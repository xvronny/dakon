package me.xvronny.web.dakon;

import me.xvronny.web.dakon.controller.GameController;
import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Pit;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.util.JsonTransformer;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Dakon is the East Javanese name for the Mancala/Lubang Menggali game.
 * "Lubang Menggali" itself means "Hole-Digging" in Indonesian.
 *
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

		/**
		 * Route requests to static file to the declared subfolder.
		 */
		staticFileLocation("/static");

		/**
		 * Exception cases should be redirected to the logs
		 */
		//
		exception(Exception.class, (exception, request, response) -> {
			exception.printStackTrace();
		});

		/**
		 * HTTP GET: Load homepage.
		 */
		get("/", (request, response) -> {
			return new ModelAndView(getProperties(), "home.ftl");
		}, new FreeMarkerEngine());

		/**
		 * HTTP GET : create new board, start a new game
		 */
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
			attributes.put("topPlayer", transformer.render(board.getFirstPlayer()));

			return new ModelAndView(attributes, "board.ftl");

		}, new FreeMarkerEngine());

		/**
		 * HTTP-POST : Load JSON file provided and recreate board in the Web UI.
		 */
		post("/dakon", (request, response) -> {

			String content = request.queryParams("fileContentField");

			JsonTransformer transformer = new JsonTransformer();
			Board board = transformer.parse(content, Board.class);

			if (board == null) halt(400, "Malformed fileContentField : "+content);
			request.session().attribute("board", board);

			Map<String, Object> attributes = getProperties();
			attributes.put("board", board);
			attributes.put("currentPlayer", transformer.render(board.getCurrentPlayer()));
			attributes.put("topPlayer", transformer.render(board.getFirstPlayer()));

			return new ModelAndView(attributes, "board.ftl");

		}, new FreeMarkerEngine());

		/**
		 * HTTP GET : read current board as a json
		 */
		get("/board", (request, response) -> {
			// forcing old browsers to go by unknown content type
			response.header("Content-Type", "text/dakon");
			response.header("Content-Disposition", "attachment; filename=dakon.json");
			// return board as plaintext json
			return request.session().attribute("board");
		}, new JsonTransformer());

		/**
		 * HTTP POST : select a particular pit to move stones out of.
		 */
		post("/board", "application/json", (request, response) -> {
			Session session = request.session(true);
			Board board = session.attribute("board");
			GameController game = new GameController(board);
			// parse request body
			Pit chosenPit = board.getPitById(request.body()).orElse(null);
			if (chosenPit == null) {
				if (request.body().contains("Store")) {
					halt(400, "Illegal move on Store : "+request.body());
				}
				halt(400, "Malformed pit id : "+request.body());
			}
			if (!chosenPit.getPlayer().equals(board.getCurrentPlayer()))
				halt(400, "Illegal move on opponent pit id : "+request.body());
			// return list of all move
			return game.getMovesForChosenPit(chosenPit);
		}, new JsonTransformer());

	}

}
