package ru.mg.redhat.Engine.Objects;

public class SecretObject {
    public int x, y, width, height;
    public boolean found = false;
    public String name;

    public SecretObject(String name, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }
}
