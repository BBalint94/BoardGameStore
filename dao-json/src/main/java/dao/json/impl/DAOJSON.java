package dao.json.impl;

import boardgamestore.exception.*;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;
import boardgamestore.model.Mechanism;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.BoardGameDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;

public class DAOJSON implements BoardGameDAO {

    File jsonFile;
    ObjectMapper mapper ;

    public DAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;
        if (!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public DAOJSON(String jsonFilePath) {
        this.jsonFile = new File(jsonFilePath);
        if (!jsonFile.exists()){
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    @Override
    public void createBoardGame(BoardGame boardGame) throws AlreadyExist {
        Collection<BoardGame> boardGames = readAllBoardGame();
        for(BoardGame b : boardGames){
            if(b.getId().equalsIgnoreCase(boardGame.getId())){
                System.out.println(b.getId()+" : "+boardGame.getId());
                throw new AlreadyExist(b.getName()+" ("+b.getId()+")");
            }
        }
        boardGames.add(boardGame);
        try{
            mapper.writeValue(jsonFile,boardGames);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Collection<BoardGame> readAllBoardGame() {
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        try{
            result = mapper.readValue(jsonFile, new TypeReference<ArrayList<BoardGame>>() {
            });
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateBoardGame(BoardGame boardGame) throws BoardGameNotExist {
        Collection<BoardGame> boardGames = readAllBoardGame();
        boolean modify = false;
        for(BoardGame b : boardGames){
            if(b.getId().equalsIgnoreCase(boardGame.getId())){
                b.setName(boardGame.getName());
                b.setPlayers(boardGame.getPlayers());
                b.setPlayTime(boardGame.getPlayTime());
                b.setSuggestedAge(boardGame.getSuggestedAge());
                b.setReleaseDate(boardGame.getReleaseDate());
                b.setCategories(boardGame.getCategories());
                b.setMechanisms(boardGame.getMechanisms());
                b.setPrice(boardGame.getPrice());
                b.setQuantity(boardGame.getQuantity());
                modify = true;
            }
        }
        if(!modify){
            throw new BoardGameNotExist(boardGame.getId());
        }
        try{
            mapper.writeValue(jsonFile,boardGames);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBoardGame(BoardGame boardGame) throws NoMatchingID {
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        try{
            BoardGame deleted = readBoardGameById(boardGame.getId());
            for (BoardGame b : boardGames){
                if(!(b.getId().equalsIgnoreCase(deleted.getId()))){
                    result.add(b);
                }
            }
            mapper.writeValue(jsonFile,result);
        }catch (NoMatchingID noMatchingID){
            throw new NoMatchingID(boardGame.getId());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public BoardGame readBoardGameById(String id) throws NoMatchingID {
        Collection<BoardGame> boardGames = readAllBoardGame();
        for (BoardGame b : boardGames){
            if(b.getId().equalsIgnoreCase(id)){
                return b;
            }
        }
        throw new NoMatchingID(id);
    }

    @Override
    public Collection<BoardGame> readBoardGamesBySuggestedAge(int age) {
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for (BoardGame b : boardGames){
            if(b.getSuggestedAge() <= age){
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public Collection<BoardGame> readBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory, MissingParam {
        if(categories.size() == 0){
            throw new MissingParam("category");
        }
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        Category[] catArray = Category.values();
        boolean containsCategory;
        for(String c : categories){
            if(c.isEmpty()){
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

    @Override
    public Collection<BoardGame> readBoardGamesByMechanisms(Collection<String> mechanisms) throws NotFoundMechanism, MissingParam {
        if(mechanisms.size()==0){
            throw new MissingParam("mechanism");
        }
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        Mechanism[] mechArray = Mechanism.values();
        boolean containsMechanism;
        for (String m : mechanisms){
            if(m.isEmpty()){
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

    @Override
    public Collection<BoardGame> readComingSoonBoardGames() {
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for(BoardGame b : boardGames){
            if(b.getReleaseDate().isAfter(LocalDate.now())){
                result.add(b);
            }
        }
        return result;
    }
}
