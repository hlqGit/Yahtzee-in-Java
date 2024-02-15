import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    
    
    private boolean is0Holding;
    private boolean is1Holding;
    private boolean is2Holding;
    private boolean is3Holding;
    private boolean is4Holding;

    final Color holdRed = new Color(1.0, 0.0, 0.0, 0.4962);
    final Color transparent = new Color(1.0, 0.0, 0.0, 0.0);

    public void holdDice0(){
        if(!is0Holding){
            hold0.setTextFill(holdRed);
            is0Holding = true;
        } else {
            hold0.setTextFill(transparent);
            is0Holding = false;
        }
    }
    public void holdDice1(){
        if(!is1Holding){
            hold1.setTextFill(holdRed);
            is1Holding = true;
        } else {
            hold1.setTextFill(transparent);
            is1Holding = false;
        }
    }
    public void holdDice2(){
        if(!is2Holding){
            hold2.setTextFill(holdRed);
            is2Holding = true;
        } else {
            hold2.setTextFill(transparent);
            is2Holding = false;
        }
    }
    public void holdDice3(){
        if(!is3Holding){
            hold3.setTextFill(holdRed);
            is3Holding = true;
        } else {
            hold3.setTextFill(transparent);
            is3Holding = false;
        }
    }
    public void holdDice4(){
        if(!is4Holding){
            hold4.setTextFill(holdRed);
            is4Holding = true;
        } else {
            hold4.setTextFill(transparent);
            is4Holding = false;
        }
        
    }

    public void rollDice(){
        if ( !is0Holding ) { rollDice0(); }
        if ( !is1Holding ) { rollDice1(); }
        if ( !is2Holding ) { rollDice2(); }
        if ( !is3Holding ) { rollDice3(); }
        if ( !is4Holding ) { rollDice4(); }
    }
    
    public void rollDice0(){
        int newNum = (int)(Math.random()*6 + 1);
        Color black = new Color(0.0, 0.0, 0.0, 1.0);
        System.out.println(newNum);
        switch(newNum){
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
        int newNum = (int)(Math.random()*6 + 1);
        Color black = new Color(0.0, 0.0, 0.0, 1.0);
        System.out.println(newNum);
        switch(newNum){
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
        int newNum = (int)(Math.random()*6 + 1);
        Color black = new Color(0.0, 0.0, 0.0, 1.0);
        System.out.println(newNum);
        switch(newNum){
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
        int newNum = (int)(Math.random()*6 + 1);
        Color black = new Color(0.0, 0.0, 0.0, 1.0);
        System.out.println(newNum);
        switch(newNum){
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
        int newNum = (int)(Math.random()*6 + 1);
        Color black = new Color(0.0, 0.0, 0.0, 1.0);
        System.out.println(newNum);
        switch(newNum){
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
