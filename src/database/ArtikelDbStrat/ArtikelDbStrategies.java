package database.ArtikelDbStrat;

public enum ArtikelDbStrategies {
    INMEMORY ("database.ArtikelDbStrat.ArtikelDbStrategy.ArtikelDbInMemory");

    //var
    private String classname;

    ArtikelDbStrategies(String classname){
        this.classname = classname;
    }
    public String getClassname(){
        return classname;
    }
}
