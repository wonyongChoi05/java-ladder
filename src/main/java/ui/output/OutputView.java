package ui.output;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_NOT_FOUND = "플레이어를 찾을 수 없습니다";
    private static final String EXECUTE_LADDER_RESULT = "사다리 결과";
    private static final String EXECUTE_RESULT = "실행 결과";
    private static final String ALL = "all";
    private static final String SPACE = " ";
    private static final String COLON = " : ";

    public static void printLadderResult(final Players players, final Lines lines, final Results results) {
        System.out.println("\n" + EXECUTE_LADDER_RESULT + "\n");
        printPlayersName(players);
        printLadder(lines, players);
        printResults(results);
    }

    private static void printPlayersName(Players players) {
        int maxPlayerNameLength = getMaxLength(players);
        calculateNamePosition(players, maxPlayerNameLength);
    }

    private static int getMaxLength(Players players) {
        return players.getPlayers().stream()
                .mapToInt(player -> player.getName().length())
                .max()
                .orElseThrow();
    }

    private static void calculateNamePosition(Players players, int maxPlayerNameLength) {
        players.getPlayers()
                .forEach(player ->
                        System.out.print(SPACE.repeat(maxPlayerNameLength - player.getName().length()) + player.getName() + SPACE)
                );
        System.out.println();
    }

    private static void printLadder(Lines lines, Players players) {
        lines.getLines()
                .forEach(line ->{
                            System.out.println(LadderShape.getLadderForm(line.getPoints(), getMaxLength(players)));
                });
    }

    private static void printResults(Results results) {
        for (Result result : results.getResults()) {
            System.out.print(" ".repeat(5 - result.getPrize().length()) + result.getPrize() + SPACE);
        }
        System.out.println();
    }

    public static void printExecuteResult(Players players, Results results, String playerName) {
        System.out.println("\n" + EXECUTE_RESULT);
        if (playerName.equals(ALL)) {
            printAll(players, results);
            return;
        }
        printDetail(results, playerName);
    }

    private static void printAll(Players players, Results results) {
        List<Result> resultList = results.getResults();
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        for (String playerName : playerNames) {
            Result result = findResult(resultList, playerName);
            System.out.println(playerName + COLON + result.getPrize());
        }
    }

    private static Result findResult(List<Result> resultList, String playerName) {
        return resultList.stream()
                .filter(r -> r.getPlayerName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_FOUND));
    }

    private static void printDetail(Results results, String playerName) {
        results.getResults().stream()
                .filter(result -> result.getPlayerName().equals(playerName))
                .forEach(result -> {
                    System.out.println(result.getPlayerName() + COLON + result.getPrize());
                });
    }

}
