import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<List<String>> matrix1 = new ArrayList<>();
    static List<List<String>> matrix2 = new ArrayList<>();

    public static void reader(String path) throws IOException {
        File file = new File(path);
        if(path.endsWith(".csv")) {
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                String rows = scan.nextLine();
                if(!rows.equals("")) {
                    matrix1.add(getData(rows));
                } else {
                    break;
                }
            }
            while (scan.hasNext()) {
                String rows = scan.nextLine();
                if(!rows.equals("")) {
                    matrix2.add(getData(rows));
                } else {
                    break;
                }
            }
            scan.close();
        } else {
            System.err.println("Invalid File Path");
            System.exit(0);
        }
    }

    private static List<String> getData(String row) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(row)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static boolean checkMatrix() {
        if (matrix1.get(0).size() == matrix2.size()) {
            if (matrix1.size() <= 3 && matrix1.get(0).size() <= 3 && matrix2.size() <= 3 && matrix2.get(0).size() <= 3) {
                return true;
            }
        }
        return false;
    }

    public static void performDotProduct() {
        System.out.println("\nMulti-threaded Dot Product Calculator");

        System.out.println("\nMatrix 1:");
        matrix1.forEach(System.out::println);

        System.out.println("\nMatrix 2:");
        matrix2.forEach(System.out::println);

        if (checkMatrix() == true) {
            System.out.println("\nAnswer:");
            for (int i=0; i < matrix1.size(); i++) {
                Thread t1 = new Thread(new DotProductCalc(i, matrix1, matrix2));
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted Exception Error");
                }
            }
        } else {
            System.err.println("\nInvalid Matrices!");
        }
    }

    public static void main (String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Input the csv file name: ");
            String path = input.nextLine();
            reader(path);
            performDotProduct();
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }
    }
}
