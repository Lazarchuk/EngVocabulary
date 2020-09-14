package voc.old;

public class WordDaoOld /*implements Dao*/ {

    /*@PersistenceContext
    private EntityManager em;
    private List<String> errors;

    @Override
    public int getWordsQuantity() {
        return 0;
    }

    // If such word is exist in DB, retur it id, else - return 0
    @Override
    public int findWordInDB(String requestWord){
        String query = "SELECT id FROM vocabulary WHERE word_eng='" +requestWord.toLowerCase()+ "'";
        int id = 0;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Word> getAll() {
        String query = "SELECT * FROM vocabulary";
        List<Word> words = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Word word = new Word();
                word.setId(resultSet.getInt("id"));
                word.setWordEng(resultSet.getString("word_eng"));
                word.setWordUkr(resultSet.getString("word_ukr"));
                word.setSpeechPart(resultSet.getString("speech_part"));
                word.setExample(resultSet.getString("example"));
                word.setCategory(resultSet.getString("category"));
                word.setMeaning(resultSet.getString("meaning"));
                words.add(word);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return words;
    }

    @Override
    public Word getWord(String requestWord) {
        Word word = null;
        String query = "SELECT * FROM vocabulary WHERE word_eng=?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, requestWord.toLowerCase());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                word = new Word();
                word.setId(resultSet.getInt("id"));
                word.setWordEng(resultSet.getString("word_eng"));
                word.setWordUkr(resultSet.getString("word_ukr"));
                word.setSpeechPart(resultSet.getString("speech_part"));
                word.setExample(resultSet.getString("example"));
                word.setCategory(resultSet.getString("category"));
                word.setMeaning(resultSet.getString("meaning"));

                // Reading synonys, antonyms
                String synonymsList = resultSet.getString("synonyms");
                String antonymsList = resultSet.getString("antonyms");
                word.setSynonyms(readJsonList(synonymsList));
                word.setAntonyms(readJsonList(antonymsList));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return word;
    }

    @Override
    public int insertWord(Word word) {
        String query = "INSERT INTO vocabulary (word_eng, word_ukr, speech_part, category, meaning, example) VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        if (isValidNewWord(word.getWordEng(), word.getWordUkr())) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, word.getWordEng().toLowerCase());
                ps.setString(2, word.getWordUkr().toLowerCase());
                ps.setString(3, word.getSpeechPart());
                ps.setString(4, word.getCategory());
                ps.setString(5, word.getMeaning());
                ps.setString(6, word.getExample());
                result = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int editWord(Word word) {
        String query = "UPDATE vocabulary SET word_eng=?, word_ukr=?, speech_part=?, category=?, meaning=?, example=? WHERE id=?";
        int result = 0;
        if (isValidEditWord(word.getWordEng(), word.getWordUkr())) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, word.getWordEng().toLowerCase());
                ps.setString(2, word.getWordUkr().toLowerCase());
                ps.setString(3, word.getSpeechPart());
                ps.setString(4, word.getCategory());
                ps.setString(5, word.getMeaning());
                ps.setString(6, word.getExample());
                ps.setInt(7, word.getId());
                result = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean addSynonym(String wordEng, String synonym){
        boolean result = false;
        int synonymId = findWordInDB(synonym);
        String jsonList = null;
        String query = "UPDATE vocabulary SET synonyms=? WHERE word_eng=?";
        Word targetWord = getWord(wordEng);
        List<String> list = targetWord.getSynonyms();
        // If the synonym exists in DB
        if (synonymId != 0){
            //If synonym exists in synonyms list
            if (isWordExistInJsonMap(synonym, list)){
                errors = new ArrayList<>();
                errors.add("This synonym has already exist");
                result = false;
            }
            // If the synonym doesn't exist in synonyms list
            else {
                list.add(synonym.toLowerCase());
                jsonList = writeJsonList(list);
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setString(1, jsonList);
                    ps.setString(2, targetWord.getWordEng());
                    int resultUpdate = ps.executeUpdate();
                    if (resultUpdate == 1) {
                        result = true;
                    } else {
                        errors = new ArrayList<>();
                        errors.add("Insertion failure");
                        result = false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        // If the synonym doesn't exist in DB, add this synonym to DB and to synonyms list
        else {
            Word newWord = new Word();
            newWord.setWordEng(synonym);
            newWord.setWordUkr("");
            newWord.setSpeechPart("");
            newWord.setCategory("");
            newWord.setMeaning("");
            newWord.setExample("");
            int insertResult = insertWord(newWord);
            if (insertResult != 0){
                list.add(synonym.toLowerCase());
                jsonList = writeJsonList(list);
                try (PreparedStatement ps = connection.prepareStatement(query)){
                    ps.setString(1, jsonList);
                    ps.setString(2, targetWord.getWordEng());
                    int resultUpdate = ps.executeUpdate();
                    if (resultUpdate == 1){
                        result = true;
                    }
                    else {
                        errors = new ArrayList<>();
                        errors.add("Insertion failure");
                        result = false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean addAntonym(String wordEng, String antonym){
        boolean result = false;
        int antonymId = findWordInDB(antonym);
        String jsonList = null;
        String query = "UPDATE vocabulary SET antonyms=? WHERE word_eng=?";
        Word targetWord = getWord(wordEng);
        List<String> list = targetWord.getAntonyms();
        // If the antonym exists in DB
        if (antonymId != 0){
            //If antonym exists in antonyms list
            if (isWordExistInJsonMap(antonym, list)){
                errors = new ArrayList<>();
                errors.add("This antonym has already exist");
                result = false;
            }
            // If the antonym doesn't exist in antonyms list
            else {
                list.add(antonym.toLowerCase());
                jsonList = writeJsonList(list);
                try (PreparedStatement ps = connection.prepareStatement(query)){
                    ps.setString(1, jsonList);
                    ps.setString(2, targetWord.getWordEng());
                    int resultUpdate = ps.executeUpdate();
                    if (resultUpdate == 1){
                        result = true;
                    }
                    else {
                        errors = new ArrayList<>();
                        errors.add("Insertion failure");
                        result = false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // If the antonym doesn't exist in DB, add this antonym to DB and to antonyms list
        else {
            Word newWord = new Word();
            newWord.setWordEng(antonym);
            newWord.setWordUkr("");
            newWord.setSpeechPart("");
            newWord.setCategory("");
            newWord.setMeaning("");
            newWord.setExample("");
            int insertResult = insertWord(newWord);
            if (insertResult != 0){
                list.add(antonym.toLowerCase());
                jsonList = writeJsonList(list);
                try (PreparedStatement ps = connection.prepareStatement(query)){
                    ps.setString(1, jsonList);
                    ps.setString(2, targetWord.getWordEng());
                    int resultUpdate = ps.executeUpdate();
                    if (resultUpdate == 1){
                        result = true;
                    }
                    else {
                        errors = new ArrayList<>();
                        errors.add("Insertion failure");
                        result = false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean deleteSynonym(String wordEng, String synonym){
        boolean result = false;
        String jsonList = null;
        String query = "UPDATE vocabulary SET synonyms=? WHERE word_eng=?";
        Word targetWord = getWord(wordEng);
        List<String> list = targetWord.getSynonyms();
        list.remove(synonym);
        jsonList = writeJsonList(list);
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, jsonList);
            ps.setString(2, targetWord.getWordEng());
            int resultUpdate = ps.executeUpdate();
            if (resultUpdate == 1){
                result = true;
            }
            else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteAntonym(String wordEng, String antonym){
        boolean result = false;
        String jsonList = null;
        String query = "UPDATE vocabulary SET antonyms=? WHERE word_eng=?";
        Word targetWord = getWord(wordEng);
        List<String> list = targetWord.getAntonyms();
        list.remove(antonym);
        jsonList = writeJsonList(list);
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, jsonList);
            ps.setString(2, targetWord.getWordEng());
            int resultUpdate = ps.executeUpdate();
            if (resultUpdate == 1){
                result = true;
            }
            else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addCategory(String targetWord, String category){
        boolean result = false;
        String query = "UPDATE vocabulary SET category=? WHERE word_eng=?";
        if (category.length() > 40){
            errors = new ArrayList<>();
            errors.add("Category should not be longer than 40 characters");
            result = false;
        }
        else {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, category);
                ps.setString(2, targetWord);
                int resultUpdate = ps.executeUpdate();
                if (resultUpdate == 1){
                    result = true;
                }
                else {
                    errors = new ArrayList<>();
                    errors.add("Insertion failure");
                    result = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<String> getAllCategories() {
        String query = "SELECT category FROM vocabulary";
        List<String> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String category = resultSet.getString("category");
                if (!categories.contains(category) && !category.equals("")){
                    categories.add(category);
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    private boolean isValidNewWord(String word, String translation){
        boolean valid = true;
        errors = new ArrayList<>();
        if (findWordInDB(word) != 0){
            errors.add("This word has already exist");
            valid = false;
        }
        if (word.length() > 40){
            errors.add("The word should not be longer than 40 characters");
            valid = false;
        }
        if (word.length() == 0){
            errors.add("Enter new word");
            System.out.println("Word " + word);
            valid = false;
        }
        if (translation.length() > 60){
            errors.add("The translation should not be longer than 60 characters");
            valid = false;
        }
        return valid;
    }

    private boolean isValidEditWord(String word, String translation){
        boolean valid = true;
        errors = new ArrayList<>();
        if (word.length() > 40){
            errors.add("The word should not be longer than 40 characters");
            valid = false;
        }
        if (translation.length() > 60){
            errors.add("The translation should not be longer than 60 characters");
            valid = false;
        }
        if (word.length() == 0){
            errors.add("Enter new word");
            valid = false;
        }
        return valid;
    }

    private boolean isWordExistInJsonMap(String requestSyn, List<String> synonymsList){
        boolean result = false;
        for (String synonym: synonymsList){
            if (synonym.equals(requestSyn.toLowerCase())){
                result = true;
                break;
            }
        }
        return result;
    }

    private List<String> readJsonList(String listJSON) {
        List<String> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        if (listJSON != null && !listJSON.equals("")) {
            try {
                list = mapper.readValue(listJSON, List.class);
            } catch(JsonMappingException e){
                e.printStackTrace();
            } catch(JsonParseException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return list;
    }

    private String writeJsonList(List<String> list){
        String listJson = null;
        ObjectMapper mapper = new ObjectMapper();
        if (list != null){
            try {
                listJson = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return listJson;
    }
*/
}
