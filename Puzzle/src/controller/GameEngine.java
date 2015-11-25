/**
 * This file is part of the BMBF funded project <B>Nebeneinander wird Miteinander</B>
 * at Technische Universität Chemnitz.
 *
 * All Copyrights reserved @year.
 */
package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import controller.listener.ILevelListener;
import model.Level;
import processing.core.PVector;
import util.Constants;
import util.AppInjector;

/**
 * This is the GameEngine. The GameEngine is responsible for the "flow" of a game. It manages
 * objects which are currently active in the application and also directs the flow of events,
 * such as reacting to the completion of a level.
 * 
 * @author <a href="mailto:storz@informatik.tu-chemnitz.de">Michael Storz</a>
 * @version 0.1 <BR>
 * <BR>
 *          History:<BR>
 *          <LI>[storz][28.07.2015] Created</LI>
 */

//public class GameEngine implements IStencilListener, ILevelListener {
public class GameEngine implements ILevelListener {
	
	private GameStates currentGameState;
	
	private ArrayList<Object> resources;
	
	// Verwaltet alle aktiven Objekte des Spiels.
	// Als aktive Objekte gelten (derzeit) das Level und alle im Level vorkommenden Objekte
	
	private Map<Integer, Level> levelMap;
	
	private int currentLevel = 0;
	
	/**
	 * A Game may use different states to interpret different events, 
	 * e.g. user I/O during STATE_PAUSE may be limited and during STATE_INGAME enabled
	 * 
	 * @author soeren
	 *
	 */
	public static enum GameStates {
		STATE_MENU,
		STATE_PAUSE,
		STATE_INGAME,
		STATE_FINISHED;
		
		private static final int size = GameStates.values().length;
	};
	
	
	public GameEngine() {
		levelMap = new HashMap<Integer, Level>();
		resources = loadResources();
		currentLevel = 1;
		
		// Since we created the Level objects in the loadResources() method we can now add the Engine as a listener to each one.
		initLevelListeners();
	
	}
	
	public GameStates getCurrentGameState() {
		return currentGameState;
	}
	
	public void setCurrentGameState(GameStates state) {
		this.currentGameState = state;
	}
	
	/**
	 * Loads all Resources from a file into the game engine. This method is only considered a placeholder.
	 * Either you would have to validate resources in here or create a method specifically for each type of resource.
	 * 
	 * ATTENTION: Levels are also considered resources (which contain additional resources).
	 * 
	 * @return The loaded resources in an ArrayList<>
	 */
	private ArrayList<Object> loadResources() {
		
		  Path path = Paths.get(Constants.RESOURCE_URI); // loads the images for a card
		  
		  ArrayList<Object> cards = new ArrayList<Object>();
		  try {
	            Stream<String> lines = Files.lines(path);
	            lines.forEach(s -> {
	            	if (!s.startsWith("#")) { //ignore comment
	            		String[] parts = s.split(",");
//	        			cards.add(new CardContent(parts[0],parts[1],parts[2]));
	            		resources.add(new Object());
	            	}
	            	});
	            lines.close();
	        } catch (IOException ex) {

	        }
		return cards;
	}
	
	private void initLevelListeners() {
		for(Level level : levelMap.values()) {
			level.addListener(this);
		}
	}
	
	public void loadLevel() {
		loadLevel(currentLevel);
	}
	
	/**
	 * This instructs the app to load a certain level.
	 * @param levelNr
	 */
	public void loadLevel(int levelNr) {
		
		// Instructions for loading a level and its objects go here
		// Consider creating helper classes for loading object information from serialized sources,
		// e.g. XML, CSV, INI, databases, etc
		
		// The Engine should instruct the Controller to load (and prepare) the necessary resources (mainly Zone objects)
		
	}
	
	/**
	 * Implemented via ILevelListener interface. Gets called if the current level is completed.
	 */
	@Override
	public void levelCompleted() {
		this.currentLevel++;
		this.loadLevel(currentLevel);
	}
	
	public int getCurrentLevelNumber() {
		return currentLevel;
	}
	
	public Level getCurrentLevel() {
		return levelMap.get(currentLevel);
	}
}
