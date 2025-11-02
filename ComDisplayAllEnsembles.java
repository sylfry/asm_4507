import java.util.List;
public class ComDisplayAllEnsembles implements Command {
    private List<Ensemble> ensembles;

    public ComDisplayAllEnsembles(List<Ensemble> ensembles) {
        this.ensembles = ensembles;
    }

    @Override
    public void execute() {
            //String olist = "Orchestra Ensemble ";
            String olist = new OrchestraFactory().getEnsembleType() + " ";
            String jlist = new JazzBandFactory().getEnsembleType() + " ";
            for (Ensemble ensemble : ensembles) {
            if(ensemble instanceof OrchestraEnsemble){
                olist += ensemble.getName()+" (" + ensemble.getEnsembleID() + ") ";
            }else if(ensemble instanceof JazzBandEnsemble){
                jlist += ensemble.getName()+" (" + ensemble.getEnsembleID() + ") ";
            }
        }
             System.out.println(olist);
             System.out.println(jlist);

       

    }

    @Override
    public void undo() {
        // No undo action for displaying all ensembles
    }
    
}
