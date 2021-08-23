# Fantasy_Basketball_21-22
Z-score based ranking for 9-cats fantasy basketball.

8.23.21)
Performs these basic functions for now:
1- Parses csv version of per-game season stats from basketball-reference.com and creates an ArrayList of 'Player' objects
2- Removes duplicate Player objects (i.e. players traded in the middle of the season to a new team)
3- Calculates the league average / standard deviations for relevant 9-cat fantasy stats
4- Computes player's fantasy ratings using Z-scores based on league averages / standard deviations
5- Sorts players based on ranking and prints positive ranking players to console
