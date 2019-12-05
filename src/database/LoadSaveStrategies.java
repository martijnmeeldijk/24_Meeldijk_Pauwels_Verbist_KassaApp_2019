package database;

public enum LoadSaveStrategies {

    TEKSTLOADSAVE ("database.ArtikelTekstLoadSave"),
    EXCEL ("database.ExcelAdapter");

    public String classname;
    LoadSaveStrategies(String classname){
        this.classname = classname;
    }
    public String getClassname(){
        return classname;
    }

}
