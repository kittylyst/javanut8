package javanut8.ch05.sealed;

public class SeasonsMain {

    public static void main(String[] args) {
        var season = Seasons.AUTUMN;
        var temp = switch(season) {
            case WINTER -> 2.0;
            case SPRING -> 10.5;
            case SUMMER -> 24.5;
            case AUTUMN -> 16.0;
        };
        System.out.println("Average temp: "+ temp);
    }

}
