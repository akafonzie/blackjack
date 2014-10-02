package assplayer;

/**
 *
 * @author Shinobi
 */
public class AssPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        model       leModel         = new model();
        makeGUI     leView          = new makeGUI(leModel);
        connect     leServer        = new connect(leView);
        controller  leController    = new controller(leModel,leView,leServer);
        //makeGUI leGUI = new makeGUI();
        leView.show();
    }
}
