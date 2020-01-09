package boardgamestore.controller;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ExceptionHandler(NoMatchingID.class)
    @ResponseBody
    public String handlerNoMatchingId(Exception e){
        return "ID not found in the database: "+e.getMessage();
    }
}
