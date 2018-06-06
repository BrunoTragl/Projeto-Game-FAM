/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public class JogadorDTO implements Serializable{
    public String Nome, IP, Porta;
    public int Id;
    public PersonagemDTO Personagem;
}
