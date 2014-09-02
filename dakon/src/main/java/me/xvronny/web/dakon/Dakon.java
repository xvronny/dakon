package me.xvronny.web.dakon;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Player;
import me.xvronny.web.dakon.util.JsonTransformer;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * Dakon is the East Javanese name for the Mancala/Lubang Menggali game. "Lubang Menggali" itself 
 * means "Hole-Digging" in Indonesian.
 * 
 * @see <a href="https://play.google.com/store/apps/details?id=com.amegoo.dakon&hl=en">Dakon Game</a>
 * @see <a href="https://www.youtube.com/watch?v=k4drX3HOnMg">How To Play Mancala</a>
 * 
 * @author ronnyhendrawan
 *
 */
public class Dakon {

   public static void main(String[] args) {
	   
	  // static file declaration
	  staticFileLocation("/static");
      
	  // load homepage template
      get("/", (request, response) -> {
    	  Map<String, Object> attributes = new HashMap<>();
          attributes.put("brand", "Dakon");
          attributes.put("title", "Lubang Menggali");
          attributes.put("source", "https://github.com/xvronny/dakon");
          attributes.put("homepage", "http://xvronny.me/");
    	  return new ModelAndView(attributes, "home.ftl");
      }, new FreeMarkerEngine());
      
      // create new board
      get("/board", (request, response) -> {
    	  
    	  Session session = request.session(true);
    	  Board board = new Board(new Player("A"), new Player("B"));
    	  session.attribute("board", board);
    	  
    	  Map<String, Object> attributes = new HashMap<>();
          attributes.put("brand", "Dakon");
          attributes.put("title", "Lubang Menggali");
          attributes.put("source", "https://github.com/xvronny/dakon");
          
          attributes.put("board", board);
    	  
          return new ModelAndView(attributes, "board.ftl");
      }, new FreeMarkerEngine());
      
      // read current board
      get("/board/read", "application/json", (request, response) -> {
    	  return request.session().attribute("board");
      }, new JsonTransformer());
      
      // load saved board elsewhere
      put("/board", "application/json", (request, response) -> {
    	  JsonTransformer transformer = new JsonTransformer(); //request.body();
    	  
    	  Board board = transformer.parse(request.body(), Board.class);
    	  request.session().attribute("board", board);
    	  return board;
      }, new JsonTransformer());
      
      // move pieces of the board
      post("/board/move", "application/json", (request, response) -> {
    	  Session session = request.session(true);
    	  Board board = session.attribute("board");
    	  return board;
      }, new JsonTransformer());
      
   }

}

