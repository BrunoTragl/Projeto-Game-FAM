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
import java.net.Socket;

/**
 *
 * @author Bruno
 */
public class Jogo extends Thread implements Runnable {

    private static int PortaServidor = 8000;

    private static String IPServidor = "127.168.0.1";
    private static final Utils utils = new Utils();
ObjetaoSocket objetaoSocket = new ObjetaoSocket();
    public static void main(String[] args) {
        //  JFrame janela;
        //Cenario cenario = new Cenario();
        //janela = cenario.SplashScreen();

        //janela.setVisible(true);
        // IPServidor = utils.getIP();
        // PortaServidor = utils.getPorta();
        
        
        try {

            //  JFrame janela;
            //Cenario cenario = new Cenario();
            //janela = cenario.SplashScreen();
            //janela.setVisible(true);
            // IPServidor = utils.getIP();
            // PortaServidor = utils.getPorta();
            // instaciando o objetao 
            ObjetaoSocket objetaoSocket = new ObjetaoSocket();
            // atribuir a variavel resposta os dados do objetão

            // atribuindo objeto a enviar objeto
            ObjetaoSocket enviarobjeto = objetaoSocket;

            // criando saida de dados       
            ObjectOutputStream oos;
            // criando entrada de dados
            ObjectInputStream ois;
            // criando coneção Socket cliente e sevidor
            Socket cliente = new Socket(IPServidor, PortaServidor);
            // atribuindo dado ao objeto mensagem para teste

            // criando envio       
            oos = new ObjectOutputStream(cliente.getOutputStream());
            // enviando o objeto

            oos.writeObject(enviarobjeto.autenticacao);
            oos.flush();
            System.out.println("enviei");

            // agora vou pegar o objeto do servidor enviado                  
            ois = new ObjectInputStream(cliente.getInputStream());

            Object verificar = ois.readObject();

            System.out.println("recebi");

            //fecho a conexao de saida
            ois.close();

            // fecho a conexao de entrada
            oos.close();
            System.exit(0);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(String.format("Erro: %s",
                    e.getLocalizedMessage()));

        }

    }
}