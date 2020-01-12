package boardgamestore.service;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.exception.NotFoundCategory;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;

import java.util.Collection;

public interface BoardGameService {

    Collection<BoardGame> listAllBoardGame();
    BoardGame getBoardGame(String id) throws NoMatchingID;
    void addBoardGame(BoardGame boardGame);
    void updateBoardGame(BoardGame boardGame);
    void deleteBoardGame(BoardGame boardGame);
    void deleteBoardGame(String id) throws NoMatchingID;
    Collection<BoardGame> listBoardGamesByName(String name);
    Collection<BoardGame> listBoardGamesBySuggestedAge(int age);
    Collection<BoardGame> listBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory;
}
