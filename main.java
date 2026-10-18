import java.util.Scanner;

public class main{
    public static void main(String[] args){
        ServerConnection connection = new ServerConnection("netsrv.cim.rhul.ac.uk", 1812);
            Scanner sc = new Scanner(System.in);
            System.out.print("Username: ");
            String username = sc.nextLine().toLowerCase();
            System.out.print("Password: ");
            String password = sc.nextLine();

                Boolean flag = true;
                boolean loggedIn = connection.login(username, password);

                if (loggedIn){
                System.out.print("""
                    1. list my cards
                    2. show my credits
                    3. list available cards
                    4. buy card
                    5. sell card
                    6. auto-trade
                    7. exit

                    Please enter your choice:  """ );
                    while (flag){
                        String menuOut = sc.nextLine();
                        switch(menuOut){
                        case "1":
                            connection.showCards();
                            System.out.println("Press return to go back to the menu");
                            sc.nextLine();
                            break;

                        case "2":
                        connection.showCredits();
                        System.out.println("Press return to go back to the menu");
                        sc.nextLine();
                        break;

                        case "3":
                            connection.showOffers();;
                        System.out.print("Press return to go back to the menu \n");
                        sc.nextLine();
                        break;

                        case "4":
                            System.out.print("What is the id of the card you would like to buy: ");
                            int cardIdBuy = sc.nextInt();
                            sc.nextLine();
                            connection.buyCards(cardIdBuy);
                        System.out.println("Press return to go back to the menu");
                        sc.nextLine();
                        break;

                        case "5":
                            System.out.print("What is the id of the card you would like to sell");
                            int cardIdSell = sc.nextInt();
                            sc.nextLine();
                            System.out.print("How much would you like to sell " + cardIdSell + " for: ");
                            Long sellPrice = sc.nextLong();
                            sc.nextLine();
                            connection.sellCards(cardIdSell, sellPrice);
                        System.out.println("Press return to go back to the menu");
                        sc.nextLine();
                        break;

                        case "6":
                            connection.autoSell();
                            connection.autoBuy();
                            break;
                        case "7":
                            flag = false;
                            sc.close();
                            connection.closeConnection();
                            break;

                }
                if (flag){
                System.out.print("""
                    1. list my cards
                    2. show my credits
                    3. list available cards
                    4. buy card
                    5. sell card
                    6. auto-trade
                    7. exit

                    Please enter your choice:  """ );
                }
            }
        }
    }
}