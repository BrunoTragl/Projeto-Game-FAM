package Servidor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Bruno
 */
public class Utils {
    public String generateKeyLetters(){
        Random random = new Random();
        String KeyLetters = "";
        int quantidadeCaracteres = 10;
        int num = 0;
        while (quantidadeCaracteres > 0){
            KeyLetters += getLetter(random.nextInt(26));
            quantidadeCaracteres -= 1;
        }
        return KeyLetters;
    }
    public char getLetter(int num){
        boolean maiusculo = false;
        Random random = new Random();
        if(random.nextBoolean())
            maiusculo = true;
        char letter = 'a';
        switch(num){
            case 1: letter = maiusculo ? 'A' : 'a'; break;
            case 2: letter = maiusculo ? 'B' : 'b'; break;
            case 3: letter = maiusculo ? 'C' : 'c'; break;
            case 4: letter = maiusculo ? 'D' : 'd'; break;
            case 5: letter = maiusculo ? 'E' : 'e'; break;
            case 6: letter = maiusculo ? 'F' : 'f'; break;
            case 7: letter = maiusculo ? 'G' : 'g'; break;
            case 8: letter = maiusculo ? 'H' : 'h'; break;
            case 9: letter = maiusculo ? 'I' : 'i'; break;
            case 10: letter = maiusculo ? 'J' : 'j'; break;
            case 11: letter = maiusculo ? 'K' : 'k'; break;
            case 12: letter = maiusculo ? 'L' : 'l'; break;
            case 13: letter = maiusculo ? 'M' : 'm'; break;
            case 14: letter = maiusculo ? 'N' : 'n'; break;
            case 15: letter = maiusculo ? 'O' : 'o'; break;
            case 16: letter = maiusculo ? 'P' : 'p'; break;
            case 17: letter = maiusculo ? 'Q' : 'q'; break;
            case 18: letter = maiusculo ? 'R' : 'r'; break;
            case 19: letter = maiusculo ? 'S' : 's'; break;
            case 20: letter = maiusculo ? 'T' : 't'; break;
            case 21: letter = maiusculo ? 'U' : 'u'; break;
            case 22: letter = maiusculo ? 'V' : 'v'; break;
            case 23: letter = maiusculo ? 'W' : 'w'; break;
            case 24: letter = maiusculo ? 'Y' : 'y'; break;
            case 25: letter = maiusculo ? 'X' : 'x'; break;
            case 26: letter = maiusculo ? 'Z' : 'z'; break;
        }
        return letter;
    }
    public String getIP(){
        return "";
    }
    public int getPorta(){
        return 0;
    }
}
