package edu.miu;

import edu.miu.movielens.functions.FunctionUtils;
import edu.miu.utils.PseudoDatabase;

public class Application {
    // instantiate database
    private static final PseudoDatabase database = PseudoDatabase.getInstance();

    public static void main(String[] args) {
        FunctionUtils.topKActorAppearedInLeadingRoleInAGivenYear
                .apply(database.getMovieActors(), 5, 2016)
                .forEach(System.out::println);
    }
}
