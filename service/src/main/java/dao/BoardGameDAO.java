package dao;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.model.BoardGame;

import java.util.Collection;

public interface BoardGameDAO {

    void createBoardGame(BoardGame boardGame);
    Collection<BoardGame> readAllBoardGame();
    void updateBoardGame(BoardGame boardGame);
    void deleteBoardGame(BoardGame boardGame);
    BoardGame readBoardGameById (String id) throws NoMatchingID;
}
