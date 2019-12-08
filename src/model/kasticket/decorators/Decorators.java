package model.kasticket.decorators;

public enum Decorators {
    HEADER("BoodschapHeader", "Header met boodschap"),
    DATUM("DatumHeader", "Header met datum en tijd"),
    BTW("BtwFooter","Footer met info over btw"),
    KORTING("KortingFooter", "Footer met korting"),
    FOOTER("BoodschapFooter", "Footer met boodschap");



    private String classname;
    private String description;

    Decorators(String classname, String description){
        this.classname = classname;
        this.description = description;
    }
    public String getClassname(){
        return classname;
    }
    public String getDescription(){
        return description;
    }
}
