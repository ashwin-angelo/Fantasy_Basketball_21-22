import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args)
    {
        String per_game_stats_path = "20-21-per_game_stats.csv";
        PerGameStatsReader perGameStatsReader = new PerGameStatsReader();
        ArrayList<Player> unfiltered = perGameStatsReader.readPerGameStats(per_game_stats_path);

        ArrayList<Player> players = removeDuplicates(unfiltered);

        float[] avgs = new float[11];
        for(Player p : players) {
            float[] stats = p.getStats();
            for(int i = 0; i < 11; i++) avgs[i] += stats[i];
        }

        for(int i = 0; i < 11; i++) {
            avgs[i] = avgs[i] / ((float) players.size());
        }

        float[] stds = new float[11];
        for(Player p : players) {
            float[] stats = p.getStats();
            for (int i = 0; i < 11; i++)
                stds[i] += ((stats[i] - avgs[i]) * (stats[i] - avgs[i]));
        }

        for(int i = 0; i < 11; i++) {
            stds[i] = stds[i] / ((float) players.size());
            stds[i] = (float) Math.sqrt(stds[i]);
        }

        for(Player p : players) p.computeRating(avgs, stds);
        Collections.sort(players);
        for (Player p : players)
            if(p.getRating() > 0.0f) System.out.println(p + " " + p.getRating());
    }

    /* Removes extra instances of players who played on multiple teams in the same season */
    private static ArrayList<Player> removeDuplicates(ArrayList<Player> players) {
        ArrayList<Player> filtered = new ArrayList<>();
        ArrayList<Player> multiples = new ArrayList<>();

        for(Player p : players)
            if(p.getProTeam().equals("TOT")) multiples.add(p);

        for(Player p : players) {
            boolean hasMultiples = false;
            for(Player m : multiples)
                if(p.getName().equals(m.getName())) {
                    hasMultiples = true;
                    break;
                }

            if(!hasMultiples) filtered.add(p);
        }

        filtered.addAll(multiples);
        return filtered;
    }
}
