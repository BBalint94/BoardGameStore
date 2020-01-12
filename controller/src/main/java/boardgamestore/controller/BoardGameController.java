package boardgamestore.controller;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public String removeBoardGame(@RequestBody(required = false) BoardGame boardGame) throws NoMatchingID, MissingParam {
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

    @RequestMapping(value = "/removeBoardGameById",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String removeBoardGameById(@RequestBody(required = false) String id) throws NoMatchingID, MissingParam {
        if(id == null){
            throw new MissingParam("id");
        }
        service.deleteBoardGame(id);
        return "Board game deleted: "+id;
    }

    @RequestMapping(value = "/boardGames",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> showAllBoardGame(){
        return service.listAllBoardGame();
    }

    @RequestMapping(value = "/boardGame",method = RequestMethod.GET)
    @ResponseBody
    public BoardGame getBoardGameById(@RequestParam(required = false) String id) throws NoMatchingID, MissingParam {
        if(id == null){
            throw new MissingParam("id");
        }
        return service.getBoardGame(id);
    }

    @RequestMapping(value = "/boardGame/{id:[0-9a-fA-F]{8}\\\\-[0-9a-fA-F]{4}\\\\-[0-9a-fA-F]{4}\\\\-[0-9a-fA-F]{4}\\\\-[0-9a-fA-F]{12}}",method = RequestMethod.GET)
    @ResponseBody
    public BoardGame getBoardGame(@PathVariable() String id) throws NoMatchingID, MissingParam {
        if(id == null){
            throw new MissingParam("id");
        }
        return service.getBoardGame(id);
    }

    @RequestMapping(value = "/boardGamesByName",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getBoardGamesByName(@RequestParam(required = false) String name) throws MissingParam {
        if(name == null){
            throw new MissingParam("name");
        }
        return service.listBoardGamesByName(name);
    }

    @RequestMapping(value = "/boardGamesByAge",method = RequestMethod.GET)
    @ResponseBody
    public Collection<BoardGame> getBoardGamesByAge(@RequestParam(required = false) Integer age) throws MissingParam {
        if(age == null){
            throw new MissingParam("age");
        }
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

    @RequestMapping(value = "/updateBoardGame",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateBoardGame(@RequestBody(required = false) BoardGame boardGame) throws BoardGameNotExist, NoMatchingID {
        service.updateBoardGame(boardGame);
        return "Board game updated: "+boardGame.getName()+" ("+boardGame.getId()+")";
    }

    @RequestMapping(value = "/increaseQuantity",method = RequestMethod.GET)
    @ResponseBody
    public String increaseQuantity(@RequestParam(required = false) String id, @RequestParam(required = false) Integer quantity) throws CanNotBeNegativeNumber, NoMatchingID, BoardGameNotExist, MissingParam {
        if(id == null){
            throw new MissingParam("id");
        }
        if(quantity == null){
            throw new MissingParam("quantity");
        }
        service.increaseQuantity(id,quantity);
        return "Increased board game ("+id+") quantity ("+quantity+")";
    }

    @RequestMapping(value = "/newPrice",method = RequestMethod.GET)
    @ResponseBody
    public String newPrice(@RequestParam(required = false) String id, @RequestParam(required = false) Double price) throws BoardGameNotExist, CanNotBeNegativeNumber, NoMatchingID, MissingParam {
        if(id == null){
            throw new MissingParam("id");
        }
        if(price == null){
            throw new MissingParam("price");
        }
        service.newPrice(id,price);
        return "Updated board game's price: $"+price+" ("+id+")";
    }

}
