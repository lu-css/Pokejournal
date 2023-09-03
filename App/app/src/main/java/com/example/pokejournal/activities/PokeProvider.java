package com.example.pokejournal.activities;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.pokejournal.R;

public class PokeProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        for(int appWidgetId : appWidgetIds){
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.poke_widget);
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

