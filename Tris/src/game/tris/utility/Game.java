package game.tris.utility;

import java.util.Random;

import android.graphics.Point;

public class Game {

	private Grid grid;
	private int player;
	private int turn;
	public static final int EASY = 0;
	public static final int GOOD = 1;
	public static final int PERFECT = 2;
	private Random rand;
	
	public Game() {
		grid = new Grid();
		player = Grid.X;
		turn = 1;
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
	}
	
	public void changePlayer(){
		if(player == Grid.X)
			player = Grid.O;
		else
			player = Grid.X;
		turn++;
	}
	
	public boolean mark(int v, int h){
		return grid.marks(v, h, player);
	}
	
	public boolean isfinish(){
		return grid.playerWins(player);
	}
	
	public int getPlayer(){
		return player;
	}
	
	public int getTurn(){
		return turn;
	}
	
	//default markIA play random
	public Point markIA(){
		Point p = easy();
		mark(p.x, p.y);
		return p;
	}
	
	//if level doesn't exist it mark randomly
	public Point markIA(int level){
		Point p;
		if(level == EASY)
			p = easy();
		else if(level == GOOD)
			p = good();
		else if(level == PERFECT)
			p = perfect();
		else //by default
			p = easy();
		mark(p.x, p.y);
		return p;
	}
	
	//algorithm: randomly
	private Point easy(){
		Point p = grid.free()[rand.nextInt(grid.getFree())];
		return p;
	}
	
	//algorithm: win or not lose easy
	private Point good(){
		Point p = null;
		boolean findGood = false;
		//try to win
		for(int i=0; i<grid.getFree() && !findGood; i++){
			Point point = grid.free()[i];
			mark(point.x, point.y);
			if(isfinish()){
				p = point;
				findGood = true;
			}
			grid.resetBox(point.x, point.y);
		}
		//try to not lose
		if(!findGood){
			changePlayer();
			turn--;
			for(int i=0; i<grid.getFree() && !findGood; i++){
				Point point = grid.free()[i];
				mark(point.x, point.y);
				if(isfinish()){
					p = point;
					findGood = true;
				}
				grid.resetBox(point.x, point.y);
			}
			changePlayer();
			turn--;
		}
		//if not win or lose play easy
		if(!findGood){
			p = easy();
		}
		return p;
	}

	//algorithm: perfect
	private Point perfect(){
		Point p = null;
		boolean findGood = false;
		//try to win
		for(int i=0; i<grid.getFree() && !findGood; i++){
			Point point = grid.free()[i];
			mark(point.x, point.y);
			if(isfinish()){
				p = point;
				findGood = true;
			}
			grid.resetBox(point.x, point.y);
		}
		//try to not lose
		if(!findGood){
			changePlayer();
			turn--;
			for(int i=0; i<grid.getFree() && !findGood; i++){
				Point point = grid.free()[i];
				mark(point.x, point.y);
				if(isfinish()){
					p = point;
					findGood = true;
				}
				grid.resetBox(point.x, point.y);
			}
			changePlayer();
			turn--;
		}
		//if not win or lose play easy
		if(!findGood){
			p = defend();
		}
		return p;
	}
	
	private Point defend(){
		Point p = null;
		boolean findGood = false;
		for(int i=0; i<grid.getFree() && !findGood; i++){
			if(grid.free()[i].x == 1 && grid.free()[i].y == 1){ //center
				p = grid.free()[i];
				findGood = true;
			}
		}
		if(!findGood){
			//chose where
		}
		
		return p;
	}
}
