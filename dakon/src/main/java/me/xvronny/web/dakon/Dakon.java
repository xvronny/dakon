package me.xvronny.web.dakon;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import me.xvronny.web.dakon.model.Board;
import me.xvronny.web.dakon.model.Player;
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
	   
	  staticFileLocation("/static");
      
      get("/dakon", (request, response) -> {
    	  Map<String, Object> attributes = new HashMap<>();
          attributes.put("brand", "Dakon");
          attributes.put("title", "Lubang Menggali");
          attributes.put("source", "https://github.com/xvronny/dakon");
          attributes.put("homepage", "http://xvronny.me/");
    	  return new ModelAndView(attributes, "dakon.ftl");
      }, new FreeMarkerEngine());
      
   }

}

