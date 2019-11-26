/* 
 * Copyright (C) 2018 Matthew Cumbo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cryptmessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Toolkit;
import org.apache.commons.io.FilenameUtils;
import java.io.*;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.*;

/**
 *
 * @author Matthew Cumbo
 */
public class EditFrame extends javax.swing.JFrame {

    private final ArrayList<JTextField> textBoxes;
    private boolean saved = true;
    private String spacePos = "";
    private int foundSpaces = 0;
    private final JFileChooser fc = new JFileChooser();
    private String prevLength = "4";
    private final FileNameExtensionFilter fftxt = new FileNameExtensionFilter(".CMF (Cryptic Message File)", "cmf");
    private final ArrayList<Character> genChars = new ArrayList<>();
    //<editor-fold defaultstate="collapsed" desc="private static final char[] CHAR_LIST">
    private static final char[] CHAR_LIST = {'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h',
                                    'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p',
                                    'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x',
                                    'Y', 'y', 'Z', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '~', '`',
                                    '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '=', '{', '[',
                                    '}', ']', '|', '\\', ':', ';', '"', '\'', '<', ',', '>', '.', '?', '/', ' '};
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="private static final String[] ENC_CHAR">
    private static final String[] ENC_CHAR = {"011110001000000100010011100100111011000000",
                                            "101010010001111111000001110101001011001100",
                                            "110101011100001110010101000000110010010110",
                                            "110100111001100101111001100111000001100100",
                                            "001110011100001111100000110001101100111011",
                                            "001001110010000000011101111000110011101011",
                                            "010111001111010111100010101001010110110001",
                                            "110101000011110011101111001000111000101111",
                                            "010001100011111010111101010110010000011000",
                                            "010101011100110010111110100111101000101110",
                                            "010001000110111000101001110001000100110000",
                                            "110000111001010010000100010001001010100110",
                                            "001111000011001000010101001101000011100011",
                                            "011100000111001111010110010010011100110001",
                                            "011111100011011100101111111010101000111110",
                                            "011111000110100000000110101000011110011010",
                                            "100010111010110010001010111001100100011011",
                                            "011011111101110010101111001101001100000011",
                                            "011110011101101101101111110101111011010101",
                                            "111100001110101010101001110000011110110111",
                                            "000001110001000101011111000001100001001101",
                                            "011010010101101011101101001000101101101010",
                                            "000001011011100010111101110001011111011110",
                                            "001111000101011011010000110101001100101101",
                                            "110101001000010110001110111101111110111000",
                                            "010100011110111001100010000101001100111010",
                                            "000001001001000101101100100100110101101110",
                                            "011100001001110011111001100111000010111000",
                                            "010000110000011010011111010110010011110101",
                                            "000010001000011100001010110111101000000110",
                                            "001110001101001001110001010100010010101110",
                                            "011001101100000010101100110110100100010010",
                                            "100110011110111010001011000010011001111100",
                                            "000111010111101010001101000111110110010000",
                                            "101110000011111001010110110010001011110111",
                                            "100111100111000111000101001110010001101100",
                                            "101001000111101100010111010101000000011010",
                                            "100101001111010001111011010111110101101001",
                                            "010010101001101111100001000101101100111000",
                                            "100100101110110001100000010000011111100110",
                                            "001100101101101010011110110011110110000000",
                                            "100011000111000000011110010100100100111010",
                                            "010010100000110110010001111110010011100001",
                                            "101010011010001010011011000011010010101111",
                                            "000111100110000011000000111100011001000010",
                                            "001111110100100111110101011111100010001011",
                                            "101111111000001101000011011010100100111010",
                                            "001111010110101101111010110011000011001000",
                                            "111111101011011110101100001111001101101101",
                                            "101100110000000011111011000101110110001011",
                                            "001110001011101100111100010001000011000000",
                                            "010011011101111110110001000101111111101010",
                                            "001110110000101101110100001011010100100101",
                                            "100100100000010000100000000110101101000100",
                                            "101111010001000110101010101001101111110100",
                                            "001111011111011011001011101110110000110100",
                                            "001110101110011111010000010010101100010010",
                                            "011101011111011011111000010111010001001101",
                                            "011000111110111110001101110010111000110000",
                                            "001000110010100011000110101001011110110111",
                                            "110010101000001011110111111111111101101110",
                                            "101100110010000001001101001010101101000001",
                                            "101111001111011010000010100111101000110010",
                                            "110010111001000001110010001101100100010101",
                                            "111000000111001011110001100011110101110011",
                                            "101000101111010001000000011110010101011001",
                                            "101011100011110011111110110000111010001010",
                                            "111110010100000111011100101010011000100101",
                                            "111000111111000100101001000111010011110010",
                                            "000011001001011111000110000101101111110100",
                                            "000101001111011110001000001011100001001010",
                                            "110010110111010011011110111001111101010011",
                                            "000000111100100110110110101010011001100001",
                                            "011000011101001111000010111101011110000101",
                                            "011101010010001100010010010011100100100011",
                                            "010111100001000111000100001111101001001000",
                                            "101000111100111010100101111101111001101101",
                                            "101101001000101011100010001101101001000001",
                                            "011111100000000100110010000110011010110100",
                                            "000010011010111100000011000001111000010100",
                                            "110000011000111010111011011110000010110000",
                                            "000100001001110101010011000001110110011111",
                                            "011000100101000110000001001001000101111010",
                                            "011101100010100111100001101100011001110100",
                                            "110000111101110011100100011011111100110101",
                                            "110000100101101100001111110001010111011001",
                                            "110101011010100111000010100100010111010100",
                                            "100111100100000000001110001010101001000100",
                                            "001011010000111110010100100010101110011101",
                                            "100110001101001100010011001111010101110011",
                                            "110101010110101011111010110000010111010010",
                                            "110111011011111001110000110101001111001111",
                                            "000010100111010110100111011100000110110110",
                                            "101100101001011011111010111100100110100010",
                                            "101000100100101101010000100010100100011101"};   
    //</editor-fold>

    /**
     * Creates new form EditFrame
     */
    public EditFrame() {
        initComponents();
        getContentPane().setBackground(Color.black);
        
        //instantiate the arraylist
        //the language name and text field is not included for generation purposes
        textBoxes = new ArrayList<>();
        //<editor-fold defaultstate="collapsed" desc="Capital letters">
        textBoxes.add(txtCapA);
        textBoxes.add(txtCapB);
        textBoxes.add(txtCapC);
        textBoxes.add(txtCapD);
        textBoxes.add(txtCapE);
        textBoxes.add(txtCapF);
        textBoxes.add(txtCapG);
        textBoxes.add(txtCapH);
        textBoxes.add(txtCapI);
        textBoxes.add(txtCapJ);
        textBoxes.add(txtCapK);
        textBoxes.add(txtCapL);
        textBoxes.add(txtCapM);
        textBoxes.add(txtCapN);
        textBoxes.add(txtCapO);
        textBoxes.add(txtCapP);
        textBoxes.add(txtCapQ);
        textBoxes.add(txtCapR);
        textBoxes.add(txtCapS);
        textBoxes.add(txtCapT);
        textBoxes.add(txtCapU);
        textBoxes.add(txtCapV);
        textBoxes.add(txtCapW);
        textBoxes.add(txtCapX);
        textBoxes.add(txtCapY);
        textBoxes.add(txtCapZ);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Lowercase letters">
        textBoxes.add(txtLowA);
        textBoxes.add(txtLowB);
        textBoxes.add(txtLowC);
        textBoxes.add(txtLowD);
        textBoxes.add(txtLowE);
        textBoxes.add(txtLowF);
        textBoxes.add(txtLowG);
        textBoxes.add(txtLowH);
        textBoxes.add(txtLowI);
        textBoxes.add(txtLowJ);
        textBoxes.add(txtLowK);
        textBoxes.add(txtLowL);
        textBoxes.add(txtLowM);
        textBoxes.add(txtLowN);
        textBoxes.add(txtLowO);
        textBoxes.add(txtLowP);
        textBoxes.add(txtLowQ);
        textBoxes.add(txtLowR);
        textBoxes.add(txtLowS);
        textBoxes.add(txtLowT);
        textBoxes.add(txtLowU);
        textBoxes.add(txtLowV);
        textBoxes.add(txtLowW);
        textBoxes.add(txtLowX);
        textBoxes.add(txtLowY);
        textBoxes.add(txtLowZ);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Numbers">
        textBoxes.add(txtNum1);
        textBoxes.add(txtNum2);
        textBoxes.add(txtNum3);
        textBoxes.add(txtNum4);
        textBoxes.add(txtNum5);
        textBoxes.add(txtNum6);
        textBoxes.add(txtNum7);
        textBoxes.add(txtNum8);
        textBoxes.add(txtNum9);
        textBoxes.add(txtNum0);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Symbols">
        textBoxes.add(txtSpace);
        textBoxes.add(txtExclamation);
        textBoxes.add(txtAt);
        textBoxes.add(txtHash);
        textBoxes.add(txtDollar);
        textBoxes.add(txtPercent);
        textBoxes.add(txtUpArrow);
        textBoxes.add(txtAnd);
        textBoxes.add(txtAsterisk);
        textBoxes.add(txtRightP);
        textBoxes.add(txtLeftP);
        textBoxes.add(txtHyphen);
        textBoxes.add(txtPlus);
        textBoxes.add(txtEqual);
        textBoxes.add(txtCurlyR);
        textBoxes.add(txtCurlyL);
        textBoxes.add(txtBracketR);
        textBoxes.add(txtBracketL);
        textBoxes.add(txtBackSlash);
        textBoxes.add(txtLine);
        textBoxes.add(txtColon);
        textBoxes.add(txtSemi);
        textBoxes.add(txtApost);
        textBoxes.add(txtQuote);
        textBoxes.add(txtLess);
        textBoxes.add(txtGreater);
        textBoxes.add(txtComma);
        textBoxes.add(txtPeriod);
        textBoxes.add(txtForeSlash);
        textBoxes.add(txtQuestion);
        textBoxes.add(txtTilde);
        textBoxes.add(txtBacktick);
        textBoxes.add(txtUnderscore);
        //</editor-fold>
    }
    
    /**
     * Sets the color of all the text boxes to black.
     */
    private void setAllToBlack(){
        txtLanguageName.setBackground(Color.black);
        txtLanguageName.setForeground(new java.awt.Color(102, 255, 51));
        txtTextLength.setBackground(Color.black);
        txtTextLength.setForeground(new java.awt.Color(102, 255, 51));
        textBoxes.forEach((n) -> {
            n.setBackground(Color.black);
            n.setForeground(new java.awt.Color(102, 255, 51));
        }); 
    }
    
    /**
     * Checks all text boxes for duplicate strings
     * @return false if there is at least one duplicate, highlight
     *         the duplicate text boxes
     */
    private boolean noDuplicate(){
       boolean aws = true;
       for(int i=0; i<textBoxes.size()-1; i++){
           for(int j=i+1; j<textBoxes.size(); j++){
                if(textBoxes.get(i).getText().equals(textBoxes.get(j).getText())){
                    textBoxes.get(i).setBackground(Color.yellow);
                    textBoxes.get(i).setForeground(Color.blue);
                    textBoxes.get(j).setBackground(Color.yellow);
                    textBoxes.get(j).setForeground(Color.blue);
                    aws = false;
                }
           }
       }
       return aws;
    }
    
    /**
     * Checks all text boxes (excluding the language name & text length)
     * if their character count meets the max input value, and turns the text
     * box yellow.
     * @return false if a text box does not equal max length
     */
    private boolean textMeetsSizeReq(){
        if (txtTextLength.getText().length()==0)
            return false;
        int maxSize = Integer.parseInt(txtTextLength.getText());
        boolean nice = true;
        for(JTextField n: textBoxes){
            if(n.getText().length() != maxSize){
                n.setBackground(Color.yellow);
                n.setForeground(Color.blue);
                nice = false;
            }
        }
        return nice;
    }
    
    /**
     * Changes all empty text boxes to red
     * @return false if an empty string is found
     */
    private boolean noEmptyText(){
        boolean cool = true;
        for(JTextField n: textBoxes){
            if(n.getText().length()==0){
                n.setBackground(Color.red);
                n.setForeground(Color.white);
                cool = false;
            }
        }
        if(txtLanguageName.getText().length()==0){
            txtLanguageName.setBackground(Color.red);
            txtLanguageName.setForeground(Color.white);
            cool = false;
        }
        if(txtTextLength.getText().length()==0){
            txtTextLength.setBackground(Color.red);
            txtTextLength.setBackground(Color.white);
            cool = false;
        }
        return cool;
    }
    
    /**
     * Checks if all of the text fields have text in them
     * @return false if at least one is empty
     */
    private boolean isFull(){
        for(JTextField n: textBoxes){
            if(n.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if there is a space at the end of any textbox string
     * @return false if there is at least one textbox with a space at the end
     */
    private boolean noSpaceAtTheEdge(){
        foundSpaces = 0;
        boolean sweet = true;
        for(JTextField n: textBoxes){
            if(n.getText().length()>0 && n.getText().charAt(n.getText().length()-1)==' ' || n.getText().charAt(0)==' '){
                if(n.getText().charAt(n.getText().length()-1)==' '){
                    spacePos = "last";
                }
                else if(n.getText().charAt(0)==' '){
                    spacePos = "first";
                }
                foundSpaces++;
                n.setBackground(Color.orange);
                n.setForeground(Color.black);
                sweet = false;
            }
        }
        return sweet;
    }
    
    /**
     * Translates encrypted text to its original format
     * @pre str has a length of 42 and is not empty
     * @param str encrypted string to translate
     * @return translated string
     */
    protected static String decrypt(String str){
        if(!(str.length()%42==0) && !str.equals("")){
            System.out.println("Cannot parse file data.");
            return null;
        }
        LinkedList<String> cal = new LinkedList<>();
        String prevString = str;
        String endString = "";
        while(prevString.length()>=42){
            if(prevString.length()==42){
                cal.offer(prevString);
                break;
            }
            cal.offer(prevString.substring(0, 42));
            prevString = prevString.substring(42);
        }
        while(!cal.isEmpty()){
            boolean good = false;
            for(int i=0; i<ENC_CHAR.length; i++){
                if(ENC_CHAR[i].equals(cal.element())){
                    endString = endString + CHAR_LIST[i];
                    cal.pop();
                    good = true;
                    break;
                }
            }
            if(!good){
                System.out.println("Failed to parse file data.");
                return "";
            }
        }
        return endString;
    }
    
    /**
     * Encrypts a character
     * @pre character is on the QWERTY keyboard
     * @param ch the character to encrypt
     * @return encrypted character's string
     */
    private String charToEnc(char ch){
        assert get(ch)!=-1: "This character is not valid.";
        return ENC_CHAR[get(ch)];
    }
    
    /**
     * Gets the location of a character from the array CHAR_LIST
     * @param a the character to look for
     * @return position of the character in CHAR_LIST, otherwise -1
     */
    private int get(char a){
        for(int i=0; i<CHAR_LIST.length; i++){
            if(CHAR_LIST[i]==a)
                    return i;
        }
        return -1;
    }
    
    /**
     * Encrypts text using the ENC_CHAR variable
     * @param str String to be encrypted
     * @return encrypted string
     */
    protected String getEncryption(String str){
        LinkedList<Character> col = new LinkedList<>();
        String endString = "";
        for(int i=0; i<str.length(); i++){
            col.offer(str.charAt(i));
        }
        while(!col.isEmpty()){
            endString = endString + charToEnc(col.remove());
        }
        return endString;
    }

    /**
     * Generates text in every character textbox based on the input settings
     */
    protected void generate(){
        int combinations = 0;
        int possibleAttempts = 50; //if there are generation options possible, but must be very specific, this comes in handy
        int attempts = 0;
        boolean shouldClear = chkClearAll.isSelected();
        boolean canSpace = chkAllowSpace.isSelected();
        boolean changed = false;
        ArrayList<String> usedText = new ArrayList<>();
        int max = Integer.parseInt(txtTextLength.getText());
        if(shouldClear){
            textBoxes.forEach((n) -> {
                n.setText("");
            });
            setAllToBlack();
            lblError.setText("");
        }
        else{
            textBoxes.forEach((n) -> {
                if(n.getText().length()>0)
                    usedText.add(n.getText());
            });
        }
        for(JTextField t: textBoxes){
            if(attempts>=possibleAttempts){
                JOptionPane.showMessageDialog(null, "Failed to find other possible combinations (" + Integer.toString((int)Math.pow(genChars.size(), max)-combinations) + " left)", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            }
            if(combinations==Math.pow(genChars.size(), max)){
                JOptionPane.showMessageDialog(null, "Maximum possible combinations used", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            }
            boolean unfinished = true;
            attempts = 0;
            if(t.getText().length()==0){
                while(unfinished && attempts<possibleAttempts){
                    String cool = "";
                    int tries = 0;
                    for(int i=0; i<max; i++){ //Generate random string into textbox based on chars from genChars
                        int ran = (int)(Math.random() * genChars.size());
                        if(chkAllowSpace.isEnabled() && !canSpace){  //cant be a space as the first or last char
                            if(genChars.get(ran)==' ' && cool.length()==0){
                                i = -1;
                                tries++;
                                continue;
                            }
                            if(genChars.get(ran)==' ' && cool.length()==max-1){
                                if(tries==20){
                                    i = 0;
                                    continue;
                                }
                                i--;
                                tries++;
                                continue;
                            }
                        }
                        cool = cool + genChars.get(ran);
                    }
                    //once it meets the text length requirement, check for conflicts
                    if(usedText.isEmpty()){
                        unfinished = false;
                    }
                    else{
                        for(String str: usedText){
                            if(cool.equals(str)){ //Is a duplicate string
                                unfinished = true;
                                attempts++;
                                break;
                            }
                            unfinished = false;
                        }
                    }
                    if(!unfinished){
                        t.setText(cool);
                        changed = true;
                        usedText.add(cool);
                        saved = false;
                        combinations++;
                    }
                    
                }
            }
            
        }
        if(!changed){
            JOptionPane.showMessageDialog(null, "Unable to generate; all text boxes contain text", "Error", JOptionPane.ERROR_MESSAGE);
            btnGenerate.setEnabled(false);
            btnGenerate.setToolTipText("No empty text boxes available");
        }
    }
    
    /**
     * Prompts the user to save the data
     */
    private void save(){
        File f;
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(fftxt);
        int fVal = fc.showSaveDialog(null);
        if(fVal==JFileChooser.APPROVE_OPTION){
            try{
                f = fc.getSelectedFile();
                if(!FilenameUtils.getExtension(f.getName()).equalsIgnoreCase("cmf")){
                    f = new File(f.toString() + ".cmf");
                    f = new File(f.getParentFile(), FilenameUtils.getBaseName(f.getName())+".cmf");
                }
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
                    bw.write(getEncryption(txtLanguageName.getText()));
                    bw.write("\n");
                    bw.write(getEncryption(txtTextLength.getText()));
                    bw.write("\n");
                    for(JTextField n: textBoxes){
                        bw.write(getEncryption(n.getText()));
                        bw.write("\n");
                    }
                    saved = true;
                }
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(null, "IOException thrown.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        txtCapA = new javax.swing.JTextField();
        lblCapA = new javax.swing.JLabel();
        txtCapB = new javax.swing.JTextField();
        lblCapB = new javax.swing.JLabel();
        txtCapC = new javax.swing.JTextField();
        lblCapC = new javax.swing.JLabel();
        txtCapD = new javax.swing.JTextField();
        lblCapD = new javax.swing.JLabel();
        txtCapE = new javax.swing.JTextField();
        lblCapE = new javax.swing.JLabel();
        txtCapF = new javax.swing.JTextField();
        lblCapF = new javax.swing.JLabel();
        txtCapG = new javax.swing.JTextField();
        lblCapG = new javax.swing.JLabel();
        txtCapH = new javax.swing.JTextField();
        lblCapH = new javax.swing.JLabel();
        txtCapI = new javax.swing.JTextField();
        lblCapI = new javax.swing.JLabel();
        txtCapJ = new javax.swing.JTextField();
        lblCapJ = new javax.swing.JLabel();
        txtCapK = new javax.swing.JTextField();
        lblCapK = new javax.swing.JLabel();
        txtCapL = new javax.swing.JTextField();
        lblCapL = new javax.swing.JLabel();
        txtCapM = new javax.swing.JTextField();
        lblCapM = new javax.swing.JLabel();
        txtCapN = new javax.swing.JTextField();
        lblCapN = new javax.swing.JLabel();
        txtCapO = new javax.swing.JTextField();
        lblCapO = new javax.swing.JLabel();
        txtCapP = new javax.swing.JTextField();
        lblCapP = new javax.swing.JLabel();
        txtCapQ = new javax.swing.JTextField();
        lblCapQ = new javax.swing.JLabel();
        txtCapR = new javax.swing.JTextField();
        lblCapR = new javax.swing.JLabel();
        txtCapS = new javax.swing.JTextField();
        lblCapS = new javax.swing.JLabel();
        txtCapT = new javax.swing.JTextField();
        lblCapT = new javax.swing.JLabel();
        txtCapU = new javax.swing.JTextField();
        lblCapU = new javax.swing.JLabel();
        txtCapV = new javax.swing.JTextField();
        lblCapV = new javax.swing.JLabel();
        txtCapW = new javax.swing.JTextField();
        lblCapW = new javax.swing.JLabel();
        txtCapX = new javax.swing.JTextField();
        lblCapX = new javax.swing.JLabel();
        txtCapY = new javax.swing.JTextField();
        lblCapY = new javax.swing.JLabel();
        txtCapZ = new javax.swing.JTextField();
        lblCapZ = new javax.swing.JLabel();
        lblLowK = new javax.swing.JLabel();
        txtLowL = new javax.swing.JTextField();
        lblLowL = new javax.swing.JLabel();
        txtLowM = new javax.swing.JTextField();
        lblLowM = new javax.swing.JLabel();
        txtLowN = new javax.swing.JTextField();
        lblLowN = new javax.swing.JLabel();
        txtLowO = new javax.swing.JTextField();
        lblLowO = new javax.swing.JLabel();
        txtLowP = new javax.swing.JTextField();
        lblLowP = new javax.swing.JLabel();
        txtLowQ = new javax.swing.JTextField();
        lblLowQ = new javax.swing.JLabel();
        txtLowR = new javax.swing.JTextField();
        lblLowR = new javax.swing.JLabel();
        txtLowS = new javax.swing.JTextField();
        lblLowS = new javax.swing.JLabel();
        txtLowT = new javax.swing.JTextField();
        lblLowT = new javax.swing.JLabel();
        txtLowU = new javax.swing.JTextField();
        lblLowU = new javax.swing.JLabel();
        txtLowV = new javax.swing.JTextField();
        lblLowV = new javax.swing.JLabel();
        txtLowA = new javax.swing.JTextField();
        txtLowW = new javax.swing.JTextField();
        lblLowA = new javax.swing.JLabel();
        lblLowW = new javax.swing.JLabel();
        txtLowB = new javax.swing.JTextField();
        txtLowX = new javax.swing.JTextField();
        lblLowB = new javax.swing.JLabel();
        txtLowC = new javax.swing.JTextField();
        lblLowC = new javax.swing.JLabel();
        txtLowD = new javax.swing.JTextField();
        lblLowX = new javax.swing.JLabel();
        txtLowY = new javax.swing.JTextField();
        lblLowY = new javax.swing.JLabel();
        txtLowZ = new javax.swing.JTextField();
        lblLowD = new javax.swing.JLabel();
        lblLowZ = new javax.swing.JLabel();
        txtLowE = new javax.swing.JTextField();
        lblLowE = new javax.swing.JLabel();
        txtLowF = new javax.swing.JTextField();
        lblLowF = new javax.swing.JLabel();
        txtLowG = new javax.swing.JTextField();
        lblLowG = new javax.swing.JLabel();
        txtLowH = new javax.swing.JTextField();
        lblLowH = new javax.swing.JLabel();
        txtLowI = new javax.swing.JTextField();
        lblLowI = new javax.swing.JLabel();
        txtLowJ = new javax.swing.JTextField();
        lblLowJ = new javax.swing.JLabel();
        txtLowK = new javax.swing.JTextField();
        txtSpace = new javax.swing.JTextField();
        lblSpace = new javax.swing.JLabel();
        txtNum1 = new javax.swing.JTextField();
        lblNum1 = new javax.swing.JLabel();
        txtNum2 = new javax.swing.JTextField();
        lblNum2 = new javax.swing.JLabel();
        txtNum3 = new javax.swing.JTextField();
        lblNum3 = new javax.swing.JLabel();
        txtNum4 = new javax.swing.JTextField();
        lblNum4 = new javax.swing.JLabel();
        txtNum5 = new javax.swing.JTextField();
        lblNum5 = new javax.swing.JLabel();
        txtNum6 = new javax.swing.JTextField();
        lblNum6 = new javax.swing.JLabel();
        txtNum7 = new javax.swing.JTextField();
        lblNum7 = new javax.swing.JLabel();
        txtNum8 = new javax.swing.JTextField();
        lblNum8 = new javax.swing.JLabel();
        txtNum9 = new javax.swing.JTextField();
        lblNum9 = new javax.swing.JLabel();
        txtNum0 = new javax.swing.JTextField();
        lblNum0 = new javax.swing.JLabel();
        txtExclamation = new javax.swing.JTextField();
        lblExclamation = new javax.swing.JLabel();
        txtAt = new javax.swing.JTextField();
        lblAt = new javax.swing.JLabel();
        txtHash = new javax.swing.JTextField();
        lblHash = new javax.swing.JLabel();
        txtDollar = new javax.swing.JTextField();
        lblDollar = new javax.swing.JLabel();
        txtPercent = new javax.swing.JTextField();
        lblPercent = new javax.swing.JLabel();
        txtUpArrow = new javax.swing.JTextField();
        lblUpArrow = new javax.swing.JLabel();
        txtAnd = new javax.swing.JTextField();
        lblAnd = new javax.swing.JLabel();
        txtAsterisk = new javax.swing.JTextField();
        lblAsterisk = new javax.swing.JLabel();
        txtRightP = new javax.swing.JTextField();
        lblRightP = new javax.swing.JLabel();
        txtLeftP = new javax.swing.JTextField();
        lblLeftP = new javax.swing.JLabel();
        txtHyphen = new javax.swing.JTextField();
        lblHyphen = new javax.swing.JLabel();
        txtPlus = new javax.swing.JTextField();
        lblPlus = new javax.swing.JLabel();
        txtEqual = new javax.swing.JTextField();
        lblEqual = new javax.swing.JLabel();
        txtCurlyR = new javax.swing.JTextField();
        lblCurlyR = new javax.swing.JLabel();
        txtCurlyL = new javax.swing.JTextField();
        lblCurlyL = new javax.swing.JLabel();
        txtBracketR = new javax.swing.JTextField();
        lblBracketR = new javax.swing.JLabel();
        txtBracketL = new javax.swing.JTextField();
        lblBracketL = new javax.swing.JLabel();
        txtBackSlash = new javax.swing.JTextField();
        lblBackSlash = new javax.swing.JLabel();
        txtLine = new javax.swing.JTextField();
        lblLine = new javax.swing.JLabel();
        txtColon = new javax.swing.JTextField();
        lblColon = new javax.swing.JLabel();
        txtSemi = new javax.swing.JTextField();
        lblSemi = new javax.swing.JLabel();
        txtApost = new javax.swing.JTextField();
        lblApost = new javax.swing.JLabel();
        txtQuote = new javax.swing.JTextField();
        lblQuote = new javax.swing.JLabel();
        txtLess = new javax.swing.JTextField();
        lblLess = new javax.swing.JLabel();
        txtGreater = new javax.swing.JTextField();
        lblGreater = new javax.swing.JLabel();
        txtComma = new javax.swing.JTextField();
        lblComma = new javax.swing.JLabel();
        txtPeriod = new javax.swing.JTextField();
        lblPeriod = new javax.swing.JLabel();
        txtForeSlash = new javax.swing.JTextField();
        lblForeSlash = new javax.swing.JLabel();
        txtQuestion = new javax.swing.JTextField();
        lblQuestion = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        lblMaxLength = new javax.swing.JLabel();
        lblMaxLength1 = new javax.swing.JLabel();
        txtLanguageName = new javax.swing.JTextField();
        txtUnderscore = new javax.swing.JTextField();
        lblUnderscore = new javax.swing.JLabel();
        txtTextLength = new javax.swing.JTextField();
        txtTilde = new javax.swing.JTextField();
        lblTilde = new javax.swing.JLabel();
        txtBacktick = new javax.swing.JTextField();
        lblBacktick = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        lblGeneration = new javax.swing.JLabel();
        panelGeneration = new javax.swing.JPanel();
        txtCharacters = new javax.swing.JTextField();
        lblCharsToUse = new javax.swing.JLabel();
        chkAllowSpace = new javax.swing.JCheckBox();
        btnGenerate = new javax.swing.JButton();
        lblGenCharCount = new javax.swing.JLabel();
        chkClearAll = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        itmOpen = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        itmClear = new javax.swing.JMenuItem();

        fileChooser.setDialogTitle("Save");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CryptMessage Language Editor");
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(EditFrame.class.getResource("appicon.png")));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtCapA.setBackground(new java.awt.Color(0, 0, 0));
        txtCapA.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapA.setForeground(new java.awt.Color(102, 255, 51));
        txtCapA.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapA.setNextFocusableComponent(txtCapB);
        txtCapA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapAActionPerformed(evt);
            }
        });
        txtCapA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCapAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapAKeyTyped(evt);
            }
        });

        lblCapA.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapA.setForeground(new java.awt.Color(102, 255, 51));
        lblCapA.setText("A:");

        txtCapB.setBackground(new java.awt.Color(0, 0, 0));
        txtCapB.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapB.setForeground(new java.awt.Color(102, 255, 51));
        txtCapB.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapB.setNextFocusableComponent(txtCapC);
        txtCapB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapBActionPerformed(evt);
            }
        });
        txtCapB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapBKeyTyped(evt);
            }
        });

        lblCapB.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapB.setForeground(new java.awt.Color(102, 255, 51));
        lblCapB.setText("B:");

        txtCapC.setBackground(new java.awt.Color(0, 0, 0));
        txtCapC.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapC.setForeground(new java.awt.Color(102, 255, 51));
        txtCapC.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapC.setNextFocusableComponent(txtCapD);
        txtCapC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapCActionPerformed(evt);
            }
        });
        txtCapC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapCKeyTyped(evt);
            }
        });

        lblCapC.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapC.setForeground(new java.awt.Color(102, 255, 51));
        lblCapC.setText("C:");

        txtCapD.setBackground(new java.awt.Color(0, 0, 0));
        txtCapD.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapD.setForeground(new java.awt.Color(102, 255, 51));
        txtCapD.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapD.setNextFocusableComponent(txtCapE);
        txtCapD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapDActionPerformed(evt);
            }
        });
        txtCapD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapDKeyTyped(evt);
            }
        });

        lblCapD.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapD.setForeground(new java.awt.Color(102, 255, 51));
        lblCapD.setText("D:");

        txtCapE.setBackground(new java.awt.Color(0, 0, 0));
        txtCapE.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapE.setForeground(new java.awt.Color(102, 255, 51));
        txtCapE.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapE.setNextFocusableComponent(txtCapF);
        txtCapE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapEActionPerformed(evt);
            }
        });
        txtCapE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapEKeyTyped(evt);
            }
        });

        lblCapE.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapE.setForeground(new java.awt.Color(102, 255, 51));
        lblCapE.setText("E:");

        txtCapF.setBackground(new java.awt.Color(0, 0, 0));
        txtCapF.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapF.setForeground(new java.awt.Color(102, 255, 51));
        txtCapF.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapF.setNextFocusableComponent(txtCapG);
        txtCapF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapFActionPerformed(evt);
            }
        });
        txtCapF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapFKeyTyped(evt);
            }
        });

        lblCapF.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapF.setForeground(new java.awt.Color(102, 255, 51));
        lblCapF.setText("F:");

        txtCapG.setBackground(new java.awt.Color(0, 0, 0));
        txtCapG.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapG.setForeground(new java.awt.Color(102, 255, 51));
        txtCapG.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapG.setNextFocusableComponent(txtCapH);
        txtCapG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapGActionPerformed(evt);
            }
        });
        txtCapG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapGKeyTyped(evt);
            }
        });

        lblCapG.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapG.setForeground(new java.awt.Color(102, 255, 51));
        lblCapG.setText("G:");

        txtCapH.setBackground(new java.awt.Color(0, 0, 0));
        txtCapH.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapH.setForeground(new java.awt.Color(102, 255, 51));
        txtCapH.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapH.setNextFocusableComponent(txtCapI);
        txtCapH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapHActionPerformed(evt);
            }
        });
        txtCapH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapHKeyTyped(evt);
            }
        });

        lblCapH.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapH.setForeground(new java.awt.Color(102, 255, 51));
        lblCapH.setText("H:");

        txtCapI.setBackground(new java.awt.Color(0, 0, 0));
        txtCapI.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapI.setForeground(new java.awt.Color(102, 255, 51));
        txtCapI.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapI.setNextFocusableComponent(txtCapJ);
        txtCapI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapIActionPerformed(evt);
            }
        });
        txtCapI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapIKeyTyped(evt);
            }
        });

        lblCapI.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapI.setForeground(new java.awt.Color(102, 255, 51));
        lblCapI.setText("I:");

        txtCapJ.setBackground(new java.awt.Color(0, 0, 0));
        txtCapJ.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapJ.setForeground(new java.awt.Color(102, 255, 51));
        txtCapJ.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapJ.setNextFocusableComponent(txtCapK);
        txtCapJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapJActionPerformed(evt);
            }
        });
        txtCapJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapJKeyTyped(evt);
            }
        });

        lblCapJ.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapJ.setForeground(new java.awt.Color(102, 255, 51));
        lblCapJ.setText("J:");

        txtCapK.setBackground(new java.awt.Color(0, 0, 0));
        txtCapK.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapK.setForeground(new java.awt.Color(102, 255, 51));
        txtCapK.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapK.setNextFocusableComponent(txtCapL);
        txtCapK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapKActionPerformed(evt);
            }
        });
        txtCapK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapKKeyTyped(evt);
            }
        });

        lblCapK.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapK.setForeground(new java.awt.Color(102, 255, 51));
        lblCapK.setText("K:");

        txtCapL.setBackground(new java.awt.Color(0, 0, 0));
        txtCapL.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapL.setForeground(new java.awt.Color(102, 255, 51));
        txtCapL.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapL.setNextFocusableComponent(txtCapM);
        txtCapL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapLActionPerformed(evt);
            }
        });
        txtCapL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapLKeyTyped(evt);
            }
        });

        lblCapL.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapL.setForeground(new java.awt.Color(102, 255, 51));
        lblCapL.setText("L:");

        txtCapM.setBackground(new java.awt.Color(0, 0, 0));
        txtCapM.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapM.setForeground(new java.awt.Color(102, 255, 51));
        txtCapM.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapM.setNextFocusableComponent(txtCapN);
        txtCapM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapMActionPerformed(evt);
            }
        });
        txtCapM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMKeyTyped(evt);
            }
        });

        lblCapM.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapM.setForeground(new java.awt.Color(102, 255, 51));
        lblCapM.setText("M:");

        txtCapN.setBackground(new java.awt.Color(0, 0, 0));
        txtCapN.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapN.setForeground(new java.awt.Color(102, 255, 51));
        txtCapN.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapN.setNextFocusableComponent(txtCapO);
        txtCapN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapNActionPerformed(evt);
            }
        });
        txtCapN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapNKeyTyped(evt);
            }
        });

        lblCapN.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapN.setForeground(new java.awt.Color(102, 255, 51));
        lblCapN.setText("N:");

        txtCapO.setBackground(new java.awt.Color(0, 0, 0));
        txtCapO.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapO.setForeground(new java.awt.Color(102, 255, 51));
        txtCapO.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapO.setNextFocusableComponent(txtCapP);
        txtCapO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapOActionPerformed(evt);
            }
        });
        txtCapO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapOKeyTyped(evt);
            }
        });

        lblCapO.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapO.setForeground(new java.awt.Color(102, 255, 51));
        lblCapO.setText("O:");

        txtCapP.setBackground(new java.awt.Color(0, 0, 0));
        txtCapP.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapP.setForeground(new java.awt.Color(102, 255, 51));
        txtCapP.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapP.setNextFocusableComponent(txtCapQ);
        txtCapP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapPActionPerformed(evt);
            }
        });
        txtCapP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapPKeyTyped(evt);
            }
        });

        lblCapP.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapP.setForeground(new java.awt.Color(102, 255, 51));
        lblCapP.setText("P:");

        txtCapQ.setBackground(new java.awt.Color(0, 0, 0));
        txtCapQ.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapQ.setForeground(new java.awt.Color(102, 255, 51));
        txtCapQ.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapQ.setNextFocusableComponent(txtCapR);
        txtCapQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapQActionPerformed(evt);
            }
        });
        txtCapQ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapQKeyTyped(evt);
            }
        });

        lblCapQ.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapQ.setForeground(new java.awt.Color(102, 255, 51));
        lblCapQ.setText("Q:");

        txtCapR.setBackground(new java.awt.Color(0, 0, 0));
        txtCapR.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapR.setForeground(new java.awt.Color(102, 255, 51));
        txtCapR.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapR.setNextFocusableComponent(txtCapS);
        txtCapR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapRActionPerformed(evt);
            }
        });
        txtCapR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapRKeyTyped(evt);
            }
        });

        lblCapR.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapR.setForeground(new java.awt.Color(102, 255, 51));
        lblCapR.setText("R:");

        txtCapS.setBackground(new java.awt.Color(0, 0, 0));
        txtCapS.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapS.setForeground(new java.awt.Color(102, 255, 51));
        txtCapS.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapS.setNextFocusableComponent(txtCapT);
        txtCapS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapSActionPerformed(evt);
            }
        });
        txtCapS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapSKeyTyped(evt);
            }
        });

        lblCapS.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapS.setForeground(new java.awt.Color(102, 255, 51));
        lblCapS.setText("S:");

        txtCapT.setBackground(new java.awt.Color(0, 0, 0));
        txtCapT.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapT.setForeground(new java.awt.Color(102, 255, 51));
        txtCapT.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapT.setNextFocusableComponent(txtCapU);
        txtCapT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapTActionPerformed(evt);
            }
        });
        txtCapT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapTKeyTyped(evt);
            }
        });

        lblCapT.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapT.setForeground(new java.awt.Color(102, 255, 51));
        lblCapT.setText("T:");

        txtCapU.setBackground(new java.awt.Color(0, 0, 0));
        txtCapU.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapU.setForeground(new java.awt.Color(102, 255, 51));
        txtCapU.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapU.setNextFocusableComponent(txtCapV);
        txtCapU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapUActionPerformed(evt);
            }
        });
        txtCapU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapUKeyTyped(evt);
            }
        });

        lblCapU.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapU.setForeground(new java.awt.Color(102, 255, 51));
        lblCapU.setText("U:");

        txtCapV.setBackground(new java.awt.Color(0, 0, 0));
        txtCapV.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapV.setForeground(new java.awt.Color(102, 255, 51));
        txtCapV.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapV.setNextFocusableComponent(txtCapW);
        txtCapV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapVActionPerformed(evt);
            }
        });
        txtCapV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapVKeyTyped(evt);
            }
        });

        lblCapV.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapV.setForeground(new java.awt.Color(102, 255, 51));
        lblCapV.setText("V:");

        txtCapW.setBackground(new java.awt.Color(0, 0, 0));
        txtCapW.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapW.setForeground(new java.awt.Color(102, 255, 51));
        txtCapW.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapW.setNextFocusableComponent(txtCapX);
        txtCapW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapWActionPerformed(evt);
            }
        });
        txtCapW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapWKeyTyped(evt);
            }
        });

        lblCapW.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapW.setForeground(new java.awt.Color(102, 255, 51));
        lblCapW.setText("W:");

        txtCapX.setBackground(new java.awt.Color(0, 0, 0));
        txtCapX.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapX.setForeground(new java.awt.Color(102, 255, 51));
        txtCapX.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapX.setNextFocusableComponent(txtCapY);
        txtCapX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapXActionPerformed(evt);
            }
        });
        txtCapX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapXKeyTyped(evt);
            }
        });

        lblCapX.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapX.setForeground(new java.awt.Color(102, 255, 51));
        lblCapX.setText("X:");

        txtCapY.setBackground(new java.awt.Color(0, 0, 0));
        txtCapY.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapY.setForeground(new java.awt.Color(102, 255, 51));
        txtCapY.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapY.setNextFocusableComponent(txtCapZ);
        txtCapY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapYActionPerformed(evt);
            }
        });
        txtCapY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapYKeyTyped(evt);
            }
        });

        lblCapY.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapY.setForeground(new java.awt.Color(102, 255, 51));
        lblCapY.setText("Y:");

        txtCapZ.setBackground(new java.awt.Color(0, 0, 0));
        txtCapZ.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCapZ.setForeground(new java.awt.Color(102, 255, 51));
        txtCapZ.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCapZ.setNextFocusableComponent(txtLowA);
        txtCapZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapZActionPerformed(evt);
            }
        });
        txtCapZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapZKeyTyped(evt);
            }
        });

        lblCapZ.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblCapZ.setForeground(new java.awt.Color(102, 255, 51));
        lblCapZ.setText("Z:");

        lblLowK.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowK.setForeground(new java.awt.Color(102, 255, 51));
        lblLowK.setText("k:");

        txtLowL.setBackground(new java.awt.Color(0, 0, 0));
        txtLowL.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowL.setForeground(new java.awt.Color(102, 255, 51));
        txtLowL.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowL.setNextFocusableComponent(txtLowM);
        txtLowL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowLActionPerformed(evt);
            }
        });
        txtLowL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowLKeyTyped(evt);
            }
        });

        lblLowL.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowL.setForeground(new java.awt.Color(102, 255, 51));
        lblLowL.setText("l:");

        txtLowM.setBackground(new java.awt.Color(0, 0, 0));
        txtLowM.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowM.setForeground(new java.awt.Color(102, 255, 51));
        txtLowM.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowM.setNextFocusableComponent(txtLowN);
        txtLowM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowMActionPerformed(evt);
            }
        });
        txtLowM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowMKeyTyped(evt);
            }
        });

        lblLowM.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowM.setForeground(new java.awt.Color(102, 255, 51));
        lblLowM.setText("m:");

        txtLowN.setBackground(new java.awt.Color(0, 0, 0));
        txtLowN.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowN.setForeground(new java.awt.Color(102, 255, 51));
        txtLowN.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowN.setNextFocusableComponent(txtLowO);
        txtLowN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowNActionPerformed(evt);
            }
        });
        txtLowN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowNKeyTyped(evt);
            }
        });

        lblLowN.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowN.setForeground(new java.awt.Color(102, 255, 51));
        lblLowN.setText("n:");

        txtLowO.setBackground(new java.awt.Color(0, 0, 0));
        txtLowO.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowO.setForeground(new java.awt.Color(102, 255, 51));
        txtLowO.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowO.setNextFocusableComponent(txtLowP);
        txtLowO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowOActionPerformed(evt);
            }
        });
        txtLowO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowOKeyTyped(evt);
            }
        });

        lblLowO.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowO.setForeground(new java.awt.Color(102, 255, 51));
        lblLowO.setText("o:");

        txtLowP.setBackground(new java.awt.Color(0, 0, 0));
        txtLowP.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowP.setForeground(new java.awt.Color(102, 255, 51));
        txtLowP.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowP.setNextFocusableComponent(txtLowQ);
        txtLowP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowPActionPerformed(evt);
            }
        });
        txtLowP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowPKeyTyped(evt);
            }
        });

        lblLowP.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowP.setForeground(new java.awt.Color(102, 255, 51));
        lblLowP.setText("p:");

        txtLowQ.setBackground(new java.awt.Color(0, 0, 0));
        txtLowQ.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowQ.setForeground(new java.awt.Color(102, 255, 51));
        txtLowQ.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowQ.setNextFocusableComponent(txtLowR);
        txtLowQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowQActionPerformed(evt);
            }
        });
        txtLowQ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowQKeyTyped(evt);
            }
        });

        lblLowQ.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowQ.setForeground(new java.awt.Color(102, 255, 51));
        lblLowQ.setText("q:");

        txtLowR.setBackground(new java.awt.Color(0, 0, 0));
        txtLowR.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowR.setForeground(new java.awt.Color(102, 255, 51));
        txtLowR.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowR.setNextFocusableComponent(txtLowS);
        txtLowR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowRActionPerformed(evt);
            }
        });
        txtLowR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowRKeyTyped(evt);
            }
        });

        lblLowR.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowR.setForeground(new java.awt.Color(102, 255, 51));
        lblLowR.setText("r:");

        txtLowS.setBackground(new java.awt.Color(0, 0, 0));
        txtLowS.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowS.setForeground(new java.awt.Color(102, 255, 51));
        txtLowS.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowS.setNextFocusableComponent(txtLowT);
        txtLowS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowSActionPerformed(evt);
            }
        });
        txtLowS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowSKeyTyped(evt);
            }
        });

        lblLowS.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowS.setForeground(new java.awt.Color(102, 255, 51));
        lblLowS.setText("s:");

        txtLowT.setBackground(new java.awt.Color(0, 0, 0));
        txtLowT.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowT.setForeground(new java.awt.Color(102, 255, 51));
        txtLowT.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowT.setNextFocusableComponent(txtLowU);
        txtLowT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowTActionPerformed(evt);
            }
        });
        txtLowT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowTKeyTyped(evt);
            }
        });

        lblLowT.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowT.setForeground(new java.awt.Color(102, 255, 51));
        lblLowT.setText("t:");

        txtLowU.setBackground(new java.awt.Color(0, 0, 0));
        txtLowU.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowU.setForeground(new java.awt.Color(102, 255, 51));
        txtLowU.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowU.setNextFocusableComponent(txtLowV);
        txtLowU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowUActionPerformed(evt);
            }
        });
        txtLowU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowUKeyTyped(evt);
            }
        });

        lblLowU.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowU.setForeground(new java.awt.Color(102, 255, 51));
        lblLowU.setText("u:");

        txtLowV.setBackground(new java.awt.Color(0, 0, 0));
        txtLowV.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowV.setForeground(new java.awt.Color(102, 255, 51));
        txtLowV.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowV.setNextFocusableComponent(txtLowW);
        txtLowV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowVActionPerformed(evt);
            }
        });
        txtLowV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowVKeyTyped(evt);
            }
        });

        lblLowV.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowV.setForeground(new java.awt.Color(102, 255, 51));
        lblLowV.setText("v:");

        txtLowA.setBackground(new java.awt.Color(0, 0, 0));
        txtLowA.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowA.setForeground(new java.awt.Color(102, 255, 51));
        txtLowA.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowA.setNextFocusableComponent(txtLowB);
        txtLowA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowAActionPerformed(evt);
            }
        });
        txtLowA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowAKeyTyped(evt);
            }
        });

        txtLowW.setBackground(new java.awt.Color(0, 0, 0));
        txtLowW.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowW.setForeground(new java.awt.Color(102, 255, 51));
        txtLowW.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowW.setNextFocusableComponent(txtLowX);
        txtLowW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowWActionPerformed(evt);
            }
        });
        txtLowW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowWKeyTyped(evt);
            }
        });

        lblLowA.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowA.setForeground(new java.awt.Color(102, 255, 51));
        lblLowA.setText("a:");

        lblLowW.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowW.setForeground(new java.awt.Color(102, 255, 51));
        lblLowW.setText("w:");

        txtLowB.setBackground(new java.awt.Color(0, 0, 0));
        txtLowB.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowB.setForeground(new java.awt.Color(102, 255, 51));
        txtLowB.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowB.setNextFocusableComponent(txtLowC);
        txtLowB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowBActionPerformed(evt);
            }
        });
        txtLowB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowBKeyTyped(evt);
            }
        });

        txtLowX.setBackground(new java.awt.Color(0, 0, 0));
        txtLowX.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowX.setForeground(new java.awt.Color(102, 255, 51));
        txtLowX.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowX.setNextFocusableComponent(txtLowY);
        txtLowX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowXActionPerformed(evt);
            }
        });
        txtLowX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowXKeyTyped(evt);
            }
        });

        lblLowB.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowB.setForeground(new java.awt.Color(102, 255, 51));
        lblLowB.setText("b:");

        txtLowC.setBackground(new java.awt.Color(0, 0, 0));
        txtLowC.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowC.setForeground(new java.awt.Color(102, 255, 51));
        txtLowC.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowC.setNextFocusableComponent(txtLowD);
        txtLowC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowCActionPerformed(evt);
            }
        });
        txtLowC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowCKeyTyped(evt);
            }
        });

        lblLowC.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowC.setForeground(new java.awt.Color(102, 255, 51));
        lblLowC.setText("c:");

        txtLowD.setBackground(new java.awt.Color(0, 0, 0));
        txtLowD.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowD.setForeground(new java.awt.Color(102, 255, 51));
        txtLowD.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowD.setNextFocusableComponent(txtLowE);
        txtLowD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowDActionPerformed(evt);
            }
        });
        txtLowD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowDKeyTyped(evt);
            }
        });

        lblLowX.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowX.setForeground(new java.awt.Color(102, 255, 51));
        lblLowX.setText("x:");

        txtLowY.setBackground(new java.awt.Color(0, 0, 0));
        txtLowY.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowY.setForeground(new java.awt.Color(102, 255, 51));
        txtLowY.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowY.setNextFocusableComponent(txtLowZ);
        txtLowY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowYActionPerformed(evt);
            }
        });
        txtLowY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowYKeyTyped(evt);
            }
        });

        lblLowY.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowY.setForeground(new java.awt.Color(102, 255, 51));
        lblLowY.setText("y:");

        txtLowZ.setBackground(new java.awt.Color(0, 0, 0));
        txtLowZ.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowZ.setForeground(new java.awt.Color(102, 255, 51));
        txtLowZ.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowZ.setNextFocusableComponent(txtNum1);
        txtLowZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowZActionPerformed(evt);
            }
        });
        txtLowZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowZKeyTyped(evt);
            }
        });

        lblLowD.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowD.setForeground(new java.awt.Color(102, 255, 51));
        lblLowD.setText("d:");

        lblLowZ.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowZ.setForeground(new java.awt.Color(102, 255, 51));
        lblLowZ.setText("z:");

        txtLowE.setBackground(new java.awt.Color(0, 0, 0));
        txtLowE.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowE.setForeground(new java.awt.Color(102, 255, 51));
        txtLowE.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowE.setNextFocusableComponent(txtLowF);
        txtLowE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowEActionPerformed(evt);
            }
        });
        txtLowE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowEKeyTyped(evt);
            }
        });

        lblLowE.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowE.setForeground(new java.awt.Color(102, 255, 51));
        lblLowE.setText("e:");

        txtLowF.setBackground(new java.awt.Color(0, 0, 0));
        txtLowF.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowF.setForeground(new java.awt.Color(102, 255, 51));
        txtLowF.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowF.setNextFocusableComponent(txtLowG);
        txtLowF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowFActionPerformed(evt);
            }
        });
        txtLowF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowFKeyTyped(evt);
            }
        });

        lblLowF.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        lblLowF.setForeground(new java.awt.Color(102, 255, 51));
        lblLowF.setText("f:");

        txtLowG.setBackground(new java.awt.Color(0, 0, 0));
        txtLowG.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowG.setForeground(new java.awt.Color(102, 255, 51));
        txtLowG.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowG.setNextFocusableComponent(txtLowH);
        txtLowG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowGActionPerformed(evt);
            }
        });
        txtLowG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowGKeyTyped(evt);
            }
        });

        lblLowG.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowG.setForeground(new java.awt.Color(102, 255, 51));
        lblLowG.setText("g:");

        txtLowH.setBackground(new java.awt.Color(0, 0, 0));
        txtLowH.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowH.setForeground(new java.awt.Color(102, 255, 51));
        txtLowH.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowH.setNextFocusableComponent(txtLowI);
        txtLowH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowHActionPerformed(evt);
            }
        });
        txtLowH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowHKeyTyped(evt);
            }
        });

        lblLowH.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowH.setForeground(new java.awt.Color(102, 255, 51));
        lblLowH.setText("h:");

        txtLowI.setBackground(new java.awt.Color(0, 0, 0));
        txtLowI.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowI.setForeground(new java.awt.Color(102, 255, 51));
        txtLowI.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowI.setNextFocusableComponent(txtLowJ);
        txtLowI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowIActionPerformed(evt);
            }
        });
        txtLowI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowIKeyTyped(evt);
            }
        });

        lblLowI.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowI.setForeground(new java.awt.Color(102, 255, 51));
        lblLowI.setText("i:");

        txtLowJ.setBackground(new java.awt.Color(0, 0, 0));
        txtLowJ.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowJ.setForeground(new java.awt.Color(102, 255, 51));
        txtLowJ.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowJ.setNextFocusableComponent(txtLowK);
        txtLowJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowJActionPerformed(evt);
            }
        });
        txtLowJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowJKeyTyped(evt);
            }
        });

        lblLowJ.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblLowJ.setForeground(new java.awt.Color(102, 255, 51));
        lblLowJ.setText("j:");

        txtLowK.setBackground(new java.awt.Color(0, 0, 0));
        txtLowK.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLowK.setForeground(new java.awt.Color(102, 255, 51));
        txtLowK.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLowK.setNextFocusableComponent(txtLowL);
        txtLowK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLowKActionPerformed(evt);
            }
        });
        txtLowK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLowKKeyTyped(evt);
            }
        });

        txtSpace.setBackground(new java.awt.Color(0, 0, 0));
        txtSpace.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtSpace.setForeground(new java.awt.Color(102, 255, 51));
        txtSpace.setCaretColor(new java.awt.Color(102, 255, 51));
        txtSpace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpaceActionPerformed(evt);
            }
        });
        txtSpace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSpaceKeyTyped(evt);
            }
        });

        lblSpace.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblSpace.setForeground(new java.awt.Color(102, 255, 51));
        lblSpace.setText("Space:");

        txtNum1.setBackground(new java.awt.Color(0, 0, 0));
        txtNum1.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum1.setForeground(new java.awt.Color(102, 255, 51));
        txtNum1.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum1ActionPerformed(evt);
            }
        });
        txtNum1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum1KeyTyped(evt);
            }
        });

        lblNum1.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum1.setForeground(new java.awt.Color(102, 255, 51));
        lblNum1.setText("1:");

        txtNum2.setBackground(new java.awt.Color(0, 0, 0));
        txtNum2.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum2.setForeground(new java.awt.Color(102, 255, 51));
        txtNum2.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum2ActionPerformed(evt);
            }
        });
        txtNum2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum2KeyTyped(evt);
            }
        });

        lblNum2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum2.setForeground(new java.awt.Color(102, 255, 51));
        lblNum2.setText("2:");

        txtNum3.setBackground(new java.awt.Color(0, 0, 0));
        txtNum3.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum3.setForeground(new java.awt.Color(102, 255, 51));
        txtNum3.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum3.setNextFocusableComponent(txtNum4);
        txtNum3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum3ActionPerformed(evt);
            }
        });
        txtNum3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum3KeyTyped(evt);
            }
        });

        lblNum3.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum3.setForeground(new java.awt.Color(102, 255, 51));
        lblNum3.setText("3:");

        txtNum4.setBackground(new java.awt.Color(0, 0, 0));
        txtNum4.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum4.setForeground(new java.awt.Color(102, 255, 51));
        txtNum4.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum4ActionPerformed(evt);
            }
        });
        txtNum4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum4KeyTyped(evt);
            }
        });

        lblNum4.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum4.setForeground(new java.awt.Color(102, 255, 51));
        lblNum4.setText("4:");

        txtNum5.setBackground(new java.awt.Color(0, 0, 0));
        txtNum5.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum5.setForeground(new java.awt.Color(102, 255, 51));
        txtNum5.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum5ActionPerformed(evt);
            }
        });
        txtNum5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum5KeyTyped(evt);
            }
        });

        lblNum5.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum5.setForeground(new java.awt.Color(102, 255, 51));
        lblNum5.setText("5:");

        txtNum6.setBackground(new java.awt.Color(0, 0, 0));
        txtNum6.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum6.setForeground(new java.awt.Color(102, 255, 51));
        txtNum6.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum6.setNextFocusableComponent(txtNum7);
        txtNum6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum6ActionPerformed(evt);
            }
        });
        txtNum6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum6KeyTyped(evt);
            }
        });

        lblNum6.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum6.setForeground(new java.awt.Color(102, 255, 51));
        lblNum6.setText("6:");

        txtNum7.setBackground(new java.awt.Color(0, 0, 0));
        txtNum7.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum7.setForeground(new java.awt.Color(102, 255, 51));
        txtNum7.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum7ActionPerformed(evt);
            }
        });
        txtNum7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum7KeyTyped(evt);
            }
        });

        lblNum7.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum7.setForeground(new java.awt.Color(102, 255, 51));
        lblNum7.setText("7:");

        txtNum8.setBackground(new java.awt.Color(0, 0, 0));
        txtNum8.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum8.setForeground(new java.awt.Color(102, 255, 51));
        txtNum8.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum8ActionPerformed(evt);
            }
        });
        txtNum8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum8KeyTyped(evt);
            }
        });

        lblNum8.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum8.setForeground(new java.awt.Color(102, 255, 51));
        lblNum8.setText("8:");

        txtNum9.setBackground(new java.awt.Color(0, 0, 0));
        txtNum9.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum9.setForeground(new java.awt.Color(102, 255, 51));
        txtNum9.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum9.setNextFocusableComponent(txtNum0);
        txtNum9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum9ActionPerformed(evt);
            }
        });
        txtNum9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum9KeyTyped(evt);
            }
        });

        lblNum9.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum9.setForeground(new java.awt.Color(102, 255, 51));
        lblNum9.setText("9:");

        txtNum0.setBackground(new java.awt.Color(0, 0, 0));
        txtNum0.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtNum0.setForeground(new java.awt.Color(102, 255, 51));
        txtNum0.setCaretColor(new java.awt.Color(102, 255, 51));
        txtNum0.setNextFocusableComponent(txtSpace);
        txtNum0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum0ActionPerformed(evt);
            }
        });
        txtNum0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNum0KeyTyped(evt);
            }
        });

        lblNum0.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblNum0.setForeground(new java.awt.Color(102, 255, 51));
        lblNum0.setText("0:");

        txtExclamation.setBackground(new java.awt.Color(0, 0, 0));
        txtExclamation.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtExclamation.setForeground(new java.awt.Color(102, 255, 51));
        txtExclamation.setCaretColor(new java.awt.Color(102, 255, 51));
        txtExclamation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExclamationActionPerformed(evt);
            }
        });
        txtExclamation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExclamationKeyTyped(evt);
            }
        });

        lblExclamation.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblExclamation.setForeground(new java.awt.Color(102, 255, 51));
        lblExclamation.setText("(!):");

        txtAt.setBackground(new java.awt.Color(0, 0, 0));
        txtAt.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtAt.setForeground(new java.awt.Color(102, 255, 51));
        txtAt.setCaretColor(new java.awt.Color(102, 255, 51));
        txtAt.setNextFocusableComponent(txtHash);
        txtAt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAtActionPerformed(evt);
            }
        });
        txtAt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAtKeyTyped(evt);
            }
        });

        lblAt.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblAt.setForeground(new java.awt.Color(102, 255, 51));
        lblAt.setText("(@):");

        txtHash.setBackground(new java.awt.Color(0, 0, 0));
        txtHash.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtHash.setForeground(new java.awt.Color(102, 255, 51));
        txtHash.setCaretColor(new java.awt.Color(102, 255, 51));
        txtHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHashActionPerformed(evt);
            }
        });
        txtHash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHashKeyTyped(evt);
            }
        });

        lblHash.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblHash.setForeground(new java.awt.Color(102, 255, 51));
        lblHash.setText("(#):");

        txtDollar.setBackground(new java.awt.Color(0, 0, 0));
        txtDollar.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtDollar.setForeground(new java.awt.Color(102, 255, 51));
        txtDollar.setCaretColor(new java.awt.Color(102, 255, 51));
        txtDollar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDollarActionPerformed(evt);
            }
        });
        txtDollar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDollarKeyTyped(evt);
            }
        });

        lblDollar.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblDollar.setForeground(new java.awt.Color(102, 255, 51));
        lblDollar.setText("($):");

        txtPercent.setBackground(new java.awt.Color(0, 0, 0));
        txtPercent.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtPercent.setForeground(new java.awt.Color(102, 255, 51));
        txtPercent.setCaretColor(new java.awt.Color(102, 255, 51));
        txtPercent.setNextFocusableComponent(txtUpArrow);
        txtPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPercentActionPerformed(evt);
            }
        });
        txtPercent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPercentKeyTyped(evt);
            }
        });

        lblPercent.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblPercent.setForeground(new java.awt.Color(102, 255, 51));
        lblPercent.setText("(%):");

        txtUpArrow.setBackground(new java.awt.Color(0, 0, 0));
        txtUpArrow.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtUpArrow.setForeground(new java.awt.Color(102, 255, 51));
        txtUpArrow.setCaretColor(new java.awt.Color(102, 255, 51));
        txtUpArrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUpArrowActionPerformed(evt);
            }
        });
        txtUpArrow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUpArrowKeyTyped(evt);
            }
        });

        lblUpArrow.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblUpArrow.setForeground(new java.awt.Color(102, 255, 51));
        lblUpArrow.setText("(^):");

        txtAnd.setBackground(new java.awt.Color(0, 0, 0));
        txtAnd.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtAnd.setForeground(new java.awt.Color(102, 255, 51));
        txtAnd.setCaretColor(new java.awt.Color(102, 255, 51));
        txtAnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAndActionPerformed(evt);
            }
        });
        txtAnd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAndKeyTyped(evt);
            }
        });

        lblAnd.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblAnd.setForeground(new java.awt.Color(102, 255, 51));
        lblAnd.setText("(&):");

        txtAsterisk.setBackground(new java.awt.Color(0, 0, 0));
        txtAsterisk.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtAsterisk.setForeground(new java.awt.Color(102, 255, 51));
        txtAsterisk.setCaretColor(new java.awt.Color(102, 255, 51));
        txtAsterisk.setNextFocusableComponent(txtRightP);
        txtAsterisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAsteriskActionPerformed(evt);
            }
        });
        txtAsterisk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAsteriskKeyTyped(evt);
            }
        });

        lblAsterisk.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblAsterisk.setForeground(new java.awt.Color(102, 255, 51));
        lblAsterisk.setText("(*):");

        txtRightP.setBackground(new java.awt.Color(0, 0, 0));
        txtRightP.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtRightP.setForeground(new java.awt.Color(102, 255, 51));
        txtRightP.setCaretColor(new java.awt.Color(102, 255, 51));
        txtRightP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRightPActionPerformed(evt);
            }
        });
        txtRightP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRightPKeyTyped(evt);
            }
        });

        lblRightP.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblRightP.setForeground(new java.awt.Color(102, 255, 51));
        lblRightP.setText("(():");

        txtLeftP.setBackground(new java.awt.Color(0, 0, 0));
        txtLeftP.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLeftP.setForeground(new java.awt.Color(102, 255, 51));
        txtLeftP.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLeftP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLeftPActionPerformed(evt);
            }
        });
        txtLeftP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeftPKeyTyped(evt);
            }
        });

        lblLeftP.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblLeftP.setForeground(new java.awt.Color(102, 255, 51));
        lblLeftP.setText("()):");

        txtHyphen.setBackground(new java.awt.Color(0, 0, 0));
        txtHyphen.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtHyphen.setForeground(new java.awt.Color(102, 255, 51));
        txtHyphen.setCaretColor(new java.awt.Color(102, 255, 51));
        txtHyphen.setNextFocusableComponent(txtPlus);
        txtHyphen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHyphenActionPerformed(evt);
            }
        });
        txtHyphen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHyphenKeyTyped(evt);
            }
        });

        lblHyphen.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblHyphen.setForeground(new java.awt.Color(102, 255, 51));
        lblHyphen.setText("(-):");

        txtPlus.setBackground(new java.awt.Color(0, 0, 0));
        txtPlus.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtPlus.setForeground(new java.awt.Color(102, 255, 51));
        txtPlus.setCaretColor(new java.awt.Color(102, 255, 51));
        txtPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlusActionPerformed(evt);
            }
        });
        txtPlus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlusKeyTyped(evt);
            }
        });

        lblPlus.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblPlus.setForeground(new java.awt.Color(102, 255, 51));
        lblPlus.setText("(+):");

        txtEqual.setBackground(new java.awt.Color(0, 0, 0));
        txtEqual.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtEqual.setForeground(new java.awt.Color(102, 255, 51));
        txtEqual.setCaretColor(new java.awt.Color(102, 255, 51));
        txtEqual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEqualActionPerformed(evt);
            }
        });
        txtEqual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEqualKeyTyped(evt);
            }
        });

        lblEqual.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblEqual.setForeground(new java.awt.Color(102, 255, 51));
        lblEqual.setText("(=):");

        txtCurlyR.setBackground(new java.awt.Color(0, 0, 0));
        txtCurlyR.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCurlyR.setForeground(new java.awt.Color(102, 255, 51));
        txtCurlyR.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCurlyR.setNextFocusableComponent(txtCurlyL);
        txtCurlyR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurlyRActionPerformed(evt);
            }
        });
        txtCurlyR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCurlyRKeyTyped(evt);
            }
        });

        lblCurlyR.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblCurlyR.setForeground(new java.awt.Color(102, 255, 51));
        lblCurlyR.setText("({):");

        txtCurlyL.setBackground(new java.awt.Color(0, 0, 0));
        txtCurlyL.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtCurlyL.setForeground(new java.awt.Color(102, 255, 51));
        txtCurlyL.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCurlyL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurlyLActionPerformed(evt);
            }
        });
        txtCurlyL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCurlyLKeyTyped(evt);
            }
        });

        lblCurlyL.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblCurlyL.setForeground(new java.awt.Color(102, 255, 51));
        lblCurlyL.setText("(}):");

        txtBracketR.setBackground(new java.awt.Color(0, 0, 0));
        txtBracketR.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtBracketR.setForeground(new java.awt.Color(102, 255, 51));
        txtBracketR.setCaretColor(new java.awt.Color(102, 255, 51));
        txtBracketR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBracketRActionPerformed(evt);
            }
        });
        txtBracketR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBracketRKeyTyped(evt);
            }
        });

        lblBracketR.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblBracketR.setForeground(new java.awt.Color(102, 255, 51));
        lblBracketR.setText("([):");

        txtBracketL.setBackground(new java.awt.Color(0, 0, 0));
        txtBracketL.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtBracketL.setForeground(new java.awt.Color(102, 255, 51));
        txtBracketL.setCaretColor(new java.awt.Color(102, 255, 51));
        txtBracketL.setNextFocusableComponent(txtBackSlash);
        txtBracketL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBracketLActionPerformed(evt);
            }
        });
        txtBracketL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBracketLKeyTyped(evt);
            }
        });

        lblBracketL.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblBracketL.setForeground(new java.awt.Color(102, 255, 51));
        lblBracketL.setText("(]):");

        txtBackSlash.setBackground(new java.awt.Color(0, 0, 0));
        txtBackSlash.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtBackSlash.setForeground(new java.awt.Color(102, 255, 51));
        txtBackSlash.setCaretColor(new java.awt.Color(102, 255, 51));
        txtBackSlash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBackSlashActionPerformed(evt);
            }
        });
        txtBackSlash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBackSlashKeyTyped(evt);
            }
        });

        lblBackSlash.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblBackSlash.setForeground(new java.awt.Color(102, 255, 51));
        lblBackSlash.setText("(\\):");

        txtLine.setBackground(new java.awt.Color(0, 0, 0));
        txtLine.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLine.setForeground(new java.awt.Color(102, 255, 51));
        txtLine.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLineActionPerformed(evt);
            }
        });
        txtLine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLineKeyTyped(evt);
            }
        });

        lblLine.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblLine.setForeground(new java.awt.Color(102, 255, 51));
        lblLine.setText("(|):");

        txtColon.setBackground(new java.awt.Color(0, 0, 0));
        txtColon.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtColon.setForeground(new java.awt.Color(102, 255, 51));
        txtColon.setCaretColor(new java.awt.Color(102, 255, 51));
        txtColon.setNextFocusableComponent(txtSemi);
        txtColon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColonActionPerformed(evt);
            }
        });
        txtColon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColonKeyTyped(evt);
            }
        });

        lblColon.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblColon.setForeground(new java.awt.Color(102, 255, 51));
        lblColon.setText("(:):");

        txtSemi.setBackground(new java.awt.Color(0, 0, 0));
        txtSemi.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtSemi.setForeground(new java.awt.Color(102, 255, 51));
        txtSemi.setCaretColor(new java.awt.Color(102, 255, 51));
        txtSemi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSemiActionPerformed(evt);
            }
        });
        txtSemi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSemiKeyTyped(evt);
            }
        });

        lblSemi.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblSemi.setForeground(new java.awt.Color(102, 255, 51));
        lblSemi.setText("(;):");

        txtApost.setBackground(new java.awt.Color(0, 0, 0));
        txtApost.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtApost.setForeground(new java.awt.Color(102, 255, 51));
        txtApost.setCaretColor(new java.awt.Color(102, 255, 51));
        txtApost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApostActionPerformed(evt);
            }
        });
        txtApost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApostKeyTyped(evt);
            }
        });

        lblApost.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblApost.setForeground(new java.awt.Color(102, 255, 51));
        lblApost.setText("('):");

        txtQuote.setBackground(new java.awt.Color(0, 0, 0));
        txtQuote.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtQuote.setForeground(new java.awt.Color(102, 255, 51));
        txtQuote.setCaretColor(new java.awt.Color(102, 255, 51));
        txtQuote.setNextFocusableComponent(txtLess);
        txtQuote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuoteActionPerformed(evt);
            }
        });
        txtQuote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuoteKeyTyped(evt);
            }
        });

        lblQuote.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblQuote.setForeground(new java.awt.Color(102, 255, 51));
        lblQuote.setText("(\"):");

        txtLess.setBackground(new java.awt.Color(0, 0, 0));
        txtLess.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtLess.setForeground(new java.awt.Color(102, 255, 51));
        txtLess.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLessActionPerformed(evt);
            }
        });
        txtLess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLessKeyTyped(evt);
            }
        });

        lblLess.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblLess.setForeground(new java.awt.Color(102, 255, 51));
        lblLess.setText("(<):");

        txtGreater.setBackground(new java.awt.Color(0, 0, 0));
        txtGreater.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtGreater.setForeground(new java.awt.Color(102, 255, 51));
        txtGreater.setCaretColor(new java.awt.Color(102, 255, 51));
        txtGreater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGreaterActionPerformed(evt);
            }
        });
        txtGreater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGreaterKeyTyped(evt);
            }
        });

        lblGreater.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblGreater.setForeground(new java.awt.Color(102, 255, 51));
        lblGreater.setText("(>):");

        txtComma.setBackground(new java.awt.Color(0, 0, 0));
        txtComma.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtComma.setForeground(new java.awt.Color(102, 255, 51));
        txtComma.setCaretColor(new java.awt.Color(102, 255, 51));
        txtComma.setNextFocusableComponent(txtPeriod);
        txtComma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCommaActionPerformed(evt);
            }
        });
        txtComma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCommaKeyTyped(evt);
            }
        });

        lblComma.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblComma.setForeground(new java.awt.Color(102, 255, 51));
        lblComma.setText("(,):");

        txtPeriod.setBackground(new java.awt.Color(0, 0, 0));
        txtPeriod.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtPeriod.setForeground(new java.awt.Color(102, 255, 51));
        txtPeriod.setCaretColor(new java.awt.Color(102, 255, 51));
        txtPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPeriodActionPerformed(evt);
            }
        });
        txtPeriod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeriodKeyTyped(evt);
            }
        });

        lblPeriod.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblPeriod.setForeground(new java.awt.Color(102, 255, 51));
        lblPeriod.setText("(.):");

        txtForeSlash.setBackground(new java.awt.Color(0, 0, 0));
        txtForeSlash.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtForeSlash.setForeground(new java.awt.Color(102, 255, 51));
        txtForeSlash.setCaretColor(new java.awt.Color(102, 255, 51));
        txtForeSlash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtForeSlashActionPerformed(evt);
            }
        });
        txtForeSlash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtForeSlashKeyTyped(evt);
            }
        });

        lblForeSlash.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblForeSlash.setForeground(new java.awt.Color(102, 255, 51));
        lblForeSlash.setText("(/):");

        txtQuestion.setBackground(new java.awt.Color(0, 0, 0));
        txtQuestion.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtQuestion.setForeground(new java.awt.Color(102, 255, 51));
        txtQuestion.setCaretColor(new java.awt.Color(102, 255, 51));
        txtQuestion.setNextFocusableComponent(txtTilde);
        txtQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuestionActionPerformed(evt);
            }
        });
        txtQuestion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuestionKeyTyped(evt);
            }
        });

        lblQuestion.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblQuestion.setForeground(new java.awt.Color(102, 255, 51));
        lblQuestion.setText("(?):");

        btnConfirm.setBackground(new java.awt.Color(0, 0, 0));
        btnConfirm.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        btnConfirm.setForeground(new java.awt.Color(102, 255, 51));
        btnConfirm.setText("Check and save");
        btnConfirm.setNextFocusableComponent(btnConfirm);
        btnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmMouseClicked(evt);
            }
        });
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        lblMaxLength.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblMaxLength.setForeground(new java.awt.Color(102, 255, 51));
        lblMaxLength.setText("Text Length:");

        lblMaxLength1.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        lblMaxLength1.setForeground(new java.awt.Color(102, 255, 51));
        lblMaxLength1.setText("Language name:");

        txtLanguageName.setBackground(new java.awt.Color(0, 0, 0));
        txtLanguageName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtLanguageName.setForeground(new java.awt.Color(102, 255, 51));
        txtLanguageName.setText("Untitled");
        txtLanguageName.setCaretColor(new java.awt.Color(102, 255, 51));
        txtLanguageName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLanguageNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLanguageNameKeyTyped(evt);
            }
        });

        txtUnderscore.setBackground(new java.awt.Color(0, 0, 0));
        txtUnderscore.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtUnderscore.setForeground(new java.awt.Color(102, 255, 51));
        txtUnderscore.setCaretColor(new java.awt.Color(102, 255, 51));
        txtUnderscore.setNextFocusableComponent(txtSpace);
        txtUnderscore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnderscoreActionPerformed(evt);
            }
        });
        txtUnderscore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnderscoreKeyTyped(evt);
            }
        });

        lblUnderscore.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblUnderscore.setForeground(new java.awt.Color(102, 255, 51));
        lblUnderscore.setText("(_):");

        txtTextLength.setBackground(new java.awt.Color(0, 0, 0));
        txtTextLength.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtTextLength.setForeground(new java.awt.Color(102, 255, 51));
        txtTextLength.setText("4");
        txtTextLength.setCaretColor(new java.awt.Color(102, 255, 51));
        txtTextLength.setNextFocusableComponent(txtCapA);
        txtTextLength.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTextLengthFocusLost(evt);
            }
        });
        txtTextLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTextLengthActionPerformed(evt);
            }
        });
        txtTextLength.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTextLengthKeyTyped(evt);
            }
        });

        txtTilde.setBackground(new java.awt.Color(0, 0, 0));
        txtTilde.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtTilde.setForeground(new java.awt.Color(102, 255, 51));
        txtTilde.setCaretColor(new java.awt.Color(102, 255, 51));
        txtTilde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTildeActionPerformed(evt);
            }
        });
        txtTilde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTildeKeyTyped(evt);
            }
        });

        lblTilde.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblTilde.setForeground(new java.awt.Color(102, 255, 51));
        lblTilde.setText("(~):");

        txtBacktick.setBackground(new java.awt.Color(0, 0, 0));
        txtBacktick.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtBacktick.setForeground(new java.awt.Color(102, 255, 51));
        txtBacktick.setCaretColor(new java.awt.Color(102, 255, 51));
        txtBacktick.setNextFocusableComponent(btnConfirm);
        txtBacktick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBacktickActionPerformed(evt);
            }
        });
        txtBacktick.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCapGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBacktickKeyTyped(evt);
            }
        });

        lblBacktick.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lblBacktick.setForeground(new java.awt.Color(102, 255, 51));
        lblBacktick.setText("(`):");

        lblError.setFont(new java.awt.Font("OCR A Extended", 0, 24)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 51, 0));

        lblGeneration.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        lblGeneration.setForeground(new java.awt.Color(102, 255, 51));
        lblGeneration.setText("Autofill");

        panelGeneration.setBackground(new java.awt.Color(1, 1, 1));
        panelGeneration.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelGeneration.setForeground(new java.awt.Color(102, 255, 51));
        panelGeneration.setLayout(null);

        txtCharacters.setBackground(new java.awt.Color(0, 0, 0));
        txtCharacters.setForeground(new java.awt.Color(102, 255, 51));
        txtCharacters.setCaretColor(new java.awt.Color(102, 255, 51));
        txtCharacters.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCharactersPropertyChange(evt);
            }
        });
        txtCharacters.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCharactersKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCharactersKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCharactersKeyTyped(evt);
            }
        });
        panelGeneration.add(txtCharacters);
        txtCharacters.setBounds(131, 14, 480, 22);

        lblCharsToUse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCharsToUse.setForeground(new java.awt.Color(102, 255, 51));
        lblCharsToUse.setText("Characters to use:");
        lblCharsToUse.setToolTipText("Characters in the text box will be used when generating a string. (ex. \"sthu\" can generate \"shut\")");
        panelGeneration.add(lblCharsToUse);
        lblCharsToUse.setBounds(10, 10, 113, 30);

        chkAllowSpace.setBackground(new java.awt.Color(0, 0, 0));
        chkAllowSpace.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkAllowSpace.setForeground(new java.awt.Color(102, 255, 51));
        chkAllowSpace.setText("Allow edge spaces");
        chkAllowSpace.setToolTipText("If checked, spaces can be the first and last character");
        chkAllowSpace.setEnabled(false);
        chkAllowSpace.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chkAllowSpacePropertyChange(evt);
            }
        });
        panelGeneration.add(chkAllowSpace);
        chkAllowSpace.setBounds(470, 50, 140, 25);

        btnGenerate.setBackground(new java.awt.Color(0, 0, 0));
        btnGenerate.setForeground(new java.awt.Color(102, 255, 51));
        btnGenerate.setText("Generate");
        btnGenerate.setToolTipText("Please input at least two characters");
        btnGenerate.setEnabled(false);
        btnGenerate.setNextFocusableComponent(btnGenerate);
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });
        panelGeneration.add(btnGenerate);
        btnGenerate.setBounds(290, 100, 100, 25);

        lblGenCharCount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGenCharCount.setForeground(new java.awt.Color(102, 255, 51));
        lblGenCharCount.setText("0");
        panelGeneration.add(lblGenCharCount);
        lblGenCharCount.setBounds(620, 10, 41, 30);

        chkClearAll.setBackground(new java.awt.Color(0, 0, 0));
        chkClearAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chkClearAll.setForeground(new java.awt.Color(102, 255, 51));
        chkClearAll.setSelected(true);
        chkClearAll.setText("Clear all text boxes upon generation");
        chkClearAll.setToolTipText("If not checked, only empty text boxes will be filled");
        chkClearAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkClearAllStateChanged(evt);
            }
        });
        panelGeneration.add(chkClearAll);
        chkClearAll.setBounds(20, 50, 270, 25);

        mnuFile.setText("File");

        itmOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itmOpen.setText("Open");
        itmOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmOpenActionPerformed(evt);
            }
        });
        mnuFile.add(itmOpen);

        menuBar.add(mnuFile);

        mnuEdit.setText("Edit");

        itmClear.setText("Clear");
        itmClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itmClearMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itmClearMousePressed(evt);
            }
        });
        itmClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmClearActionPerformed(evt);
            }
        });
        mnuEdit.add(itmClear);

        menuBar.add(mnuEdit);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCapE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapG, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapI, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapB, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapJ, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapK, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapL, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCapM, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCapE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapJ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCapN)
                    .addComponent(lblCapO)
                    .addComponent(lblCapP)
                    .addComponent(lblCapQ)
                    .addComponent(lblCapR)
                    .addComponent(lblCapS)
                    .addComponent(lblCapT)
                    .addComponent(lblCapU)
                    .addComponent(lblCapV)
                    .addComponent(lblCapW)
                    .addComponent(lblCapX)
                    .addComponent(lblCapY)
                    .addComponent(lblCapZ))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCapN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLowK, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowL, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowM, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowB, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowG, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowI, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowJ, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLowL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowJ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLowN, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowO, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowQ, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowR, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowS, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowU, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowW, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowX, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowY, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLowZ, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLowN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLowZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNum4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNum7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHash, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUpArrow, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRightP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPlus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCurlyL, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblBackSlash, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSemi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLess, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPeriod, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTilde, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSpace, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNum1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNum4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNum7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUpArrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRightP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurlyL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBackSlash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSemi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeriod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTilde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSpace, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNum1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComma, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblQuestion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUnderscore, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPercent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsterisk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHyphen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurlyR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBracketL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuote, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuestion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnderscore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLine, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBracketR, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEqual, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLeftP, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(364, 364, 364))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblBacktick)
                        .addGap(364, 364, 364))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblForeSlash)
                        .addGap(364, 364, 364))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblGreater)
                        .addGap(364, 364, 364))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblApost)
                        .addGap(364, 364, 364))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNum3)
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNum6)
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNum9)
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblQuote)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblColon)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblBracketL)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCurlyR)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblHyphen)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAsterisk)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblPercent)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAt)
                        .addGap(164, 164, 164))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1089, 1089, 1089)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAnd)
                                    .addComponent(lblDollar)
                                    .addComponent(lblExclamation)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1105, 1105, 1105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNum2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNum5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNum8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNum0, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNum2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExclamation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDollar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtBacktick, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtForeSlash, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtGreater, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtApost, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtLine, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtBracketR, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtEqual, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1134, 1134, 1134)
                        .addComponent(txtLeftP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lblMaxLength1)
                        .addGap(5, 5, 5)
                        .addComponent(txtLanguageName, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(lblMaxLength)
                        .addGap(5, 5, 5)
                        .addComponent(txtTextLength, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(810, 810, 810)
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(899, 899, 899)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1337, 1337, 1337)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblMaxLength1))
                            .addComponent(txtLanguageName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblMaxLength))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtTextLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCapA)
                            .addComponent(txtCapA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCapN)
                            .addComponent(txtCapN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLowA)
                            .addComponent(txtLowA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLowN)
                            .addComponent(txtLowN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNum1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNum2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblNum3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCapB)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapC)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapD)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapE)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapF))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCapB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCapO)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapP)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapQ)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapR)
                                .addGap(7, 7, 7)
                                .addComponent(lblCapS))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCapO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtCapS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLowB)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowC)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowD)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowE)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowF))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtLowB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLowO)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowP)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowQ)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowR)
                                .addGap(7, 7, 7)
                                .addComponent(lblLowS))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtLowO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtLowS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNum4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtNum4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(7, 7, 7)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNum7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtNum7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNum5)
                                            .addComponent(lblNum6)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNum5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNum6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNum8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNum9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblNum8)
                                                    .addComponent(lblNum9))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNum0)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(txtNum0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSpace, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtExclamation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblExclamation))
                                    .addComponent(txtAt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(lblSpace))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(lblAt)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPercent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDollar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDollar)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAsterisk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAnd))
                            .addComponent(txtUpArrow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHyphen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLeftP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRightP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEqual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurlyR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBracketR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCurlyL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblBracketR))
                            .addComponent(txtBracketL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtColon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBackSlash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSemi)
                                    .addComponent(lblApost)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtApost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(panelGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblLess)
                                                    .addComponent(lblGreater)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtLess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtGreater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(7, 7, 7)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblPeriod)
                                                    .addComponent(lblForeSlash)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtForeSlash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(11, 11, 11)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtUnderscore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtBacktick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTilde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32)
                                                .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lblComma)
                                                    .addComponent(txtComma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lblQuestion)
                                                    .addComponent(txtQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addComponent(lblUnderscore))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addComponent(lblBacktick))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addComponent(lblTilde))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtQuote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(lblEqual))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(lblPlus))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(lblHyphen)
                        .addGap(10, 10, 10)
                        .addComponent(lblCurlyR))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(lblPercent))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(lblHash))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(txtLowT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(lblLowT))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(txtLowG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(lblLowG))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(txtCapT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(lblCapT))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(txtCapG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(lblCapG))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(lblAsterisk))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(lblUpArrow))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(txtLowU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(lblLowU))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(txtLowH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(lblLowH))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(txtCapU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(lblCapU))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(txtCapH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(lblCapH))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(lblLeftP))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(lblRightP))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(txtLowV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(lblLowV)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowW)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowX)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowY)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowZ))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(txtLowI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtLowM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(lblLowI)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowJ)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowK)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowL)
                        .addGap(7, 7, 7)
                        .addComponent(lblLowM))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(txtCapV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(lblCapV)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapW)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapX)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapY)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapZ))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(txtCapI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtCapM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(lblCapI)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapJ)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapK)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapL)
                        .addGap(7, 7, 7)
                        .addComponent(lblCapM))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(lblCurlyL))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(lblBracketL))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(lblLine))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(lblBackSlash))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(lblColon)
                        .addGap(10, 10, 10)
                        .addComponent(lblQuote)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Unused functions">
    private void txtCapAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapAActionPerformed

    private void txtCapBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapBActionPerformed

    private void txtCapCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapCActionPerformed

    private void txtCapDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapDActionPerformed

    private void txtCapEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapEActionPerformed

    private void txtCapFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapFActionPerformed

    private void txtCapGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapGActionPerformed

    private void txtCapHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapHActionPerformed

    private void txtCapIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapIActionPerformed

    private void txtCapJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapJActionPerformed

    private void txtCapKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapKActionPerformed

    private void txtCapLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapLActionPerformed

    private void txtCapMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapMActionPerformed

    private void txtCapNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapNActionPerformed

    private void txtCapOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapOActionPerformed

    private void txtCapPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapPActionPerformed

    private void txtCapQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapQActionPerformed

    private void txtCapRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapRActionPerformed

    private void txtCapSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapSActionPerformed

    private void txtCapTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapTActionPerformed

    private void txtCapUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapUActionPerformed

    private void txtCapVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapVActionPerformed

    private void txtCapWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapWActionPerformed

    private void txtCapXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapXActionPerformed

    private void txtCapYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapYActionPerformed

    private void txtCapZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapZActionPerformed

    private void txtLowLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowLActionPerformed

    private void txtLowMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowMActionPerformed

    private void txtLowNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowNActionPerformed

    private void txtLowOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowOActionPerformed

    private void txtLowPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowPActionPerformed

    private void txtLowQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowQActionPerformed

    private void txtLowRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowRActionPerformed

    private void txtLowSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowSActionPerformed

    private void txtLowTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowTActionPerformed

    private void txtLowUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowUActionPerformed

    private void txtLowVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowVActionPerformed

    private void txtLowAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowAActionPerformed

    private void txtLowWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowWActionPerformed

    private void txtLowBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowBActionPerformed

    private void txtLowXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowXActionPerformed

    private void txtLowCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowCActionPerformed

    private void txtLowDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowDActionPerformed

    private void txtLowYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowYActionPerformed

    private void txtLowZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowZActionPerformed

    private void txtLowEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowEActionPerformed

    private void txtLowFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowFActionPerformed

    private void txtLowGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowGActionPerformed

    private void txtLowHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowHActionPerformed

    private void txtLowIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowIActionPerformed

    private void txtLowJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowJActionPerformed

    private void txtLowKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLowKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLowKActionPerformed

    private void txtSpaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpaceActionPerformed

    private void txtNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum1ActionPerformed

    private void txtNum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum2ActionPerformed

    private void txtNum3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum3ActionPerformed

    private void txtNum4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum4ActionPerformed

    private void txtNum5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum5ActionPerformed

    private void txtNum6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum6ActionPerformed

    private void txtNum7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum7ActionPerformed

    private void txtNum8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum8ActionPerformed

    private void txtNum9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum9ActionPerformed

    private void txtNum0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum0ActionPerformed

    private void txtExclamationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExclamationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExclamationActionPerformed

    private void txtAtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAtActionPerformed

    private void txtHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHashActionPerformed

    private void txtDollarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDollarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDollarActionPerformed

    private void txtPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPercentActionPerformed

    private void txtUpArrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUpArrowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUpArrowActionPerformed

    private void txtAndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAndActionPerformed

    private void txtAsteriskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAsteriskActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAsteriskActionPerformed

    private void txtRightPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRightPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRightPActionPerformed

    private void txtLeftPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLeftPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLeftPActionPerformed

    private void txtHyphenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHyphenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHyphenActionPerformed

    private void txtPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlusActionPerformed

    private void txtEqualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEqualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEqualActionPerformed

    private void txtCurlyRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurlyRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurlyRActionPerformed

    private void txtCurlyLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurlyLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurlyLActionPerformed

    private void txtBracketRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBracketRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBracketRActionPerformed

    private void txtBracketLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBracketLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBracketLActionPerformed

    private void txtBackSlashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBackSlashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBackSlashActionPerformed

    private void txtLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLineActionPerformed

    private void txtColonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColonActionPerformed

    private void txtSemiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSemiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSemiActionPerformed

    private void txtApostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApostActionPerformed

    private void txtQuoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuoteActionPerformed

    private void txtLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLessActionPerformed

    private void txtGreaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGreaterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGreaterActionPerformed

    private void txtCommaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCommaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCommaActionPerformed

    private void txtPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPeriodActionPerformed

    private void txtForeSlashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtForeSlashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtForeSlashActionPerformed

    private void txtQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuestionActionPerformed
//</editor-fold>
    private void itmOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmOpenActionPerformed
        if(!saved){
            int con = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(con==JOptionPane.CANCEL_OPTION)
                return;
            if(con==JOptionPane.YES_OPTION){
                //cannot call the confirm button method due to the need of a return value
                setAllToBlack();
                lblError.setText("");
                //check if any of the boxes are blank
                if(!noEmptyText()){
                    lblError.setVisible(true);
                    lblError.setText("Empty text found");
                    return;
                }
                //check if each box meets the size requirement
                if(!textMeetsSizeReq()){
                    txtTextLength.setBackground(Color.red);
                    txtTextLength.setForeground(Color.white);
                    lblError.setVisible(true);
                    lblError.setText("Text must equal size");
                    return;
                }
                //check for any duplicates
                if(!noDuplicate()){
                    lblError.setVisible(true);
                    lblError.setText("Duplicate text found");
                    return;
                }
                //passed the tests, user can save
                save();
            }
        }
        File f;
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(fftxt); //only allow .txt files
        int opVal = fc.showOpenDialog(null);
        if(opVal==JFileChooser.APPROVE_OPTION){
            f = fc.getSelectedFile();
        }
        else{return;}
        setAllToBlack();
        lblError.setText("");
        try{
            String t;
            BufferedReader br = new BufferedReader(new FileReader(f));
            int n = 0;
            while((t = br.readLine())!=null){
                if(n==0)
                    txtLanguageName.setText(decrypt(t));
                if(n==1)
                    txtTextLength.setText(decrypt(t));
                if(n>1)
                    textBoxes.get(n-2).setText(decrypt(t));
                n++;
            }
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "This file does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Input/Output exception was thrown.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(isFull()){
            btnGenerate.setEnabled(false);
            if(txtCharacters.getText().length()<2){
                btnGenerate.setToolTipText("Please input at least two characters");
            }
            else{
                btnGenerate.setToolTipText("No empty text boxes available");
            }
        }
    }//GEN-LAST:event_itmOpenActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        //reset the status
        setAllToBlack();
        lblError.setText("");
        
        //check if any of the boxes are blank
        if(!noEmptyText()){
            lblError.setText("Empty text found");
            return;
        }
        //check if each box meets the size requirement
        if(!textMeetsSizeReq()){
            txtTextLength.setBackground(Color.red);
            txtTextLength.setForeground(Color.white);
            lblError.setText("Text must equal size");
            return;
        }
        //check for any duplicates
        if(!noDuplicate()){
            lblError.setText("Duplicate text found");
            return;
        }
        //warn if spaces are at the end of a string
        if(!noSpaceAtTheEdge()){
            int fun = 0; 
            if(foundSpaces==1){
                fun = JOptionPane.showConfirmDialog(null, "There is a textbox that contains a space as the " + spacePos + " character. This could cause issues\nwhen translating if this character is " + spacePos + " in a message. Proceed anyway?", "Caution", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            }
            else if(foundSpaces>1){
                fun = JOptionPane.showConfirmDialog(null, "There are " + foundSpaces + " textboxes that contain a space as the first or last character.\nThese could cause issues when translating messages. Proceed anyway?", "Caution", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            }
            if(fun==JOptionPane.YES_OPTION){
                setAllToBlack();
                save();
            }
        }
        else{
            save();
        }
        
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtUnderscoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnderscoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnderscoreActionPerformed

    private void btnConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmMouseClicked

    private void txtTextLengthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextLengthKeyTyped
        //Only allow numbers to be typed
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter)) || txtTextLength.getText().length()>=2){
            evt.consume();
        }
        
    }//GEN-LAST:event_txtTextLengthKeyTyped

    private void txtTildeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTildeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTildeActionPerformed

    private void txtBacktickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBacktickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBacktickActionPerformed

    private void itmClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itmClearMouseClicked
           
    }//GEN-LAST:event_itmClearMouseClicked

    private void itmClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itmClearMousePressed
        int j = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all the text boxes?", "WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(j==JOptionPane.YES_OPTION){
            textBoxes.forEach((n) -> {
                n.setText("");
            });
            setAllToBlack();
            lblError.setText("");
            if(txtCharacters.getText().length()>=2){
                btnGenerate.setEnabled(true);
                btnGenerate.setToolTipText("");
            }
            else{
                btnGenerate.setEnabled(false);
                btnGenerate.setToolTipText("Please input at least two characters");
            }
        }
        
    }//GEN-LAST:event_itmClearMousePressed

    private void txtTextLengthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTextLengthFocusLost
        if(txtTextLength.getText().length()==0 || Integer.parseInt(txtTextLength.getText())<1)
            txtTextLength.setText("1");
        if(txtTextLength.getText().charAt(0)=='0'){
            txtTextLength.setText(txtTextLength.getText().charAt(1) + "");
        }
        if(Integer.parseInt(txtTextLength.getText())>10 && !txtTextLength.getText().equals(prevLength)){
            prevLength = txtTextLength.getText();
            int l = JOptionPane.showConfirmDialog(null, "Setting the text limit higher than 10 can cause lengthy unsendable messages.", "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(l==JOptionPane.CANCEL_OPTION){
                txtTextLength.setText("10");
            }
        }
        textBoxes.forEach((n) -> {
           if(n.getText().length()>Integer.parseInt(txtTextLength.getText())){
               n.setText(n.getText().substring(0, Integer.parseInt(txtTextLength.getText())));
           } 
        });
    }//GEN-LAST:event_txtTextLengthFocusLost

    private void txtLanguageNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLanguageNameKeyPressed
        
    }//GEN-LAST:event_txtLanguageNameKeyPressed

    private void txtLanguageNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLanguageNameKeyTyped
        if(txtLanguageName.getText().length()>=45){
            evt.consume();
        }
    }//GEN-LAST:event_txtLanguageNameKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(saved){
            dispose();
        }
        else{
            int p = JOptionPane.showConfirmDialog(null, "You have unsaved changes. Are you sure you want to exit?", "Caution", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(p==JOptionPane.YES_OPTION){
                dispose();
            }
            else{
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtTextLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTextLengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTextLengthActionPerformed

    private void txtCharactersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCharactersKeyTyped
        txtCharacters.setBackground(Color.black);
        txtCharacters.setForeground(new java.awt.Color(102, 255, 51));
        //duplicate characters cannot be typed
        if(txtCharacters.getText().contains("" + evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCharactersKeyTyped

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        if(txtCharacters.getText().length()<=1){
            txtCharacters.setBackground(Color.red);
            txtCharacters.setForeground(Color.white);
            JOptionPane.showMessageDialog(null, "There must be at least two characters available for generation", "Unable to generate", JOptionPane.ERROR_MESSAGE);
            btnGenerate.setEnabled(false);
            btnGenerate.setToolTipText("Please input at least two characters");
        }
        else{
            int conf = JOptionPane.showConfirmDialog(null, "Generate with these settings?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(conf==JOptionPane.YES_OPTION){
                genChars.clear();
                for(int i=0; i<txtCharacters.getText().length(); i++){
                    genChars.add(txtCharacters.getText().charAt(i));
                }
                generate();
            }
        }
        if(isFull() && !chkClearAll.isSelected()){
            btnGenerate.setEnabled(false);
            btnGenerate.setToolTipText("No empty text boxes available");
        }
        
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void txtCharactersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCharactersKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCharactersKeyPressed

    private void txtCharactersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCharactersKeyReleased
        lblGenCharCount.setText("" + txtCharacters.getText().length());
        if(txtCharacters.getText().contains(" ")){
            chkAllowSpace.setEnabled(true);
        }
        else{
            chkAllowSpace.setSelected(false);
            chkAllowSpace.setEnabled(false);
        }
        if(txtCharacters.getText().length()>=2 && !isFull()){
            btnGenerate.setEnabled(true);
            btnGenerate.setToolTipText("");
        }
        else{
            btnGenerate.setEnabled(false);
            if(txtCharacters.getText().length()<2){
                btnGenerate.setToolTipText("Please input at least two characters");
            }
            else{
                btnGenerate.setToolTipText("No empty text boxes available");
            }
        }
    }//GEN-LAST:event_txtCharactersKeyReleased

    private void itmClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmClearActionPerformed

    private void txtCharactersPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCharactersPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCharactersPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        txtCharacters.setTransferHandler(null);
    }//GEN-LAST:event_formWindowActivated

    private void chkAllowSpacePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chkAllowSpacePropertyChange
        
    }//GEN-LAST:event_chkAllowSpacePropertyChange

    //THIS FUNCTION APPLIES TO ALL THE TEXT BOXES!
    private void txtCapGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapGKeyReleased
        saved = false;
        if(!isFull() && txtCharacters.getText().length()>=2){
            btnGenerate.setEnabled(true);
            btnGenerate.setToolTipText("");
        }
        else{
            btnGenerate.setEnabled(false);
            btnGenerate.setToolTipText("No empty text boxes available");
        }
    }//GEN-LAST:event_txtCapGKeyReleased

    private void txtCapAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapAKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapAKeyPressed

    private void txtCapAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapAKeyTyped
        if(txtCapA.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapAKeyTyped

    private void txtCapBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapBKeyTyped
        if(txtCapB.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapBKeyTyped

    private void txtCapCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapCKeyTyped
        if(txtCapC.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapCKeyTyped

    private void txtCapDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapDKeyTyped
        if(txtCapD.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapDKeyTyped

    private void txtCapEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapEKeyTyped
        if(txtCapE.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapEKeyTyped

    private void txtCapFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapFKeyTyped
        if(txtCapF.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapFKeyTyped

    private void txtCapGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapGKeyTyped
        if(txtCapG.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapGKeyTyped

    private void txtCapHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapHKeyTyped
        if(txtCapH.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapHKeyTyped

    private void txtCapIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapIKeyTyped
        if(txtCapI.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapIKeyTyped

    private void txtCapJKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapJKeyTyped
        if(txtCapJ.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapJKeyTyped

    private void txtCapKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapKKeyTyped
        if(txtCapK.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapKKeyTyped

    private void txtCapLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapLKeyTyped
        if(txtCapL.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapLKeyTyped

    private void txtCapMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapMKeyTyped
        if(txtCapM.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapMKeyTyped

    private void txtCapNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapNKeyTyped
        if(txtCapN.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapNKeyTyped

    private void txtLowAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowAKeyTyped
        if(txtLowA.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowAKeyTyped

    private void txtCapOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapOKeyTyped
        if(txtCapO.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapOKeyTyped

    private void txtCapPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapPKeyTyped
        if(txtCapP.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapPKeyTyped

    private void txtCapQKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapQKeyTyped
        if(txtCapQ.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapQKeyTyped

    private void txtCapRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapRKeyTyped
        if(txtCapR.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapRKeyTyped

    private void txtCapSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapSKeyTyped
        if(txtCapS.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapSKeyTyped

    private void txtCapTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapTKeyTyped
        if(txtCapT.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapTKeyTyped

    private void txtCapUKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapUKeyTyped
        if(txtCapU.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapUKeyTyped

    private void txtCapVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapVKeyTyped
        if(txtCapV.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapVKeyTyped

    private void txtCapWKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapWKeyTyped
        if(txtCapW.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapWKeyTyped

    private void txtCapXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapXKeyTyped
        if(txtCapX.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapXKeyTyped

    private void txtCapYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapYKeyTyped
        if(txtCapY.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapYKeyTyped

    private void txtCapZKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapZKeyTyped
        if(txtCapZ.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCapZKeyTyped

    private void txtLowBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowBKeyTyped
        if(txtLowB.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowBKeyTyped

    private void txtLowCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowCKeyTyped
        if(txtLowC.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowCKeyTyped

    private void txtLowDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowDKeyTyped
        if(txtLowD.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowDKeyTyped

    private void txtLowEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowEKeyTyped
        if(txtLowE.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowEKeyTyped

    private void txtLowFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowFKeyTyped
        if(txtLowF.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowFKeyTyped

    private void txtLowGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowGKeyTyped
        if(txtLowG.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowGKeyTyped

    private void txtLowHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowHKeyTyped
        if(txtLowH.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowHKeyTyped

    private void txtLowIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowIKeyTyped
        if(txtLowI.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowIKeyTyped

    private void txtLowJKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowJKeyTyped
        if(txtLowJ.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowJKeyTyped

    private void txtLowKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowKKeyTyped
        if(txtLowK.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowKKeyTyped

    private void txtLowLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowLKeyTyped
        if(txtLowL.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowLKeyTyped

    private void txtLowMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowMKeyTyped
        if(txtLowM.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowMKeyTyped

    private void txtLowNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowNKeyTyped
        if(txtLowN.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowNKeyTyped

    private void txtLowOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowOKeyTyped
        if(txtLowO.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowOKeyTyped

    private void txtLowPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowPKeyTyped
        if(txtLowP.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowPKeyTyped

    private void txtLowQKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowQKeyTyped
        if(txtLowQ.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowQKeyTyped

    private void txtLowRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowRKeyTyped
        if(txtLowR.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowRKeyTyped

    private void txtLowSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowSKeyTyped
        if(txtLowS.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowSKeyTyped

    private void txtLowTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowTKeyTyped
        if(txtLowT.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowTKeyTyped

    private void txtLowUKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowUKeyTyped
        if(txtLowU.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowUKeyTyped

    private void txtLowVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowVKeyTyped
        if(txtLowV.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowVKeyTyped

    private void txtLowWKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowWKeyTyped
        if(txtLowW.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowWKeyTyped

    private void txtLowXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowXKeyTyped
        if(txtLowX.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowXKeyTyped

    private void txtLowYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowYKeyTyped
        if(txtLowY.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowYKeyTyped

    private void txtLowZKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLowZKeyTyped
        if(txtLowZ.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLowZKeyTyped

    private void txtNum1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum1KeyTyped
        if(txtNum1.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum1KeyTyped

    private void txtNum2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum2KeyTyped
        if(txtNum2.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum2KeyTyped

    private void txtNum3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum3KeyTyped
        if(txtNum3.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum3KeyTyped

    private void txtNum4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum4KeyTyped
        if(txtNum4.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum4KeyTyped

    private void txtNum5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum5KeyTyped
        if(txtNum5.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum5KeyTyped

    private void txtNum6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum6KeyTyped
        if(txtNum6.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum6KeyTyped

    private void txtNum7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum7KeyTyped
        if(txtNum7.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum7KeyTyped

    private void txtNum8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum8KeyTyped
        if(txtNum8.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum8KeyTyped

    private void txtNum9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum9KeyTyped
        if(txtNum9.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum9KeyTyped

    private void txtNum0KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNum0KeyTyped
        if(txtNum0.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNum0KeyTyped

    private void txtUnderscoreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnderscoreKeyTyped
        if(txtUnderscore.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtUnderscoreKeyTyped

    private void txtSpaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSpaceKeyTyped
        if(txtSpace.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtSpaceKeyTyped

    private void txtExclamationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExclamationKeyTyped
        if(txtExclamation.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtExclamationKeyTyped

    private void txtAtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAtKeyTyped
        if(txtAt.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtAtKeyTyped

    private void txtHashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHashKeyTyped
        if(txtHash.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtHashKeyTyped

    private void txtDollarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDollarKeyTyped
        if(txtDollar.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtDollarKeyTyped

    private void txtPercentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPercentKeyTyped
        if(txtPercent.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtPercentKeyTyped

    private void txtUpArrowKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUpArrowKeyTyped
        if(txtUpArrow.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtUpArrowKeyTyped

    private void txtAndKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAndKeyTyped
        if(txtAnd.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtAndKeyTyped

    private void txtAsteriskKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAsteriskKeyTyped
        if(txtAsterisk.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtAsteriskKeyTyped

    private void txtRightPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRightPKeyTyped
        if(txtRightP.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtRightPKeyTyped

    private void txtLeftPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeftPKeyTyped
        if(txtLeftP.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLeftPKeyTyped

    private void txtHyphenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHyphenKeyTyped
        if(txtHyphen.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtHyphenKeyTyped

    private void txtPlusKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlusKeyTyped
        if(txtPlus.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtPlusKeyTyped

    private void txtEqualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEqualKeyTyped
        if(txtEqual.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtEqualKeyTyped

    private void txtCurlyRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCurlyRKeyTyped
        if(txtCurlyR.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCurlyRKeyTyped

    private void txtCurlyLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCurlyLKeyTyped
        if(txtCurlyL.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCurlyLKeyTyped

    private void txtBracketRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBracketRKeyTyped
        if(txtBracketR.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtBracketRKeyTyped

    private void txtBracketLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBracketLKeyTyped
        if(txtBracketL.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtBracketLKeyTyped

    private void txtBackSlashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBackSlashKeyTyped
        if(txtBackSlash.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtBackSlashKeyTyped

    private void txtLineKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineKeyTyped
        if(txtLine.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLineKeyTyped

    private void txtColonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColonKeyTyped
        if(txtColon.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtColonKeyTyped

    private void txtSemiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemiKeyTyped
        if(txtSemi.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtSemiKeyTyped

    private void txtApostKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApostKeyTyped
        if(txtApost.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtApostKeyTyped

    private void txtQuoteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuoteKeyTyped
        if(txtQuote.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtQuoteKeyTyped

    private void txtLessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLessKeyTyped
        if(txtLess.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtLessKeyTyped

    private void txtGreaterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGreaterKeyTyped
        if(txtGreater.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtGreaterKeyTyped

    private void txtCommaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCommaKeyTyped
        if(txtComma.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCommaKeyTyped

    private void txtPeriodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeriodKeyTyped
        if(txtPeriod.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtPeriodKeyTyped

    private void txtForeSlashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtForeSlashKeyTyped
        if(txtForeSlash.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtForeSlashKeyTyped

    private void txtQuestionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuestionKeyTyped
        if(txtQuestion.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtQuestionKeyTyped

    private void txtTildeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTildeKeyTyped
        if(txtTilde.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtTildeKeyTyped

    private void txtBacktickKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBacktickKeyTyped
        if(txtBacktick.getText().length()>=Integer.parseInt(txtTextLength.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_txtBacktickKeyTyped

    private void chkClearAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkClearAllStateChanged
        if(txtCharacters.getText().length()<2) return;
        btnGenerate.setEnabled(true);
        btnGenerate.setToolTipText("");
        if(!chkClearAll.isSelected()){
            if(isFull()){
                btnGenerate.setEnabled(false);
                btnGenerate.setToolTipText("No empty text boxes available");
            }
        }
    }//GEN-LAST:event_chkClearAllStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows Classic look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JCheckBox chkAllowSpace;
    private javax.swing.JCheckBox chkClearAll;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenuItem itmClear;
    private javax.swing.JMenuItem itmOpen;
    private javax.swing.JLabel lblAnd;
    private javax.swing.JLabel lblApost;
    private javax.swing.JLabel lblAsterisk;
    private javax.swing.JLabel lblAt;
    private javax.swing.JLabel lblBackSlash;
    private javax.swing.JLabel lblBacktick;
    private javax.swing.JLabel lblBracketL;
    private javax.swing.JLabel lblBracketR;
    private javax.swing.JLabel lblCapA;
    private javax.swing.JLabel lblCapB;
    private javax.swing.JLabel lblCapC;
    private javax.swing.JLabel lblCapD;
    private javax.swing.JLabel lblCapE;
    private javax.swing.JLabel lblCapF;
    private javax.swing.JLabel lblCapG;
    private javax.swing.JLabel lblCapH;
    private javax.swing.JLabel lblCapI;
    private javax.swing.JLabel lblCapJ;
    private javax.swing.JLabel lblCapK;
    private javax.swing.JLabel lblCapL;
    private javax.swing.JLabel lblCapM;
    private javax.swing.JLabel lblCapN;
    private javax.swing.JLabel lblCapO;
    private javax.swing.JLabel lblCapP;
    private javax.swing.JLabel lblCapQ;
    private javax.swing.JLabel lblCapR;
    private javax.swing.JLabel lblCapS;
    private javax.swing.JLabel lblCapT;
    private javax.swing.JLabel lblCapU;
    private javax.swing.JLabel lblCapV;
    private javax.swing.JLabel lblCapW;
    private javax.swing.JLabel lblCapX;
    private javax.swing.JLabel lblCapY;
    private javax.swing.JLabel lblCapZ;
    private javax.swing.JLabel lblCharsToUse;
    private javax.swing.JLabel lblColon;
    private javax.swing.JLabel lblComma;
    private javax.swing.JLabel lblCurlyL;
    private javax.swing.JLabel lblCurlyR;
    private javax.swing.JLabel lblDollar;
    private javax.swing.JLabel lblEqual;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblExclamation;
    private javax.swing.JLabel lblForeSlash;
    private javax.swing.JLabel lblGenCharCount;
    private javax.swing.JLabel lblGeneration;
    private javax.swing.JLabel lblGreater;
    private javax.swing.JLabel lblHash;
    private javax.swing.JLabel lblHyphen;
    private javax.swing.JLabel lblLeftP;
    private javax.swing.JLabel lblLess;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblLowA;
    private javax.swing.JLabel lblLowB;
    private javax.swing.JLabel lblLowC;
    private javax.swing.JLabel lblLowD;
    private javax.swing.JLabel lblLowE;
    private javax.swing.JLabel lblLowF;
    private javax.swing.JLabel lblLowG;
    private javax.swing.JLabel lblLowH;
    private javax.swing.JLabel lblLowI;
    private javax.swing.JLabel lblLowJ;
    private javax.swing.JLabel lblLowK;
    private javax.swing.JLabel lblLowL;
    private javax.swing.JLabel lblLowM;
    private javax.swing.JLabel lblLowN;
    private javax.swing.JLabel lblLowO;
    private javax.swing.JLabel lblLowP;
    private javax.swing.JLabel lblLowQ;
    private javax.swing.JLabel lblLowR;
    private javax.swing.JLabel lblLowS;
    private javax.swing.JLabel lblLowT;
    private javax.swing.JLabel lblLowU;
    private javax.swing.JLabel lblLowV;
    private javax.swing.JLabel lblLowW;
    private javax.swing.JLabel lblLowX;
    private javax.swing.JLabel lblLowY;
    private javax.swing.JLabel lblLowZ;
    private javax.swing.JLabel lblMaxLength;
    private javax.swing.JLabel lblMaxLength1;
    private javax.swing.JLabel lblNum0;
    private javax.swing.JLabel lblNum1;
    private javax.swing.JLabel lblNum2;
    private javax.swing.JLabel lblNum3;
    private javax.swing.JLabel lblNum4;
    private javax.swing.JLabel lblNum5;
    private javax.swing.JLabel lblNum6;
    private javax.swing.JLabel lblNum7;
    private javax.swing.JLabel lblNum8;
    private javax.swing.JLabel lblNum9;
    private javax.swing.JLabel lblPercent;
    private javax.swing.JLabel lblPeriod;
    private javax.swing.JLabel lblPlus;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblQuote;
    private javax.swing.JLabel lblRightP;
    private javax.swing.JLabel lblSemi;
    private javax.swing.JLabel lblSpace;
    private javax.swing.JLabel lblTilde;
    private javax.swing.JLabel lblUnderscore;
    private javax.swing.JLabel lblUpArrow;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JPanel panelGeneration;
    private javax.swing.JTextField txtAnd;
    private javax.swing.JTextField txtApost;
    private javax.swing.JTextField txtAsterisk;
    private javax.swing.JTextField txtAt;
    private javax.swing.JTextField txtBackSlash;
    private javax.swing.JTextField txtBacktick;
    private javax.swing.JTextField txtBracketL;
    private javax.swing.JTextField txtBracketR;
    private javax.swing.JTextField txtCapA;
    private javax.swing.JTextField txtCapB;
    private javax.swing.JTextField txtCapC;
    private javax.swing.JTextField txtCapD;
    private javax.swing.JTextField txtCapE;
    private javax.swing.JTextField txtCapF;
    private javax.swing.JTextField txtCapG;
    private javax.swing.JTextField txtCapH;
    private javax.swing.JTextField txtCapI;
    private javax.swing.JTextField txtCapJ;
    private javax.swing.JTextField txtCapK;
    private javax.swing.JTextField txtCapL;
    private javax.swing.JTextField txtCapM;
    private javax.swing.JTextField txtCapN;
    private javax.swing.JTextField txtCapO;
    private javax.swing.JTextField txtCapP;
    private javax.swing.JTextField txtCapQ;
    private javax.swing.JTextField txtCapR;
    private javax.swing.JTextField txtCapS;
    private javax.swing.JTextField txtCapT;
    private javax.swing.JTextField txtCapU;
    private javax.swing.JTextField txtCapV;
    private javax.swing.JTextField txtCapW;
    private javax.swing.JTextField txtCapX;
    private javax.swing.JTextField txtCapY;
    private javax.swing.JTextField txtCapZ;
    private javax.swing.JTextField txtCharacters;
    private javax.swing.JTextField txtColon;
    private javax.swing.JTextField txtComma;
    private javax.swing.JTextField txtCurlyL;
    private javax.swing.JTextField txtCurlyR;
    private javax.swing.JTextField txtDollar;
    private javax.swing.JTextField txtEqual;
    private javax.swing.JTextField txtExclamation;
    private javax.swing.JTextField txtForeSlash;
    private javax.swing.JTextField txtGreater;
    private javax.swing.JTextField txtHash;
    private javax.swing.JTextField txtHyphen;
    private javax.swing.JTextField txtLanguageName;
    private javax.swing.JTextField txtLeftP;
    private javax.swing.JTextField txtLess;
    private javax.swing.JTextField txtLine;
    private javax.swing.JTextField txtLowA;
    private javax.swing.JTextField txtLowB;
    private javax.swing.JTextField txtLowC;
    private javax.swing.JTextField txtLowD;
    private javax.swing.JTextField txtLowE;
    private javax.swing.JTextField txtLowF;
    private javax.swing.JTextField txtLowG;
    private javax.swing.JTextField txtLowH;
    private javax.swing.JTextField txtLowI;
    private javax.swing.JTextField txtLowJ;
    private javax.swing.JTextField txtLowK;
    private javax.swing.JTextField txtLowL;
    private javax.swing.JTextField txtLowM;
    private javax.swing.JTextField txtLowN;
    private javax.swing.JTextField txtLowO;
    private javax.swing.JTextField txtLowP;
    private javax.swing.JTextField txtLowQ;
    private javax.swing.JTextField txtLowR;
    private javax.swing.JTextField txtLowS;
    private javax.swing.JTextField txtLowT;
    private javax.swing.JTextField txtLowU;
    private javax.swing.JTextField txtLowV;
    private javax.swing.JTextField txtLowW;
    private javax.swing.JTextField txtLowX;
    private javax.swing.JTextField txtLowY;
    private javax.swing.JTextField txtLowZ;
    private javax.swing.JTextField txtNum0;
    private javax.swing.JTextField txtNum1;
    private javax.swing.JTextField txtNum2;
    private javax.swing.JTextField txtNum3;
    private javax.swing.JTextField txtNum4;
    private javax.swing.JTextField txtNum5;
    private javax.swing.JTextField txtNum6;
    private javax.swing.JTextField txtNum7;
    private javax.swing.JTextField txtNum8;
    private javax.swing.JTextField txtNum9;
    private javax.swing.JTextField txtPercent;
    private javax.swing.JTextField txtPeriod;
    private javax.swing.JTextField txtPlus;
    private javax.swing.JTextField txtQuestion;
    private javax.swing.JTextField txtQuote;
    private javax.swing.JTextField txtRightP;
    private javax.swing.JTextField txtSemi;
    private javax.swing.JTextField txtSpace;
    private javax.swing.JTextField txtTextLength;
    private javax.swing.JTextField txtTilde;
    private javax.swing.JTextField txtUnderscore;
    private javax.swing.JTextField txtUpArrow;
    // End of variables declaration//GEN-END:variables
}
