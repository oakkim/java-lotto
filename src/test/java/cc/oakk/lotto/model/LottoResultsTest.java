package cc.oakk.lotto.model;

import cc.oakk.lotto.model.Lotto;
import cc.oakk.lotto.model.LottoResults;
import cc.oakk.lotto.model.Lottos;
import cc.oakk.lotto.model.Rank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultsTest {
    LottoResults results;

    @BeforeEach
    public void beforeEach() {
        Lotto winningLotto = Lotto.of(1, 3, 5, 7, 9, 11);
        Lottos lottos = new Lottos(Arrays.asList(
                Lotto.of(1, 2,3, 4, 5, 6),
                Lotto.of(1, 3, 5, 7, 9,  11),
                Lotto.of(1, 3, 5, 7, 9,  10),
                Lotto.of(1, 3, 5, 7, 10,  11),
                Lotto.of(1, 4, 8, 12, 16, 20),
                Lotto.of(1, 14, 18, 22, 26, 30)));

        results = lottos.getResults(winningLotto);
    }

    @Test
    public void getRankCount() {
        assertThat(results.getRankCount(Rank.FIRST)).isEqualTo(1);
        assertThat(results.getRankCount(Rank.SECOND)).isEqualTo(2);
        assertThat(results.getRankCount(Rank.THIRD)).isEqualTo(0);
        assertThat(results.getRankCount(Rank.FOURTH)).isEqualTo(1);
        assertThat(results.getRankCount(Rank.FIFTH)).isEqualTo(0);
        assertThat(results.getRankCount(Rank.NONE)).isEqualTo(2);
    }

    @Test
    public void calculateRevenue() {
        LottoPrizeProvider<?> provider = new SimpleLottoPrizeProvider();
        assertThat(results.calculateRevenue(provider)).isEqualTo(2003005000L);
    }
}
