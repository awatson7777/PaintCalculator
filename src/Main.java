import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;

public class Main {

    static Scanner myScanner = new Scanner(System.in);
    static double height;
    static double length;
    static double walls;
    static double wallArea;
    static double finalArea;
    static double obstructionTotal;
    static double totalPaint;
    static int stockLevel = 100; //litres
    static float paintPerCan;  //litres
    static int numPaintLayers;
    static boolean addWalls;
    static double doorArea;
    static double doorPresence;
    static double windowArea;
    static double windowPresence;
    static float obstructHeight;
    static float obstructLength;
    static float obstructRadius;

    public static void main(String[] args) {

        System.out.println("Welcome to the Paint Calculator, what is your name?");
        String Name = myScanner.nextLine();

        //paintPerCan = RequestLitres("How many litres in a paint can?: ");

        System.out.println(Name + " how many walls do you want to paint?");
        walls = Double.parseDouble(myScanner.nextLine());

        numPaintLayers = RequestCoats("How many coats of paint do you want to apply?: ");

        boolean b = false;

        while (!b) {

            boolean addWalls = RequestAnswer("Would you like to calculate the wall area?");

            if (addWalls) {
                System.out.print("Please enter the height of the wall (in metres): ");
                height = Double.parseDouble(myScanner.nextLine());
                System.out.println(height + " metres");

                System.out.print(Name + " please enter the length of the wall (in metres): ");
                length = Double.parseDouble(myScanner.nextLine());
                System.out.println(length + " metres");
                boolean obstruct = false;

                while (!obstruct) {
                    boolean addObstruct = RequestAnswer("Does the wall have any obstructions?");
                    if (addObstruct) {
                        String obstructType = RequestObstruct("What shape is the obstruction?");
                        if (obstructType.equals("rectangle")) {
                            obstructHeight = RequestLitres("What is the height of the rectangular obstruction? (metres): ") * 100;
                            obstructLength = RequestLitres("What is the width of the rectangular obstruction? (metres): ") * 100;
                        } else {
                            obstructRadius = RequestLitres("What is the radius of the circular obstruction? (metres): ") * 100;
                        }
                    } else {
                        obstruct = true;
                        b = true;
                    }
                }
            }
        }

        wallArea = length * height;
        obstructionTotal =  - doorPresence + windowPresence + obstructHeight + obstructLength + obstructRadius;
        finalArea = wallArea - obstructionTotal;


        totalPaint = stockLevel - paintNeeded(stockLevel);

        doorsPresent();
        windowsPresent();

        System.out.println(wallArea + " Total Wall Area");
        displayPaintNeeded(paintNeeded(finalArea)*numPaintLayers / 10);
        computePrice(paintNeeded(finalArea));
        displayPrice(computePrice(paintNeeded(finalArea)) / 10);
        //checkColourStock();
        //System.out.println(String.format("%.2f", totalPaint) + " litres of paint left");

    }


    /** Methods */

    private static String RequestObstruct(String ObstructQuestion) {
        while (true)
        {
            System.out.println(ObstructQuestion);
        switch (myScanner.nextLine().toLowerCase(Locale.ROOT)) {
            case "rectangle":
            case "r":
                return "rectangle";
            case "circle":
            case "c":
                return "circle";
            default:
                System.out.println("Please answer rectangle or circle");
        }
    }

}

    private static int RequestCoats(String s) {
        while (true)
        {
            System.out.println(s);

            try
            {
                return Integer.parseInt(myScanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a whole number");
            }
        }
    }

    private static float RequestLitres(String s) {
        while (true)
        {
            System.out.println(s);

            try
            {
                return Float.parseFloat(myScanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a decimal number");
            }
        }
    }

    private static boolean RequestAnswer(String s) {
        while (true)
        {
            System.out.println(s);
            switch (myScanner.nextLine().toLowerCase(Locale.ROOT))
            {
                case "y":
                case "yes":
                    return true;
                case "n":
                case "no":
                    return false;
                default:
                    System.out.println("Please type yes or no");

            }
        }
    }

    public static double paintNeeded(double wallArea) {
        double squareFtPerLitre = 100;
        double litresOfPaintNeeded = finalArea / squareFtPerLitre;
        return litresOfPaintNeeded;
    }

    public static void displayPaintNeeded(double litresNeeded) {
        System.out.println("You need " + String.format("%.2f",litresNeeded) + " litres of paint.");
        if (litresNeeded >= 100) {
            //System.out.println(("Paint Basket Limit of 100 Litres Reached "));
        }
    }

    public static void doorsPresent() {

        double areDoorsPresent = 0;

        System.out.print("Enter number of doors: ");
        areDoorsPresent = Double.parseDouble(myScanner.nextLine());

        if (areDoorsPresent == 1) {
            System.out.print("Enter height of doors: ");
            height = Integer.parseInt(myScanner.nextLine());
            System.out.println(height + " metres");
            System.out.print("Enter length of doors: ");
            length = Integer.parseInt(myScanner.nextLine());
            System.out.println(length + " metres");
            double doorArea = length * height;


        } else if (areDoorsPresent > 1) {
            System.out.print("Enter height of doors: ");
            height = Integer.parseInt(myScanner.nextLine());
            System.out.println(height + " metres");
            System.out.print("Enter length of doors: ");
            length = Integer.parseInt(myScanner.nextLine());
            System.out.println(length + " metres");
            doorArea = length * height;
            System.out.println("Door area = " + doorArea);
            doorPresence = doorArea * areDoorsPresent;
            System.out.println("Obstruction total = " + (wallArea -= doorPresence));
        }
        if (areDoorsPresent == 0) {
            System.out.println("No Doors Present");
        }
    }

    public static void windowsPresent() {

        double areWindowsPresent = 0;

        System.out.print("Enter number of windows: ");
        areWindowsPresent = Double.parseDouble(myScanner.nextLine());

        if (areWindowsPresent >= 1) {
            System.out.print("Enter length of windows: ");
            length = Integer.parseInt(myScanner.nextLine());
            System.out.print("Enter height of windows: ");
            height = Integer.parseInt(myScanner.nextLine());
            double windowArea = length * height;

        } else if (areWindowsPresent > 1) {
            System.out.print("Enter height of doors: ");
            height = Integer.parseInt(myScanner.nextLine());
            System.out.println(height + " metres");
            System.out.print("Enter length of doors: ");
            length = Integer.parseInt(myScanner.nextLine());
            System.out.println(length + " metres");
            windowArea = length * height;
            windowPresence = windowArea * areWindowsPresent;
            System.out.println("Obstruction total = " + (wallArea -= windowPresence));
        }
        if (areWindowsPresent == 0) {
            System.out.println("No Windows Present");
        }
    }

//    public static void chooseColour() {
//        int colour = 0;
//        String pickedColour;
//        String[] paintColour = {"Red", "Blue", "Green", "Yellow", "White", "Black" };
//        switch (colour) {
//            case 1:
//               pickedColour = paintColour[1];
//                checkColourStock();
//                break;
//            case 2:
//                pickedColour = paintColour[2];
//                checkColourStock();
//                break;
//            case 3:
//                pickedColour = paintColour[3];
//                checkColourStock();
//                break;
//            case 4:
//                pickedColour = paintColour[4];
//                checkColourStock();
//                break;
//            default:
//                pickedColour = paintColour[5];
//                checkColourStock();
//                break;
//            case 6:
//                pickedColour = paintColour[6];
//                checkColourStock();
//                break;
//            case 7:
//                if (paintColour != paintColour) {
//                System.out.println("Colour Not Available, Please Choose Another");
//            }
//        }
//        System.out.println(paintColour);
//    }

    public static void checkColourStock() {
        switch (stockLevel) {
            case 1:
                if (stockLevel >= 75) {
                    System.out.println("Paint Available for Order");
                }
                break;
            case 2:
                if (stockLevel >= 50) {
                    System.out.println("Stock at 50%");
                }
                break;
            case 3:
                if (stockLevel < 50) {
                    if (stockLevel > 25) {
                        System.out.println("Stock less than 50%, Please Consider Placing A Stock Order");
                    }
                }
                break;
            case 4:
                if (stockLevel < 25) {
                    System.out.println("Stock Low, Place an Order Immediately");
                }
                break;
            case 5:
                if (stockLevel == 0) {
                    System.out.println("Out of Stock, Please Choose Another Colour");
                }
                break;
        }
        while (stockLevel <= 50) {
            System.out.println("Paint Level Below 50%, Would You Like To Order More? [Yes/No]");
            String Answer = myScanner.nextLine();
            System.out.println(Answer);
        }
    }

    public static double computePrice(double paintNeeded)
    {

        double totalPrice;
        double pricePerLitre = 14.76; //random realistic value
        totalPrice = pricePerLitre * paintNeeded;
        return totalPrice;

    }

    public static void displayPrice(double totalPrice)
    {

        System.out.println("The total price is " + String.format("%.2f",totalPrice) + "GBP.");

    }
}