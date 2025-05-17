/*
 * Noah Sibley - N01512207
 * Project 3 - Find Exit of Matrix
 */


package p3;

import stacksandqueues.*;
import java.io.*;
public class ExitFinder {
	//Initializes all the maze info, also to keep track of things such as visited cells and whether solution is discoverable
	int m, n;
	boolean[][] matrixVisited;

	char[][] maze, mazeRoute;

	Cell start, exit;

	boolean solutionCalc = false, solutionFound = true; //Boolean flags that determine if a solution has been calculated and found


	//Represents each cell in the maze
	private static class Cell {
		int i, j;

		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}

		//Determines whether cells are equivalent
		public boolean equals(Cell c) {
			return (c.i == i && c.j == j);
		}
	}
	public ExitFinder(String mazeInputFile)  throws IOException{
		FileReader input = new FileReader(mazeInputFile);
		BufferedReader readMaze = new BufferedReader(input);

		//Reads in the maze dimensions
		String[] rowsCols = readMaze.readLine().split(" ");
		m = Integer.parseInt(rowsCols[0]);
		n = Integer.parseInt(rowsCols[1]);
		maze = new char[m][n];
		mazeRoute = new char[m][n];
		matrixVisited = new boolean[m][n];

		//Determines the actual layout of the maze
		for (int i = 0; i < m; i++) {
			rowsCols = readMaze.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				mazeRoute[i][j] = maze[i][j] = rowsCols[j].charAt(0);
				if ( maze[i][j] == 'R')
					start = new Cell(i, j);
				else if (maze[i][j] == 'E')
					exit = new Cell(i, j);
			}
		}
		readMaze.close();
		input.close();
	}

	public void findExitPathStackBased() {
		solutionCalc = true;
		LinkedStack<Cell> C = new LinkedStack<>();
		C.push(start);

		//Marks starting point
		while (!C.isEmpty()) {
			Cell current = C.pop();
			if (current.equals(exit)) {
				solutionFound = true;
				mazeRoute[start.i][start.j] = 'R';

				return;
			}
			//Marks visited cells as visited
			matrixVisited[current.i][current.j] = true;
				mazeRoute[current.i][current.j] = '.';

			//Checks neighbor cells, pushes right ones to stack, determines up, down, left and right movement
			if (canHeMoveHere(current.i-1, current.j)) {
				Cell temp = new Cell(current.i-1, current.j);
				C.push(temp);
			}

			if (canHeMoveHere(current.i+1, current.j)) {
				Cell temp = new Cell(current.i+1, current.j);
				C.push(temp);
			}

			if (canHeMoveHere(current.i, current.j-1)) {
				Cell temp = new Cell(current.i, current.j-1);
				C.push(temp);
			}

			if (canHeMoveHere(current.i, current.j+1)) {
				Cell temp = new Cell(current.i, current.j+1);
				C.push(temp);
			}
		}
		solutionFound = false;

	}

	public void sendSolutionTo(String mazeSolution)  throws IOException {
		File file = new File(mazeSolution);
		FileWriter writeMaze = new FileWriter(file);

		//Closes if solution has not been calculated
		if (!solutionCalc) {
			writeMaze.close();
			System.out.println("Solution unable to be calculated");
		}

		//Writes it to a file if it's been found
		if(solutionFound) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++)
					writeMaze.append(String.valueOf(mazeRoute[i][j])).append(" ");
				writeMaze.append("\n");
			}
		} else
			writeMaze.append("No path exists.");

		writeMaze.close();
	}

	//Checks to see if the robot guy can move to a particular cell
	private boolean canHeMoveHere(int i, int j) {
		return ( i >= 0 && i < m && j >= 0 && j < n && !matrixVisited[i][j] && maze[i][j] != '1');
	}


}
