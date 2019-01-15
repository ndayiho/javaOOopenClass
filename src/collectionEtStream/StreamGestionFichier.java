package collectionEtStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamGestionFichier {
public static void main(String[] args) {
    readFile();
}

protected  static void readFile(){
    String fileName = "E:\\formation\\testUnitaire\\src\\collectionEtStream\\test.txt";
    try(Stream<String> sf = Files.lines(Paths.get(fileName))){
        sf.forEach(System.out::println);
    }catch(IOException e) {
        e.printStackTrace();
    }
}

//    protected  static List<Integer> getListFromFile(){
//        List<Integer> resultatTes=new ArrayList<>();
//        String fileName = "E:\\formation\\testUnitaire\\src\\collectionEtStream\\test.txt";
//        try(
//        Stream<String> sf = Files.lines(Paths.get(fileName))){
//         List<Integer> sflist = sf.collect(Collectors.toList());
//        }
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//        return sf;
//    }

//    protected  static void CreateFile(){
//        String fileName = "E:\\formation\\testUnitaire\\src\\collectionEtStream\\test.txt";
//        try(Stream<File> sf = File.createTempFile("test","test","test")){
//            sf.forEach(System.out::println);
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//    }


}
