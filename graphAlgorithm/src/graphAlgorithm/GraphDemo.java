package graphAlgorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.PrintWriter;
/**
 * 
 * @author muzhouchen
 * last modified: June 5, 2019
 * Overview: this program use Kruskal's algorithm to get the minimum weight needed to connect the graph from 
 * adjacency matrix, which is to pick smallest weighted edge and connect. The input file represent the weight
 *  of each path, the file doesn't shows the vertices, however, we can imagine the index 0, 1, 2, 3 etc. as 
 *  whatever what vertices we need in different cases. 
 *  -1 represents not connected; 
 */
public class GraphDemo {
	//the main function;
	public static void main(String[]args) {
		//to store the integers from input file, create temp;
		String temp = "";
		String[] tempString;
		//use a 2d array to store the adjacency matrix;
		int[][] weights = new int[0][0];
		int a = 0;
		try{
			//read the file
			Scanner input = new Scanner(new File("input.txt"));
			while(input.hasNextLine()) {
				//store the whole line, split by comma; 
				temp = input.nextLine();
				tempString = temp.split(",");
				//this is the case when we get the first line from the file; 
				if(a == 0) {
					//we define the weight with the length of tempString we got from the file. 
					weights = new int[tempString.length][tempString.length];
				}
				for(int i = 0; i < tempString.length; i ++) {
					//switch from string to integer. 
					weights[a][i] = Integer.parseInt(tempString[i]);
				}
				a += 1;
			}
			input.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			//the result is the 2d integer array; call the Kruskal method to get the result; 
			int[][] result = Kruskal(weights);
			File outFile = new File("output.txt");
			//write the output file here; 
			PrintWriter output = new PrintWriter(outFile);
			//need 2 for loop to write the 2d array; 
			for(int i = 0; i < result.length; i++) {
				for(int j = 0; j < result.length; j++) {
					output.print(result[i][j]);
					//we separate the integers by comma except last one; 
					if(j < result.length - 1) {
						output.print(",");
					}
				}
				output.println();
			}
			output.close();
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//here shows the kruskal's algorithm; 
	public static int[][] Kruskal(int[][] weights) {
		//use Hash Set to check if the vertices connected, we say all vertices from the graph connected
		//to each other when all the vertices exist in hash set by checking the size of hash set. 
		Set<String> set = new HashSet<String>();
		int[][] result = new int[weights.length][weights.length];
		for(int i = 0; i < weights.length; i++) {
			for(int j = 0; j < weights.length; j++) {
				result[i][j] = -1;
			}
		}
		//while they are not all connected: 
		while(set.size()<weights.length - 1) {
			int[] axis = findMin(weights);
			//another variable edge to show all the edges we connected; 
			String edge = axis[0] + "," + axis[1];
			if(set.contains(edge)) {
				continue;
			}else {
				set.add(edge);
			}
			result[axis[0]][axis[1]] = weights[axis[0]][axis[1]];
			result[axis[1]][axis[0]] = weights[axis[0]][axis[1]];
			//when we add the path to result, we let the original data from input adjacency matrix become -1, 
			//so that we can back to check next minimum weighted path;
			weights[axis[0]][axis[1]] = -1;
		}
		return result;
	}
	
	//the findMin method to find the minimum weighted path from adjacency matrix; 
	public static int[] findMin(int[][]weights){
		//initialize temp as max value; 
		int temp = Integer.MAX_VALUE;
		//this axis stores the axis of minimum weighted path; 
		int[] axis = new int[2];
		//use 2 for loop to check elements from 2d array;
		for(int i = 0; i < weights.length; i++) {
			for(int j = i; j < weights.length; j++) {
				//if the weight of path not equal to 0, and the temp is larger than what we check, we 
				//store this path to the temp and then get the axis as the minimum weight in the graph
				//except -1; 
				if(temp > weights[i][j] && weights[i][j] > -1) {
					temp = weights[i][j];
					axis[0] = i;
					axis[1] = j;
				}
			}
		}
		return axis;
	}
}


