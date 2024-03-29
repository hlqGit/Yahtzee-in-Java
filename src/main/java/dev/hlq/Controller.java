package dev.hlq;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Controller {

    @FXML
    private Rectangle dice0, dice1, dice2, dice3, dice4;

    @FXML
    private Label label1, label2, label3, label4, label5, label6, label7, label8,
    label9, label10, label11, label12, label13, label14, label15, label16, label17,
    label18, label19, label20, label21, label22, label23, label24, label25, label26,
    label27, label28, label29, label30, label31, label32, label33, label34, label35;

    @FXML
    private Label hold0, hold1, hold2, hold3, hold4;

    @FXML
    private Label aces, twos, threes, fours, fives, sixes, totalScoreUpper, bonus,
    threeKind, fourKind, fullHouse, smStraight, lgStraight, yahtzee, chance,
    yahtzeeBonusScore, yahtzeeBonus0, yahtzeeBonus1, yahtzeeBonus2, totalScoreLower,
    grandTotal, infoBoxLabel, informationLabel;

    @FXML
    private Button rollDiceButton;

    private boolean acesScored, twosScored, threesScored, foursScored, fivesScored,
    sixesScored, threeKindScored, fourKindScored, fullHouseScored, smStraightScored,
    lgStraightScored, yahtzeeScored, chanceScored, yahtzeeBonus0Scored, yahtzeeBonus1Scored,
    yahtzeeBonus2Scored, is0Holding, is1Holding, is2Holding, is3Holding, is4Holding;

    private static boolean turnStarted = false, canRollDice = true, canScore = false,
    kindsTempAccess = false, lowerSectionTempAccess = false;

    private static int dice0Value, dice1Value, dice2Value, dice3Value, dice4Value;
    private static int yahtzeeBonusCount;
    private int rollCount;

    private static int[] diceList = new int[5];

    final Color holdRed = new Color(1.0, 0.0, 0.0, 0.4962);
    final Color transparent = new Color(1.0, 0.0, 0.0, 0.0);
    final Color black = new Color(0.0, 0.0, 0.0, 1.0);

    Random random = new Random();

    public void showHelp() throws IOException{
        Stage mainStage = new Stage();
        Scene newScene = new Scene(App.loadFXML("Instructions"));
        mainStage.setScene(newScene);
        mainStage.show();
        mainStage.setResizable(false);
    }

    public void holdDice0(){
        if(!turnStarted) { return; }
        if(!is0Holding ){ hold0.setTextFill(holdRed); is0Holding = true; } 
        else { hold0.setTextFill(transparent); is0Holding = false; }
    }
    public void holdDice1(){
        if(!turnStarted) { return; }
        if(!is1Holding ){ hold1.setTextFill(holdRed); is1Holding = true; } 
        else { hold1.setTextFill(transparent); is1Holding = false; }
    }
    public void holdDice2(){
        if(!turnStarted) { return; }
        if(!is2Holding ){ hold2.setTextFill(holdRed); is2Holding = true; } 
        else { hold2.setTextFill(transparent); is2Holding = false; }
    }
    public void holdDice3(){
        if(!turnStarted) { return; }
        if(!is3Holding ){ hold3.setTextFill(holdRed); is3Holding = true; } 
        else { hold3.setTextFill(transparent); is3Holding = false; }
    }
    public void holdDice4(){
        if(!turnStarted) { return; }
        if(!is4Holding ){ hold4.setTextFill(holdRed); is4Holding = true; } 
        else { hold4.setTextFill(transparent); is4Holding = false; }
    }

    public void nextTurn(){
        rollDiceButton.setText("Roll!\n3 Rolls left");
        canRollDice = true; rollCount = 0;
        if(is0Holding){ holdDice0(); }
        if(is1Holding){ holdDice1(); }
        if(is2Holding){ holdDice2(); }
        if(is3Holding){ holdDice3(); }
        if(is4Holding){ holdDice4(); }
        turnStarted = false;
        calcUpperScore(); calcLowerScore();
        yahtzeeBonusScore.setFont(Font.font("System", FontWeight.BOLD, 20));
        yahtzeeBonusScore.setText(yahtzeeBonusCount > 0 ? "" + 100 * yahtzeeBonusCount : "");
        calcGrandScore();
        if(allCategoriesScored()){
            canRollDice = false;
            rollDiceButton.setText("Game Over!");
        }
    }

    public boolean allCategoriesScored(){
        return acesScored && twosScored && threesScored && foursScored 
        && fivesScored && sixesScored && threeKindScored && fourKindScored 
        && fullHouseScored && smStraightScored && lgStraightScored && yahtzeeScored
        && chanceScored;
    }

    public void rollDice(){
        if(canRollDice){
            informationLabel.setText("");
            infoBoxLabel.setText("");
            turnStarted = true;
            canScore = true;
            if (!is0Holding) { rollDice0(); }
            if (!is1Holding) { rollDice1(); }
            if (!is2Holding) { rollDice2(); }
            if (!is3Holding) { rollDice3(); }
            if (!is4Holding) { rollDice4(); }
            switch(rollCount){
                case 0 : {
                    ++rollCount;
                    rollDiceButton.setText("Roll!\n2 Rolls left");
                    break;
                }
                case 1 : {
                    ++rollCount;
                    rollDiceButton.setText("Roll!\n1 Roll left");
                    break;
                }
                case 2 : {
                    canRollDice = false;
                    rollDiceButton.setText("Out of rolls!");
                    informationLabel.setText("Information:");
                    infoBoxLabel.setText("You must fill out your scorecard now!");
                    break;
                }
            }
            fillScoreCardValues();
        }
    }

    public void fillScoreCardValues(){
        
        diceList[0] = dice0Value;
        diceList[1] = dice1Value;
        diceList[2] = dice2Value;
        diceList[3] = dice3Value;
        diceList[4] = dice4Value;

        Arrays.sort(diceList);
        
        //upper section
        if(!acesScored) { aces.setText("" + acesScore(diceList)); }
        if(!twosScored) { twos.setText("" + twosScore(diceList)); }
        if(!threesScored) { threes.setText("" + threesScore(diceList)); }
        if(!foursScored) { fours.setText("" + foursScore(diceList)); }
        if(!fivesScored) { fives.setText("" + fivesScore(diceList)); }
        if(!sixesScored) { sixes.setText("" + sixesScore(diceList)); }

        //lower section
        if(!threeKindScored) { threeKind.setText("" + threeKindScore(diceList)); }
        if(!fourKindScored) { fourKind.setText("" + fourKindScore(diceList)); }
        if(!fullHouseScored) { fullHouse.setText("" + fullHouseScore(diceList));}
        if(!smStraightScored) { smStraight.setText("" + smStraightScore(diceList)); }
        if(!lgStraightScored) { lgStraight.setText("" + lgStraightScore(diceList)); }
        if(!yahtzeeScored) { yahtzee.setText("" + yahtzeeScore(diceList)); }
        if(!chanceScored) { chance.setText("" + chanceScore(diceList)); }
        if(yahtzeeScored && !yahtzeeBonus0Scored) { yahtzeeBonus0.setText("" + yahtzeeBonusScore(diceList)); }
        if(yahtzeeBonus0Scored && !yahtzeeBonus1Scored) { yahtzeeBonus1.setText("" + yahtzeeBonusScore(diceList)); }
        if(yahtzeeBonus1Scored && !yahtzeeBonus2Scored) { yahtzeeBonus2.setText("" + yahtzeeBonusScore(diceList)); }
    }

    public void setAcesScore(){
        if(canScore){
            aces.setFont(Font.font("System", FontWeight.BOLD, 20));
            acesScored = true;
            aces.cursorProperty().set(Cursor.DEFAULT);
            aces.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setTwosScore(){
        if(canScore){
            twos.setFont(Font.font("System", FontWeight.BOLD, 20));
            twosScored = true;
            twos.cursorProperty().set(Cursor.DEFAULT);
            twos.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setThreesScore(){
        if(canScore){
            threes.setFont(Font.font("System", FontWeight.BOLD, 20));
            threesScored = true;
            threes.cursorProperty().set(Cursor.DEFAULT);
            threes.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setFoursScore(){
        if(canScore){
            fours.setFont(Font.font("System", FontWeight.BOLD, 20));
            foursScored = true;
            fours.cursorProperty().set(Cursor.DEFAULT);
            fours.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setFivesScore(){
        if(canScore){
            fives.setFont(Font.font("System", FontWeight.BOLD, 20));
            fivesScored = true;
            fives.cursorProperty().set(Cursor.DEFAULT);
            fives.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setSixesScore(){
        if(canScore){
            sixes.setFont(Font.font("System", FontWeight.BOLD, 20));
            sixesScored = true;
            sixes.cursorProperty().set(Cursor.DEFAULT);
            sixes.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setThreeKindScore(){
        if(canScore || kindsTempAccess){
            threeKind.setFont(Font.font("System", FontWeight.BOLD, 20));
            threeKindScored = true;
            threeKind.cursorProperty().set(Cursor.DEFAULT);
            threeKind.onMouseClickedProperty().set(null);
            canScore = false; kindsTempAccess = false;
            nextTurn();
        }
    }
    public void setFourKindScore(){
        if(canScore || kindsTempAccess){
            fourKind.setFont(Font.font("System", FontWeight.BOLD, 20));
            fourKindScored = true;
            fourKind.cursorProperty().set(Cursor.DEFAULT);
            fourKind.onMouseClickedProperty().set(null);
            canScore = false; kindsTempAccess = false;
            nextTurn();
        }
    }
    public void setFullHouseScore(){
        if(canScore || lowerSectionTempAccess){
            fullHouse.setFont(Font.font("System", FontWeight.BOLD, 20));
            fullHouseScored = true;
            fullHouse.cursorProperty().set(Cursor.DEFAULT);
            fullHouse.onMouseClickedProperty().set(null);
            canScore = false; lowerSectionTempAccess = false;
            nextTurn();
        } 
    }
    public void setSmStraightScore(){
        if(canScore || lowerSectionTempAccess){
            smStraight.setFont(Font.font("System", FontWeight.BOLD, 20));
            smStraightScored = true;
            smStraight.cursorProperty().set(Cursor.DEFAULT);
            smStraight.onMouseClickedProperty().set(null);
            canScore = false; lowerSectionTempAccess = false;
            nextTurn();
        }
    }
    public void setLgStraightScore(){
        if(canScore || lowerSectionTempAccess){
            lgStraight.setFont(Font.font("System", FontWeight.BOLD, 20));
            lgStraightScored = true;
            lgStraight.cursorProperty().set(Cursor.DEFAULT);
            lgStraight.onMouseClickedProperty().set(null);
            canScore = false; lowerSectionTempAccess = false;
            nextTurn();
        }
        
    }
    public void setYahtzeeScore(){
        if(canScore){
            yahtzee.setFont(Font.font("System", FontWeight.BOLD, 20));
            yahtzeeScored = true;
            yahtzee.cursorProperty().set(Cursor.DEFAULT);
            yahtzee.onMouseClickedProperty().set(null);
            canScore = false; 
            nextTurn();
        }
    }
    public void setChanceScore(){
        if(canScore){
            chance.setFont(Font.font("System", FontWeight.BOLD, 20));
            chanceScored = true;
            chance.cursorProperty().set(Cursor.DEFAULT);
            chance.onMouseClickedProperty().set(null);
            canScore = false;
            nextTurn();
        }
    }
    public void setYahtzeeBonusScore(){
        if(canScore){
            switch (yahtzeeBonusCount){
                case 0 : {
                    if(canScore){
                        yahtzeeBonus0.setFont(Font.font("System", FontWeight.BOLD, 20));
                        yahtzeeBonus0Scored = true;
                        yahtzeeBonus0.cursorProperty().set(Cursor.DEFAULT);
                        yahtzeeBonus0.onMouseClickedProperty().set(null);
                    }
                    break;
                }
                case 1 : {
                    if(canScore){
                        yahtzeeBonus1.setFont(Font.font("System", FontWeight.BOLD, 20));
                        yahtzeeBonus1Scored = true;
                        yahtzeeBonus1.cursorProperty().set(Cursor.DEFAULT);
                        yahtzeeBonus1.onMouseClickedProperty().set(null);
                    }
                    break;
                }
                case 2 : {
                    if(canScore){
                        yahtzeeBonus2.setFont(Font.font("System", FontWeight.BOLD, 20));
                        yahtzeeBonus2Scored = true;
                        yahtzeeBonus2.cursorProperty().set(Cursor.DEFAULT);
                        yahtzeeBonus2.onMouseClickedProperty().set(null);
                    }
                    break;
                }
            }
            yahtzeeBonusCount++;
            informationLabel.setText("Information:");
            switch(dice0Value){
                case 1 : {
                    if (!acesScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and 5 more for the Aces category which has been filled out automatically.");
                        setAcesScore();
                        canScore = false;
                        nextTurn();
                    } else if (!threeKindScored || !fourKindScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points. Now you must put a 5 in either the three or four of a kind category.");
                        canScore = false; canRollDice = false;
                        kindsTempAccess = true;
                    } else if (!fullHouseScored || !smStraightScored || !lgStraightScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and must select any lower section category to fill out too!");
                        fullHouse.setText("25");
                        smStraight.setText("30");
                        lgStraight.setText("40");
                        canScore = false; canRollDice = false;
                        lowerSectionTempAccess = true;
                    } else {
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points.");
                        canScore = false; canRollDice = false;
                    }
                    break;
                }
                case 2 : {
                    if (!twosScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and 10 more for the Twos category which has been filled out automatically.");
                        setTwosScore();
                        canScore = false;
                        nextTurn();
                    } else if (!threeKindScored || !fourKindScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points. Now you must put a 10 in either the three or four of a kind category.");
                        canScore = false; canRollDice = false;
                        kindsTempAccess = true;
                    } else if (!fullHouseScored || !smStraightScored || !lgStraightScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and must select any lower section category to fill out too!");
                        fullHouse.setText("25");
                        smStraight.setText("30");
                        lgStraight.setText("40");
                        canScore = false; canRollDice = false;
                        lowerSectionTempAccess = true;
                    } else {
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points.");
                        canScore = false; canRollDice = false;
                    }
                    break;
                }
                case 3 : {
                    if (!threesScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and 15 more for the Threes category which has been filled out automatically.");
                        setThreesScore();
                        canScore = false;
                        nextTurn();
                    } else if (!threeKindScored || !fourKindScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points. Now you must put a 15 in either the three or four of a kind category.");
                        canScore = false; canRollDice = false;
                        kindsTempAccess = true;
                    } else if (!fullHouseScored || !smStraightScored || !lgStraightScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and must select any lower section category to fill out too!");
                        fullHouse.setText("25");
                        smStraight.setText("30");
                        lgStraight.setText("40");
                        canScore = false; canRollDice = false;
                        lowerSectionTempAccess = true;
                    } else {
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points.");
                        canScore = false; canRollDice = false;
                    }
                    break;
                }
                case 4 : {
                    if (!foursScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and 20 more for the Fours category which has been filled out automatically.");
                        setFoursScore();
                        canScore = false;
                        nextTurn();
                    } else if (!threeKindScored || !fourKindScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points. Now you must put a 20 in either the three or four of a kind category.");
                        canScore = false; canRollDice = false;
                        kindsTempAccess = true;
                    } else if (!fullHouseScored || !smStraightScored || !lgStraightScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and must select any lower section category to fill out too!");
                        fullHouse.setText("25");
                        smStraight.setText("30");
                        lgStraight.setText("40");
                        canScore = false; canRollDice = false;
                        lowerSectionTempAccess = true;
                    } else {
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points.");
                        canScore = false; canRollDice = false;
                    }
                    break;
                }
                case 5 : {
                    if (!fivesScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and 25 more for the Fives category which has been filled out automatically.");
                        setFivesScore();
                        canScore = false;
                        nextTurn();
                    } else if (!threeKindScored || !fourKindScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points. Now you must put a 25 in either the three or four of a kind category.");
                        canScore = false; canRollDice = false;
                        kindsTempAccess = true;
                    } else if (!fullHouseScored || !smStraightScored || !lgStraightScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and must select any lower section category to fill out too!");
                        fullHouse.setText("25");
                        smStraight.setText("30");
                        lgStraight.setText("40");
                        canScore = false; canRollDice = false;
                        lowerSectionTempAccess = true;
                    } else {
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points.");
                        canScore = false; canRollDice = false;
                    }
                    break;
                }
                case 6 : {
                    if (!sixesScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and 30 more for the Sixes category which has been filled out automatically.");
                        setSixesScore();
                        canScore = false;
                        nextTurn();
                    } else if (!threeKindScored || !fourKindScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points. Now you must put a 30 in either the three or four of a kind category.");
                        canScore = false; canRollDice = false;
                        kindsTempAccess = true;
                    } else if (!fullHouseScored || !smStraightScored || !lgStraightScored){
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points, and must select any lower section category to fill out too!");
                        fullHouse.setText("25");
                        smStraight.setText("30");
                        lgStraight.setText("40");
                        canScore = false; canRollDice = false;
                        lowerSectionTempAccess = true;
                    } else {
                        infoBoxLabel.setText("Yahtzee Bonus! You earned 100 points.");
                        canScore = false; canRollDice = false;
                    }
                    break;
                }
            }
        }
    }

    public static int acesScore(int[] diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 1){
                score += value;
            }
        }
        return score;
    }
    public static int twosScore(int[] diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 2){
                score += value;
            }
        }
        return score;
    }
    public static int threesScore(int[] diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 3){
                score += value;
            }
        }
        return score;
    }
    public static int foursScore(int[] diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 4){
                score += value;
            }
        }
        return score;
    }
    public static int fivesScore(int[] diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 5){
                score += value;
            }
        }
        return score;
    }
    public static int sixesScore(int[] diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 6){
                score += value;
            }
        }
        return score;
    }
    public void calcUpperScore(){
        int score = 0;
        if(acesScored){ score += Integer.parseInt(aces.getText()); }
        if(twosScored){ score += Integer.parseInt(twos.getText()); }
        if(threesScored){ score += Integer.parseInt(threes.getText()); }
        if(foursScored){ score += Integer.parseInt(fours.getText()); }
        if(fivesScored){ score += Integer.parseInt(fives.getText()); }
        if(sixesScored){ score += Integer.parseInt(sixes.getText()); }
        if(score >= 63){ 
            bonus.setFont(Font.font("System", FontWeight.BOLD, 15));
            bonus.setText("+35 to\nGrand Total");
        }
        totalScoreUpper.setFont(Font.font("System", FontWeight.BOLD, 20));
        totalScoreUpper.setText("" + score);
    }

    public boolean hasConsecutive(int[] diceList, int checkNum, int kind){
        int count = 0;
        for(int val : diceList) { if ( val == checkNum ){ count++; } }
        return count >= kind;
    }

    public boolean hasStraight(int[] diceList, int straightVal){
        int count = 0;
        for(int i = 1; i < diceList.length; i++){
            if(diceList[i] == diceList[i-1] + 1){
                count++;
            }
        }
        return count >= (straightVal - 1);
    }

    public int threeKindScore(int[] diceList){
        boolean hasThreeKind = false;

        if(hasConsecutive(diceList, 1, 3)){ hasThreeKind = true; }
        if(hasConsecutive(diceList, 2, 3)){ hasThreeKind = true; }
        if(hasConsecutive(diceList, 3, 3)){ hasThreeKind = true; }
        if(hasConsecutive(diceList, 4, 3)){ hasThreeKind = true; }
        if(hasConsecutive(diceList, 5, 3)){ hasThreeKind = true; }
        if(hasConsecutive(diceList, 6, 3)){ hasThreeKind = true; }

        return hasThreeKind ? dice0Value + dice1Value + dice2Value + dice3Value + dice4Value : 0;
    }
    public int fourKindScore(int[] diceList){
        boolean hasFourKind = false;

        if(hasConsecutive(diceList, 1, 4)){ hasFourKind = true; }
        if(hasConsecutive(diceList, 2, 4)){ hasFourKind = true; }
        if(hasConsecutive(diceList, 3, 4)){ hasFourKind = true; }
        if(hasConsecutive(diceList, 4, 4)){ hasFourKind = true; }
        if(hasConsecutive(diceList, 5, 4)){ hasFourKind = true; }
        if(hasConsecutive(diceList, 6, 4)){ hasFourKind = true; }

        return hasFourKind ? dice0Value + dice1Value + dice2Value + dice3Value + dice4Value : 0;
    }
    public int fullHouseScore(int[] diceList){
        boolean hasPair = false, hasThreeKind = false;
        int numChecked = -1;
        
        if(hasConsecutive(diceList, 1, 3)){ hasThreeKind = true; numChecked = 1; }
        if(hasConsecutive(diceList, 2, 3)){ hasThreeKind = true; numChecked = 2; }
        if(hasConsecutive(diceList, 3, 3)){ hasThreeKind = true; numChecked = 3; }
        if(hasConsecutive(diceList, 4, 3)){ hasThreeKind = true; numChecked = 4; }
        if(hasConsecutive(diceList, 5, 3)){ hasThreeKind = true; numChecked = 5; }
        if(hasConsecutive(diceList, 6, 3)){ hasThreeKind = true; numChecked = 6; }

        if(numChecked != 1 && hasConsecutive(diceList, 1, 2)){ hasPair = true; }
        if(numChecked != 2 && hasConsecutive(diceList, 2, 2)){ hasPair = true; }
        if(numChecked != 3 && hasConsecutive(diceList, 3, 2)){ hasPair = true; }
        if(numChecked != 4 && hasConsecutive(diceList, 4, 2)){ hasPair = true; }
        if(numChecked != 5 && hasConsecutive(diceList, 5, 2)){ hasPair = true; }
        if(numChecked != 6 && hasConsecutive(diceList, 6, 2)){ hasPair = true; }

        return hasThreeKind && hasPair ? 25 : 0;
    }
    public int smStraightScore(int[] diceList){
        if(hasStraight(diceList, 4)){
            return 30;
        }
        return 0;
    }
    public int lgStraightScore(int[] diceList){
        if(hasStraight(diceList, 5)){
            return 40;
        }
        return 0;
    }
    public int yahtzeeScore(int[] diceList){
        boolean hasYahtzee = false;
        if(hasConsecutive(diceList, 1, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 2, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 3, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 4, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 5, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 6, 5)){ hasYahtzee = true; }
        return hasYahtzee ? 50 : 0;
    }
    public int chanceScore(int[] diceList){
        return dice0Value + dice1Value + dice2Value + dice3Value + dice4Value;
    }
    public String yahtzeeBonusScore(int[] diceList){
        boolean hasYahtzee = false;
        if(hasConsecutive(diceList, 1, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 2, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 3, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 4, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 5, 5)){ hasYahtzee = true; }
        if(hasConsecutive(diceList, 6, 5)){ hasYahtzee = true; }
        return hasYahtzee ? "X" : "";
    }
    public void calcLowerScore(){
        int score = 0;
        if(threeKindScored) { score += Integer.parseInt(threeKind.getText()); }
        if(fourKindScored) { score += Integer.parseInt(fourKind.getText()); }
        if(fullHouseScored) { score += Integer.parseInt(fullHouse.getText()); }
        if(smStraightScored) { score += Integer.parseInt(smStraight.getText()); }
        if(lgStraightScored) { score += Integer.parseInt(lgStraight.getText()); }
        if(yahtzeeScored) { score += Integer.parseInt(yahtzee.getText()); }
        if(chanceScored) { score += Integer.parseInt(chance.getText()); }
        score += yahtzeeBonusCount * 100;
        totalScoreLower.setFont(Font.font("System", FontWeight.BOLD, 20));
        totalScoreLower.setText("" + score);
    }
    public void calcGrandScore(){
        int score = 0;
        grandTotal.setFont(Font.font("System", FontWeight.BOLD, 20));
        score += Integer.parseInt(totalScoreLower.getText());
        score += Integer.parseInt(totalScoreUpper.getText());
        if(bonus.getText().contains("+")){ score += 35; }
        grandTotal.setText("" + score);
    }
    
    public void rollDice0(){
        dice0Value = (random.nextInt(6) + 1);
        switch(dice0Value){
            case 1 : {
                label1.setTextFill(transparent);
                label2.setTextFill(transparent);
                label3.setTextFill(transparent);
                label4.setTextFill(black);
                label5.setTextFill(transparent);
                label6.setTextFill(transparent);
                label7.setTextFill(transparent);
                break;
            }
            case 2 : {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(transparent);
                label4.setTextFill(transparent);
                label5.setTextFill(transparent);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
                break;
            }
            case 3 : {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(transparent);
                label4.setTextFill(black);
                label5.setTextFill(transparent);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
                break;
            }
            case 4 : {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(black);
                label4.setTextFill(transparent);
                label5.setTextFill(black);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
                break;
            }
            case 5 : {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(black);
                label4.setTextFill(black);
                label5.setTextFill(black);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
                break;
            }
            case 6 : {
                label1.setTextFill(black);
                label2.setTextFill(black);
                label3.setTextFill(black);
                label4.setTextFill(transparent);
                label5.setTextFill(black);
                label6.setTextFill(black);
                label7.setTextFill(black);
                break;
            }
        }
    }
    public void rollDice1(){
        dice1Value = (random.nextInt(6)+1);
        switch(dice1Value){
            case 1 : {
                label8.setTextFill(transparent);
                label9.setTextFill(transparent);
                label10.setTextFill(transparent);
                label11.setTextFill(black);
                label12.setTextFill(transparent);
                label13.setTextFill(transparent);
                label14.setTextFill(transparent);
                break;
            }
            case 2 : {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(transparent);
                label11.setTextFill(transparent);
                label12.setTextFill(transparent);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
                break;
            }
            case 3 : {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(transparent);
                label11.setTextFill(black);
                label12.setTextFill(transparent);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
                break;
            }
            case 4 : {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(black);
                label11.setTextFill(transparent);
                label12.setTextFill(black);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
                break;
            }
            case 5 : {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(black);
                label11.setTextFill(black);
                label12.setTextFill(black);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
                break;
            }
            case 6 : {
                label8.setTextFill(black);
                label9.setTextFill(black);
                label10.setTextFill(black);
                label11.setTextFill(transparent);
                label12.setTextFill(black);
                label13.setTextFill(black);
                label14.setTextFill(black);
                break;
            }
        }
    }
    public void rollDice2(){
        dice2Value = (random.nextInt(6)+1);
        switch(dice2Value){
            case 1 : {
                label15.setTextFill(transparent);
                label16.setTextFill(transparent);
                label17.setTextFill(transparent);
                label18.setTextFill(black);
                label19.setTextFill(transparent);
                label20.setTextFill(transparent);
                label21.setTextFill(transparent);
                break;
            }
            case 2 : {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(transparent);
                label18.setTextFill(transparent);
                label19.setTextFill(transparent);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
                break;
            }
            case 3 : {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(transparent);
                label18.setTextFill(black);
                label19.setTextFill(transparent);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
                break;
            }
            case 4 : {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(black);
                label18.setTextFill(transparent);
                label19.setTextFill(black);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
                break;
            }
            case 5 : {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(black);
                label18.setTextFill(black);
                label19.setTextFill(black);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
                break;
            }
            case 6 : {
                label15.setTextFill(black);
                label16.setTextFill(black);
                label17.setTextFill(black);
                label18.setTextFill(transparent);
                label19.setTextFill(black);
                label20.setTextFill(black);
                label21.setTextFill(black);
                break;
            }
        }
    }
    public void rollDice3(){
        dice3Value = (random.nextInt(6)+1);
        switch(dice3Value){
            case 1 : {
                label22.setTextFill(transparent);
                label23.setTextFill(transparent);
                label24.setTextFill(transparent);
                label25.setTextFill(black);
                label26.setTextFill(transparent);
                label27.setTextFill(transparent);
                label28.setTextFill(transparent);
                break;
            }
            case 2 : {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(transparent);
                label25.setTextFill(transparent);
                label26.setTextFill(transparent);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
                break;
            }
            case 3 : {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(transparent);
                label25.setTextFill(black);
                label26.setTextFill(transparent);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
                break;
            }
            case 4 : {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(black);
                label25.setTextFill(transparent);
                label26.setTextFill(black);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
                break;
            }
            case 5 : {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(black);
                label25.setTextFill(black);
                label26.setTextFill(black);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
                break;
            }
            case 6 : {
                label22.setTextFill(black);
                label23.setTextFill(black);
                label24.setTextFill(black);
                label25.setTextFill(transparent);
                label26.setTextFill(black);
                label27.setTextFill(black);
                label28.setTextFill(black);
                break;
            }
        }
    }
    public void rollDice4(){
        dice4Value = (random.nextInt(6)+1);
        switch(dice4Value){
            case 1 : {
                label29.setTextFill(transparent);
                label30.setTextFill(transparent);
                label31.setTextFill(transparent);
                label32.setTextFill(black);
                label33.setTextFill(transparent);
                label34.setTextFill(transparent);
                label35.setTextFill(transparent);
                break;
            }
            case 2 : {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(transparent);
                label32.setTextFill(transparent);
                label33.setTextFill(transparent);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
                break;
            }
            case 3 : {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(transparent);
                label32.setTextFill(black);
                label33.setTextFill(transparent);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
                break;
            }
            case 4 : {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(black);
                label32.setTextFill(transparent);
                label33.setTextFill(black);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
                break;
            }
            case 5 : {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(black);
                label32.setTextFill(black);
                label33.setTextFill(black);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
                break;
            }
            case 6 : {
                label29.setTextFill(black);
                label30.setTextFill(black);
                label31.setTextFill(black);
                label32.setTextFill(transparent);
                label33.setTextFill(black);
                label34.setTextFill(black);
                label35.setTextFill(black);
                break;
            }
        }
    }
}