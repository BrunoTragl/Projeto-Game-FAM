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
public class BatalhaDTO implements Serializable {
    public int IdBatalha, IdJogador1, VidaPersonagemJogador1, IdJogador2, VidaPersonagemJogador2, IdJogadorVencedor, IdJogadorPerdedor;
}
