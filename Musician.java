public class Musician {

    private final String musicianID;
    private String mName;
    private int role;
    
    public Musician(String mID){
        this.musicianID = mID;
        this.mName = "";
        this.role = 0;
    }
    public String getMID(){return this.musicianID;}
   
    public int getRole(){return this.role;}
   
    public void setRole(int role){this.role = role;}

    public String getName(){return this.mName;}

    public void setName(String name){this.mName = name;}

}
