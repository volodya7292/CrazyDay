package ru.mg.crazyday.Game;

import ru.mg.crazyday.MainActivity;

public class Config {
    private String levelName;

    public Config(String levelName) {
        this.levelName = levelName;
    }

    public void setInt(String key, int value) {
        MainActivity.prefsEditor.putInt(levelName + "-" + key, value);
    }

    public int getInt(String key, int value) {
        return MainActivity.prefs.getInt(levelName + "-" + key, value);
    }

    public void setString(String key, String value) {
        MainActivity.prefsEditor.putString(levelName + "-" + key, value);
    }

    public String getString(String key, String value) {
        return MainActivity.prefs.getString(levelName + "-" + key, value);
    }

    public void setBoolean(String key, boolean value) {
        MainActivity.prefsEditor.putBoolean(levelName + "-" + key, value);
    }

    public boolean getBoolean(String key, boolean value) {
        return MainActivity.prefs.getBoolean(levelName + "-" + key, value);
    }

    public void remove(String key) {
        MainActivity.prefsEditor.remove(levelName + "-" + key);
    }

    public void save() {
        MainActivity.prefsEditor.commit();
    }
}
