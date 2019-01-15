package partie1.gestionDefichier.java_7EtPlus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadAndWrite {

    public static void main(String[] args) {
        Path source = Paths.get("E:\\javaOOopenClass\\dossier test\\test2.txt");
//Ouverture en lecture :
        try ( InputStream input = Files.newInputStream(source) ) {
            input.read();

            System.out.println(input.read());
        }catch (IOException e) { e.printStackTrace();

        }

////Ouverture en écriture :
//        try ( OutputStream output = Files.newOutputStream(source) )  {
//
//        }catch (IOException e) { e.printStackTrace();
//
//        }

////Ouverture d'un Reader en lecture :
//        try ( BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8) )  {
//
//        }catch (IOException e) { e.printStackTrace();
//
//        }
//
////Ouverture d'un Writer en écriture :
//        try ( BufferedWriter writer = Files.newBufferedWriter(source, StandardCharsets.UTF_8) )  {
//
//
//        }catch (IOException e) { e.printStackTrace();
//
//        }
    }
}
