public interface EnsembleFactory {
    Ensemble creatEnsemble(String id);
    Musician createMusician(String id);
    boolean isValidRole(int role);
    String getRoleName(int role);     
    String showRoleName();
    int[] getAllRoles();   // 返回该工厂所支持的所有 role（无参）
    String getEnsembleType();
    //Ensemble getEnsembleByMusician(Musician musician, Ensemble ensemble);用注册表处理
    void showEnsembleInfo(Ensemble ensemble);  //两个方法一样，后面可考虑用另一个抽象父类统一处理
}