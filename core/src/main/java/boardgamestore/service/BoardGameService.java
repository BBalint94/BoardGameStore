package boardgamestore.service;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;

import java.util.Collection;

public interface BoardGameService {

    Collection<BoardGame> listAllBoardGame();
    BoardGame getBoardGame(String id) throws NoMatchingID;
    void addBoardGame(BoardGame boardGame) throws AlreadyExist;
    void updateBoardGame(BoardGame boardGame) throws BoardGameNotExist, NoMatchingID;
    void increaseQuantity(String id, int quantity) throws BoardGameNotExist, NoMatchingID, CanNotBeNegativeNumber;
    void newPrice(String id, double price) throws CanNotBeNegativeNumber, NoMatchingID, BoardGameNotExist;
    void deleteBoardGame(BoardGame boardGame) throws NoMatchingID;
    void deleteBoardGame(String id) throws NoMatchingID;
    Collection<BoardGame> listBoardGamesByName(String name);
    Collection<BoardGame> listBoardGamesBySuggestedAge(int age);
    Collection<BoardGame> listBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory, MissingParam;
    Collection<BoardGame> listBoardGamesByMechanisms(Collection<String> mechanisms) throws NotFoundMechanism, MissingParam;
    Collection<BoardGame> listComingSoonBoardGames();
}
