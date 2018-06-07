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
public class HistoricoBatalhaDTO implements Serializable {
    public int IdHistoricoBatalha =0, IdBatalha=0, IdJogadorAtaque=0, IdJogadorDefesa=0, DadosAtaque=0, DadosDefesa=0, TirarQtdeVida=0;
    public boolean AtaqueGanhou=false;
}
