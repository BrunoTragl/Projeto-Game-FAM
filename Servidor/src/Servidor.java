
import ConexaoBanco.ObjetaoSocket;
import ConexaoBanco.*;
import Entidades.*;
import java.io.*;
import java.net.*;

public class Servidor {

    // O soquete do servidor.
    private static ServerSocket serverSocket = null;
    // O soquete do cliente .
    private static Socket clienteSocket = null;
    // Objeto para manipular dados do banco de dados
    private static ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
    // Dados em que o servidor está conectado
    private static int PortaServidor = 0;
    private static String IPServidor = "";
    // Objeto que será usado para trafegar dados pela rede
    private static ObjetaoSocket objetaoSocket = new ObjetaoSocket();
    
    private static Utils utils = new Utils();
    // aqui estou definindo 5 jogadores
    private static final int Quantidadedejogador = 2;
    private static final clientThread[] threads = new clientThread[Quantidadedejogador];

    public static void main(String args[]) {
        //Exemplo de utilização do objeto conexaoSQLite
        //PersonagemDTO personagem1 = conexaoSQLite.SelecionarPersonagemPeloId(1);
        
        //Pega IP e Porta do servidor
        IPServidor = utils.getIP();
        PortaServidor = utils.getPorta();
        
        if (args.length < 1) {
            System.out
                    .println("porta conectada " + PortaServidor);
        } else {
            PortaServidor = Integer.valueOf(args[0]).intValue();
        }

        try {
            serverSocket = new ServerSocket(PortaServidor);
        } catch (IOException e) {
            System.out.println(e);
        }

        /*
   
      Crie um soquete de cliente para cada conexão e passe-o para um novo cliente
     * 
         */
        while (true) {
            try {
                clienteSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < Quantidadedejogador; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clienteSocket, threads)).start();
                        break;
                    }
                }
                if (i == Quantidadedejogador) {
                    PrintStream saida = new PrintStream(clienteSocket.getOutputStream());
                    saida.println("Servidor ocupado tente mais tarde");
                    saida.close();
                    clienteSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

class clientThread extends Thread {

    //ler dados
    private DataInputStream entrada = null;
    //metodo de saida de dados
    private PrintStream saida = null;

    private Socket clienteSocket = null;
    private final clientThread[] threads;
    private int Quantidadedejogador;

    public clientThread(Socket clienteSocket, clientThread[] threads) {
        this.clienteSocket = clienteSocket;
        this.threads = threads;
        Quantidadedejogador = threads.length;
    }

    @Override
    public void run() {
        int Quantidadedejogador = this.Quantidadedejogador;
        clientThread[] threads = this.threads;

        try {
            /*
       * Crie fluxos de entrada e saída para este cliente.
             */
            entrada = new DataInputStream(clienteSocket.getInputStream());
            saida = new PrintStream(clienteSocket.getOutputStream());
            saida.println("Digite seu nome.");
            String name = entrada.readLine().trim();
            saida.println("nome do jogador: " + name);
            for (int i = 0; i < Quantidadedejogador; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].saida.println("o jogador: " + name +" entrou no jogo");
                }
            }
            while (true) {
                String line = entrada.readLine();
                if (line.startsWith("/quit")) {
                    break;
                }
                for (int i = 0; i < Quantidadedejogador; i++) {
                    if (threads[i] != null) {
                        threads[i].saida.println("<" + name + "&gr; " + line);
                    }
                }
            }
            for (int i = 0; i < Quantidadedejogador; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].saida.println("o jogador " + name
                            + " saiu do jogo ");
                }
            }
            saida.println("thau " + name + " ");

            /*
       * Limpar. Definir a variável de thread atual como null para que um novo jogador 
       * possa ser aceito pelo servidor.
             */
            for (int i = 0; i < Quantidadedejogador; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }

            /*
       * 
  Fechando o fluxo de saída, feche o fluxo de entrada, feche o soquete.
             */
            entrada.close();
            saida.close();
            clienteSocket.close();
        } catch (IOException e) {
        }
    }
}
