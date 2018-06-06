package Servidor;

import Entidades.*;
import java.io.Serializable;

public class ObjetaoSocket implements Serializable{
    public String mensagem;
    public JogadorDTO jogador, jogadorAdversario;
    public PersonagemDTO personagem;
    public BatalhaDTO batalha;
    public HistoricoBatalhaDTO historicoBatalha;
}
