package com.wavelus.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class View {

    public double startTime;
    public double stopTime;
    Presenter presenter;
    JTextField jTextField;
    JButton buttonOne;
    JButton buttonTwo;

    public View() {
        createUI();
    }

    private void createUI() {

        buttonOne = new JButton("Podaj liczbę wpisów");
        buttonOne.addActionListener((ActionEvent e) -> {
            startTime = System.nanoTime();
            presenter.action("String One");
            stopTime = System.nanoTime();
        });

        buttonTwo = new JButton("Coś innego");
        buttonTwo.addActionListener((ActionEvent e) -> {
            presenter.action("String Two");
        });


        jTextField = new JTextField();
        jTextField.setSize(250, 250);
        Box topBox = Box.createVerticalBox();
        topBox.add(buttonOne);
        topBox.add(Box.createVerticalStrut(8));
        topBox.add(buttonTwo);
        topBox.add(Box.createVerticalStrut(8));
        topBox.add(jTextField);

        JFrame frame = new JFrame("Aplikacja kliencka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(topBox, BorderLayout.NORTH);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public void setPresenter(Presenter p) {
        presenter = p;
    }

}
