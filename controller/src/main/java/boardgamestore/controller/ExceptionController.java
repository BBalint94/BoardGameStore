package boardgamestore.controller;

import boardgamestore.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoMatchingID.class)
    @ResponseBody
    public String handlerNoMatchingId(Exception e){
        return "ID not found in the database: "+e.getMessage();
    }

    @ExceptionHandler(NotFoundCategory.class)
    @ResponseBody
    public String handlerNotFoundCategory(Exception e){ return "Not found this category: "+e.getMessage(); }

    @ExceptionHandler(MissingParam.class)
    @ResponseBody
    public String handlerMissingParam(Exception e){ return "Missing parameter: "+e.getMessage(); }

    @ExceptionHandler(NotFoundMechanism.class)
    @ResponseBody
    public String handlerNotFoundMechanism(Exception e){ return "Not found this mechanism: "+e.getMessage(); }

    @ExceptionHandler(AlreadyExist.class)
    @ResponseBody
    public String handlerAlreadyExist(Exception e){ return "Already exists: "+e.getMessage(); }

    @ExceptionHandler(BoardGameNotExist.class)
    @ResponseBody
    public String handlerBoardGameNotExist(Exception e){ return "Board game not exist: "+e.getMessage(); }

    @ExceptionHandler(CanNotBeNegativeNumber.class)
    @ResponseBody
    public String handlerCanNotBeNegativeNumber(Exception e){ return "Can not be negative number: "+e.getMessage(); }
}
