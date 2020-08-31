package cc.oakk.lotto.model.prizeprovider.impl;

import cc.oakk.lotto.model.MoneyPrize;
import cc.oakk.lotto.model.Rank;
import cc.oakk.lotto.model.prizeprovider.LottoPrizeProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SimpleLottoPrizeProviderTest {
    @Test
    public void getPrizeByRank() {
        final LottoPrizeProvider<Long> provider = new SimpleLottoPrizeProvider();

        assertThat(provider.getPrizeByRank(Rank.FIRST)).isEqualTo(new MoneyPrize(2_000_000_000L));
        assertThat(provider.getPrizeByRank(Rank.SECOND)).isEqualTo(new MoneyPrize(30_000_000L));
        assertThat(provider.getPrizeByRank(Rank.THIRD)).isEqualTo(new MoneyPrize(1_500_000L));
        assertThat(provider.getPrizeByRank(Rank.FOURTH)).isEqualTo(new MoneyPrize(50_000L));
        assertThat(provider.getPrizeByRank(Rank.FIFTH)).isEqualTo(new MoneyPrize(5_000L));
        assertThat(provider.getPrizeByRank(Rank.NONE)).isEqualTo(new MoneyPrize(0L));

        assertThatIllegalArgumentException().isThrownBy(() -> provider.getPrizeByRank(null));
    }
}
