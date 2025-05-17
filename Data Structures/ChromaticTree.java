/*
 *  Noah Sibley
 *  N01512207
 *  Project 4 Chromatic Trees - Due 4/14/24
 */

package p4;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import stacksandqueues.*;


//The constructor class for the chromatic tree, sets variables
public class ChromaticTree implements Iterable <String> {
    private static class TreeNode {
        String city;
        String color;
        TreeNode right;
        TreeNode left;
        TreeNode parent;

        TreeNode(String city, String color) {
            this.city = city;
            this.color = color;
            this.right = null;
            this.left = null;
            this.parent = null;
        }
    }

    //Introduces the root node
    private TreeNode root;


    //Reads from the input files to construct the chromatic tree
    public ChromaticTree (String inputFile) {
        try {
            File file = new File(inputFile);
            Scanner scnr = new Scanner(file);

            //Gets the root node of the tree
            String rootLine = scnr.nextLine().trim();
            String[] pieces = rootLine.split("\\s+");
            String city = pieces[0];
            String color = pieces[1];
            root = new TreeNode(city, color);


            //Builds the rest of the chromatic tree by reading the rest of the lines
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine().trim();
                pieces = line.split("\\s+");
                city = pieces[0];
                color = pieces[1];
                String traversalPath = pieces[2];

                TreeNode current = root;
                for (int i = 0; i < traversalPath.length(); i++) {
                    char direction = traversalPath.charAt(i);
                    if (direction == 'L') {
                        if (current.left == null) {
                            current.left = new TreeNode(city, color);
                            current.left.parent = current;
                        }
                        current = current.left;
                    }
                    else if (direction == 'R') {
                        if (current.right == null) {
                            current.right = new TreeNode(city, color);
                            current.right.parent = current;
                        }
                        current = current.right;
                    }
                }
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //Finds out the number of tri-chromatic groups
    private int countTriChromaticGroups(TreeNode node) {

        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }

        int count = 0;

        //Checks to see if the current node forms a tri-chromatic group with its children nodes
        if (node.left != null && node.right != null &&
                !node.left.color.equals(node.color) &&
                !node.right.color.equals(node.color) &&
                !node.left.color.equals(node.right.color)) {
            count = 1;
        }

        //Calls recursively to process left and right child nodes
        return count + countTriChromaticGroups(node.left) + countTriChromaticGroups(node.right);
    }

    //The public method call to count from the root upwards
    public int countTriChromaticGroups() {
        return countTriChromaticGroups(root);
    }

    //Gets the cities in reverse elevator order
    public String getReverseElevatorOrder() {
        if (root == null) {
            return " ";
        }

        StringBuilder sb = new StringBuilder();
        LinkedQueue<TreeNode> queue = new LinkedQueue<>();
        queue.enqueue(root);

        //Processing the nodes using queues
        while (!queue.isEmpty()) {
            TreeNode node = queue.dequeue();
            sb.append(node.city);
            if (node.right != null) {
                queue.enqueue(node.right);
            }
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (!queue.isEmpty()) {
                sb.append(" ");
            }
        }

        return sb.toString();

    }
    public int computeHeight() {
        return computeHeight(root);
    }

    //Computes the height of the tree from the node upwards using recursion
    private int computeHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }

        //Left and right subtrees
        int heightRight = computeHeight(node.right);
        int heightLeft = computeHeight(node.left);

        //Max height + 1 cuz of the current node
        return Math.max(heightRight, heightLeft) + 1;
    }

    //Returns all node paths to root in a string
    public String printPathsBackToTheRoot() {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        LinkedQueue<TreeNode> queue = new LinkedQueue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.dequeue();

            //Gets the path of the current node to the root
            List<TreeNode> traversalPath = rootPath(node.city);
            if (traversalPath != null){
                //Adds the path found to the result
                for (int i = traversalPath.size() - 1; i >= 0; i--) {
                    sb.append(traversalPath.get(i).city);
                    if (i > 0) {
                        sb.append("\n");
                    }
                }
                sb.append("\n");
            }
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
        return sb.toString().trim();
    }

    //Finds the first common city between two given ones in the chromatic tree
    public String findFirstCommonCity (String cityA, String cityB) {

        //Path to the root for each of the cities
        List<TreeNode> pathA = rootPath(cityA);
        List<TreeNode> pathB = rootPath(cityB);

        if (pathA == null || pathB == null) {
            return null;
        }

        int i = 0;

        //Finds the first common node between the two
        while (i < pathA.size() && i < pathB.size() && pathA.get(i) == pathB.get(i)) {
            i++;
        }
        if (i == 0) {
            return null;
        }

        //Will either return the city from the most recent common node, or null if not applicable
        return pathA.get(i - 1).city;

    }

    //Returns the path of any city to the root
    private List<TreeNode> rootPath(String city) {
        List<TreeNode> traversalPath = new ArrayList<>();
        TreeNode node = findNode(root, city);
        if (node == null) {
            return null;
        }
        while (node != null) {
            traversalPath.add(0, node);
            node = node.parent;
        }

        return traversalPath;
    }

    //Can find any given city in the tree by name using recursion
    private TreeNode findNode(TreeNode node, String city) {
        if (node == null) {
            return null;
        }
        if (node.city.equals(city)) {
            return node;
        }
        TreeNode right = findNode(node.right, city);
        if (right != null) {
            return right;
        }
        return findNode(node.left, city);
    }

    //Allows for iteration over cities and colors in the tree
    @Override
    public Iterator <String> iterator() {
        List<String> result = new ArrayList<>();
        traverseCorrectly(root, result);
        return result.iterator();
    }

    //Helps to traverse the tree in order, and adds cities/colors to the list
    private void traverseCorrectly (TreeNode node, List<String> result) {
        if (node == null) {
            return;
        }

        //Traverses subtrees, adding current node to the results
        traverseCorrectly(node.left, result);
        result.add("<" + node.city + ", " + node.color + ">");
        traverseCorrectly(node.right, result);
    }



}
