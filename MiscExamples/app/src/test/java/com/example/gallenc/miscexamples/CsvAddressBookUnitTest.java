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

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CsvAddressBookUnitTest {



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