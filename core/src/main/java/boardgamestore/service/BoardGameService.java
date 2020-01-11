package boardgamestore.service;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.model.BoardGame;

import java.util.Collection;

public interface BoardGameService {

    Collection<BoardGame> listAllBoardGame();
    BoardGame getBoardGame(String id) throws NoMatchingID;
    void addBoardGame(BoardGame boardGame);
    void updateBoardGame(BoardGame boardGame);
    void deleteBoardGame(BoardGame boardGame);
    void deleteBoardGame(String id) throws NoMatchingID;
    Collection<BoardGame> listBoardGamesByName(String name);
}
