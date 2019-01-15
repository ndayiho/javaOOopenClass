package partie1.gestionDefichier.java_7EtPlus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyAndReplace {

    public static void main(String[] args) {
    Path source = Paths.get("E:\\javaOOopenClass\\dossier test\\test2.txt");
    Path cible = Paths.get("E:\\javaOOopenClass\\testcopie.txt");

try {
//        Files.copy(source, cible, StandardCopyOption.REPLACE_EXISTING);
        Files.createFile(source);
//        Files.move(source, cible, StandardCopyOption.REPLACE_EXISTING);

    } catch (IOException e) { e.printStackTrace();

}
    }
}
