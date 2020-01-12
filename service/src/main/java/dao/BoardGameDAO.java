package dao;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;

import java.util.Collection;

public interface BoardGameDAO {

    void createBoardGame(BoardGame boardGame) throws AlreadyExist;
    Collection<BoardGame> readAllBoardGame();
    void updateBoardGame(BoardGame boardGame) throws NoMatchingID, BoardGameNotExist;
    void deleteBoardGame(BoardGame boardGame) throws NoMatchingID;
    BoardGame readBoardGameById (String id) throws NoMatchingID;
    Collection<BoardGame> readComingSoonBoardGames();
}
