public class Player implements Comparable<Player>
{
    private final String name;
    private final String position;
    private final int age;
    private final String proTeam;
    private final int gamesPlayed;
    private final int gamesStarted;
    private final float minutesPlayed;
    private final float fieldGoalsMade;
    private final float fieldGoalsAttempted;
    private final float fieldGoalPercentage;
    private final float threePointersMade;
    private final float threePointersAttempted;
    private final float threePointPercentage;
    private final float twoPointersMade;
    private final float twoPointersAttempted;
    private final float twoPointPercentage;
    private final float effectiveFieldGoalPercentage;
    private final float freeThrowsMade;
    private final float freeThrowsAttempted;
    private final float freeThrowPercentage;
    private final float offensiveRebounds;
    private final float defensiveRebounds;
    private final float totalRebounds;
    private final float assists;
    private final float steals;
    private final float blocks;
    private final float turnovers;
    private final float personalFouls;
    private final float points;

    /* float array for 9-cats specific stats */
    private final float[] stats;

    /* determined post */
    private float[] zScores;
    private float rating;

    public Player(String name, String position, int age, String proTeam,
                  int gamesPlayed, int gamesStarted, float minutesPlayed,
                  float fieldGoalsMade, float fieldGoalsAttempted, float fieldGoalPercentage,
                  float threePointersMade, float threePointersAttempted, float threePointPercentage,
                  float twoPointersMade, float twoPointersAttempted, float twoPointPercentage,
                  float effectiveFieldGoalPercentage,
                  float freeThrowsMade, float freeThrowsAttempted, float freeThrowPercentage,
                  float offensiveRebounds, float defensiveRebounds, float totalRebounds,
                  float assists, float steals, float blocks,
                  float turnovers, float personalFouls, float points) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.proTeam = proTeam;
        this.gamesPlayed = gamesPlayed;
        this.gamesStarted = gamesStarted;
        this.minutesPlayed = minutesPlayed;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.fieldGoalPercentage = fieldGoalPercentage;
        this.threePointersMade = threePointersMade;
        this.threePointersAttempted = threePointersAttempted;
        this.threePointPercentage = threePointPercentage;
        this.twoPointersMade = twoPointersMade;
        this.twoPointersAttempted = twoPointersAttempted;
        this.twoPointPercentage = twoPointPercentage;
        this.effectiveFieldGoalPercentage = effectiveFieldGoalPercentage;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.freeThrowPercentage = freeThrowPercentage;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.totalRebounds = totalRebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.personalFouls = personalFouls;
        this.points = points;

        stats = new float[11];
        stats[0] = points;
        stats[1] = totalRebounds;
        stats[2] = assists;
        stats[3] = steals;
        stats[4] = blocks;
        stats[5] = turnovers;
        stats[6] = threePointersMade;
        stats[7] = fieldGoalsMade;
        stats[8] = fieldGoalsAttempted - fieldGoalsMade;
        stats[9] = freeThrowsMade;
        stats[10] = freeThrowsAttempted - freeThrowsMade;

        zScores = new float[11];
        rating = 0.0f;

    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public String getProTeam() {
        return proTeam;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public float getMinutesPlayed() {
        return minutesPlayed;
    }

    public float getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public float getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public float getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public float getThreePointersMade() {
        return threePointersMade;
    }

    public float getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public float getThreePointPercentage() {
        return threePointPercentage;
    }

    public float getTwoPointersMade() {
        return twoPointersMade;
    }

    public float getTwoPointersAttempted() {
        return twoPointersAttempted;
    }

    public float getTwoPointPercentage() {
        return twoPointPercentage;
    }

    public float getEffectiveFieldGoalPercentage() {
        return effectiveFieldGoalPercentage;
    }

    public float getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public float getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public float getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public float getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public float getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public float getTotalRebounds() {
        return totalRebounds;
    }

    public float getAssists() {
        return assists;
    }

    public float getSteals() {
        return steals;
    }

    public float getBlocks() {
        return blocks;
    }

    public float getTurnovers() {
        return turnovers;
    }

    public float getPersonalFouls() {
        return personalFouls;
    }

    public float getPoints() {
        return points;
    }

    public float[] getStats() {
        return stats;
    }

    public void computeRating(float[] avgs, float[] stds) {
        for(int i = 0; i < 11; i++) {
            zScores[i] = (stats[i] - avgs[i]) / stds[i];
            /* negative contributions are at indices 5, 8, and 10:
            * turnovers, field goals missed, and free throws missed */
            if(i == 5 || i == 8 || i == 10) rating -= zScores[i];
            else rating += zScores[i];
        }
    }

    public float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return name + " (" + proTeam + ")";
    }

    @Override
    public int compareTo(Player o) {
        if(o.rating > rating) return -1;
        else if(o.rating < rating) return 1;
        return 0;
    }
}
