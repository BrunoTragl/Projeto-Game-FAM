package Servidor;


import ConexaoBanco.*;
import Entidades.*;
import java.io.*;
import java.net.*;
import java.io.IOException;
 import java.net.ServerSocket;
  import java.net.Socket;


public class Servidor {
 private static int PortaServidor = 8000;

    private static Utils utils = new Utils();
    
    
    public static void main(String[] args) {
        
        
     try {
         
       // PortaServidor = utils.getPorta();
          
       ServerSocket sServidor = new ServerSocket(PortaServidor);
       while (true) {
         Socket sCliente = sServidor.accept();
         new Tratamento(sCliente).start();
       }
     } catch (IOException e) {
      System.out.println(String.format("Erro: %s",e.getLocalizedMessage()));
     }
   }
 }


