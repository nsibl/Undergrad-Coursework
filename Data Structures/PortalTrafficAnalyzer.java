/* Speed up results (Asus Zephyrus G15, 16 GBs RAM):
    Unplugged:
        n = 1000, Avg. speedup: 65X
        n = 2000, Avg. speedup: 488X
        n = 3000, Avg. speedup: 1526X
        n = 4000, Avg. speedup: 3234X
        n = 5000, Avg. speedup: 6337X
        n = 6000, Avg. speedup: 11086X
        n = 7000, Avg. speedup: 17580X
        n = 8000, Avg. speedup: 26244X
        n = 9000, Avg. speedup: 37619X
        n = 10000, Avg. speedup: 51102X

    Plugged in:
        n = 1000, Avg. speedup: 50X
        n = 2000, Avg. speedup: 397X
        n = 3000, Avg. speedup: 1350X
        n = 4000, Avg. speedup: 3212X
        n = 5000, Avg. speedup: 6309X
        n = 6000, Avg. speedup: 10935X
        n = 7000, Avg. speedup: 17356X
        n = 8000, Avg. speedup: 26136X
        n = 9000, Avg. speedup: 37003X
        n = 10000, Avg. speedup: 50783X


 */

package p1; // do not delete; you may get a 0 if you do so

public class PortalTrafficAnalyzer {
    // A nested class for handling the results returned by the implementations
    public static class Result {
        public final int sum, leftIndex, rightIndex;

        public Result(int first, int second, int third){
            this.sum = first;
            this.leftIndex = second;
            this.rightIndex = third;
        }

        public String toString() {
            return "<" + sum + ", " + leftIndex + ", " + rightIndex + ">";
        }
    }
	
	// Returns the maximum sum, left index of the max. subarray, and the right index of the subarray
    public static Result enCubedImplementation(int[] A){
        int maxSum = Integer.MIN_VALUE, leftIndex = 0, rightIndex = 0; // dummy values; feel free to change the values

        for (int i = 0; i < A.length; i++) {        //Iterates through array to find all possible
            for (int j = i; j < A.length; j++) {     //sub-arrays, updating values as it goes
                int currentSum = 0;
                for (int k = i; k < j; k++) {
                    currentSum += A[k];
                }

                if (currentSum > maxSum) {          //Updates the maxSum if currentSum finds a larger
                    maxSum = currentSum;            //value, until it finds the highest value
                    leftIndex = i;                  //Sets the left and rightmost indices
                    rightIndex = j;
                }
            }
        }
        // complete
        return new Result(maxSum,leftIndex,rightIndex);
    }

	// Returns the maximum sum, left index of the max. subarray, and the right index of the subarray
    public static Result enSquaredImplementation(int[] A){
        int maxSum = Integer.MIN_VALUE, leftIndex = 0, rightIndex = 0; // dummy values; feel free to change the values
        int currentSum = 0;
        int tempLeftIndex = 0;     //Stores starting index of subarray with current sum

        for (int i = 0; i < A.length; i++) {        //Iterates through array to find max sum subarray
            currentSum += A[i];

            if (currentSum > maxSum) {      //Updates maxSum with currentSum if it is a larger value
                maxSum = currentSum;
                leftIndex = tempLeftIndex;
                rightIndex = i;
            }

            if (currentSum < 0) {           //If value becomes negative, resets and updates index position
                currentSum = 0;
                tempLeftIndex = i + 1;
            }
        }

        if (maxSum == Integer.MIN_VALUE) {          //If all elements in the array are negative, find max element
            for (int i = 0; i < A.length; i++) {
                if (A[i] > maxSum) {
                    maxSum = A[i];
                    leftIndex = rightIndex = i;  //If subarray has only one element, the indices are the same
                }
            }
        }

        // complete
       
        return new Result(maxSum,leftIndex,rightIndex);
    }
}
