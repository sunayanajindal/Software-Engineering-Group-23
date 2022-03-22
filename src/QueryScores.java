import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class QueryScores {
    private static String SCOREHISTORY_DAT = "SCOREHISTORY.DAT";
    //private int highestScore, lowestScore, individualAverage;
    //private int overallHighest, overallLowest, overallAverage;
    private static Hashtable<String, String> allStats = new Hashtable<String, String>();

    public static String updateStats(String nickName, String flag) throws IOException {
        int highestScore = -1, lowestScore = 100000, num_ind_records = 0;
        int overallHighest = -1, overallLowest = 100000, num_records = 0;
        String bestPlayer, worstPlayer;
        String data;
        int score;
        Double individualAvg = 0.0, overallAvg = 0.0;
        BufferedReader in = new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        System.out.println("nickname - " + nickName);
        while ((data = in.readLine()) != null) {
            String[] rowData = data.split("\t");

            score = Integer.parseInt(rowData[2]);
            if( nickName.equals("-")){
                System.out.println(rowData[0] + "  " + rowData[2] );
                num_records += 1;
                overallAvg += score;
                if (overallHighest < score){
                    overallHighest = score;
                    bestPlayer = rowData[0];
                    allStats.put("Best player", bestPlayer);
                }
                if (overallLowest > score){
                    overallLowest = score;
                    worstPlayer = rowData[0];
                    allStats.put("Worst player", worstPlayer);
                }
            }
            else{
                if (rowData[0].equals(nickName)){
                    //System.out.println(rowData[0] + "  " + rowData[1] + "  " + rowData[2] );
                    //System.out.println(rowData[0] + " " + rowData[2]);
                    num_ind_records += 1;
                    individualAvg += score;
                    if (highestScore < score)
                        highestScore = score;
                    if (lowestScore > score)
                        lowestScore = score;

                }
            }
        }
        if (num_ind_records > 0)
            individualAvg /= num_ind_records;
        else
            individualAvg = -1.0;
        overallAvg /= num_records;

        allStats.put("Highest score", Integer.toString(highestScore));    //  0 - highest score
        allStats.put("Lowest score", Integer.toString(lowestScore));     //  1 - lowest score
        allStats.put("overall highest", Integer.toString(overallHighest));  //  4 - overall highest
        allStats.put("overall lowest", Integer.toString(overallLowest));   //  5 - overall lowest
        allStats.put("Average score", Double.toString(individualAvg));    //  6 - individual average
        allStats.put("Average overall score", Double.toString(overallAvg));       //  7 - overall average
        System.out.println(allStats);
        System.out.println(allStats.get(flag));
        return allStats.get(flag);
    }
}
