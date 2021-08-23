import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PerGameStatsReader
{
    /* parses per-game stats csv's from basketball-reference.com */
    public ArrayList<Player> readPerGameStats(String path)
    {
        ArrayList<Player> players = new ArrayList<>();
        File perGameStatsFile = new File(path);
        try (Scanner scanner = new Scanner(perGameStatsFile)) {
            String line = scanner.nextLine(); /* skipping the first line */
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] split_line = line.split(",");
                if(split_line.length != 30) {
                    System.out.printf("Error in PerGameStatsReader method readPerGameStats:\n" +
                            "Unable to correctly split line in file %s:\n" +
                            "Line content: %s", path, line);
                    continue;
                }

                String name = split_line[1];
                String position = split_line[2];
                int age = Integer.parseInt(split_line[3]);
                String proTeam = split_line[4];
                int gamesPlayed = Integer.parseInt(split_line[5]);
                int gamesStarted = Integer.parseInt(split_line[6]);
                float minutesPlayed = Float.parseFloat(split_line[7]);
                float fieldGoalsMade = Float.parseFloat(split_line[8]);
                float fieldGoalsAttempted = Float.parseFloat(split_line[9]);
                float fieldGoalPercentage;
                if(split_line[10].length() != 0) {
                    fieldGoalPercentage = Float.parseFloat(split_line[10]);
                } else fieldGoalPercentage = 0.0f;
                float threePointersMade = Float.parseFloat(split_line[11]);
                float threePointersAttempted = Float.parseFloat(split_line[12]);
                float threePointPercentage;
                if(split_line[13].length() != 0) {
                    threePointPercentage = Float.parseFloat(split_line[13]);
                } else threePointPercentage = 0.0f;
                float twoPointersMade = Float.parseFloat(split_line[14]);
                float twoPointersAttempted = Float.parseFloat(split_line[15]);
                float twoPointPercentage;
                if(split_line[16].length() != 0) {
                    twoPointPercentage = Float.parseFloat(split_line[16]);
                } else twoPointPercentage = 0.0f;
                float effectiveFieldGoalPercentage;
                if(split_line[17].length() != 0) {
                    effectiveFieldGoalPercentage = Float.parseFloat(split_line[17]);
                } else effectiveFieldGoalPercentage = 0.0f;
                float freeThrowsMade = Float.parseFloat(split_line[18]);
                float freeThrowsAttempted = Float.parseFloat(split_line[19]);
                float freeThrowPercentage;
                if(split_line[20].length() != 0) {
                    freeThrowPercentage = Float.parseFloat(split_line[20]);
                } else freeThrowPercentage = 0.0f;
                float offensiveRebounds = Float.parseFloat(split_line[21]);
                float defensiveRebounds = Float.parseFloat(split_line[22]);
                float totalRebounds = Float.parseFloat(split_line[23]);
                float assists = Float.parseFloat(split_line[24]);
                float steals = Float.parseFloat(split_line[25]);
                float blocks = Float.parseFloat(split_line[26]);
                float turnovers = Float.parseFloat(split_line[27]);
                float personalFouls = Float.parseFloat(split_line[28]);
                float points = Float.parseFloat(split_line[29]);

                int indexOfBackslash = name.indexOf("\\");
                String concatenatedName = name.substring(0, indexOfBackslash);

                players.add(new Player(
                        concatenatedName, position, age, proTeam,
                        gamesPlayed, gamesStarted, minutesPlayed,
                        fieldGoalsMade, fieldGoalsAttempted, fieldGoalPercentage,
                        threePointersMade, threePointersAttempted, threePointPercentage,
                        twoPointersMade, twoPointersAttempted, twoPointPercentage,
                        effectiveFieldGoalPercentage,
                        freeThrowsMade, freeThrowsAttempted, freeThrowPercentage,
                        offensiveRebounds, defensiveRebounds, totalRebounds,
                        assists, steals, blocks,
                        turnovers, personalFouls, points
                ));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("Error in PerGameStatsReader method readPerGameStats:\n" +
                    "Couldn't open file: %s\n", path);
            e.printStackTrace();
        }

        return  players;
    }
}
