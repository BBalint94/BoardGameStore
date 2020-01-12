package dao;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;

import java.util.Collection;

public interface BoardGameDAO {

    void createBoardGame(BoardGame boardGame) throws AlreadyExist;
    Collection<BoardGame> readAllBoardGame();
    void updateBoardGame(BoardGame boardGame);
    void deleteBoardGame(BoardGame boardGame) throws NoMatchingID;
    BoardGame readBoardGameById (String id) throws NoMatchingID;
    Collection<BoardGame> readBoardGamesByName (String name);
    Collection<BoardGame> readBoardGamesBySuggestedAge (int age);
    Collection<BoardGame> readBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory, MissingParam;
    Collection<BoardGame> readBoardGamesByMechanisms(Collection<String> mechanisms) throws NotFoundMechanism, MissingParam;
    Collection<BoardGame> readComingSoonBoardGames();
}
