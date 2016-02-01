//Assignment1- This program runs a bookstore that sells books, bookmarks, and paintings of books
//every third customer gets a discount of 10 percent


import java.util.Scanner;

public class a1 {

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
//declaring the variables
    int numbooks = 0;
    int nummarks = 0;
    int numpaintings = 0;
    double cost = 0.00;
    double costbooks = 0.00;
    double costmarks = 0.00;
    double costpaintings = 0.00;
    double tax = 0.00;
    double pay;
    double discount;
    double total;
    int bookmarkpacks=0;
    int singlebookmarks=0;




    int i = 1; //number of students

      System.out.println("More customers in line? (1 = Yes, 2 = No)");
      int cust = sc.nextInt();
      while (cust == 1 ){


        //choices that customer can make
        System.out.println("1 - Buy Books - $5.00 each");
        System.out.println("2 - Buy Bookmarks - $1.00 each, $5.00 for a six-pack");
        System.out.println("3 - Buy Paintings of Books - $100.00 each");
        System.out.println("4 - See current order");
        System.out.println("5 - Checkout");
        System.out.println("Please enter a valid option (1-5) >> \n");
          int choice = sc.nextInt();
        //while loop to make sure that user inputs a correct choice
        while (choice > 5 || choice < 0){
                System.out.println("Please enter a number 1 through 5");
                choice = sc.nextInt();
                                        }
        if ( choice  == 1 ){
          // Choice to buy books
            System.out.println("Books currently in cart : " + numbooks);
            System.out.println("How many books do you want to buy?");
            int addbooks = sc.nextInt();
            numbooks = numbooks + addbooks; //adds number of new books to be bough to number already being bought
            costbooks = cost+(numbooks*5.00);
                            }
        if ( choice == 2){
          //choice to buy bookmarks
          System.out.println("Bookmarks currently in cart : " + nummarks);
          System.out.println("How many Bookmarks do you want to buy?");
          int addmarks = sc.nextInt();
          nummarks = nummarks + addmarks;
            //start of if statement to determine if bookmarks are in packs or singles
            if (nummarks >= 6){
                //using the modulus to see if the int can be divided into
                //, and if so then bookmarks packs are used
                costmarks = ((nummarks/6)*5.00)+(nummarks%6*1.00);
                bookmarkpacks = nummarks/6;
                singlebookmarks = nummarks%6;
                              }

            else{

                costmarks = cost + (nummarks*1.00);

                }
                      }
        if (choice == 3){
            //choice to buy paintings
            System.out.println("Paintings currently in cart: " + numpaintings);
            System.out.println("How many Paintings do you want to buy?");
            int addpaintings = sc.nextInt();
            numpaintings = numpaintings + addpaintings;
            costpaintings = cost + (numpaintings*100.00);

                        }

        if (choice == 4){
          //this just prints out the number of things that the users is going to buy
              System.out.println("Your current order is:");
              System.out.println(numbooks + " Books");
              System.out.println(nummarks + " Bookmarks");
              System.out.println(numpaintings + " Paintings");
                        }

        if (choice == 5){
          //checkout if statement, seperated into 2 sections, one with discount and one without

            if (i%3 == 0){ //DISCOUNT IF STATEMENT, using an i counter modulus to determine if multiple of 3

              System.out.println("You get a %10 discount!");
                if (numbooks > 0){
                  System.out.printf("%d books:\t\t$%.2f\n", numbooks, costbooks);
                                  }
                if (nummarks > 0 ){ //if statement to show seperate bookmark packs and singles
                    if ( bookmarkpacks > 0 ){
                        System.out.printf("%d Bookmark Packs:\t$%.2f\n", bookmarkpacks, bookmarkpacks*5.00);
                          if ( singlebookmarks > 0 ){
                            System.out.printf("%d Single bookmarks:\t\t$%.2f\n", singlebookmarks, singlebookmarks*1.00 );
                                                  }
                                          }

                                  }
                if (numpaintings > 0){
                    System.out.println(numpaintings + " Paintings:\t\t$" + costpaintings);
                                    }

                                    cost = costbooks + costmarks + costpaintings;
                                    discount = cost*.1;
                                    cost = cost-discount;
                                    tax = cost*.07;
                                    total = cost + tax;

                System.out.printf("Discount! Saved:\t$%.2f\n", discount);
                System.out.printf("\n\nSubtotal:\t\t$%.2f\n", cost);
                System.out.printf("Tax:\t\t\t$%.2f\n", tax);
                System.out.println("\n------------------------------");
                System.out.printf("Total:\t\t$%.2f\n\n", total);

                          }
              else{ //NON DISCOUNT if statement

                  if (numbooks > 0){
                      System.out.println("You did not get a discount! Better luck next time!");
                      System.out.printf("%d Books:\t\t$%.2f\n", numbooks, costbooks);
                                    }
                  if (nummarks > 0 ){
                      if ( bookmarkpacks > 0 ){
                          System.out.printf("%d Bookmark Packs:\t$%.2f\n", bookmarkpacks, bookmarkpacks*5.00);
                              if ( singlebookmarks > 0 ){
                                  System.out.printf("%d Single bookmarks:\t\t$%.2f\n" ,singlebookmarks, singlebookmarks*1.00 );
                                                                      }
                                                              }

                                                      }

                  if (numpaintings > 0){
                      System.out.printf("%d Paintings:\t\t$%.2f\n", numpaintings, costpaintings);
                                        }

                  cost = costbooks + costmarks + costpaintings;
                  tax = cost*.07;
                  total = cost + tax;

                  System.out.printf("\nSubtotal:\t\t$%.2f", cost);
                  System.out.printf("\nTax:\t\t\t$%.2f\n", tax);
                  System.out.println("------------------------------");
                  System.out.printf("Total:\t\t$%.2f\n\n", total);
                                        }

              System.out.println("Enter dollar amount paid: \n>>");
              pay = sc.nextDouble();

                while (pay < total){
                    System.out.println("Not enough money, please re-enter");
                      pay = sc.nextDouble();
                                  }

                System.out.printf("Your change is : $%.2f\n", (pay - total));
                System.out.println("Thanks for shopping!\n\n");
                //resetting the variables to 0 for next customer
                numbooks=0;
                nummarks=0;
                numpaintings=0;
                costbooks=0;
                costmarks=0;
                costpaintings=0;

                cost = 0;
                tax=0;
                pay=0;
                discount=0;
                total=0;



                i++;
                //rechecking if there is a customer inline and redeclaring the cust variable
                //to make sure to still run the while statement again
                System.out.println("More customers in line? (1 = Yes, 2 = No)");
                cust = sc.nextInt();

                        }

          }


      }
}
