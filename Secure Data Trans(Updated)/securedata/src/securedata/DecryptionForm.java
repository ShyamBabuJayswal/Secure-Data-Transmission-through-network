package securedata;
import enc.Tiny;
import java.awt.FileDialog;
import java.io.File;
import javax.swing.JFrame;

public class DecryptionForm extends javax.swing.JFrame {
    private File inputFile;
    private File outDirectory;
         
    public DecryptionForm() {
        initComponents();
    }
   
   private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusDisplay = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Decryption Form");
        setResizable(false);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 18));
        jLabel6.setText("Decryption");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jLabel1.setFont(new java.awt.Font("Century", 1, 12));
        jLabel1.setText("Select File");

        jButton1.setFont(new java.awt.Font("Century", 1, 12));
        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century", 1, 12));
        jButton2.setText("Decrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century", 1, 12));
        jLabel2.setText("Selected File    :");

        jLabel3.setFont(new java.awt.Font("Century", 1, 12));

        statusDisplay.setColumns(20);
        statusDisplay.setEditable(false);
        statusDisplay.setRows(5);
        jScrollPane1.setViewportView(statusDisplay);

        jButton3.setFont(new java.awt.Font("Century", 1, 12));
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(146, 146, 146)
                        .add(jButton2))
                    .add(layout.createSequentialGroup()
                        .add(45, 45, 45)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 239, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jButton3)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel1)
                                        .add(18, 18, 18)
                                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jButton1)))))
                .addContainerGap(53, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(168, 168, 168)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(46, 46, 46)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(32, 32, 32)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .add(31, 31, 31)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton3))
                .add(35, 35, 35)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 55, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 173, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }
      public static String getKeyString()
    {
        return keyString;
    }
    public static void setKeyString(String kString)
    {
        keyString = kString;
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        FileDialog filedialog = new FileDialog(new JFrame(), "Select Encrypted File ");
        filedialog.setFile("*.*");
        String s = new String();
        filedialog.setVisible(true);
        filedialog.getFile();
        jLabel3.setText(filedialog.getFile());
        jTextField1.setText(filedialog.getDirectory() + filedialog.getFile());
     }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        String s1 = jTextField1.getText();
        
        boolean flag;

        if(s1.equals("")||s1.equals("nullnull"))
	   flag=new Tiny().DBST(3,s1,statusDisplay); 
        else
          flag=new Tiny().DBST(2,s1,statusDisplay); 

        if(flag) 
        {
            statusDisplay.append("\nFile Decryption Sucessful");
        }
        else 
        {
            statusDisplay.append("\nFile Decryption failed");
        }
                 
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
            this.dispose();
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DecryptionForm().setVisible(true);
            }
        });
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea statusDisplay;
    public static String keyString;
    public int state;
    public String outString;
  }