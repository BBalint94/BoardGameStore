package boardgamestore.controller;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.exception.NotFoundCategory;
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
}
