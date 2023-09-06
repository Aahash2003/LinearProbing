package Q2;
import Q1.*;

import java.util.Arrays;

import static Q1.AahashDemoHashingWithLinearProbing.*;

public class AahashDemoHashingWithAllOpenAddressingTechniques {

    //adding values into the hashtable
    public static void addValueQuadraticProbe(Integer value) {
        //Finding the index
        int index = value % tableCapacity;


        if (hashTable[index].getKey() == -1 || hashTable[index].getKey() == -111) {
            hashTable[index].setKey(value);
        } else {
            //Creating a for loop to search for the values
            for (int i = 0; i < tableCapacity; i++) {
                int t = (index + i * i) % tableCapacity;
                //Checks if its null and add the value
                if (hashTable[t].getKey() == -1) {
                    hashTable[t].setKey(value);
                    break;
                }
            }
        }
    }


    public static int ThePrimeNumberForSecondHashFunction(int size) {
        //Finds whether the value is within the size
        for (int i = size - 1; i >= 1; i--) {

            int cnt = 0;

            for (int j = 2; j * j <= i; j++)
                if (i % j == 0)
                    cnt++;
            //returns the prime value
            if (cnt == 0)
                return i;
        }
        return size;
    }

    //Add value using DoubleHashing
    public static void addValueDoubleHashing(Integer value) {

        int primeSize = ThePrimeNumberForSecondHashFunction(tableCapacity);
        //Finding the first value
        int hash1 = value % tableCapacity;
        int hash2 = primeSize - (value % primeSize);

        while (hashTable[hash1].getKey() != -1) {
            hash1 += hash2;
            hash1 %= tableCapacity;
        }
        hashTable[hash1].setKey(value);
    }

    public static void emptyHashTable() {
        for (int i = 0; i < tableCapacity; i++) {
            //Initializing the hashtable to set it -1
            hashTable[i] = new AahashValueEntry();
        }
    }

    //Printing out the array
    public static void printArray(Integer[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {

        header(3, 2, "Aahash Srikumar", 251225561, "Working with open addressing techniques by using methods from other classes");

        System.out.println("Let's Demonstrate our Understanding on all the Open Addressing Techniques... \n");
        //Asking the user the number of data items needed
        System.out.print("How Many Data Items: ");
        items = input.nextInt();

        //Asking the user for the initial load factor
        System.out.print("What is the Load Factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();

        tableCapacity = checkPrime((int) (Math.round(items) / lf));

        //Creating the hashtable with the new table capacity
        System.out.println("The minimum required table capacity would be: " + tableCapacity + "\n");
        hashTable = new AahashValueEntry[tableCapacity];

        //Initializing the hashtable to set it -1
        for (int i = 0; i < tableCapacity; i++) {
            hashTable[i] = new AahashValueEntry();
        }

        //printing the integer array
       // Integer[] numbers = {7, 14, -21, -28, 35};
        Integer[] numbers = {-11, 22, -33, -44, 55};

        //Printing out the array
        System.out.print("The Given Data Set: ");
        printArray(numbers);

        System.out.println("\nLet's Enter Each Data Item From The Above To The Hash Table:");


        for (int i = 0; i < numbers.length; i++) {
            addValueLinearProbe(numbers[i]);
        }
        //Printing out the hashtable
        System.out.println("\nAdding Data - Linear Probing Resolves Collision:");
        System.out.print("The Current Hash Table");
        printHashTable();

        //Emptying the Hashtable
        emptyHashTable();
        //Adding each value using the quartic probe
        for (int i = 0; i < numbers.length; i++) {
            addValueQuadraticProbe(numbers[i]);
        }
        System.out.println("\n\nAdding Data - Quadratic Probing Resolves Collision:");

        if (lf > 0.5) {
            System.out.println("Probing failed! Use a load factor of 0.5 or less. Goodbye!");
        }
        System.out.print("The Current Hash Table");
        printHashTable();

        //Emptying the Hashtable
        emptyHashTable();
        //Adding the values to the method
        for (int i = 0; i < numbers.length; i++) {
            addValueDoubleHashing(numbers[i]);
        }
        //Printing the new values
        System.out.println("\n\nAdding Data - Double-Hashing Resolves Collision:");
        System.out.println("The q Value for Double Hashing is: " + ThePrimeNumberForSecondHashFunction(tableCapacity));
        System.out.print("The Current Hash Table");
        printHashTable();

        footer("Aahash", 3,2);
    }
}