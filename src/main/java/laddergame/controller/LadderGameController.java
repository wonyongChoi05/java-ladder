package laddergame.controller;

import laddergame.NameList;
import laddergame.domain.ladder.Ladder;
import laddergame.domain.ladder.NormalLadderFactory;
import laddergame.domain.ladder.LadderHeight;
import laddergame.domain.player.PlayersNamesFactory;
import laddergame.domain.result.GameResult;
import laddergame.domain.reward.RewardsNamesFactory;
import laddergame.view.InputView;
import laddergame.view.OutputView;

import java.util.Optional;

public class LadderGameController {
	public static final String QUIT_COMMAND = "quit";
	public static final String ALL_COMMAND = "all";

	private GameResult ladderGameResult;

	public LadderGameController() {
	}

	public void init() {
		NameList players = assignPlayers();
		NameList rewards = assignRewards(players);
		LadderHeight ladderHeight = assignLadderHeight();
		Ladder ladder = NormalLadderFactory.getInstance().create(ladderHeight, players.getSize());
		this.ladderGameResult = GameResult.of(players, rewards, ladder);

		printLadderGameScreen(players, ladder, rewards);
	}


	private NameList assignPlayers() {
		PlayersNamesFactory playersFactory = new PlayersNamesFactory(InputView.inputPlayers());
		try {
			return playersFactory.create();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return assignPlayers();
		}
	}

	private NameList assignRewards(NameList players) throws IllegalArgumentException {
		RewardsNamesFactory rewardsFactory = new RewardsNamesFactory(InputView.inputRewards());
		try {
			NameList rewards = rewardsFactory.create();
			checkPlayersWithRewards(players, rewards);
			return rewards;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return assignRewards(players);
		}
	}


	private LadderHeight assignLadderHeight() {
		try {
			return LadderHeight.of(InputView.inputLadderHeight());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return assignLadderHeight();
		}
	}

	private void checkPlayersWithRewards(NameList players, NameList rewards) {
		if (!rewards.isSizeEqual(players)) {
			throw new IllegalArgumentException("결과를 참여자와 같은 개수로 입력하세요");
		}
	}

	private void printLadderGameScreen(NameList players, Ladder ladder, NameList rewards) {
		OutputView.showMessageOfExecution();
		OutputView.showPlayers(players);
		OutputView.showLadder(ladder);
		OutputView.showRewards(rewards);
	}

	public void proceedGame() {
		Optional<String> optCommand;
		do {
			optCommand = Optional.ofNullable(InputView.inputCommand());
		} while (proceedWithCommand(optCommand.orElse(ALL_COMMAND)));
	}

	private boolean proceedWithCommand(final String command) {
		try {
			return checkAndShowCommand(command.trim());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	private boolean checkAndShowCommand(final String command) {
		if (command.equals(QUIT_COMMAND)) {
			OutputView.quitGame();
			return false;
		}
		if (command.equals(ALL_COMMAND)) {
			OutputView.showAllResult(this.ladderGameResult.getAllGameResultFormat());
			return true;
		}
		OutputView.showResult(this.ladderGameResult.getGameResultFormat(command));
		return true;
	}
}