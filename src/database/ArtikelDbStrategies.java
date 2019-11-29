package database;

public enum ArtikelDbStrategies {
    INMEMORY ("database.ArtikelDbInMemory");


    public String classname;
    private ArtikelDbStrategies(String classname){
        this.classname = classname;
    }
    public String getClassname(){
        return classname;
    }
}
