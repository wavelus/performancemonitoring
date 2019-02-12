import javax.swing.*;

public class Main {
    private static StringOne myString;
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            View view = new View();
            view.setPresenter(new Presenter(view, myString));
        });
    }
}
