package boardgamestore.controller;

import boardgamestore.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String mewthod(Exception e){
        return "Method is incorrect" ;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public String jsonmappingexception(Exception e){
        return e.getLocalizedMessage();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public String mediatype(Exception e){
        return "Use one of the followings: "+ MediaType.APPLICATION_JSON_VALUE ;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void testIllegalArgument(Exception e){
        System.out.println(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public String handleNotFoundError(NoHandlerFoundException ex, HttpServletRequest request) {
        return "404";
    }
}
