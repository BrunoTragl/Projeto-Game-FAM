/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;


import cenario.Personagem;
import cenario.Utils;
import java.util.Random;

/**
 *
 * @author Bruno
 */
public class Jogador {
    private String Id, Nome, IP;
    private int Porta;
    private Personagem Personagem;
    Utils util = new Utils();
    public Jogador(String Nome, String IP, int Porta){
        this.Id = util.generateKeyLetters();
        this.Nome = Nome;
        this.IP = IP;
        this.Porta = Porta;
    }
    public String getNome(){
        return this.Nome;
    }
    
    public Personagem getPersonagem(){
        return this.Personagem;
    }
    
    public void setPersonagem(Personagem Personagem){
        this.Personagem = Personagem;
    }
}
