package game.tris.utility;

import android.R.bool;
import android.graphics.Point;

/*
 ---- ---- ----
|    |    |    | v0
|    |    |    |
 ---- ---- ----
|    |    |    | v1
|    |    |    |
 ---- ---- ----
|    |    |    | v2
|    |    |    |
 ---- ---- ----
  h0   h1   h2
*/

public class Grid {

	public static final int EMPTY = 0;
	public static final int X = 1;
	public static final int O = 2;
	private int line;
	private int matrix[][];
	private int nfree;
	
	//use this to create a default grid for Tic-Tac-Toe
	public Grid() {
		line = 3;
		createMatrix();
	}
	//use this to mark the grid. If the box is not empty then return false
	
	//use this to marks the grid. If the box is not empty then return false
	public boolean marks(int v, int h, int mark) {
		boolean empty = true;
		if(matrix[v][h]==EMPTY) {
			matrix[v][h] = mark;
			nfree--;
		}
		else {
			empty = false;
		}
		return empty;
	}
	//use this to control if a player(mark) wins
	public boolean playerWins(int mark) {
		boolean win = false;
		//win in v0
		if((matrix[0][0] == mark)&&(matrix[0][1] == mark)&&(matrix[0][2] == mark))
			win = true;
		//win in v1
		else if((matrix[1][0] == mark)&&(matrix[1][1] == mark)&&(matrix[1][2] == mark))
			win = true;
		//win in v2
		else if((matrix[2][0] == mark)&&(matrix[2][1] == mark)&&(matrix[2][2] == mark))
			win = true;
		//win in h0
		else if((matrix[0][0] == mark)&&(matrix[1][0] == mark)&&(matrix[2][0] == mark))
			win = true;
		//win in h1
		else if((matrix[0][1] == mark)&&(matrix[1][1] == mark)&&(matrix[2][1] == mark))
			win = true;
		//win in h2
		else if((matrix[0][2] == mark)&&(matrix[1][2] == mark)&&(matrix[2][2] == mark))
			win = true;
		//win in diagonal1
		else if((matrix[0][0] == mark)&&(matrix[1][1] == mark)&&(matrix[2][2] == mark))
			win = true;
		//win in diagonal2
		else if((matrix[0][2] == mark)&&(matrix[1][1] == mark)&&(matrix[2][0] == mark))
			win = true;
		return win;
	}
	
	private void createMatrix() {
		matrix = new int[line][line];
		resetAll();
			
	}
	
	//call this after 'Point[] free()'
	public int getFree() {
		return nfree;
	}
	
	public Point[] free(){
		Point[] free = new Point[9];
		int k = 0;
		for(int i=0; i<line; i++) {
			for(int j=0; j<line; j++) {
				if(matrix[i][j] == EMPTY){
					Point p = new Point();
					p.x = i;
					p.y = j;
					free[k] = p;
					k++;
				}
			}
		}
		return free;
	}
	
	//set a box empty
	public void resetBox(int v, int h){
		matrix[v][h] = EMPTY;
		nfree++;
	}
	
	//set all empty
	public void resetAll(){
		for(int i=0; i<line; i++) {
			for(int j=0; j<line; j++) {
				matrix[i][j] = EMPTY;
			}
		}
		nfree = line*line;
	}
	
	public boolean markBy (int v, int h, int player){
		boolean mark = false;
		if(matrix[v][h] == player)
			mark = true;
		return mark;
	}
}
