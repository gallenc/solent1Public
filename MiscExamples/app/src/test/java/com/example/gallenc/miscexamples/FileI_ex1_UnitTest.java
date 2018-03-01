package com.example.gallenc.miscexamples;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FileI_ex1_UnitTest {

    @Test
    public void fileOutputStreamExample() throws Exception {

        File f=null;

        DataOutputStream ds=null;
        FileOutputStream fs=null;
        try {
            // creating a temporary directory if doesn't exist
            f = new File("./temp/data.bin");
            if(!f.exists()){
                f.getParentFile().mkdirs();
            }
            System.out.println("writing to:"+f.getAbsolutePath());
            fs = new FileOutputStream(f);
            ds = new DataOutputStream(fs);
            ds.writeInt(1);
            ds.writeChar('b');
            ds.writeDouble(3.1415927);

        } catch (IOException e) {
            System.out.println(e);
        } finally{
            if (ds!=null) ds.close(); // close the file to ensure data is flushed to file
        }

        // read file
        FileInputStream fis=null;
        DataInputStream dis=null;
        try {

            System.out.println("reading from:" + f.getAbsolutePath());
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            Integer i =  dis.readInt();
            Character c = dis.readChar();
            Double d = dis.readDouble();

            System.out.println(" integer: " +i+
                    " character: " +c+
                    " double " +d);

        } catch (IOException e) {
            System.out.println(e);
        } finally{
            if (ds!=null) ds.close(); // close the file to ensure data is flushed to file
        }
    }

    @Test
    public void fileCharacterStreamExample() throws Exception {
        File f=null;
        try
        {
            // creating a temporary directory if doesn't exist
            f = new File("./temp/data.txt");
            if(!f.exists()){
                f.getParentFile().mkdirs();
            }
            System.out.println("writing to:"+f.getAbsolutePath());
            PrintWriter pw =
                    new PrintWriter( new FileWriter(f));

            pw.println("Hello");
            pw.println("It's a nice day!");
            pw.close(); // close the file to ensure data is flushed to file
        }
        catch(IOException e)
        {
            System.out.println ("I/O Error: " + e);
        }

        // read from file
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch(IOException e)
        {
            System.out.println("ERROR: " + e);
        }
    }

}