package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomValueGeneratorTest {

    @DisplayName("True 또는 False가 반환되어야 한다.")
    @Test
    void 참_또는_거짓() {
        RandomValueGenerator randomValueGenerator = new RandomValueGenerator();
        assertThat(randomValueGenerator.getRandomValue())
                .isExactlyInstanceOf(Boolean.class);
    }

}
