package service.impl;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;
import boardgamestore.service.BoardGameService;
import dao.BoardGameDAO;

import java.util.ArrayList;
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
        Collection<BoardGame> boardGames = listAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for (BoardGame b : boardGames){
            if(b.getName().equalsIgnoreCase(name)){
                result.add(b);
            }
        }
        return result;
    }

    public Collection<BoardGame> listBoardGamesBySuggestedAge(int age) {
        return dao.readBoardGamesBySuggestedAge(age);
    }

    public Collection<BoardGame> listBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory, MissingParam {
        return dao.readBoardGamesByCategories(categories);
    }

    public Collection<BoardGame> listBoardGamesByMechanisms(Collection<String> mechanisms) throws NotFoundMechanism, MissingParam {
        return dao.readBoardGamesByMechanisms(mechanisms);
    }

    public Collection<BoardGame> listComingSoonBoardGames() {
        return dao.readComingSoonBoardGames();
    }

    public void addBoardGame(BoardGame boardGame) throws AlreadyExist {
        dao.createBoardGame(boardGame);
    }

    public void updateBoardGame(BoardGame boardGame) throws BoardGameNotExist, NoMatchingID {
        dao.updateBoardGame(boardGame);
    }

    public void increaseQuantity(String id, int quantity) throws BoardGameNotExist, NoMatchingID, CanNotBeNegativeNumber {
        Collection<BoardGame> boardGames = listAllBoardGame();
        if(quantity < 0){
            throw new CanNotBeNegativeNumber("quantity ("+quantity+")");
        }
        BoardGame updated = null;
        for(BoardGame b : boardGames){
            if(b.getId().equalsIgnoreCase(id)){
                updated = b;
                updated.setQuantity(b.getQuantity()+quantity);
            }
        }
        if(updated == null){
            throw new NoMatchingID(id);
        }
        dao.updateBoardGame(updated);
    }

    public void newPrice(String id, double price) throws CanNotBeNegativeNumber, NoMatchingID, BoardGameNotExist {
        Collection<BoardGame> boardGames = listAllBoardGame();
        if(price < 0){
            throw new CanNotBeNegativeNumber("price ("+price+")");
        }
        BoardGame updated = null;
        for(BoardGame b : boardGames){
            if(b.getId().equalsIgnoreCase(id)){
                updated = b;
                updated.setPrice(price);
            }
        }
        if(updated == null){
            throw new NoMatchingID(id);
        }
        dao.updateBoardGame(updated);
    }

    public void deleteBoardGame(BoardGame boardGame) throws NoMatchingID {
        dao.deleteBoardGame(boardGame);
    }

    public void deleteBoardGame(String id) throws NoMatchingID {
        BoardGame boardGame = dao.readBoardGameById(id);
        dao.deleteBoardGame(boardGame);
    }
}
