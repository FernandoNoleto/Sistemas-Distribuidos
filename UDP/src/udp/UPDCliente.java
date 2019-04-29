package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author fernando
 */
public class UPDCliente {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket cliente = new DatagramSocket();
        
        
        InetAddress enderecoIP = InetAddress.getByName("127.0.0.1");
        byte[] recebimento = new byte[1024];
        byte[] envio = new byte[1024];
        
        System.out.println("Informe a mensagem de envio:. ");
        String texto = entrada.readLine();
        envio = texto.getBytes();
        
        DatagramPacket pacoteEnvio = new DatagramPacket(envio, envio.length, enderecoIP, 9876);
        cliente.send(pacoteEnvio);
        
        DatagramPacket pacoteRecebimento = new DatagramPacket(recebimento, recebimento.length);
        cliente.receive(pacoteRecebimento);
        
    }
    
}
