package Servidor;

import Entidades.JogadorDTO;
import Entidades.BatalhaDTO;
import Entidades.HistoricoBatalhaDTO;
import Entidades.PersonagemDTO;


import java.io.Serializable;


public class ObjetaoSocket implements Serializable {
    public String mensagem;
    public JogadorDTO jogador, jogadorAdversario;
    public PersonagemDTO personagem;
    public BatalhaDTO batalha;
    public HistoricoBatalhaDTO historicoBatalha;
}
