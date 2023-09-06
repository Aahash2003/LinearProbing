package Q1;

import java.util.Scanner;


        import java.util.Arrays;
        import java.util.Hashtable;
        import java.util.Scanner;
public class AahashDemoHashingWithLinearProbing {
    public static int items;
            public static int tableCapacity;
    public static double lf;

    //Creating whether the scanner input
    public static Scanner input = new Scanner(System.in);

    //Initializing the values of Q1.AahashValueEntry typw with hashtable
    public static AahashValueEntry[] hashTable;
    public static AahashValueEntry[] workingHashTable;

    //Adds the values using the linear probe
    public static void addValueLinearProbe(Integer value){
        //Finding the index value
        int index = value % tableCapacity;

        //Checks whether the index is negative and will convert the value to its positive side
        if (index < 0){
            index += tableCapacity;
        }

        //Creates while loop to add onto the index if a value is already taken that spot
        while(hashTable[index].getKey() != -1 && hashTable[index].getKey() != -111){
            index++;
            if (index == tableCapacity){
                index = 0;
            }

        }
        //Sets the value to the index value
        if (hashTable[index].getKey() == -1 || hashTable[index].getKey() == -111){
            hashTable[index].setKey(value);
        }
    }

    public static int checkPrime(int n) {
        //divide the n factor
        int m = n / 2;

        for (int i = 3; i <= m; i++) {

            if (n % i == 0) {
                //will reset i to 2
                i = 2;

                //System.out.printf("i = %d\n",i);
                n++;
                m = n / 2;//looks through half the n factors
            }
        }
        return n;
    }

    //it will remove a value
    public static void removeValueLinearProbe(Integer value){

        boolean isFound = false;

        //if found given a value of -111
        for(int i = 0; i < hashTable.length; i++) {
            if (hashTable[i].getKey() == value) {
                hashTable[i].setKey(-111);
                //Sets isFound to true
                isFound = true;
            }
        }

        //will remove the asked of value
        if(isFound){
            System.out.println(value + " is Found and removed!");
        }
        else{
            System.out.println(value + " is not Found!");
        }

    }


    public static void printHashTable(){
        String output = "[";

        //Adds the value onto the output
        for(int i = 0; i < hashTable.length; i++){

            if(hashTable[i].getKey() == -1){
                output += "null, ";

            } else if(hashTable[i].getKey() == -111){
                output += "available, ";
            }
            else{
                output += (hashTable[i].getKey() + ", ");
            }
        }
        System.out.print(output + "\b\b]");
    }

    public static void rehashingWithLinearProbe(){

        //Declaring the size of workinghashtable
        workingHashTable = new AahashValueEntry[tableCapacity];

        //Set values of array to -1
        for(int i = 0; i < tableCapacity; i++){
            workingHashTable[i] = new AahashValueEntry();
        }

        for(int i = 0; i < tableCapacity; i++){
            workingHashTable[i].setKey(hashTable[i].getKey());
        }

        //Prints the values out
        tableCapacity = checkPrime((tableCapacity*2));
        System.out.println("The Rehashed Table Capacity is: " + tableCapacity);

        //Creates new hashtable entries
        hashTable = new AahashValueEntry[tableCapacity];


        for(int i = 0; i < tableCapacity; i++){
            hashTable[i] = new AahashValueEntry();
        }

        //Brings the values over and assigns them to the array
        for(int i = 0; i < workingHashTable.length; i++){
            if(workingHashTable[i].getKey() != -111){
                addValueLinearProbe(workingHashTable[i].getKey());
            }
        }
    }


    public static void header(int number, int question, String name, int SD, String explanation) {
        System.out.println("===================================================================");
        System.out.printf(" Lab Assignment %d,Q%d \n Prepared By: %s \n Student Number: %d \n Goal of this Exercise: %s\n", number,question, name, SD, explanation);
        System.out.println("===================================================================");
    }

    public static void footer(String name, int lab, int q) {
        System.out.println("\n===================================================================");
        System.out.printf("Completion of Lab Assignment %d, %d is successful!\nSigning Off - %s\n", lab,q, name);
        System.out.println("===================================================================");
    }

    public static void main(String []args){

        header(3,1,"Aahash Srikumar", 251225561, "Printing the values of linear probing");

        //Asking for the size
        System.out.println("Let's Decide on The Initial Table Capacity Based on the Load Factor and Dataset Size \n");
        System.out.print("How Many Data Items: ");
        items = input.nextInt();

        //Getting the load factor
        System.out.print("What is the Load Factor (Recommended: <= 0.5): ");
        lf = input.nextDouble();

        //Finding the table capacity
        tableCapacity = checkPrime((int)(Math.round(items)/lf));

        //Initializing the hashTable
        System.out.println("The minimum required table capacity would be: " + tableCapacity + "\n");
        hashTable = new AahashValueEntry[tableCapacity];

        for(int i = 0; i < tableCapacity; i++){
            hashTable[i] = new AahashValueEntry();
        }

        //asking the user for what value they would like to be added to the hashtable
        for(int i = 0; i < items; i++){
            System.out.print("Enter Item " + (i+1) + ": ");
            int value = input.nextInt();
            addValueLinearProbe(value);
        }

        //Printing the hashTable
        System.out.print("The Current Hash Table:" );
        printHashTable();


        System.out.println("\n\nLet’s Remove Two Values from the Table & Then Add One......." );

        //Asking the user the value they would like to remove
        System.out.print("\nEnter a value you want to remove: ");
        int num1 = input.nextInt();
        removeValueLinearProbe(num1);
        System.out.print("The Current Hash Table: ");
        printHashTable();


        System.out.print("\n\nEnter a value you want to remove: ");
        int num2 = input.nextInt();
        removeValueLinearProbe(num2);
        System.out.print("The Current Hash Table: ");
        printHashTable();

        //adding a value through user input to the hashtable
        System.out.print("\n\nEnter a Value You Want to Add to the Table: ");
        int num3 = input.nextInt();
        addValueLinearProbe(num3);
        System.out.print("The Current Hash Table: ");
        printHashTable();

        //Rehashing the table
        System.out.println("\n\nRehashing the Table...");
        rehashingWithLinearProbe();

        //Checking whether its been rehashed
        System.out.print("The Current Hash Table: ");
        printHashTable();

        footer("Aahash", 3,1);
    }


}


