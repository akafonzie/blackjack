package assserver;
/**
 *
 * @author Shinobi
 */
public class AssServer {
    static pdt pp = new pdt();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        pp.pal("START OF GAME");
        model       leModel         = new model();
        makeGUI     leGUI           = new makeGUI(leModel);
        makeServer  leServer        = new makeServer(leGUI);
        controller  leController    = new controller
                                        (leModel,leGUI,leServer);
        leGUI.show();
    }
}
