package boardgamestore.service;

import boardgamestore.model.BoardGame;

import java.util.Collection;

public interface BoardGameService {

    Collection<BoardGame> listAllBoardGame();
    BoardGame getBoardGame(int id);
    void addBoardGame(BoardGame boardGame);
    void updateBoardGame(BoardGame boardGame);
    void deleteBoardGame(BoardGame boardGame);
    void deleteBoardGame(int id);
}
