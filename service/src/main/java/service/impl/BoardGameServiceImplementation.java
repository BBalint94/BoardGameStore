package service.impl;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.model.BoardGame;
import boardgamestore.service.BoardGameService;
import dao.BoardGameDAO;

import java.util.Collection;

public class BoardGameServiceImplementation implements BoardGameService {

    BoardGameDAO dao;

    public BoardGameServiceImplementation(BoardGameDAO dao) {
        this.dao = dao;
    }

    public Collection<BoardGame> listAllBoardGame() {
        return dao.readAllBoardGame();
    }

    public BoardGame getBoardGame(String id) throws NoMatchingID {
        return dao.readBoardGameById(id);
    }

    public Collection<BoardGame> listBoardGamesByName(String name) {
        return dao.readBoardGamesByName(name);
    }

    public void addBoardGame(BoardGame boardGame) {
        dao.createBoardGame(boardGame);
    }

    public void updateBoardGame(BoardGame boardGame) {
        dao.updateBoardGame(boardGame);
    }

    public void deleteBoardGame(BoardGame boardGame) {
        dao.deleteBoardGame(boardGame);
    }

    public void deleteBoardGame(String id) throws NoMatchingID {
        BoardGame boardGame = dao.readBoardGameById(id);
        dao.deleteBoardGame(boardGame);
    }
}
