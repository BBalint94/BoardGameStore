package service.impl;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;
import boardgamestore.model.Mechanism;
import boardgamestore.service.BoardGameService;
import dao.BoardGameDAO;

import java.time.LocalDate;
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

    @Override
    public Collection<BoardGame> listBoardGamesByPlayers(String players) {
        Collection<BoardGame> boardGames = listAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for (BoardGame b : boardGames){
            if(b.getPlayers().equalsIgnoreCase(players)){
                result.add(b);
            }
        }
        return result;
    }

    public Collection<BoardGame> listBoardGamesBySuggestedAge(int age) {
        Collection<BoardGame> boardGames = listAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for (BoardGame b : boardGames){
            if(b.getSuggestedAge() <= age){
                result.add(b);
            }
        }
        return result;
    }

    public Collection<BoardGame> listBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory, MissingParam {
        if(categories.size() == 0){
            throw new MissingParam("category");
        }
        Collection<BoardGame> boardGames = listAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        Category[] catArray = Category.values();
        boolean containsCategory;
        for(String c : categories){
            if(c == null){
                throw new MissingParam("category");
            }
            containsCategory = false;
            for(int i=0;i<catArray.length;i++){
                if(catArray[i].toString().equals(c)){
                    containsCategory = true;
                }
            }
            if(!containsCategory){
                throw new NotFoundCategory(c);
            }
        }
        boolean addToResult;
        for (BoardGame b : boardGames) {
            addToResult = false;
            for (String c : categories) {
                if (!b.getCategories().toString().contains(c)) {
                    addToResult = false;
                    break;
                }else {
                    addToResult = true;
                }
            }
            if(addToResult){
                result.add(b);
            }
        }
        return result;
    }

    public Collection<BoardGame> listBoardGamesByMechanisms(Collection<String> mechanisms) throws NotFoundMechanism, MissingParam {
        if(mechanisms.size()==0){
            throw new MissingParam("mechanism");
        }
        Collection<BoardGame> boardGames = listAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        Mechanism[] mechArray = Mechanism.values();
        boolean containsMechanism;
        for (String m : mechanisms){
            if(m == null){
                throw new MissingParam("mechanism");
            }
            containsMechanism = false;
            for(int i=0;i<mechArray.length;i++){
                if(mechArray[i].toString().equals(m)){
                    containsMechanism = true;
                }
            }
            if(!containsMechanism){
                throw new NotFoundMechanism(m);
            }
        }
        boolean addToResult;
        for (BoardGame b : boardGames){
            addToResult = false;
            for(String m : mechanisms){
                if(!b.getMechanisms().toString().contains(m)){
                    addToResult = false;
                    break;
                }else{
                    addToResult = true;
                }
            }
            if(addToResult){
                result.add(b);
            }
        }
        return result;
    }

    public Collection<BoardGame> listComingSoonBoardGames() {
        Collection<BoardGame> boardGames = listAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for(BoardGame b : boardGames){
            if(b.getReleaseDate().isAfter(LocalDate.now())){
                result.add(b);
            }
        }
        return result;
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
