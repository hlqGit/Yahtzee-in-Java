import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller {

    @FXML
    private Rectangle dice0;
    @FXML
    private Rectangle dice1;
    @FXML
    private Rectangle dice2;
    @FXML
    private Rectangle dice3;
    @FXML
    private Rectangle dice4;

    @FXML
    private Label label1;
    @FXML 
    private Label label2;
    @FXML 
    private Label label3; 
    @FXML
    private Label label4; 
    @FXML
    private Label label5; 
    @FXML
    private Label label6; 
    @FXML
    private Label label7; 
    @FXML
    private Label label8; 
    @FXML
    private Label label9; 
    @FXML
    private Label label10; 
    @FXML
    private Label label11; 
    @FXML
    private Label label12; 
    @FXML
    private Label label13; 
    @FXML
    private Label label14; 
    @FXML
    private Label label15; 
    @FXML
    private Label label16; 
    @FXML
    private Label label17; 
    @FXML
    private Label label18; 
    @FXML
    private Label label19; 
    @FXML
    private Label label20; 
    @FXML
    private Label label21; 
    @FXML
    private Label label22;
    @FXML
    private Label label23; 
    @FXML
    private Label label24; 
    @FXML
    private Label label25; 
    @FXML
    private Label label26; 
    @FXML
    private Label label27; 
    @FXML
    private Label label28; 
    @FXML
    private Label label29; 
    @FXML
    private Label label30; 
    @FXML
    private Label label31;
    @FXML 
    private Label label32; 
    @FXML
    private Label label33; 
    @FXML
    private Label label34; 
    @FXML
    private Label label35;

    @FXML
    private Label hold0;
    @FXML
    private Label hold1;
    @FXML
    private Label hold2;
    @FXML
    private Label hold3;
    @FXML
    private Label hold4;

    @FXML
    private Label aces;
    @FXML
    private Label twos;
    @FXML
    private Label threes;
    @FXML
    private Label fours;
    @FXML
    private Label fives;
    @FXML
    private Label sixes;
    @FXML
    private Label totalScoreUpper;
    @FXML
    private Label bonus;
    @FXML
    private Label threeKind;
    @FXML
    private Label fourKind;
    @FXML
    private Label fullHouse;
    @FXML
    private Label smStraight;
    @FXML
    private Label lgStraight;
    @FXML
    private Label yahtzee;
    @FXML
    private Label chance;
    @FXML
    private Label yahtzeeBonus;
    @FXML
    private Label totalScoreLower;
    @FXML
    private Label grandTotal;

    @FXML
    private Button rollDiceButton;

    private boolean acesScored;
    private boolean twosScored;
    private boolean threesScored;
    private boolean foursScored;
    private boolean fivesScored;
    private boolean sixesScored;
    
    private static int dice0Value;
    private static int dice1Value;
    private static int dice2Value;
    private static int dice3Value;
    private static int dice4Value;
    
    private boolean is0Holding;
    private boolean is1Holding;
    private boolean is2Holding;
    private boolean is3Holding;
    private boolean is4Holding;

    private static boolean turnStarted;
    private static boolean canRollDice = true;
    private static boolean canScore = true;

    private static int turnsRemaining = 13;
    private int rollCount;

    final Color holdRed = new Color(1.0, 0.0, 0.0, 0.4962);
    final Color transparent = new Color(1.0, 0.0, 0.0, 0.0);
    final Color black = new Color(0.0, 0.0, 0.0, 1.0);

    Random random = new Random();

    public void holdDice0(){
        if(!turnStarted) { return; }
        if(!is0Holding ){
            hold0.setTextFill(holdRed);
            is0Holding = true;
        } else {
            hold0.setTextFill(transparent);
            is0Holding = false;
        }
    }
    public void holdDice1(){
        if(!turnStarted) { return; }
        if(!is1Holding){
            hold1.setTextFill(holdRed);
            is1Holding = true;
        } else {
            hold1.setTextFill(transparent);
            is1Holding = false;
        }
    }
    public void holdDice2(){
        if(!turnStarted) { return; }
        if(!is2Holding){
            hold2.setTextFill(holdRed);
            is2Holding = true;
        } else {
            hold2.setTextFill(transparent);
            is2Holding = false;
        }
    }
    public void holdDice3(){
        if(!turnStarted) { return; }
        if(!is3Holding){
            hold3.setTextFill(holdRed);
            is3Holding = true;
        } else {
            hold3.setTextFill(transparent);
            is3Holding = false;
        }
    }
    public void holdDice4(){
        if(!turnStarted) { return; }
        if(!is4Holding){
            hold4.setTextFill(holdRed);
            is4Holding = true;
        } else {
            hold4.setTextFill(transparent);
            is4Holding = false;
        }
        
    }

    public void nextTurn(){
        rollDiceButton.setText("Roll!\n3 Rolls left");
        canRollDice = true;
        rollCount = 0;
        if(is0Holding){ holdDice0(); }
        if(is1Holding){ holdDice1(); }
        if(is2Holding){ holdDice2(); }
        if(is3Holding){ holdDice3(); }
        if(is4Holding){ holdDice4(); }
        turnStarted = false;
        calcUpperScore();
    }

    public void rollDice(){
        turnStarted = true;
        canScore = true;
        if (!is0Holding && canRollDice) { rollDice0(); }
        if (!is1Holding && canRollDice) { rollDice1(); }
        if (!is2Holding && canRollDice) { rollDice2(); }
        if (!is3Holding && canRollDice) { rollDice3(); }
        if (!is4Holding && canRollDice) { rollDice4(); }
        switch(rollCount){
            case 0 -> {
                ++rollCount;
                rollDiceButton.setText("Roll!\n2 Rolls left");
            }
            case 1 -> {
                ++rollCount;
                rollDiceButton.setText("Roll!\n1 Roll left");
            }
            case 2 -> {
                canRollDice = false;
                rollDiceButton.setText("Cannot Roll:\nFill in the scorecard");
            }
        }
        fillScoreCardValues();
    }

    public void fillScoreCardValues(){
        ArrayList<Integer> diceList = new ArrayList<>();
        diceList.add(dice0Value);
        diceList.add(dice1Value);
        diceList.add(dice2Value);
        diceList.add(dice3Value);
        diceList.add(dice4Value);

        Collections.sort(diceList);

        if(!acesScored) { aces.setText("" + acesScore(diceList)); }
        if(!twosScored) { twos.setText("" + twosScore(diceList)); }
        if(!threesScored) { threes.setText("" + threesScore(diceList)); }
        if(!foursScored) { fours.setText("" + foursScore(diceList)); }
        if(!fivesScored) { fives.setText("" + fivesScore(diceList)); }
        if(!sixesScored) { sixes.setText("" + sixesScore(diceList)); }
    }

    public void setAcesScore(){
        if(canScore){
            aces.setFont(Font.font("System", FontWeight.BOLD, 20));
            acesScored = true;
            aces.cursorProperty().set(Cursor.DEFAULT);
            aces.onMouseClickedProperty().set(null);
        }
        canScore = false;
        nextTurn();
    }
    public void setTwosScore(){
        if(canScore){
            twos.setFont(Font.font("System", FontWeight.BOLD, 20));
            twosScored = true;
            twos.cursorProperty().set(Cursor.DEFAULT);
            twos.onMouseClickedProperty().set(null);
        }   
        canScore = false;
        nextTurn();
    }
    public void setThreesScore(){
        if(canScore){
            threes.setFont(Font.font("System", FontWeight.BOLD, 20));
            threesScored = true;
            threes.cursorProperty().set(Cursor.DEFAULT);
            threes.onMouseClickedProperty().set(null);
        }
        canScore = false;
        nextTurn();
    }
    public void setFoursScore(){
        if(canScore){
            fours.setFont(Font.font("System", FontWeight.BOLD, 20));
            foursScored = true;
            fours.cursorProperty().set(Cursor.DEFAULT);
            fours.onMouseClickedProperty().set(null);
        }
        canScore = false;
        nextTurn();
    }
    public void setFivesScore(){
        if(canScore){
            fives.setFont(Font.font("System", FontWeight.BOLD, 20));
            fivesScored = true;
            fives.cursorProperty().set(Cursor.DEFAULT);
            fives.onMouseClickedProperty().set(null);
        }
        canScore = false;
        nextTurn();
    }
    public void setSixesScore(){
        if(canScore){
            sixes.setFont(Font.font("System", FontWeight.BOLD, 20));
            sixesScored = true;
            sixes.cursorProperty().set(Cursor.DEFAULT);
            sixes.onMouseClickedProperty().set(null);
        }
        canScore = false;
        nextTurn();
    }

    public static int acesScore(ArrayList<Integer> diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 1){
                score += value;
            }
        }
        return score;
    }
    public static int twosScore(ArrayList<Integer> diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 2){
                score += value;
            }
        }
        return score;
    }
    public static int threesScore(ArrayList<Integer> diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 3){
                score += value;
            }
        }
        return score;
    }
    public static int foursScore(ArrayList<Integer> diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 4){
                score += value;
            }
        }
        return score;
    }
    public static int fivesScore(ArrayList<Integer> diceList){
        int score = 0;
        for(int value : diceList){
            if(value == 5){
                score += value;
            }
        }
        return score;
    }
    public static int sixesScore(ArrayList<Integer> diceList){
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
    public int threeKindScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int fourKindScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int smStraightScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int lgStraightScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int yahtzeeScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int chanceScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int calcLowerScore(ArrayList<Integer> diceList){
        return 0;
    }
    public int calcGrandScore(ArrayList<Integer> diceList){
        return 0;
    }
    
    public void rollDice0(){
        dice0Value = (random.nextInt(6) + 1);
        switch(dice0Value){
            case 1 -> {
                label1.setTextFill(transparent);
                label2.setTextFill(transparent);
                label3.setTextFill(transparent);
                label4.setTextFill(black);
                label5.setTextFill(transparent);
                label6.setTextFill(transparent);
                label7.setTextFill(transparent);
            }
            case 2 -> {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(transparent);
                label4.setTextFill(transparent);
                label5.setTextFill(transparent);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
            }
            case 3 -> {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(transparent);
                label4.setTextFill(black);
                label5.setTextFill(transparent);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
            }
            case 4 -> {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(black);
                label4.setTextFill(transparent);
                label5.setTextFill(black);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
            }
            case 5 -> {
                label1.setTextFill(black);
                label2.setTextFill(transparent);
                label3.setTextFill(black);
                label4.setTextFill(black);
                label5.setTextFill(black);
                label6.setTextFill(transparent);
                label7.setTextFill(black);
            }
            case 6 -> {
                label1.setTextFill(black);
                label2.setTextFill(black);
                label3.setTextFill(black);
                label4.setTextFill(transparent);
                label5.setTextFill(black);
                label6.setTextFill(black);
                label7.setTextFill(black);
            }
        }
    }
    public void rollDice1(){
        dice1Value = (random.nextInt(6)+1);
        switch(dice1Value){
            case 1 -> {
                label8.setTextFill(transparent);
                label9.setTextFill(transparent);
                label10.setTextFill(transparent);
                label11.setTextFill(black);
                label12.setTextFill(transparent);
                label13.setTextFill(transparent);
                label14.setTextFill(transparent);
            }
            case 2 -> {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(transparent);
                label11.setTextFill(transparent);
                label12.setTextFill(transparent);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
            }
            case 3 -> {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(transparent);
                label11.setTextFill(black);
                label12.setTextFill(transparent);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
            }
            case 4 -> {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(black);
                label11.setTextFill(transparent);
                label12.setTextFill(black);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
            }
            case 5 -> {
                label8.setTextFill(black);
                label9.setTextFill(transparent);
                label10.setTextFill(black);
                label11.setTextFill(black);
                label12.setTextFill(black);
                label13.setTextFill(transparent);
                label14.setTextFill(black);
            }
            case 6 -> {
                label8.setTextFill(black);
                label9.setTextFill(black);
                label10.setTextFill(black);
                label11.setTextFill(transparent);
                label12.setTextFill(black);
                label13.setTextFill(black);
                label14.setTextFill(black);
            }
        }
    }
    public void rollDice2(){
        dice2Value = (random.nextInt(6)+1);
        switch(dice2Value){
            case 1 -> {
                label15.setTextFill(transparent);
                label16.setTextFill(transparent);
                label17.setTextFill(transparent);
                label18.setTextFill(black);
                label19.setTextFill(transparent);
                label20.setTextFill(transparent);
                label21.setTextFill(transparent);
            }
            case 2 -> {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(transparent);
                label18.setTextFill(transparent);
                label19.setTextFill(transparent);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
            }
            case 3 -> {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(transparent);
                label18.setTextFill(black);
                label19.setTextFill(transparent);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
            }
            case 4 -> {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(black);
                label18.setTextFill(transparent);
                label19.setTextFill(black);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
            }
            case 5 -> {
                label15.setTextFill(black);
                label16.setTextFill(transparent);
                label17.setTextFill(black);
                label18.setTextFill(black);
                label19.setTextFill(black);
                label20.setTextFill(transparent);
                label21.setTextFill(black);
            }
            case 6 -> {
                label15.setTextFill(black);
                label16.setTextFill(black);
                label17.setTextFill(black);
                label18.setTextFill(transparent);
                label19.setTextFill(black);
                label20.setTextFill(black);
                label21.setTextFill(black);
            }
        }
    }
    public void rollDice3(){
        dice3Value = (random.nextInt(6)+1);
        switch(dice3Value){
            case 1 -> {
                label22.setTextFill(transparent);
                label23.setTextFill(transparent);
                label24.setTextFill(transparent);
                label25.setTextFill(black);
                label26.setTextFill(transparent);
                label27.setTextFill(transparent);
                label28.setTextFill(transparent);
            }
            case 2 -> {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(transparent);
                label25.setTextFill(transparent);
                label26.setTextFill(transparent);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
            }
            case 3 -> {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(transparent);
                label25.setTextFill(black);
                label26.setTextFill(transparent);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
            }
            case 4 -> {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(black);
                label25.setTextFill(transparent);
                label26.setTextFill(black);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
            }
            case 5 -> {
                label22.setTextFill(black);
                label23.setTextFill(transparent);
                label24.setTextFill(black);
                label25.setTextFill(black);
                label26.setTextFill(black);
                label27.setTextFill(transparent);
                label28.setTextFill(black);
            }
            case 6 -> {
                label22.setTextFill(black);
                label23.setTextFill(black);
                label24.setTextFill(black);
                label25.setTextFill(transparent);
                label26.setTextFill(black);
                label27.setTextFill(black);
                label28.setTextFill(black);
            }
        }
    }
    public void rollDice4(){
        dice4Value = (random.nextInt(6)+1);
        switch(dice4Value){
            case 1 -> {
                label29.setTextFill(transparent);
                label30.setTextFill(transparent);
                label31.setTextFill(transparent);
                label32.setTextFill(black);
                label33.setTextFill(transparent);
                label34.setTextFill(transparent);
                label35.setTextFill(transparent);
            }
            case 2 -> {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(transparent);
                label32.setTextFill(transparent);
                label33.setTextFill(transparent);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
            }
            case 3 -> {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(transparent);
                label32.setTextFill(black);
                label33.setTextFill(transparent);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
            }
            case 4 -> {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(black);
                label32.setTextFill(transparent);
                label33.setTextFill(black);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
            }
            case 5 -> {
                label29.setTextFill(black);
                label30.setTextFill(transparent);
                label31.setTextFill(black);
                label32.setTextFill(black);
                label33.setTextFill(black);
                label34.setTextFill(transparent);
                label35.setTextFill(black);
            }
            case 6 -> {
                label29.setTextFill(black);
                label30.setTextFill(black);
                label31.setTextFill(black);
                label32.setTextFill(transparent);
                label33.setTextFill(black);
                label34.setTextFill(black);
                label35.setTextFill(black);
            }
        }
    }
}