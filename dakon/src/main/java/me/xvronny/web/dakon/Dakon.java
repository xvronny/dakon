package me.xvronny.web.dakon;

import static spark.Spark.*;
import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Player;
import spark.Session;

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
      
      get("/dakon", (request, response) -> {
    	  Session session = request.session(true);
    	  if (session.attribute("board") == null) {
    		  session.attribute("board", new Board(new Player("A"), new Player("B"))) ;
    	  }
    	  return "Hello Ho Hi Ho!"+session.attribute("board");
      });
      
   }

}

