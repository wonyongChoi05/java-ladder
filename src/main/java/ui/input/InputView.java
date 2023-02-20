package ui.input;

import java.util.Scanner;

/**
 * @author 베베
 * @version 1.0.0
 * @Created by 베베 on 2023. 02. 20.
 */
public class InputView {

    private static final Scanner sc = new Scanner(System.in);
    private static final String INPUT_PLAYERS_NAME = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String INPUT_LADDER_HEIGHT = "최대 사다리 높이는 몇 개인가요?";

    public static String getPlayersName() {
        System.out.println(INPUT_PLAYERS_NAME);
        return sc.next();
    }

    public static int getLadderHeight() {
        System.out.println(INPUT_LADDER_HEIGHT);
        return sc.nextInt();
    }
}
