package Interface;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author fernando
 */
public class Main extends javax.swing.JFrame {

    JFileChooser FC;
    
    public Main() {
        initComponents();
        centerFrame();
        new Servidor().setVisible(true);
    }
    
    private void centerFrame() {

        Dimension windowSize = getSize();
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        setLocation(dx, dy);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Enviar_Button = new javax.swing.JButton();
        Drag_and_Drop_Label = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        Abrir_Imagem_Menu_Item = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Enviar Imagens");

        Enviar_Button.setText("Enviar");
        Enviar_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enviar_ButtonActionPerformed(evt);
            }
        });

        Drag_and_Drop_Label.setBackground(java.awt.SystemColor.activeCaption);
        Drag_and_Drop_Label.setText("              Arraste e solte uma imagem aqui");
        Drag_and_Drop_Label.setToolTipText("Arraste e solte uma imagem aqui");

        Menu.setText("Arquivo");

        Abrir_Imagem_Menu_Item.setText("Abrir imagem");
        Abrir_Imagem_Menu_Item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Abrir_Imagem_Menu_ItemActionPerformed(evt);
            }
        });
        Menu.add(Abrir_Imagem_Menu_Item);

        jMenuBar1.add(Menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Enviar_Button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Drag_and_Drop_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Drag_and_Drop_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Enviar_Button)
                .addContainerGap())
        );

        Enviar_Button.getAccessibleContext().setAccessibleName("Enviar_Button");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void Abrir_Imagem_Menu_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Abrir_Imagem_Menu_ItemActionPerformed
        FC = new JFileChooser();
        FileFilter filtro = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        FC.setFileFilter(filtro);
        int RESPONSE = FC.showOpenDialog(Main.this);
        if (RESPONSE == JFileChooser.APPROVE_OPTION){
            Drag_and_Drop_Label.setIcon(new ImageIcon(FC.getSelectedFile().toString()));
            Drag_and_Drop_Label.setText("");
        }
    }//GEN-LAST:event_Abrir_Imagem_Menu_ItemActionPerformed

    private void Enviar_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enviar_ButtonActionPerformed


        BufferedImage img;
        try {
            img = ImageIO.read(new File(FC.getSelectedFile().toString()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();        
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            byte[] buffer = baos.toByteArray();

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
            System.out.println(buffer.length);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, IPAddress, 2000);

            clientSocket.send(packet);
        } catch (IOException ex) {
            System.err.println("ERRO: "+ex);
        }
        
    }//GEN-LAST:event_Enviar_ButtonActionPerformed

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir_Imagem_Menu_Item;
    private javax.swing.JLabel Drag_and_Drop_Label;
    private javax.swing.JButton Enviar_Button;
    private javax.swing.JMenu Menu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
