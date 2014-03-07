package game.tris.utility;

import java.util.Random;

import android.graphics.Point;

public class Game {

	private Grid grid;
	private int player;
	private int turn;
	static final int LEVEL = 2;
	static final int MAXLEVEL = 3; //IA prop = (MAXLEVEL-LEVEL)/MAXLEVEL
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
	
	public Point markIA(){
		Point p = grid.free()[rand.nextInt(grid.getFree())];
		grid.marks(p.x, p.y, player);
		return p;
	}
}
