package com.example;

public class Game {

	private Grid grid;
	private int player;
	
	public Game() {
		grid = new Grid();
		player = Grid.O;
	}
	
	public void setPlayer(){
		if(player == Grid.X)
			player = Grid.O;
		else
			player = Grid.X;
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
}
