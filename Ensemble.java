import java.util.AbstractList;

import java.util.Iterator;

import java.util.LinkedList;

public abstract class Ensemble {
    private final String ensembleID;
    private String eName;
    private final AbstractList<Musician> musicians;

    public Ensemble(String eID){
        this.ensembleID = eID;
        this.eName = "";
        this.musicians = new LinkedList<>();
    }

    public String getEnsembleID(){return this.ensembleID; }

    public String getName(){return this.eName;}

    public void setName(String name){this.eName = name;}    

    public void addMusician(Musician m){
        this.musicians.add(m);
    }

    public void dropMusician(Musician m){
        // Iterator<Musician> it = this.musicians.iterator();
        // while (it.hasNext()) {
        //     if (it.next().getMID().equals(m.getMID())) {
        //         it.remove();
        //         break;
        //     }
        // }
        this.musicians.remove(m);  //只删除当前对象，如果ID重复不会全部删除，上面注释的可以根据ID全部删除
    }

    public Iterator<Musician> getMusicians(){
        return this.musicians.iterator();
    }
            // 在需要List的地方可以这样转换：
        //Iterator<Musician> iterator = ensemble.getMusicians();
        //List<Musician> musicianList = new ArrayList<>();
        //while (iterator.hasNext()) {
        //  musicianList.add(iterator.next());
        //}
        // 现在可以使用musicianList进行各种操作

    public abstract void updateMusicianRole();

    public abstract void showEnsemble();
}   