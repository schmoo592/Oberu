import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * @author Alex
 * Created by Alex on 11/14/2015.
 *
 * This is where we wil define what a "card" is. A card is an object that has three strings: Body, Header, and Footer.
 * With these three strings the user is able to see all necessary information for the card.
 * It's essentially a flash card.
 */

public class Card implements Comparable<Card>, Comparator<Card> {
    private String cardBody, cardHeader, cardFooter, fileLocation;
    protected int difficulty = 0;

    /**
     * @param body
     * @param header
     * @param footer
     * @param difficulty
     */
    public Card(String header, String body, String footer, int difficulty, String fileLocation) {
        cardBody = body;
        cardHeader = header;
        cardFooter = footer;
        this.difficulty = difficulty;
        this.fileLocation = fileLocation;
    }

    public Card() {

    }

    public String getBody() {
        /**
         * @return cardBody
         */
        return this.cardBody;
    }

    public String getHeader() {
        /**
         * @return cardHeader
         */
        return this.cardHeader;
    }

    public String getFooter() {
        /**
         * @return cardFooter
         */
        return this.cardFooter;
    }


    public void setCardBody(String cardBody) {
        /**
         * This will allow us to set the card body.
         */
        this.cardBody = cardBody;
    }

    public void setCardHeader(String cardHeader) {
        /**
         * This will allow us to set the card header to whatever we please.
         */
        this.cardHeader = cardHeader;
    }

    public void setCardFooter(String cardFooter) {
        /**
         * This allows us to set the card footer as whatever we please.
         */
        //Sets the Card footer to a desired string
        this.cardFooter = cardFooter;
    }

    public void setDifficulty(int difficulty) {
        /**
         * This sets the difficult of the card.
         */
        //Sets the difficulty of the Card
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        /**
         * @reutrn cardDifficulty returns the difficulty of the card
         */
        return this.difficulty;

    }

    public int compareTo(Card c) {
        /**
         * @param Card c
         * This method allows Oberu to sort the cards as needed.
         */
        Integer thisDifficulty = this.getDifficulty();
        Integer cDifficulty = c.getDifficulty();
        return thisDifficulty.compareTo(cDifficulty);

    }

    public int compare(Card c1, Card c2) {
        /**
         * @param Card c1, Card c2.
         * This method also allows the program to sort the cards in the deck in the order they must appear in.
         * With the most difficult cards appearing first and last.
         */
        return c1.difficulty - c2.difficulty;

    }

    public void difficult() {
        /**
         * This method is incredibly important to the program. This allows the user to alter the difficulty level of the cards.
         * If the user tags a card as being difficult then the card's data is altered so that the difficulty is decremented by 10.
         */
        File file = new File(this.fileLocation);
        try {
            String rawCardData = FileUtils.readFileToString(file, "UTF-8");
            String[] processedCardData = rawCardData.split("\\r?\\n");
            int numDifficulty = Integer.parseInt(processedCardData[3]) - 10;
            String acDifficulty = Integer.toString(numDifficulty);
            System.out.println(acDifficulty);
            processedCardData[3] = acDifficulty;
            System.out.println(processedCardData[3]);
            FileUtils.deleteQuietly(file);
            for (int i = 0; i < processedCardData.length; i++) {
                System.out.println("DID WE MAKE IT?");
                FileUtils.writeStringToFile(file, processedCardData[i], "UTF-8", true);
                FileUtils.writeStringToFile(file, System.lineSeparator(), "UTF-8", true);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException: " + e.getMessage());
        }

    }

    public void easy() {
        /**
         * This method is also incredibly important to the program. This does the same thing as the difficult() method
         * but instead increments the card's data by 10, makign the card marked as "easier" compared to other cards.
         */
        File file = new File(this.fileLocation);
        try {
            String rawCardData = FileUtils.readFileToString(file, "UTF-8");
            String[] processedCardData = rawCardData.split("\\r?\\n");
            int numDifficulty = Integer.parseInt(processedCardData[3]) + 10;
            String acDifficulty = Integer.toString(numDifficulty);
            processedCardData[3] = acDifficulty;
            FileUtils.deleteQuietly(file);
            for (int i = 0; i < processedCardData.length; i++) {
                FileUtils.writeStringToFile(file, processedCardData[i], "UTF-8", true);
                FileUtils.writeStringToFile(file, System.lineSeparator(), "UTF-8", true);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException: " + e.getMessage());
        }

    }
    public void saveCard(String header, String body, String footer, String fileLocation){
        /**
         * @params String header, body, footer, fileLocation
         * This method allows the user to save a card to a certain location. This is important because it allows the user to
         * create cards to study in a simple an intuitive environment.
         */
        Card card = new Card(header, body, footer, 0, fileLocation);
        File file = new File(card.fileLocation + File.separator + body + ".txt");
        String[] cardInfo = {header, body, footer, "0"};
        try {
            if (file.exists()) {
                JOptionPane.showMessageDialog(null, "A card with the same body already exists in this deck");
            } else {
                for (int i = 0; i < cardInfo.length; i++) {
                    FileUtils.writeStringToFile(file, cardInfo[i], "UTF-8", true);
                    FileUtils.writeStringToFile(file, System.lineSeparator(), "UTF-8", true);
                }
                JOptionPane.showMessageDialog(null, "File successfully created");
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "IOException: " + e.getMessage());
        }
    }
}



