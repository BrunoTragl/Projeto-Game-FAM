/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBanco;

import Entidades.*;
import java.io.File;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Bruno
 */
public class ConexaoSQLite {

    public ConexaoSQLite() {

    }

    public static Connection ConectarNoBanco() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String pathSQLFile = new File(".").getAbsolutePath();
            pathSQLFile = pathSQLFile.replace("\\.", "");
            pathSQLFile += "\\src\\ConexaoBanco\\JogoFAM.db";
            c = DriverManager.getConnection("jdbc:sqlite:" + pathSQLFile);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public static ResultSet PesquisarNoBanco(String Query) {
        Connection conn = ConectarNoBanco();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            return rs;
        }
    }

    public static boolean FazerUmaOperacaoNoBanco(String Query) {
        boolean retorno = false;
        Connection conn = ConectarNoBanco();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            retorno = stmt.execute(Query);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return retorno;
        } finally {
            return retorno;
        }
    }

    public static PersonagemDTO CriarPersonagem(String Nome, int Vida, int AtaqueBasico, int AtaqueEspecial, String TipoPoder) {
        PersonagemDTO personagem = new PersonagemDTO();
        personagem.Nome = Nome;
        personagem.Vida = Vida;
        personagem.AtaqueBasico = AtaqueBasico;
        personagem.AtaqueEspecial = AtaqueEspecial;
        personagem.TipoPoder = TipoPoder;
        FazerUmaOperacaoNoBanco("INSERT INTO Personagem(Nome, Vida, AtaqueBasico, AtaqueEspecial, TipoPoder) VALUES (" + Nome + "," + Vida + "," + AtaqueBasico + "," + AtaqueEspecial + "," + TipoPoder + ")");
        return personagem;
    }

    public static PersonagemDTO SelecionarPersonagemPeloId(int Id) {
        PersonagemDTO personagem = new PersonagemDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Personagem WHERE Id = " + Integer.toString(Id));
            while (rs.next()) {
                personagem.Id = rs.getInt("Id");
                personagem.Nome = rs.getString("Nome");
                personagem.AtaqueBasico = rs.getInt("AtaqueBasico");
                personagem.AtaqueEspecial = rs.getInt("AtaqueEspecial");
                personagem.ImagemAtaqueBasico = rs.getString("ImagemAtaqueBasico");
                personagem.ImagemAtaqueEspecial = rs.getString("ImagemAtaqueEspecial");
                personagem.TipoPoder = rs.getString("TipoPoder");
                personagem.TipoPoder = rs.getString("Vida");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return personagem;
    }

    public static PersonagemDTO SelecionarPersonagemPeloNome(String Nome) {
        PersonagemDTO personagem = new PersonagemDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Personagem WHERE Nome = " + Nome);
            while (rs.next()) {
                personagem.Id = rs.getInt("Id");
                personagem.Nome = rs.getString("Nome");
                personagem.AtaqueBasico = rs.getInt("AtaqueBasico");
                personagem.AtaqueEspecial = rs.getInt("AtaqueEspecial");
                personagem.ImagemAtaqueBasico = rs.getString("ImagemAtaqueBasico");
                personagem.ImagemAtaqueEspecial = rs.getString("ImagemAtaqueEspecial");
                personagem.TipoPoder = rs.getString("TipoPoder");
                personagem.TipoPoder = rs.getString("Vida");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return personagem;
    }

    public static boolean CriarJogador(ObjetaoSocket obj) {
        String query = "INSERT INTO Jogador(Nome, IP, Porta) VALUES (" +
                obj.jogador.Nome + "," + obj.jogador.IP + "," + obj.jogador.Porta + ")";
        return FazerUmaOperacaoNoBanco(query);
    }

    public static JogadorDTO SelecionarJogadorPeloId(int Id) {
        JogadorDTO jogador = new JogadorDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Jogador WHERE Id = " + Id);
            while (rs.next()) {
                jogador.Id = rs.getInt("Id");
                jogador.Personagem = SelecionarPersonagemPeloId(rs.getInt("IdPersonagem"));
                jogador.Nome = rs.getString("Nome");
                jogador.IP = rs.getString("IP");
                jogador.Porta = rs.getString("Porta");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return jogador;
    }

    public static JogadorDTO SelecionarJogadorPeloNome(String Nome) {
        JogadorDTO jogador = new JogadorDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Jogador WHERE Nome = " + Nome);
            while (rs.next()) {
                jogador.Id = rs.getInt("Id");
                jogador.Personagem = SelecionarPersonagemPeloId(rs.getInt("IdPersonagem"));
                jogador.Nome = rs.getString("Nome");
                jogador.IP = rs.getString("IP");
                jogador.Porta = rs.getString("Porta");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return jogador;
    }

    public static BatalhaDTO CriarBatalha(int IdJogador1) {
        JogadorDTO jogador1 = SelecionarJogadorPeloId(IdJogador1);
        PersonagemDTO personagem = jogador1.Personagem;
        BatalhaDTO batalha = new BatalhaDTO();
        batalha.IdJogador1 = IdJogador1;
        batalha.VidaPersonagemJogador1 = personagem.getVida();
        FazerUmaOperacaoNoBanco("INSERT INTO Batalha(IdJogador1, VidaPersonagemJogador1) VALUES (" + batalha.IdJogador1 + "," + batalha.VidaPersonagemJogador1 + ")");
        return batalha;
    }
    
    public static JogadorDTO SelecionarBatalhaPeloId(int Id) {
        JogadorDTO jogador = new JogadorDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT * FROM Jogador WHERE Id = " + Id);
            while (rs.next()) {
                jogador.Id = rs.getInt("Id");
                jogador.Personagem = SelecionarPersonagemPeloId(rs.getInt("IdPersonagem"));
                jogador.Nome = rs.getString("Nome");
                jogador.IP = rs.getString("IP");
                jogador.Porta = rs.getString("Porta");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return jogador;
    }

    public static BatalhaDTO SelecionarUltimaBatalhaPeloIdJogador(int IdJogador) {
        BatalhaDTO batalha = new BatalhaDTO();
        ResultSet rs = null;
        try {
            rs = PesquisarNoBanco("SELECT TOP 1 * FROM Batalha WHERE IdJogador1 = " + IdJogador + " OR IdJogador2 = " + IdJogador + " OrderBy Id Desc");
            while (rs.next()) {
                batalha.IdBatalha = rs.getInt("Id");
                batalha.IdJogador1 = rs.getInt("IdJogador1");
                batalha.IdJogador2 = rs.getInt("IdJogador2");
                batalha.VidaPersonagemJogador1 = rs.getInt("VidaPersonagemJogador1");
                batalha.VidaPersonagemJogador2 = rs.getInt("VidaPersonagemJogador2");
                batalha.IdJogadorVencedor = rs.getInt("IdJogadorVencedor");
                batalha.IdJogadorPerdedor = rs.getInt("IdJogadorPerdedor");
            }
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return batalha;
    }
}
