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
    public int IdHistoricoBatalha, IdBatalha, IdJogadorAtaque, IdJogadorDefesa, DadosAtaque, DadosDefesa, TirarQtdeVida;
    public boolean AtaqueGanhou;
}
