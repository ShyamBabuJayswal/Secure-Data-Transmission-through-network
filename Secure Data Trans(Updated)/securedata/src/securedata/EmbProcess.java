package securedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EmbProcess {

    String embfilename;

    public String emb(String s, String s1) {
        try {
            File file = new File(s);
            File file1 = new File(s1);
            FileInputStream fileinputstream = new FileInputStream(s);
            FileOutputStream fileoutputstream = new FileOutputStream("temp");
            byte abyte0[] = new byte[8];
            int i;
            int k;
            for (k = 0; (i = fileinputstream.read(abyte0, 0, 8)) > 0; k = i) {
                fileoutputstream.write(abyte0, 0, i);
            }

            fileinputstream.close();
            for (int l = 1; l <= 8 - k; l++) {
                fileoutputstream.write(65);
            }

            fileoutputstream.write("DATAFILE".getBytes(), 0, 8);
            System.out.println("File name===" + file.getName());
            StringBuffer stringbuffer = new StringBuffer(file1.getName());
            stringbuffer.setLength(50);
            fileoutputstream.write(stringbuffer.toString().getBytes(), 0, 50);
            fileinputstream = new FileInputStream(s1);
            int j;
            while ((j = fileinputstream.read(abyte0, 0, 8)) > 0) {
                fileoutputstream.write(abyte0, 0, j);
            }
            fileinputstream.close();
            fileoutputstream.close();
            file.delete();
            File file2 = new File("temp");
            file2.renameTo(file);
            embfilename = file.getName();
        } catch (Exception e) {
            e.printStackTrace();
            embfilename = "";
        }
        return embfilename;
    }

    public String demb(String s) {
        boolean flag;
        String demfile = "";
        try {
            File file = new File(s);
            String outpath = s.substring(0, s.lastIndexOf("\\") + 1);
            FileInputStream fileinputstream = new FileInputStream(s);
            char c = '\b';
            byte abyte0[] = new byte[c];
            String s1 = "";
            int i;
            while ((i = fileinputstream.read(abyte0, 0, c)) > 0) {
                s1 = new String(abyte0);
                if (s1.equals("DATAFILE")) {
                    break;
                }
            }
            if (!s1.equals("DATAFILE")) {
                flag = false;
                fileinputstream.close();
                return demfile;
            }
            abyte0 = new byte[50];
            fileinputstream.read(abyte0, 0, 50);
            s1 = new String(abyte0);
            String s2 = s1.trim();
            String fpath = s2.substring(0, s2.lastIndexOf(".") + 1) + "enc";
            System.out.println("fpath------" + fpath);
            FileOutputStream fileoutputstream = new FileOutputStream(outpath + fpath);
            c = '\u5000';
            abyte0 = new byte[c];
            while ((i = fileinputstream.read(abyte0, 0, c)) > 0) {
                fileoutputstream.write(abyte0, 0, i);
                //fileinputstream.delete(abyte0, 0, c);
            }
            fileinputstream.close();
            fileoutputstream.close();
            demfile = fpath;
        } catch (Exception exception) {
            demfile = "";
            exception.printStackTrace();
            System.out.println(exception);
        }
        return demfile;
    }
}
