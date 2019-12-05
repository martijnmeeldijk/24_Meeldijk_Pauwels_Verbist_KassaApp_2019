package database.LoadSaveStrat;

public enum LoadSaveStrategies {
    TEKSTLOADSAVE ("ArtikelTextLoadSave"),
    EXCEL ("ExcelAdapter");

    public String classname;
    LoadSaveStrategies(String classname){
        this.classname = classname;
    }
    public String getClassname(){
        return classname;
    }

}
