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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import controller.listener.ILevelListener;
import gui.Piece;
import model.ImageProperties;
import model.Level;
import util.AppInjector;
import util.Constants;
import vialab.SMT.Zone;

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
	
	private ArrayList<ImageProperties> imageProperties;
	
	private ImageProperties optimalProperties;
	
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
		imageProperties = loadImageProperties();
		optimalProperties = getImagePropertiesByDisplaySize();
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
//	private ArrayList<Object> loadResources() {
//		
//		  Path path = Paths.get(Constants.RESOURCE_URI); // loads the images for a card
//		  
//		  ArrayList<Object> cards = new ArrayList<Object>();
//		  try {
//	            Stream<String> lines = Files.lines(path);
//	            lines.forEach(s -> {
//	            	if (!s.startsWith("#")) { //ignore comment
//	            		String[] parts = s.split(",");
////	        			cards.add(new CardContent(parts[0],parts[1],parts[2]));
//	            		resources.add(new Object());
//	            	}
//	            	});
//	            lines.close();
//	        } catch (IOException ex) {
//
//	        }
//		return cards;
//	}
	
	/**
	 * Loads all Resources from a file with the properties of the puzzle image into the game engine. 
	 * 
	 * @return The loaded resources in the ArrayList<ImageProperties>
	 */
	private ArrayList<ImageProperties> loadImageProperties() {
		
		  Path path = Paths.get(Constants.PUZZLE_IMAGES_INFO); // loads the images for the puzzle
		  
		  ArrayList<ImageProperties> images = new ArrayList<ImageProperties>();
		  try {
	            Stream<String> lines = Files.lines(path);
	            lines.forEach(s -> {
	            	if (!s.startsWith("#")) { //ignore comment
	            		String[] parts = s.split(",");
	            		images.add(new ImageProperties(Integer.parseInt(parts[0],10),Integer.parseInt(parts[1],10),Integer.parseInt(parts[2],10),Integer.parseInt(parts[3],10),parts[4]));
	            	}
	            });
	            lines.close();
	        } catch (IOException ex) {

	        }
		return images;
	}
	
	public int[] getNumberOfPieces(){
		
		Path path = Paths.get(Constants.PUZZLE_NUMBERS_INFO);
		
		int number[] = {0,0};
		int count = Constants.NUMBER_OF_PIECES; 
		try {
			Stream<String> lines = Files.lines(path);
            lines.forEach(s -> {
            	if (!s.startsWith("#")) { //ignore comment
            		String[] parts = s.split(",");
//            		if(parts[0] == "R16" && optimalProperties.getRatioWidth() == 16 && Integer.parseInt(parts[3]) == 360) {
//            		TODO read number of pieces depending on chosen number of pieces by the user
            		if(Integer.parseInt(parts[3]) == count) {
            			number[0] = Integer.parseInt(parts[1]);
            			number[1] = Integer.parseInt(parts[2]);
            		}
//            		else if (parts[0] == "R4" && optimalProperties.getRatioWidth() == 4 && Integer.parseInt(parts[3]) == 360) {
            		else if(Integer.parseInt(parts[3]) == count) {
            			number[0] = Integer.parseInt(parts[1]);
            			number[1] = Integer.parseInt(parts[2]);
            		}
            	}
            });
            lines.close();
        } catch (IOException ex) {

        }
		return number;
	}
	
	public ArrayList<ImageProperties> getImageProperties() {
		return imageProperties;
	}
	
	/**
	 * @return the optimalProperties
	 */
	public ImageProperties getOptimalProperties() {
		return optimalProperties;
	}

	/**
	 * Calculates the optimal area size of the puzzle image. 
	 * 
	 * @return ImageProperties
	 */
	private ImageProperties getImagePropertiesByDisplaySize() {
		float min = 0;
		ImageProperties props = null;
		
		for(ImageProperties properties:imageProperties){
			if (min == 0) {
				min = Math.abs((properties.getRatioWidth()*properties.getRatioHeight()) - (AppInjector.application().displayWidth*AppInjector.application().displayHeight));
				props = properties;
			}
			else if (min > Math.abs((properties.getRatioWidth()*properties.getRatioHeight()) - (AppInjector.application().displayWidth*AppInjector.application().displayHeight))) {
				min = Math.abs((properties.getRatioWidth()*properties.getRatioHeight()) - (AppInjector.application().displayWidth*AppInjector.application().displayHeight));
				props = properties;
			}
		}
		return props;
	}
	
	public void randomizePlacement() {
		ArrayList<Zone> pieces = new ArrayList<Zone>();
		pieces = AppInjector.zoneManager().getZonesByClass(Piece.class);

		for (Zone piece : pieces) {
			Random x = new Random();
			Random y = new Random();
			piece.setLocation((int)x.nextInt()*y.nextInt(), (int)y.nextInt()*x.nextInt());
		}
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
