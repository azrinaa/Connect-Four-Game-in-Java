import java.util.Scanner;

public class ConnectFour {
	
	public static void main(String[] args)
	{

			char[][] connectFourGrid = new char[6][7];
			displayGrid(connectFourGrid);
			game(connectFourGrid);
			
	}

		// Method for displaying the connect four grid with red or yellow disk of there are any.
		public static void displayGrid(char[][] grid) 
		{
			for(int i = 0; i < grid.length; i++) { //looping for the row
				for(int j = 0; j < grid[i].length; j++) { //looping for the column
					System.out.print("|" + grid[i][j]);
					}
				System.out.println("|");
			}
			for (int i = 0; i < grid.length-1; i++)
			System.out.print("---");
			System.out.println("");
		}
		
		//Method to play The Connect 4 Game, 
		//Ask the user to enter the R/Y disk and the game ends when there are four same colored disk in a row,column,diagonal or a tie.
		public static void game (char[][] grid) 
		{
			Scanner input = new Scanner(System.in);
			boolean gameConnectFour = true;
			boolean playerTurn = true;
			int colPosition = 0;
			char diskColor;
			
			while (gameConnectFour)
			{
				if(playerTurn) //playerTurn = True is Red Player's Turn.
				{
					System.out.print("Drop a Red Disk at column (0-6) : ");
					diskColor = 'R';
				}
				else { //playerTurn == False, Yellow player's turn
					System.out.print("Drop a Yellow Disk at column (0-6) : ");
					diskColor = 'Y';
				}
			 
				colPosition = input.nextInt();
				
				if (colPosition < 0 || colPosition > 6)
				{
					System.out.print("Invalid Input. Please enter number from 0 to 6 only. ");
					colPosition = input.nextInt();
				}
				
				playerTurn = !playerTurn; //switch player's turn
				
				if (dropDisk(grid,colPosition,diskColor))
				{
					playerTurn = !playerTurn; //ask the same player to input other column in case the column is full.
				} 
				
				else {
				displayGrid(grid);
				}
				
				if(gameStatus(grid,colPosition,diskColor))
				{
			    gameConnectFour = false;
				}
				
				else if (checkTie(grid))
				{
					gameConnectFour = false;
					System.out.print("Its a tie. Game Over!");
				}
						
			 
			}
			
		}
		
		//Method for dropping the disk to the bottom of the column of the grid.
		//If the column is full, ask the same player to drop the disk again.
		public static boolean dropDisk(char[][] grid, int colPosition, char diskColor)
		{
			for(int i=(grid.length - 1); i >=0; i--) //drop disk from bottom to top
			{
				if (grid[i][colPosition] == 0)
				{
					grid[i][colPosition] = diskColor;
					return false;
				}
			}
			
			System.out.println("Column is full! :( " + diskColor + " player, please try again." );
			
			return true;
		}
		
		//Method for checking if there is any consecutive 4.
		//Returns true if four same-colored disk in a row, column or diagonal is found.
		public static boolean gameStatus (char[][] grid, int colPosition, char diskColor) 
		{
			int rowPosition = 0;
			
			for(int i = 0; i < grid.length; i++)
			{
				if(grid[i][colPosition] != 0)
				{
					rowPosition = i;
					break;
				}
			}
				
			if(checkVertical(grid,colPosition,diskColor,rowPosition))
			return true;
			if(checkHorizontal(grid,colPosition,diskColor,rowPosition))
			return true;
			if(checkDiagonalL(grid,colPosition,diskColor,rowPosition))
			return true;
			if(checkDiagonalR(grid,colPosition,diskColor,rowPosition))
			return true;
			
			
			
			return false;
		}
		
		//Method to check 4 consecutive pattern vertically.(column by column)
		//Returns true if four same-colored disk in a column is found.
		public static boolean checkVertical (char[][] grid, int colPosition, char diskColor, int rowPosition) 
		{
			int counter = 1;
			
			if((rowPosition + 4) <= 6)
			{
				for(int i = rowPosition+1; i <= rowPosition+3; i++)
				{
					if(diskColor == grid[i][colPosition])
						counter++;
					else
					break;
				}
				
				if(counter == 4)
					return true;
			}
			
			return false;
		}
		
		//Method to check 4 consecutive pattern vertically.(row by row)
		//Returns true if four same-colored disk in a row is found.
		public static boolean checkHorizontal (char[][] grid, int colPosition, char diskColor, int rowPosition) 
		{ 
			int counter = 1;
			
			for(int i=colPosition-1; i>=0 ; i--)  //checking row to the left side if there is any consecutive four.
			{
				if(diskColor==grid[rowPosition][i])
					counter++;
				else
					break;
			}
			
			if(counter == 4)
			return true;
			
			for(int i=colPosition+1; i < grid[0].length ; i--)  //checking row to the right side if there is any consecutive four.
			{
				if(diskColor==grid[rowPosition][i])
					counter++;
				else
					break;
			}
			
			if(counter == 4)
			return true;
				
		
			return false;
		}
		
		//Method to check 4 consecutive pattern Major Diagonal.(start from middle.)
		//Returns true if four same-colored disk in a Major Diagonal is found.
		public static boolean checkDiagonalL (char[][] grid, int colPosition, char diskColor, int rowPosition)
		{
			int counter = 1;
			
			for(int i = (rowPosition -1); i >= 0 ; i--) //checking Major Diagonal to the left side. 
			{
				for (int j = (colPosition-1); j >= 0; j--)
				{
					if (diskColor == grid[i][j])
						counter++;
					else
						break;
				}
			}
			
			if (counter >=4 )
				return true;
			
			for (int i = (rowPosition+1); i < grid.length; i++) //checking Major Diagonal to the right side. 
			{
				for (int j = (colPosition+1); j < grid.length; j++)
				{
					if (diskColor == grid[i][j])
						counter++;
					else
						break;
				}
			}
			
			if (counter >=4 )
				return true;
			
			return false;
		}
		
		//Method to check 4 consecutive pattern Minor Diagonal.(start from middle.)
		//Returns true if four same-colored disk in a Minor Diagonal is found.
		public static boolean checkDiagonalR (char[][] grid, int colPosition, char diskColor, int rowPosition)
		{
		
			int counter = 1;
			
			for (int i = (rowPosition+1); i < grid.length; i++)
			{
				for (int j = (colPosition-1); j >= 0; j--)
				{
					if (diskColor == grid[i][j])
						counter++;
					else
						break;
				}
			}
			
			if (counter >=4 )
				return true;
			
			for (int i = (rowPosition-1); i >= 0; i--)
			{
				for (int j = (colPosition+1); j < grid.length; j++)
				{
					if (diskColor == grid[i][j])
						counter++;
					else
						break;
				}
			}
			
			if (counter >=4 )
				return true;
			
		
			
			return false;
		}
		
		//Returns true if row 0 of the grid is entirely filled
		public static boolean checkTie (char[][] grid)
		{
			for (int i = 0; i < grid[0].length; i++)
			{
				if(grid[0][i] == 0)
				return false;
			}
			return true;
		}
		


}
