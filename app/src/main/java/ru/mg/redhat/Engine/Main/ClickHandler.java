package ru.mg.redhat.Engine.Main;

import java.io.IOException;

public interface ClickHandler {
    void onClick() throws IOException;

    void onDown();

    void onUp();
}
