package boardgamestore.controller;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;
import boardgamestore.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class BoardGameController {

    BoardGameService service;

    public BoardGameController(@Autowired BoardGameService service) {
        this.service = service;
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public String showBoardGameCount(){
        return String.valueOf(service.listAllBoardGame().size());
    }

    @RequestMapping(value = "/addBoardGame",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addBoardGame(@RequestBody(required = false) BoardGame boardGame) throws AlreadyExist, MissingParam {
            if(boardGame.getId() == null){
                throw new MissingParam("id");
            }else if(boardGame.getName() == null){
                throw new MissingParam("name");
            }else if(boardGame.getReleaseDate() == null){
                throw new MissingParam("releaseDate");
            }else if(boardGame.getPlayers() == null){
                throw new MissingParam("players");
            }else if(boardGame.getPlayTime() == null){
                throw new MissingParam("playTime");
            }else if(boardGame.getSuggestedAge() == 0){
                throw new MissingParam("suggestedAge");
            }else if(boardGame.getCategories() == null){
                throw new MissingParam("categories");
            }else if(boardGame.getMechanisms() == null){
                throw new MissingParam("mechanisms");
            }else if(boardGame.getPrice() == 0){
                throw new MissingParam(("price"));
            }
            service.addBoardGame(boardGame);
            return "New board game added: "+boardGame.getName()+ " ("+boardGame.getId()+")";
    }

    @RequestMapping(value = "/removeBoardGame",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String removeBoardGame(@RequestBody BoardGame boardGame) throws NoMatchingID, MissingParam {
        if(boardGame.getId() == null){
            throw new MissingParam("id");
        }else if(boardGame.getName() == null){
            throw new MissingParam("name");
        }else if(boardGame.getReleaseDate() == null){
            throw new MissingParam("releaseDate");
        }else if(boardGame.getPlayers() == null){
            throw new MissingParam("players");
        }else if(boardGame.getPlayTime() == null){
            throw new MissingParam("playTime");
        }else if(boardGame.getSuggestedAge() == 0){
            throw new MissingParam("suggestedAge");
        }else if(boardGame.getCategories() == null){
            throw new MissingParam("categories");
        }else if(boardGame.getMechanisms() == null){
            throw new MissingParam("mechanisms");
        }else if(boardGame.getPrice() == 0){
            throw new MissingParam(("price"));
        }
        service.deleteBoardGame(boardGame);
        return "Board game deleted: "+boardGame.getName()+" ("+boardGame.getId()+")";
    }

    @RequestMapping(value = "/boardGames",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> showAllBoardGame(){
        return service.listAllBoardGame();
    }

    @RequestMapping(value = "/boardGame",method = RequestMethod.GET)
    @ResponseBody
    public BoardGame getBoardGameById(@RequestParam(required = false) String id) throws NoMatchingID {
        return service.getBoardGame(id);
    }

    @RequestMapping(value = "/boardGamesByName",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getBoardGamesByName(@RequestParam(required = false) String name){
        return service.listBoardGamesByName(name);
    }

    @RequestMapping(value = "/boardGamesByAge",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getBoardGamesByAge(@RequestParam(required = false) int age){
        return service.listBoardGamesBySuggestedAge(age);
    }

    @RequestMapping(value = "/boardGamesByCategory",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getBoardGamesByCategory(@RequestParam(required = false) Collection<String> category) throws NotFoundCategory, MissingParam {
        return service.listBoardGamesByCategories(category);
    }

    @RequestMapping(value = "/boardGamesByMechanism",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getBoardGamesByMechanism(@RequestParam(required = false) Collection<String> mechanism) throws NotFoundMechanism, MissingParam {
        return service.listBoardGamesByMechanisms(mechanism);
    }

    @RequestMapping(value = "/comingSoon",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getComingSoonBoardGames(){
        return service.listComingSoonBoardGames();
    }

}
