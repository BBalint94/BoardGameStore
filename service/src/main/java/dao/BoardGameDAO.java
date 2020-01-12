package dao;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.exception.NotFoundCategory;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;

import java.util.Collection;

public interface BoardGameDAO {

    void createBoardGame(BoardGame boardGame);
    Collection<BoardGame> readAllBoardGame();
    void updateBoardGame(BoardGame boardGame);
    void deleteBoardGame(BoardGame boardGame);
    BoardGame readBoardGameById (String id) throws NoMatchingID;
    Collection<BoardGame> readBoardGamesByName (String name);
    Collection<BoardGame> readBoardGamesBySuggestedAge (int age);
    Collection<BoardGame> readBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory;
}
