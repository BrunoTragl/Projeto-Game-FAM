/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenario;

import Jogo.Jogador;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import sun.launcher.resources.launcher;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bruno
 */
public class Cenario extends Thread {

    private String titulo = "NATURALMON";
    private JFrame janela;
    private Jogador jogador;
    private Timer timer = null;
    private int count = 0;
    private String seila = "0";
    private int contador = 0;
    private JProgressBar jProgressBar = new JProgressBar();
    public Cenario() {
        //Quando cenario é instanciado, inclui o titulo no topo da janela
        this.janela = new JFrame(titulo);
        //Quando cenario é instanciado, quando é fechado encerra a aplicação
        this.janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //Métodos Criadores de Janela
    public JFrame SplashScreen() {
        CriarNovaJanela(660, 685);
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        ImageIcon imagemSplashScreen = new ImageIcon(Cenario.class.getResource("SplashScreenMontada.png"));
        JLabel labelImagem = new JLabel(imagemSplashScreen);
        painel.add(labelImagem, BorderLayout.NORTH);

        

//determina o tamanho do progressbar
        jProgressBar.setBounds(new Rectangle(20, 20, 100, 20));
//determina valor minimo
        jProgressBar.setMinimum(0);
//determina valor maximo
        jProgressBar.setMaximum(100);
//Faz aparecer o valor em porcentagem
        jProgressBar.setStringPainted(true);
//determina o quanto a sua progressbar esta preenchida
        painel.add(jProgressBar, BorderLayout.CENTER);
        Thread thread = new Thread() {
            public void run() {
                FazAlgumaCoisa();
            }
        };
        thread.start();
        JLabel seilala = CriarLabel(this.seila, "", 20, true);
        painel.add(seilala, BorderLayout.WEST);

        JButton iniciarJogo = CriarBotao("INICIAR JOGO", "", 15, true, Color.gray);
        iniciarJogo.addActionListener(new ActionListener() {
            //Quando botão for clicado
            @Override
            public void actionPerformed(ActionEvent ae) {
                CadastraJogador();
            }
        });

        iniciarJogo.setFocusPainted(false);
        painel.add(iniciarJogo, BorderLayout.SOUTH);
        this.janela.add(painel);
        this.janela.setVisible(true);
        return janela;
    }

    public void FazAlgumaCoisa() {
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 5000);
    }

    class RemindTask extends TimerTask {

        public void run() {
            acrescentaSeiLa();
            int contador2 = 0;
            int retornaContador2 = 0;
            while(retornaContador2 < 100){
                retornaContador2 = retornaContador();
                jProgressBar.setValue(retornaContador2);
                System.out.println(retornaContador2);
                int fazEsperarWhile = 0;
                contador2++;
                while(fazEsperarWhile < 10000){
                    fazEsperarWhile++;
                }
            }
            
        }
    }
    
    public String acrescentaSeiLa() {
        this.contador++;
        return this.seila += Integer.toString(this.contador);
    }

    public int retornaContador() {
        return this.contador++;
    }

    public JFrame CadastraJogador() {
        //Remove todos os componentes da janela e redimensiona para incluir novos componentes
        CriarNovaJanela(450, 350);

        JPanel painel = new JPanel();
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //set layout formato grid com 6 colunas e 1 linha
        painel.setLayout(new GridLayout(9, 1));

        //Cria e adiciona uma label com infos para o jogador na janela do jogo
        painel.add(CriarLabel("CADASTRO", "", 15, true));

        //Cria e adiciona uma label para o campo nome na janela do jogo
        painel.add(CriarLabel("NOME", "", 12, false));
        //Cria e adiciona campo NOME na janela do jogo
        JTextField campoNome = CriarCampoTexto(100, 15);
        painel.add(campoNome);

        //Cria e adiciona uma label para o campo IP na janela do jogo
        painel.add(CriarLabel("IP", "", 12, false));
        //Cria e adiciona campo Nome na janela do jogo
        JTextField campoIP = CriarCampoTexto(100, 15);
        painel.add(campoIP);

        //Cria e adiciona uma label para o campo PORTA na janela do jogo
        painel.add(CriarLabel("PORTA", "", 12, false));
        //Cria e adiciona campo Nome na janela do jogo
        JTextField campoPorta = CriarCampoTexto(100, 15);
        painel.add(campoPorta);

        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new BorderLayout());
        painelBotao.setBorder(new EmptyBorder(10, 0, 0, 0));

        //Cria botão
        JButton botaoAcessoJogo = CriarBotao("ACESSAR JOGO", "", 15, true, Color.getHSBColor(29, 90, 214));
        //Incluo ação quando botão for clicado
        botaoAcessoJogo.addActionListener(new ActionListener() {
            //Quando botão for clicado
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nome = campoNome.getText(), IP = campoIP.getText(), porta = campoPorta.getText();
                if (nome.equals("") || IP.equals("") || porta.equals("")) {
                    Mensagem("Para continuar é necessário informar todos os campos!");
                    return;
                }
                if (!validaIP(IP)) {
                    Mensagem("Informe um número de IP válido!");
                    return;
                }
                if (!validaPorta(porta)) {
                    Mensagem("Informe um número de PORTA válido!");
                    return;
                }
                setJogador(nome, IP, Integer.parseInt(porta));
                SelecionarPersonagem();
            }
        });
        //botaoAcessoJogo.setBorder(new EmptyBorder(5,5,5,5));
        //Cria e adiciona um botão para imputar os dados do jogador no sistema
        painelBotao.add(botaoAcessoJogo, BorderLayout.CENTER);
        painel.add(painelBotao);

        this.janela.add(painel);
        return this.janela;
    }

    public JFrame SelecionarPersonagem() {
        //Remove todos os componentes da janela e redimensiona para incluir novos componentes
        CriarNovaJanela(600, 720);
        //Set Layout 5 linhas e 1 coluna
        this.janela.setLayout(new FlowLayout());
        this.janela.add(CriarLabel("SELECIONE O SEU PERSONAGEM", "", 20, true));
        this.janela.add(CriarPersonagem(new Personagem("FLOSTER", "GELO", 10, 35, 155, "flosterFrente.png")));
        this.janela.add(CriarPersonagem(new Personagem("VULKYAN", "VULCÃO", 7, 30, 170, "vulkyanFrente.png")));
        this.janela.add(CriarPersonagem(new Personagem("ORION", "AGUA", 12, 40, 200, "orionFrente.png")));
        this.janela.add(CriarPersonagem(new Personagem("SEEDER", "PLANTA", 7, 30, 170, "seederFrente.png")));
        return this.janela;
    }

    //Métodos Auxiliadores
    private JTextField CriarCampoTexto(int width, int height) {
        JTextField campo = new JTextField();
        campo.setSize(width, height);
        return campo;
    }

    private JLabel CriarLabel(String texto, String nomeFonte, int tamanhoFonte, boolean negrito) {
        JLabel label = new JLabel();
        label.setText(texto);
        Font fonte = new Font(nomeFonte.equals("") ? "" : nomeFonte, negrito ? Font.BOLD : 0, tamanhoFonte == 0 ? 10 : tamanhoFonte);
        label.setFont(fonte);
        return label;
    }

    private JButton CriarBotao(String textoBotao, String nomeFonte, int tamanhoFonte, boolean negrito, Color Cor) {
        JButton botao = new JButton(textoBotao);
        Font fonte = new Font(nomeFonte.equals("") ? "" : nomeFonte, negrito ? Font.BOLD : 0, tamanhoFonte == 0 ? 10 : tamanhoFonte);
        botao.setFont(fonte);
        botao.setBackground(Cor);
        return botao;
    }

    private JButton CriarBotao(String textoBotao) {
        JButton botao = new JButton(textoBotao);
        return botao;
    }

    private void CriarNovaJanela(int width, int height) {
        //remove todos os componentes dentro da janela atual
        this.janela.getContentPane().removeAll();
        //redimensiona o tamanho da janela
        this.janela.setSize(width, height);
        //pega as dimensões do monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //calcula o meio do monitor
        int localeWidth = (dim.width / 2) - (this.janela.getSize().width / 2);
        int localHeight = (dim.height / 2) - (this.janela.getSize().height / 2);
        //coloca a janela no meio do monitor
        this.janela.setLocation(localeWidth, localHeight);
    }

    private void Mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this.janela, mensagem);
    }

    private JPanel CriarPersonagem(Personagem personagem) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBorder(new EmptyBorder(0, 2, 2, 0));
        painel.setPreferredSize(new Dimension(500, 155));

        JPanel painelImagemInfos = new JPanel();
        painelImagemInfos.setLayout(new BorderLayout());
        painelImagemInfos.setSize(200, 300);

        ImageIcon imagemPersonagem = new ImageIcon(Cenario.class.getResource(personagem.getImagem()));
        JLabel imagemLabel = new JLabel(imagemPersonagem);
        imagemLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        painelImagemInfos.add(imagemLabel, BorderLayout.LINE_START);

        JPanel painelInfos = new JPanel();
        painelInfos.setLayout(new GridLayout(5, 2));
        painelInfos.add(new JLabel("NOME:  " + personagem.getNome()));
        painelInfos.add(new JLabel("PODER:  " + personagem.getTipoPoder()));
         painelInfos.add(new JLabel("VIDA:  " + Integer.toString(personagem.getVida()) + "HP"));
        painelInfos.add(new JLabel("GOLPE BASICO:  " + Integer.toString(personagem.getAtaqueBasico()) + "HP"));
        painelInfos.add(new JLabel("GOLPE ESPECIAL:  " + Integer.toString(personagem.getAtaqueEspecial()) + "HP"));
        painelImagemInfos.add(painelInfos, BorderLayout.CENTER);

        String textoBotao = "<html>E<br/>S<br/>C<br/>O<br/>L<br/>H<br/>E<br/>R</html>";

        JButton btnSelecionarPersonagem = CriarBotao("Escolher", "", 12, true, Color.getHSBColor(40, 120, 214));

        btnSelecionarPersonagem.addActionListener(new ActionListener() {
            //Quando botão for clicado
            @Override
            public void actionPerformed(ActionEvent ae) {
                setPersonagem(personagem);
                //Criar janela para aguardar jogador
            }
        });
        btnSelecionarPersonagem.setText(textoBotao);
        painelImagemInfos.add(btnSelecionarPersonagem, BorderLayout.LINE_END);

        painel.add(painelImagemInfos, BorderLayout.PAGE_START);
        painel.add(new JSeparator(), BorderLayout.PAGE_END);
        return painel;
    }

    private void setJogador(String Nome, String IP, int Porta) {
        this.jogador = new Jogador(Nome, IP, Porta);
    }

    private void setPersonagem(Personagem Personagem) {
        this.jogador.setPersonagem(Personagem);
    }

    private boolean validaIP(String IP) {
        try {
            if (IP == null || IP.isEmpty() || IP.equals("")) {
                return false;
            }

            String[] dividindoIPemPartes = IP.split("\\.");
            if (dividindoIPemPartes.length < 4 && dividindoIPemPartes.length > 4) {
                return false;
            }

            for (String parte : dividindoIPemPartes) {
                int num = Integer.parseInt(parte);
                if (num < 0 && num > 256) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validaPorta(String Porta) {
        try {
            int porta = Integer.parseInt(Porta);
            if (porta < 1 && porta > 99999) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
