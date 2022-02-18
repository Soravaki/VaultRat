import java.io.File;
import java.io.IOException;
import java.util.*;

public class VaultMap extends Graph{
    int[][] adjMazeArray;
    int length, height;
    String[][] mazeArray;
    Graph g;
    VaultMap(String filename) throws IOException {
        //adjmap = new HashMap<>();
        // Instantiate Scanner and takes in input for how many mazes
        Scanner sc = new Scanner(new File(filename));
        int numMaze = sc.nextInt();
        sc.nextLine(); // scanner refresh

        while (numMaze != 0) {
            this.g = new Graph();
            String[] dimensions = sc.nextLine().split(" ");
            this.height = Integer.parseInt(dimensions[0]);
            this.length = Integer.parseInt(dimensions[1]);
            // System.out.println("dimensions in");
            // ties the characters together into a graph
            // until num = height -1

            // creates 2d array that stores the vaultmap and another array to act its location
            this.mazeArray = new String[height][length];
            this.adjMazeArray = new int[height][length];
            // creates maze
            for (int i=0; i<height; i++){
                mazeArray[i] = sc.nextLine().split("|");
            }
            // creates maze reference for making adjacent list
            int num=0;
            for (int y=0; y<height; y++){
                for (int x=0; x<length; x++){
                    adjMazeArray[y][x] = num;
                    num++;
                }
            }

            // traverses 2d array to make adjList
            for (int y=0; y<height; y++){
                for (int x=0; x<length; x++){
                    //System.out.print(adjMazeRef[y][x] + " ");
                    // addEdge to the right if in bounds
                    if (x<length-1 && !(hasVertex( Integer.toString(adjMazeArray[y][x]))))
                        g.addEdge(Integer.toString(adjMazeArray[y][x]), Integer.toString((adjMazeArray[y][x]+1)));

                    // addEdge to the left if in bounds
                    if (x>0 && !(hasVertex( Integer.toString(adjMazeArray[y][x]))))
                        g.addEdge(Integer.toString(adjMazeArray[y][x]), Integer.toString(( adjMazeArray[y][x]-1)));

                    // addEdge above if in bounds
                    if (y>0 && !(hasVertex( Integer.toString(adjMazeArray[y][x]))))
                        g.addEdge(Integer.toString(adjMazeArray[y][x]), Integer.toString((adjMazeArray[y][x]-height)));

                    // addEdge below if in bounds
                    if (y<height-1 && !(hasVertex( Integer.toString(adjMazeArray[y][x]))))
                        g.addEdge(Integer.toString(adjMazeArray[y][x]), Integer.toString((adjMazeArray[y][x]+height)));
                }
            }
            //System.out.println(g.adjmap.keySet());
            /*for (int i=0; i<length*height; i++){
                System.out.println(adjmap.containsKey(Integer.toString(i)));
            }*/
            // debug - prints out maze
            for (int i=0; i<height; i++){
                System.out.println(Arrays.toString(mazeArray[i]));
            }
            //System.out.println("compiled. length : " + length + " height : " + height + " num : " +numMaze );
            // subtracts 1 maze from the total needed to be compiled
            pathfinding();
            numMaze--;
        }
    }

    // method for grabbing coords for the number array
    public int[] getNumCoords(int num){
        int[] coords = new int[2];
        for (int y=0; y<adjMazeArray.length; y++){
            for (int x=0; x<adjMazeArray[y].length; x++){
                if (num == adjMazeArray[y][x]) {
                    // stores location into array that houses the cords
                    coords[0] = y;
                    coords[1] = x;
                }
            }
        }
        return coords;
    }

    // method for grabbing coords for the content array
    public int[] getContentCoords(String item){
        int[] coords = new int[2];
        for (int y=0; y<mazeArray.length; y++){
            for (int x=0; x<mazeArray[y].length; x++){
                if (Objects.equals(item, mazeArray[y][x])) {
                    // stores location into array that houses the cords
                    coords[0] = y;
                    coords[1] = x;
                }
            }
        }
        return coords;
    }


    public String numToContent(int num){
        int[] coords = getNumCoords(num);
        return mazeArray[coords[0]][coords[1]];
    }

    public int contentToNum(String item){
        int[] coords = getContentCoords(item);
        return adjMazeArray[coords[0]][coords[1]];
    }

    public void pathfinding(){
        // instantiate objects for pathfinding
        Queue<Integer> q = new LinkedList<>();
        int[] startPosCords = new int[2];
        boolean[] visited = new boolean[length*height];


        // nested for loop to iterate through the entire maze to find the starting point
        for (int y=0; y<mazeArray.length; y++){
            for (int x=0; x<mazeArray[y].length; x++){
                if (Objects.equals(mazeArray[y][x], "S")) {
                    // stores startPos into array that houses the cords
                    startPosCords[0] = y;
                    startPosCords[1] = x;
                }
            }
        }
        int startPos = adjMazeArray[startPosCords[0]][startPosCords[1]];
        visited[startPos] = true;
        q.add(startPos);

        while (q.size() != 0){
            // Dequeue a vertex from queue and print it
            int location = q.remove();
            System.out.print(location +" ");
            String locationString = Integer.toString(location);

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<String> i = g.adjacentTo(locationString).iterator();
            while (i.hasNext())
            {
                String adjLoc = i.next();
                int adjNumLoc = Integer.parseInt(adjLoc);

                if (!visited[adjNumLoc])
                {
                    visited[adjNumLoc] = true;
                    q.add(adjNumLoc);
                }
            }
        }
    }
}
