package server;

import java.awt.FileDialog;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReceiverClient extends Thread {

    Socket socket;

    public ReceiverClient() {
        addr = JOptionPane.showInputDialog("Enter IP address of Receiver: ");
        name = "";
        if (addr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter IP address of Receiver", "Message", 1);
        } else {

            try {
                FileDialog filedialog = new FileDialog(new JFrame(), "select the File", 0);
                filedialog.setFile("*.*");
                filedialog.show();
                if (filedialog.getFile() != null) {
                    name = (new StringBuilder()).append(filedialog.getDirectory()).append(filedialog.getFile()).toString();
                }
                System.out.println((new StringBuilder()).append("name").append(name).toString());
                File file = new File(name);

                if (file.exists()) {
                    if (JOptionPane.showConfirmDialog(null, "Confirm the host " + addr) == 0) {
                        //String dir=name.substring(name.lastIndexOf("\\")+1,name.lastIndexOf("."));
                        System.out.println("dir===" + name);
                        byte abyte1[] = new byte[name.getBytes().length];
                        socket = new Socket(addr, 2222);
                        System.out.println("dir123===" + name);
                        abyte1 = name.getBytes();

                        java.io.OutputStream outputstream = socket.getOutputStream();
                        outputstream.write(abyte1);

                        FileInputStream fileinputstream = new FileInputStream(file);
                        byte abyte0[] = new byte[fileinputstream.available()];
                        outputstream = socket.getOutputStream();
                        for (int i = 0; i < abyte0.length; i++) {
                            abyte0[i] = (byte) fileinputstream.read();
                        }
                        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                        bytearrayoutputstream.write(abyte0, 0, abyte0.length);
                        bytearrayoutputstream.writeTo(outputstream);

                        JOptionPane.showMessageDialog(null, "File Sending in process", "Message", 1);
                        socket.close();

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "File not found", "Message", 1);

                }
            } catch (UnknownHostException unknownhostexception) {
                JOptionPane.showMessageDialog(null, "Host not found", "Message", 1);
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unable to send the file", "Message", 1);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e, "Message", 1);
            }
        }
    }
    String addr;
    String name;
}
