package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author fernando
 */
public class UDPServer {

    /**
     * @param args the command line arguments
     * @throws java.net.SocketException
     */
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket servidor = new DatagramSocket(9876);
        byte[] recebimento = new byte[1024];
        byte[] envio = new byte[1024];
        
        System.out.println("Aguardando mensagens de clientes...");
        
        while(true){
            DatagramPacket pacoteRecebimento = new DatagramPacket(recebimento, recebimento.length);
            servidor.receive(pacoteRecebimento);
            String texto = new String(pacoteRecebimento.getData());
            System.out.println("Mensagem recebida: "+texto);
            
            
            InetAddress enderecoIP = pacoteRecebimento.getAddress();
            int porta = pacoteRecebimento.getPort();
            String textoAlterado = texto.toUpperCase();
            envio = textoAlterado.getBytes();
            DatagramPacket pacoteEnvio = new DatagramPacket(envio, envio.length, enderecoIP, porta);
            servidor.send(pacoteEnvio);
            
        }
        
    }
    
}
