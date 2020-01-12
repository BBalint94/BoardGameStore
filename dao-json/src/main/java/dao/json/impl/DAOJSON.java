package dao.json.impl;

import boardgamestore.exception.NoMatchingID;
import boardgamestore.exception.NotFoundCategory;
import boardgamestore.model.BoardGame;
import boardgamestore.model.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.BoardGameDAO;

import java.io.FileWriter;
import java.io.IOException;
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
    public void createBoardGame(BoardGame boardGame) {
        Collection<BoardGame> boardGames = readAllBoardGame();
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
    public void updateBoardGame(BoardGame boardGame) {
        Collection<BoardGame> boardGames = readAllBoardGame();
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
            }
        }
        try{
            mapper.writeValue(jsonFile,boardGames);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBoardGame(BoardGame boardGame) {
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
        }catch (IOException | NoMatchingID e){
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
        throw new NoMatchingID();
    }

    @Override
    public Collection<BoardGame> readBoardGamesByName(String name) {
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        for (BoardGame b : boardGames){
            if(b.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(b);
            }
        }
        return result;

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
    public Collection<BoardGame> readBoardGamesByCategories(Collection<String> categories) throws NotFoundCategory {
        Collection<BoardGame> boardGames = readAllBoardGame();
        Collection<BoardGame> result = new ArrayList<BoardGame>();
        Category[] catArray = Category.values();
        boolean containsCategory;
        for(String c : categories){
            containsCategory = false;
            System.out.println(categories.size());
            for(int i=0;i<catArray.length;i++){
                if(catArray[i].toString().equals(c)){
                    containsCategory = true;
                }
            }
            if(!containsCategory){
                throw new NotFoundCategory(c);
            }
        }
        for (BoardGame b : boardGames) {
            for (String c : categories) {
                if (b.getCategories().toString().contains(c)) {
                    result.add(b);
                }
            }
        }
        return result;
    }
}
