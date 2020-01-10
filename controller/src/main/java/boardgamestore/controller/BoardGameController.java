package boardgamestore.controller;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.model.BoardGame;
import boardgamestore.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addBoardGame",method = RequestMethod.POST)
    @ResponseBody
    public String addBoardGame(@RequestBody BoardGame boardGame){
        service.addBoardGame(boardGame);
        return "New board game added: "+boardGame.getName()+ " ("+boardGame.getId()+")";
    }

    @RequestMapping(value = "/removeBoardGame",method = RequestMethod.DELETE)
    @ResponseBody
    public String removeBoardGame(@RequestBody BoardGame boardGame){
        service.deleteBoardGame(boardGame);
        return "Board game deleted: "+boardGame.getName()+" ("+boardGame.getId()+")";
    }

    @ExceptionHandler(NoMatchingID.class)
    @ResponseBody
    public String handlerNoMatchingId(Exception e){
        return "ID not found in the database: "+e.getMessage();
    }
}
