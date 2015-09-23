package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Implementation of App Widget functionality.
 */
public class ScoresWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        RemoteViews resultViews = firstMatchResultView(context);
        ComponentName widgetName = new ComponentName(context,ScoresWidget.class);
        appWidgetManager.updateAppWidget(widgetName, resultViews);
    }

    private RemoteViews firstMatchResultView(Context context){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.scores_widget);
        views.setTextViewText(R.id.score_textview, " - ");
        views.setImageViewResource(R.id.home_crest, R.drawable.no_icon);
        views.setImageViewResource(R.id.away_crest, R.drawable.no_icon);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.home_crest, pendingIntent);
        views.setOnClickPendingIntent(R.id.away_crest, pendingIntent);
        views.setOnClickPendingIntent(R.id.score_textview, pendingIntent);
        views.setOnClickPendingIntent(R.id.time_textview, pendingIntent);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(context.getString(R.string.date_format_ymd), Locale.getDefault());
        String[] arg = new String[1];
        arg[0] = format.format(date);
        Cursor cursor = context.getContentResolver().query(
                DatabaseContract.scores_table.buildScoreWithDate(),
                null,
                null,
                arg,
                null
        );

        if(cursor.moveToFirst()){
            String homeName = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_COL));
            String awayName = cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_COL));
            views.setImageViewResource(R.id.home_crest,
                    Utilies.getTeamCrestByTeamName(homeName));
            views.setImageViewResource(R.id.away_crest,
                    Utilies.getTeamCrestByTeamName(awayName));
            views.setTextViewText(R.id.home_name, homeName);
            views.setTextViewText(R.id.away_name, awayName);

            int homeScore = cursor.getInt(cursor.getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL));
            int awayScore = cursor.getInt(cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_GOALS_COL));
            views.setTextViewText(R.id.score_textview, Utilies.getScores(homeScore, awayScore));

            views.setTextViewText(R.id.time_textview, cursor.getString(cursor.getColumnIndex(DatabaseContract.scores_table.TIME_COL)));
        }
        cursor.close();

        return views;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.scores_widget);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

