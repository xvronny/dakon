package me.xvronny.dakon;

import static spark.Spark.*;

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
         return "Hello World!";
      });
      
   }

}
