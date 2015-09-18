package barqsoft.footballscores;

import android.content.Context;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{
    public static final int SERIE_A = 401;
    public static final int PREMIER_LEGAUE = 398;
    public static final int CHAMPIONS_LEAGUE = 405;
    public static final int PRIMERA_DIVISION = 399;
    public static final int BUNDESLIGA1 = 394;
    public static final int BUNDESLIGA2 = 395;
    public static final int SEGUNDA_DIVISION = 400;
    public static final int LIGUE1 = 396;
    public static final int LIGUE2 = 397;

    public static String getLeague(Context context, int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return context.getString(R.string.seria_a);
            case PREMIER_LEGAUE : return context.getString(R.string.premier_league);
            case CHAMPIONS_LEAGUE : return context.getString(R.string.champions_league);
            case PRIMERA_DIVISION : return context.getString(R.string.primera_divison);
            case BUNDESLIGA1 : return context.getString(R.string.bundesliga1);
            case BUNDESLIGA2 : return context.getString(R.string.bundesliga2);
            case SEGUNDA_DIVISION : return context.getString(R.string.segunda_division);
            case LIGUE1 : return context.getString(R.string.ligue1);
            case LIGUE2 : return context.getString(R.string.ligue2);

            default: return context.getString(R.string.unknown_league);
        }
    }
    public static String getMatchDay(Context context, int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return context.getString(R.string.group_stage_text);
            }
            else if(match_day == 7 || match_day == 8)
            {
                return context.getString(R.string.first_knockout_round);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return context.getString(R.string.quarter_final);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return context.getString(R.string.semi_final);
            }
            else
            {
                return context.getString(R.string.final_text);
            }
        }
        else
        {
            return context.getString(R.string.matchday_text, String.valueOf(match_day));
        }
    }

    public static String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        {
            case "Arsenal FC" : return R.drawable.arsenal;
            case "Chelsea FC" : return R.drawable.chelsea;
            case "Manchester City FC" : return R.drawable.manchester_city;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Newcastle United FC" : return R.drawable.newcastle_united;
            case "Swansea City FC" : return R.drawable.swansea_city_afc;
            case "Leicester City FC" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            case "West Bromwich Albion FC" : return R.drawable.west_bromwich_albion_hd_logo;
            default: return R.drawable.no_icon;
        }
    }
}
