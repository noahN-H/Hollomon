import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ServerConnection {
    private Socket sock;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ServerConnection(String hostname, int port){
        try{
            sock = new Socket(hostname, port);

            reader = new BufferedReader(
                new InputStreamReader(
                    sock.getInputStream()));
            writer = new BufferedWriter(
                new OutputStreamWriter(
                    sock.getOutputStream()));
        }

        catch (IOException e) {
            System.out.println("An error has occured");
        }
    }

    public boolean login(String username, String password){
        boolean loggedIn = false;
        try {
            writer.write(username);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.flush();

            String loginOut = reader.readLine();
            System.out.println(loginOut);

            if (loginOut.equals("User " + username + " logged in successfully.")){
                loggedIn = true;
            }
            // consumes all cards when logged in to reduce clutter if user wants to display their card collection they can choose todo so through the menu
            if (loggedIn){
                while (!reader.readLine().equals("OK")){}
            }
            } catch (IOException e) {
                System.out.println("Failed to log in");
            }
            return loggedIn;
    }

    public void showCards(){
        try {
        writer.write("CARDS");
        writer.newLine();
        writer.flush();
        System.out.println(cardHelper());
        }
        catch (IOException e) {
                System.out.println("Failed to show cards");
        }
    }

    public void showCredits(){
        try {
        writer.write("CREDITS");
        writer.newLine();
        writer.flush();
        String creditsOut = reader.readLine();
        while (!creditsOut.equals("OK")){
            System.out.println(creditsOut);
            creditsOut = reader.readLine();
        }
        } catch (IOException e) {
            System.out.println("Failed to show credits");
        }
    }

    public void showOffers() {
        try{
            writer.write("OFFERS");
            writer.newLine();
            writer.flush();
            System.out.println(cardHelper());
        } catch (IOException e) {
            System.out.println("Failed to show offers");
        }
    }

    public String buyCards(int cardId){
        try {
            writer.write("BUY " + cardId);
            writer.newLine();
            writer.flush();
            String buyOut = reader.readLine();
            System.out.println(buyOut);
            return buyOut;
        } catch (IOException e) {
                return "Failed to buy cards";
            }
    }

    public void sellCards(int cardId, Long sellPrice){
        try {
        writer.write("SELL " + cardId + " " + sellPrice);
        writer.newLine();
        writer.flush();
        String sellOut = reader.readLine();
        System.out.println(sellOut);
        } catch (IOException e) {
            System.out.println("Failed to sell cards");
        }
    }

    //HashSets used as they are the most efficient way of checking for duplicate cards

    public void autoSell(){
        try {
        writer.write("CARDS");
        writer.newLine();
        writer.flush();
        List<Card> cardDisplay = cardHelper();
        HashSet<String> nameStore = new HashSet<>();

        for (Card cards : cardDisplay ){
            if (!nameStore.contains(cards.name)) nameStore.add(cards.name);
            else {
                switch(cards.rank){
                    case COMMON:
                        sellCards(cards.id, cards.salePrice);
                        break;
                    case UNCOMMON:
                        sellCards(cards.id, cards.salePrice);
                        break;
                    case RARE:
                        sellCards(cards.id, cards.salePrice);
                        break;
                    case UNIQUE:
                        sellCards(cards.id, cards.salePrice);
                        break;
                }
            }
        }
        }
        catch (IOException e) {
                System.out.println("Failed to complete auto-sell");
        }
    }

    public void autoBuy(){
        try {
        writer.write("CARDS");
        writer.newLine();
        writer.flush();
        List<Card> cardDisplay = cardHelper();
        HashSet<Integer> idStore = new HashSet<>();

        for (Card cards : cardDisplay ){
            idStore.add(cards.id);
        }

        writer.write("OFFERS");
        writer.newLine();
        writer.flush();
        List<Card> offerDisplay = cardHelper();

        for (Card cards : offerDisplay ){
            if (!idStore.contains(cards.id)) {
                String buy = buyCards(cards.id);
                if (buy.equals("ERROR")) {
                    break;
                }
            }
        }
        }
        catch (IOException e) {
                System.out.println("Failed to complete auto-buy");
        }
    }

    public List<Card> cardHelper() {
        List<Card> cardArray = new ArrayList<>();
        try {
            String cardOut = reader.readLine();
            while (!cardOut.equals("OK")){
                String idTempStr = reader.readLine();
                int idTempInt = Integer.parseInt(idTempStr);

                String nameTemp = reader.readLine();

                String rankTempStr = reader.readLine();
                Card.Rank rankTempRank = Card.Rank.valueOf(rankTempStr);

                String priceTempStr = reader.readLine();
                Long priceTempLong = Long.parseLong(priceTempStr);

                Card cardTemp = new Card(idTempInt, nameTemp, rankTempRank, priceTempLong);
                cardArray.add(cardTemp);
                cardOut = reader.readLine();

            }
        } catch (IOException e) {
            System.out.println("Failed to create card list");
        }
        Collections.sort(cardArray);
        return cardArray;
    }

    public void closeConnection(){
        try {
            writer.close();
            reader.close();
            sock.close();
        } catch (IOException e) {
        }
    }
}