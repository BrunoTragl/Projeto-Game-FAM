package Servidor;


import ConexaoBanco.ConexaoSQLite;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Tratamento extends Thread {

    private static ConexaoSQLite conexaoSQLite = new ConexaoSQLite();

    private static ObjetaoSocket objetaoSocket = new ObjetaoSocket();

    private final Socket socket;

    public Tratamento(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            ObjectInputStream ois;
            ObjectOutputStream oos;
            // variavel verificar o objeto
            String verificar,   mensagem="";
            // instanciar o objetao
            ObjetaoSocket objetaoSocket = new ObjetaoSocket();
            // atribuir a variavel resposta os dados do objetão
          
// criar um input para verificar os dados do jogador
            ois = new ObjectInputStream(socket.getInputStream());
            // verifica se a mensagen do cliete
            verificar = (String) ois.readObject();
            
            // se a mensagen do cliente for objetao significa que os dados do jogo foram preenchidos de um dos clientes
            // sendo assim resposta recebe o objetao e retorna os dados ao cliente 
            if (verificar.equalsIgnoreCase("objetao")) {
          oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            // enviando resposta com objeto ao cliente
            oos.writeObject(objetaoSocket);
            
            
                mensagem ="enviou";
  }
            else
            {
         
            }
        
            
                   oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
         oos.writeObject(mensagem);
                    
                    
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(String.format("Erro: %s",
                    e.getLocalizedMessage()));
        }
    }
}
