import java.util.ArrayList;
import java.util.List;

public class DotProductCalc implements Runnable {

    private List<String> row;
    private List<Integer> finalA = new ArrayList<Integer>();
    int answer = 0;

    public DotProductCalc(int n, List<List<String>> matrix1, List<List<String>> matrix2) {
        row = matrix1.get(n);
        for (int i=0; i < matrix2.get(0).size(); i++) {
            for (int j=0; j < matrix2.size(); j++) {
                int rowMatrix1 = Integer.parseInt(row.get(j));
                int colMatrix2 = Integer.parseInt(matrix2.get(j).get(i));
                answer += rowMatrix1 * colMatrix2;
            }
            finalA.add(answer);
            answer = 0;
        }
    }

    @Override
    public void run() {
        System.out.println(finalA);
    }
}
