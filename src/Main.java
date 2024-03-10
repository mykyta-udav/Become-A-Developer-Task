import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        Path path = Paths.get("input.txt");

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        }

        List<Integer> longestIncSeq = findLongestIncreasingSequence(numbers);
        List<Integer> longestDecSeq = findLongestDecreasingSequence(numbers);
        Collections.sort(numbers);

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);


        double median;
        if (numbers.size() % 2 == 0) {
            median = ((double) numbers.get(numbers.size() / 2 - 1) + numbers.get(numbers.size() / 2)) / 2;
        } else {
            median = numbers.get(numbers.size() / 2);
        }

        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(Double.NaN);

        System.out.printf("Max: %d, Min: %d, Median: %.2f, Average: %.2f%n", max, min, median, average);
        System.out.println("Найбільша зростаюча послідовність: " + longestIncSeq);
        System.out.println("Найбільша зменшувальна послідовність: " + longestDecSeq);

    }

    public static List<Integer> findLongestIncreasingSequence(List<Integer> numbers) {
        if (numbers.isEmpty()) return Collections.emptyList();

        List<Integer> currentSeq = new ArrayList<>();
        List<Integer> maxSeq = new ArrayList<>();

        currentSeq.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                currentSeq.add(numbers.get(i));
            } else {
                if (currentSeq.size() > maxSeq.size()) {
                    maxSeq = new ArrayList<>(currentSeq);
                }
                currentSeq.clear();
                currentSeq.add(numbers.get(i));
            }
        }

        if (currentSeq.size() > maxSeq.size()) {
            maxSeq = currentSeq;
        }

        return maxSeq;
    }

    public static List<Integer> findLongestDecreasingSequence(List<Integer> numbers) {
        if (numbers.isEmpty()) return Collections.emptyList();

        List<Integer> currentSeq = new ArrayList<>();
        List<Integer> maxSeq = new ArrayList<>();

        currentSeq.add(numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < numbers.get(i - 1)) {
                currentSeq.add(numbers.get(i));
            } else {
                if (currentSeq.size() > maxSeq.size()) {
                    maxSeq = new ArrayList<>(currentSeq);
                }
                currentSeq.clear();
                currentSeq.add(numbers.get(i));
            }
        }

        if (currentSeq.size() > maxSeq.size()) {
            maxSeq = currentSeq;
        }

        return maxSeq;
    }
}