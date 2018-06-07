package Servidor;

import ConexaoBanco.ConexaoSQLite;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            // crio conexao de entrada
            ObjectInputStream ois;

            // crio conexao de saida 
            ObjectOutputStream oos = null;

            // instanciar o objetao
            ObjetaoSocket objetaoSocket = new ObjetaoSocket();
            ObjetaoSocket objetorecebido = objetaoSocket;

            // instancio a classe para pegar o objetao
            ois = new ObjectInputStream(socket.getInputStream());

            //  crio uma variavel do tipo objeto para ler o objeto enviado pelo jogador
            objetorecebido = (ObjetaoSocket) ois.readObject();

                            // confirmando que foi recebido      
                System.out.println("recebido");
                
                // agora vou enviar o objeto novamente ao jogador     
                
                
                // criando envio       
                oos = new ObjectOutputStream(socket.getOutputStream());
                // enviando o objeto
                oos.writeObject(objetorecebido);
                oos.flush();


// imprimo os objetos recebidos    
            System.out.println("dado do objeto recebido " + objetorecebido);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(String.format("Erro: %s",
                    e.getLocalizedMessage()));
        }
    }
}
