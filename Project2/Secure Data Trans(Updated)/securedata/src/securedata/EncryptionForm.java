package securedata;

import enc.Tiny;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

public class EncryptionForm extends javax.swing.JFrame {

    

    public EncryptionForm() {
        initComponents();
    }

    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusDisplay = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Encryption Form");
        setResizable(false);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18));
        jLabel6.setText("Encryption");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century", 1, 12));
        jLabel1.setText("Select Data File");

        jButton1.setFont(new java.awt.Font("Century", 1, 12));
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century", 1, 12));
        jButton2.setText("Encrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century", 1, 12));

        statusDisplay.setColumns(20);
        statusDisplay.setEditable(false);
        statusDisplay.setRows(5);
        jScrollPane1.setViewportView(statusDisplay);

        jButton4.setFont(new java.awt.Font("Century", 1, 12));
        jButton4.setText("Close");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .add(191, 191, 191)
                .add(jButton2)
                .add(29, 29, 29)
                .add(jButton4))
                .add(layout.createSequentialGroup()
                .add(45, 45, 45)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .add(111, 111, 111)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 239, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(layout.createSequentialGroup()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton1))))
                .add(layout.createSequentialGroup()
                .add(210, 210, 210)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 472, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(30, 30, 30)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel1)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jButton1))
                .add(29, 29, 29)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jButton2)
                .add(jButton4))
                .add(37, 37, 37)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 32, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 218, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));

        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        FileDialog filedialog = new FileDialog(new JFrame(), "Select File For Encryption");
        filedialog.setFile("*.*;");
        String s = new String();
        filedialog.setVisible(true);
        filedialog.getFile();
        jLabel3.setText(filedialog.getFile());
        jTextField1.setText(filedialog.getDirectory() + filedialog.getFile());
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        String s1 = jTextField1.getText();
        boolean flag;
        if (s1.equals("") || s1.equals("nullnull")) {
            flag = new Tiny().DBST(3, s1, statusDisplay);
        } else {
            flag = new Tiny().DBST(1, s1, statusDisplay);
        }

        if (flag) {
            statusDisplay.append("\nFile Successfully Encrypted");
        } else {
            statusDisplay.append("\nFile Encryption failed");
        }

    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EncryptionForm().setVisible(true);
            }
        });
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea statusDisplay;
    public static String keyString;
    public int state;
    public String outString;
}
