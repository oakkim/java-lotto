package cc.oakk.lotto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {
    public static final int NUMBER_COUNT = 6;
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("Lotto's number size must be %d!", NUMBER_COUNT));
        }

        List<Integer> validatedList = numbers.stream()
                .map(Lotto::throwIfNotValidNumber)
                .sorted(Comparator.comparingInt(n -> n))
                .collect(Collectors.toList());

        this.numbers = Collections.unmodifiableList(validatedList);
    }

    public static Lotto of(int first, int second, int third, int fourth, int fifth, int sixth) {
        List<Integer> numbers = Stream.of(first, second, third, fourth, fifth, sixth)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public Rank compare(Lotto target) {
        int matchingCount = (int) numbers.stream()
                .filter(target.numbers::contains)
                .count();
        return Rank.getRankByDifferentCount(NUMBER_COUNT - matchingCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    private static int throwIfNotValidNumber(int target) {
        if (target < MIN_NUMBER || target > MAX_NUMBER) {
            throw new IllegalArgumentException(String.format("%d is not between 0 and %d.", target, MAX_NUMBER));
        }
        return target;
    }
}
