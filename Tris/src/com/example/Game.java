package com.example;

public class Game {

	private Grid grid;
	private int player;
	private int turn;
	static final int LEVEL = 2;
	static final int MAXLEVEL = 3; //IA prop = (MAXLEVEL-LEVEL)/MAXLEVEL
	
	public Game() {
		grid = new Grid();
		player = Grid.O;
		turn = 0;
	}
	
	public void setPlayer(){
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
}
