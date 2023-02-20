package javanut8.ch02;

import java.util.List;

public class SwitchExpressions {
    public static void main(String[] args) {
        var input = args.length <= 0 ? "O" : args[0];
        Boolean yesOrNo = null;
        switch(input) {
            case "y":
            case "Y":
                yesOrNo = true;
                break;
            case "n":
            case "N":
                yesOrNo = false;
                break;
            default:
                throw new IllegalArgumentException("Response must be Y or N");
        }

        yesOrNo = switch(input) {
            case "y" -> true;
            case "Y" -> true;
            case "N" -> false;
            case "n" -> false;
            default -> throw new IllegalArgumentException("Response must be Y or N");
        };

        yesOrNo = switch(input) {
            case "y", "Y" -> true;
            case "n", "N" -> false;
            default -> throw new IllegalArgumentException("Response must be Y or N");
        };

        yesOrNo = switch(input) {
            case "y", "Y" -> { System.out.println("Sure"); yield true; }
            case "n", "N" -> { System.out.println("Nope"); yield false; }
            default -> throw new IllegalArgumentException("Response must be Y or N");
        };

        switch(input) {
            case "y", "Y" -> System.out.println("Sure");
            case "n", "N" -> System.out.println("Nope");
            default -> throw new IllegalArgumentException("Response must be Y or N");
        }

        System.out.println(yesOrNo);
    }

    private static List<String> maybe() {
       return List.of("n");
    }
}
