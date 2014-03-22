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
			Point[] corner = new Point[4];
			Point[] side = new Point[4];
			int corners = 0;
			int sides = 0;
			for(int i=0; i<grid.getFree() && !findGood; i++){
				if(grid.free()[i].x % 2 == 0 && grid.free()[i].y % 2 == 0){ //corners
					corner[corners] = grid.free()[i];
					corners++;
				}
				else{
					side[sides] = grid.free()[i];	//sides
					sides++;
				}
			}
			if(corners>0){
				if(corners == 2 && grid.markBy(1, 1, player)){
					p = side[rand.nextInt(sides)]; //random side
					findGood = true;
				}
				else{
					if(sides == 2 && grid.markBy(1, 1, player)){
						//delete wrongCorner
						boolean findWrongCorner = false;
						for(int i=0; i<corners && !findWrongCorner; i++){
							if((corner[i].x == side[0].x && corner[i].y == side[1].y) || 
									(corner[i].x == side[1].x && corner[i].y == side[0].y)){
								Point tmp = corner[corners-1];
								corner[corners-1] = corner[i];
								corner[i] = tmp;
								corners--;
								findWrongCorner = true;
							}
						}
					}
					else if((sides == 3) && (corners == 3) && (grid.markBy(1, 1, player))){
						//delete wrongCorner
						boolean findWrongCorner = false;
						Point wrongSide;
						if((side[0].x == side[1].x) || (side[0].y == side[1].y))
							wrongSide = side[2];
						else if((side[0].x == side[2].x) || (side[0].y == side[2].y))
							wrongSide = side[1];
						else
							wrongSide = side[0];
						for(int i=0; i<corners && !findWrongCorner; i++){
							if((corner[i].x == wrongSide.x) || (corner[i].y == wrongSide.y)){
								Point tmp = corner[corners-1];
								corner[corners-1] = corner[i];
								corner[i] = tmp;
								corners--;
								findWrongCorner = true;
							}
						}
					}
					p = corner[rand.nextInt(corners)]; //random corner
					findGood = true;
				}
			}
			else
				p = easy();
		}
		
		return p;
	}
}
