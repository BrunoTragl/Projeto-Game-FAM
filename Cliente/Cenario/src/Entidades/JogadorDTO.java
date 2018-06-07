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
    public String Nome=null, IP=null, Porta=null;
    public int Id=0;
    public PersonagemDTO Personagem;
}
