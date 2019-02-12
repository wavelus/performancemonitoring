import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class View{

    Presenter presenter;
    JTextField jTextField;
    JButton buttonOne;
    JButton buttonTwo;
    public double startTime;
    public double stopTime;

    public View(){
        createUI();
    }

    private void createUI(){

        buttonOne = new JButton("String One");
        buttonOne.addActionListener((ActionEvent e) -> {
            startTime = System.nanoTime();
            presenter.action("String One");
            stopTime = System.nanoTime();
        });

        buttonTwo = new JButton("String Two");
        buttonTwo.addActionListener((ActionEvent e) -> {
            presenter.action("String Two");
        });

        Box topBox = Box.createVerticalBox();
        topBox.add(buttonOne);
        topBox.add(Box.createVerticalStrut(8));
        topBox.add(buttonTwo);
        topBox.add(Box.createVerticalStrut(8));
        topBox.add(jTextField);

        JFrame frame = new JFrame("Coś innego niż Chuj");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(topBox, BorderLayout.NORTH);
        frame.setSize(150,150);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public void setPresenter(Presenter p){
        presenter = p;
    }

}
