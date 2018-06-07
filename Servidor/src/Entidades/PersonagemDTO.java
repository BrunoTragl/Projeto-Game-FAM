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
public class PersonagemDTO implements Serializable{
    public static String Nome=null, TipoPoder=null, ImagemAtaqueBasico=null, ImagemAtaqueEspecial=null;
    public static int Id=0, AtaqueBasico=0, AtaqueEspecial=0, Vida=0;
    
    public int getId(){
        return this.Id;
    }
    public String getNome(){
        return this.Nome;
    }
    public int getVida(){
        return this.Vida;
    }
}
