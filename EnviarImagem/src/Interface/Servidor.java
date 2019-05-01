package Interface;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author fernando
 */
public class Servidor extends javax.swing.JFrame{
    
    static JLabel imagem;
    
    public Servidor(){
        initUI();
    }

    public static void main(String[] args) throws SocketException, IOException {
             
        Main main = new Main();
        main.setVisible(true);
        
        
        /*------------------------------------------------------------*/
        
        
        DatagramSocket servidor = new DatagramSocket(2000);
        byte[] recebimento = new byte[1024];
        byte[] envio = new byte[1024];
        
        System.out.println("Aguardando mensagens de clientes...");
        
        while(true){
            DatagramPacket pacoteRecebimento = new DatagramPacket(recebimento, recebimento.length);
            servidor.receive(pacoteRecebimento);
            String texto = new String(pacoteRecebimento.getData());
            
//            System.out.println("Mensagem recebida: "+texto);
            
            imagem.setIcon(new ImageIcon(recebimento));
            imagem.setText("");
            
//            InetAddress enderecoIP = pacoteRecebimento.getAddress();
//            int porta = pacoteRecebimento.getPort();
//            String textoAlterado = texto.toUpperCase();
//            envio = textoAlterado.getBytes();
//            DatagramPacket pacoteEnvio = new DatagramPacket(envio, envio.length, enderecoIP, porta);
//            servidor.send(pacoteEnvio);
//            imagem.setIcon(new ImageIcon(texto));
            
        }


    }
    
    
    private void initUI(){
        JFrame frame = new JFrame();
        frame.setTitle("Servidor");
        frame.setSize(600, 400);
        imagem = new JLabel();
        imagem.setText("Aguardando mensagens de clientes...");
        frame.add(imagem);
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
    }
    
    

}
