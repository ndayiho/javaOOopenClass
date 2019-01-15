package enumeration;

public enum Langage {
    //Objets directement construits
    JAVA("Langage JAVA", "Eclipse",true),
    C ("Lanage C", "Code Block",false),
    CPlus ("Langage C++", "Visual studio",false),
    PHP ("Langage PHP", "PS Pad",false);

    private String name = "";
    private String editor = "";

    private boolean aime=true;

    //Constructeur
    Langage(String name, String editor,boolean aime ){
        this.name = name;
        this.editor = editor;
        this.aime=aime;
    }

    public void getEditor(){
        System.out.println("Editeur : " + editor);
    }

    public boolean isAime() {
        return aime;
    }

    public String toString(){
        return name;
    }

}