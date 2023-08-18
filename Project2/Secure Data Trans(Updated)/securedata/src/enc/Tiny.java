package enc;

import java.io.*;
import javax.swing.*;
import java.awt.*;

class filedialog extends JFrame {

    String name;

    filedialog() {
        FileDialog fd = new FileDialog(filedialog.this, "Save as", FileDialog.SAVE);
        fd.show();
        if (fd.getFile() != null) {
            name = fd.getDirectory() + fd.getFile();
            setTitle(name);
        }
    }

    public String getfile() {
        return name;
    }
};

public class Tiny {

    public static String quote = "Now rise, and show your Strength. ";

    void pack(byte[] src, int[] dest, int destOffset) {
        assert destOffset + (src.length / 4) <= dest.length;
        int i = 0, shift = 24;
        int j = destOffset;
        dest[j] = 0;
        while (i < src.length) {
            dest[j] |= ((src[i] & 0xff) << shift);
            if (shift == 0) {
                shift = 24;
                j++;
                if (j < dest.length) {
                    dest[j] = 0;
                }
            } else {
                shift -= 8;
            }
            i++;
        }
    }
    static String name;

    byte[] unpack(int[] src, int srcOffset, int destLength) {
        assert destLength <= (src.length - srcOffset) * 4;
        byte[] dest = new byte[destLength];
        int i = srcOffset;
        int count = 0;
        for (int j = 0; j < destLength; j++) {
            dest[j] = (byte) ((src[i] >> (24 - (8 * count))) & 0xff);
            count++;
            if (count == 4) {
                count = 0;
                i++;
            }
        }
        return dest;
    }

    public byte[] encrypt(byte[] clear) {
        int paddedSize = ((clear.length / 8) + (((clear.length % 8) == 0) ? 0 : 1)) * 2;
        int[] buffer = new int[paddedSize + 1];
        buffer[0] = clear.length;
        pack(clear, buffer, 1);
        //brew(buffer);
        return unpack(buffer, 0, buffer.length * 4);
    }
    byte[] original = quote.getBytes();
    /* Run it through the cipher... and back */
    byte[] crypt = encrypt(original);

    public boolean DBST(int op, String x, javax.swing.JTextArea jLabel9) {
        boolean flag = false;
        String toBeSaved = "";
        String theText;
        int choice = op;
        name = x;
        if (choice == 1) {
            try {
                byte[] theFile = getFile(name);

                String key = JOptionPane.showInputDialog("Enter your key (the longer the better):");
                if (key.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter the key", "information", JOptionPane.ERROR_MESSAGE);
                } else {
                    Encryption encryption = new Encryption(theFile, key);

                    encryption.encrypt();

                    toBeSaved = saveFile(encryption.getFileBytes());
                    jLabel9.setText(toBeSaved);
                    JOptionPane.showMessageDialog(null, "\nYour file has been encrypted and saved\n", "message", JOptionPane.INFORMATION_MESSAGE);
                    flag = true;
                }
            } catch (Exception e) {
                flag = false;
                jLabel9.setText("");
                e.printStackTrace();
            }
        } else if (choice == 2) {
            try {
                byte[] theFile = getFile(name);

                JPasswordField pf = new JPasswordField("Enter the key: ");

                String key = JOptionPane.showInputDialog("Enter the key: ");
                if (key.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter the key", "information", JOptionPane.ERROR_MESSAGE);
                } else {

                    Encryption encryption = new Encryption(theFile, key);
                    encryption.decrypt();

                    toBeSaved = saveFile(encryption.getFileBytes());
                    jLabel9.setText(toBeSaved);
                    JOptionPane.showMessageDialog(null, "\nYour file has been decrypted and saved\n", "message", JOptionPane.INFORMATION_MESSAGE);
                    flag = true;
                }
            } catch (Exception e) {
                flag = false;
                jLabel9.setText("");
                e.printStackTrace();
            }

        } else if (choice == 4) {
            System.out.println("GOOD DAY!");
            System.exit(0);
        } else if (choice == 3) {

            String out = "\n Select the file";
            JOptionPane.showMessageDialog(null, out, "information", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }

    public static byte[] getFile(String name) {

        byte[] readFromFile = null;
        String txt = name;
        try {
            FileInputStream in = new FileInputStream(txt);
            readFromFile = new byte[in.available()];
            in.read(readFromFile);
            in.close();
        } catch (IOException e) {
            System.out.println("\nSorry - file not found!\n");
        }
        return readFromFile;
    }

    public static String saveFile(byte[] toSave) {

        String txt;
        filedialog fd = new filedialog();
        txt = fd.getfile();
        try {
            FileOutputStream out = new FileOutputStream(txt);
            out.write(toSave);
            out.close();

        } catch (IOException e) {
            System.out.println("Sorry, but there seems to have been a problem\n"
                    + "saving your file. Perhaps your hard-drive is full\n"
                    + "or the write permissions need to be changed\n");
        }
        return txt;
    }

    class Encryption {

        public Encryption(byte[] fileBytes, String key) {
            this.fileBytes = fileBytes;
            this.key = key;

            keys = new char[key.length()];
            pivot = (int) (fileBytes.length / 2);

            delta = 0x9e3779b9;
            alpha = 0x7f2637c6;
            beta = 0x5d656dc8;
            gamma = 0x653654d9;

            sumA = (long) (alpha >> key.charAt(0));
            sumB = (long) (beta << key.charAt(1));
            sumC = (long) (gamma >> key.charAt(2));
            sumD = (long) (delta >> key.charAt(3));

            if (fileBytes.length % 5 > 0) {
                inter = (int) ((fileBytes.length - 1) / 5);
            } else {
                inter = (int) (fileBytes.length / 5);
            }

            forLevel2 = key.length();
        }

        public void setFileBytes(byte[] newBytes) {
            fileBytes = newBytes;
        }

        public byte[] getFileBytes() {
            return fileBytes;
        }

        public void encrypt() {

            int f = 0;
            boolean truth = true;

            key = keyStream();

            keys = new char[key.length()];

            for (int c = 0; c < key.length(); c++) {
                keys[c] = key.charAt(c);
            }


            System.out.println("\nEncrypting\n");

            for (int extra = 0; extra < 127; extra++) {
                for (int i = 0; i < fileBytes.length; i = i + keys.length) {
                    if (truth == false) {
                        break;
                    }
                    f = 0;
                    for (int j = i; j < i + keys.length; j++) {

                        if (j >= fileBytes.length) {
                            truth = false;
                            break;
                        }

                        fileBytes[j] = (byte) ((fileBytes[j]
                                ^ (keys[f] - 'A' << sumD)) ^ (keys[f] + sumD));

                        sumD -= delta;
                        f++;
                    }

                }
                fileBytes = splitNSwap(fileBytes);
                setFileBytes(fileBytes);
            }
            setFileBytes(level2(fileBytes, true));
        }

        public void decrypt() {

            setFileBytes(level2(fileBytes, false));
            int f = 0;
            boolean truth = true;

            key = keyStream();

            keys = new char[key.length()];

            for (int c = 0; c < key.length(); c++) {
                keys[c] = key.charAt(c);
            }


            System.out.println("\nDecrypting\n");
            for (int extra = 0; extra < 127; extra++) {
                fileBytes = getFileBytes();

                fileBytes = splitNSwap(fileBytes);

                for (int i = 0; i < fileBytes.length; i = i + keys.length) {

                    if (truth == false) {
                        break;
                    }

                    f = 0;
                    for (int j = i; j < i + keys.length; j++) {

                        if (j >= fileBytes.length) {
                            truth = false;
                            break;
                        }

                        fileBytes[j] = (byte) ((fileBytes[j]
                                ^ (keys[f] - 'A' << sumD)) ^ (keys[f] + sumD));

                        sumD -= delta;
                        f++;
                    }
                }
                setFileBytes(fileBytes);
            }
        }

        public byte[] splitNSwap(byte[] zeBytes) {
            if (zeBytes.length % 2 == 0) {
                pivot = (int) (zeBytes.length / 2);
            } else {
                pivot = (int) ((zeBytes.length - 1) / 2);
            }
            fileBytez = new byte[zeBytes.length];
            for (int reverse = 0; reverse < pivot; reverse++) {
                fileBytez[reverse] = (byte) (zeBytes[reverse + pivot] ^ fileBytez[reverse]);
            }
            for (int reverseB = pivot; reverseB < zeBytes.length; reverseB++) {
                fileBytez[reverseB] = (byte) (zeBytes[reverseB - pivot] ^ fileBytez[reverseB]);
            }
            setFileBytes(fileBytez);
            return fileBytez;

        }

        public String scrambleKey(String toBeScrambledFurther) {
            pivot = (int) (toBeScrambledFurther.length() / 2);
            String newKey = "";
            String sub1 = "", sub2 = "";

            for (int a = 0; a < pivot; a++) {
                sub1 += toBeScrambledFurther.charAt(a + pivot);
            }

            for (int b = pivot; b < toBeScrambledFurther.length(); b++) {
                sub2 += toBeScrambledFurther.charAt(b - pivot);
            }

            newKey = sub1 + sub2;
            return newKey;
        }

        public byte[] level2(byte[] oldBytes, boolean state) {
            if (state) {
                System.out.println("Scrambling encrypted data");
            } else {
                System.out.println("\nDescrambling encrypted data");
            }

            int s = forLevel2;

            int stop = oldBytes.length % s;

            byte[] newBytes = new byte[oldBytes.length];
            byte[] tempBytes = new byte[oldBytes.length - stop];
            byte[] resultBytes = new byte[oldBytes.length];
            byte[] remainderBytes = new byte[stop];

            int hello = oldBytes.length - stop;

            for (int old = 0; old < oldBytes.length - stop; old++) {
                tempBytes[old] = oldBytes[old];
            }

            for (int old = 0; old < stop; old++) {
                remainderBytes[old] = oldBytes[(oldBytes.length - stop + old)];
            }

            if (state) {
                for (int outer = 0; outer < s; outer++) {
                    for (int c = outer; c < hello + outer; c += s) {
                        if (c + s < oldBytes.length) {
                            newBytes[c] = (byte) (oldBytes[c + s] - sumA);
                            newBytes[c + s] = (byte) (oldBytes[c] + sumB);
                        } else {
                            break;
                        }
                    }
                }
            } else if (!state) {
                for (int outer = s - 1; outer >= 0; outer--) {
                    for (int c = (hello - 1 - outer); c >= 0 - outer; c -= s) {
                        if (c - s >= 0) {
                            newBytes[c - s] = (byte) (oldBytes[c] - sumB);
                            newBytes[c] = (byte) (oldBytes[c - s] + sumA);

                        } else {
                            break;
                        }
                    }
                    if (outer <= 0) {
                        break;
                    } else {
                        continue;
                    }
                }
            }

            for (int rep = 0; rep < newBytes.length; rep++) {
                resultBytes[rep] = newBytes[rep];
            }

            for (int rep = 0; rep < remainderBytes.length; rep++) {
                resultBytes[rep] = remainderBytes[rep];
            }
            setFileBytes(newBytes);
            return newBytes;
        }

        public String keyStream() {

            System.out.println("\nGenerating key stream\n");

            String answer = key;
            String thekey = key;

            for (int i = 0; i < (thekey.length() * 128); i++) {
                answer = answer + getPart(thekey);
                thekey = getPart(thekey);
            }
            return answer;
        }

        public String getPart(String thekey) {
            char[] keyPart = new char[thekey.length()];
            String result = "";

            for (int c = 0; c < thekey.length() - 1; c++) {
                keyPart[c] = (char) (thekey.charAt(c + 1) - 1);
            }

            keyPart[thekey.length() - 1] = thekey.charAt(0);
            for (int put = 0; put < keyPart.length; put++) {
                result = result + keyPart[put];
            }

            return result;
        }
        private String key;
        private char[] keys;
        private byte[] fileBytes;
        private byte[] fileBytez;
        private int pivot;
        private int inter;
        private long alpha;
        private long beta;
        private long gamma;
        private long delta;
        private long sumA;
        private long sumB;
        private long sumC;
        private long sumD;
        private byte[] fileBytesB;
        private int forLevel2;
    }
}