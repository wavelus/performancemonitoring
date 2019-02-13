package com.wavelus.client;

import javax.swing.*;

public class AppClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            view.setPresenter(new Presenter(view));
        });
    }
}
