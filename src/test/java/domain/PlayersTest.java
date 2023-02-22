package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersTest {

    @DisplayName("이름들을 담은 배열을 받아 Player 객체로 변환해야 한다.")
    @Test
    void convertPlayerObject() {
        List<String> names = List.of("be", "bebe", "bebebe");
        assertAll(() -> {
            assertEquals(new Players(names).getPlayers().get(0).getName(), "be");
            assertEquals(new Players(names).getPlayers().get(1).getName(), "bebe");
            assertEquals(new Players(names).getPlayers().get(2).getName(), "bebebe");
        });
    }

    @DisplayName("존재하는 player만 입력받을 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"베베", "카일"})
    void validateInputPlayerName(String playerName) {
        // given
        Players players = new Players(List.of("베베", "카일"));

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.validateInputPlayer(playerName);
        });
    }

}
