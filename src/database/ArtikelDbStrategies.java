package database;

public enum ArtikelDbStrategies {
    INMEMORY ("database.ArtikelDbInMemory");

    //var
    private String classname;

    ArtikelDbStrategies(String classname){
        this.classname = classname;
    }
    public String getClassname(){
        return classname;
    }
}
