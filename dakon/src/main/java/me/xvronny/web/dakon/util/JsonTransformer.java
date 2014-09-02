package me.xvronny.web.dakon.util;

import spark.ResponseTransformer;

import com.google.gson.Gson;

public class JsonTransformer implements ResponseTransformer {

   private Gson gson = new Gson();
	
   @Override
   public String render(Object model) {
      return gson.toJson(model);
   }

   public <T> T parse(String jsonString, Class<T> elementType) {
      return gson.fromJson(jsonString, elementType);
   }
 
}

