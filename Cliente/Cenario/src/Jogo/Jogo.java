/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;
import cenario.Utils;
import java.io.IOException;
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
  

import java.net.ServerSocket;
import java.net.Socket;

import java.net.Socket;
 import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
/**
 *
 * @author Bruno
 */
public class Jogo {
    
    private static int PortaServidor = 8000;
    
        private static String IPServidor = "127.168.0.1";
            private static final Utils utils = new Utils();

    public static void main(String[] args){
      //  JFrame janela;
        //Cenario cenario = new Cenario();
        //janela = cenario.SplashScreen();
    
        //janela.setVisible(true);
                      // IPServidor = utils.getIP();
       // PortaServidor = utils.getPorta();
        
           try {
       String mensagem;
                ObjetaoSocket objetaoSocket = new ObjetaoSocket();
            // atribuir a variavel resposta os dados do objetão
            ObjetaoSocket resposta = objetaoSocket;
       
       
       
       
       ObjectOutputStream oos;
       ObjectInputStream ois;
       Socket cliente = new Socket(IPServidor,PortaServidor );
       oos = new ObjectOutputStream(cliente.getOutputStream());
       oos.flush();
       
       // se a conexao for feita mando uma mensagem para com a palavra objtao 
       oos.writeObject("objetao");
       
       ois = new ObjectInputStream(cliente.getInputStream());
       resposta = (ObjetaoSocket) ois.readObject();

   
       
            ois = new ObjectInputStream(cliente.getInputStream());
       mensagem = (String) ois.readObject();
       JOptionPane.showMessageDialog(null, mensagem);
       
       ois.close();
       oos.close();
       System.exit(0);
     } catch (IOException | ClassNotFoundException e) {
       System.out.println(String.format("Erro: %s",
               e.getLocalizedMessage()));
     }
   }
 }

        
        
        
        
    
