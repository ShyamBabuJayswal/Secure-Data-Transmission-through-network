package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ReceiverServer {

    static String dir = "";
    static String dir2 = "";
    public static boolean serverstatus;
    ServerSocket serversocket;

    public ReceiverServer() {
        boolean flag = false;
        boolean flag1 = false;
        byte abyte0[] = new byte[0x989680];


        try {
            while (true) {
                serversocket = new ServerSocket(2222);
                Socket socket = serversocket.accept();
                System.out.println("After Socket Accept");
                InputStream inputstream = socket.getInputStream();
                byte b[] = new byte[3000];
                inputstream.read(b);

                String dir3 = new String(b).trim();
                String dir1 = dir3.substring(dir3.lastIndexOf("\\") + 1, dir3.lastIndexOf("."));
                System.out.println("dir1==" + dir1);

                File fi = new File("WorkSpace");
                fi.mkdir();
                dir2 = "WorkSpace/" + dir1;
                fi = new File(dir2);
                System.out.println(fi.mkdir());
                System.out.println("after creation");
                dir = dir1;

                System.out.println("dir2===========" + dir2);
                FileOutputStream fileoutputstream = new FileOutputStream(dir2 + "/" + dir3.substring(dir3.lastIndexOf("\\") + 1, dir3.length()));

                System.out.println("dir3===========" + dir2 + "/" + dir3.substring(dir3.lastIndexOf("\\") + 1, dir3.length()));
                int count = 0;
                int i = 0;
                for (i = inputstream.read(); i != -1; i = inputstream.read()) {
                    fileoutputstream.write(i);
                    fileoutputstream.flush();
                }

                System.out.println("i is==" + i);
                fileoutputstream.close();
                inputstream.close();
                System.out.println("End of Writing into file");
                JOptionPane.showMessageDialog(null, "File Received Successfully", "Message", 1);
                serversocket.close();


            }
        } catch (StringIndexOutOfBoundsException siob) {
            JOptionPane.showMessageDialog(null, "The File Name format Must be xxx_yyy", "Message", 1);
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Receiver Server Stopped", "Message", 1);
            serverstatus = false;
        }
    }

    public static void main(String args[]) {
        System.out.println("Server Starting");
        new ReceiverServer();
        System.out.println("Hello World!");
    }
}
