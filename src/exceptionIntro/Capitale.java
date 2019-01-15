package exceptionIntro;

import exceptionIntro.Ville;

class Capitale extends Ville {
    private String monument;

    public Capitale(){
        //Ce mot clé appelle le constructeur de la classe mère
        super();
        monument = "aucun";
    }

    public String decrisToi(String test){
        String str =  super.decrisToi()+test;
        System.out.println("Invocation de super.decrisToi()");
        return str;
    }
}