package ru.mg.redhat.Game;

import android.os.AsyncTask;

public class BitmapLoader extends AsyncTask<Void, Void, Void> {
    public String state;
    public loadEvent event;

    @Override
    protected Void doInBackground(Void... params) {
        switch (state) {
            case "UI":
                Bitmaps.loadUIBitmaps();
                break;

            case "Game":
                Bitmaps.loadGameBitmaps();
                break;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        event.loaded();
    }

    public interface loadEvent {
        void loaded();
    }
}
