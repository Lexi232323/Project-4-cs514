import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AI_PlayerOne implements WheelOfFortunePlayer{
    private String lettersList;
    private int indexLettersList;
    private Set<String> previousPopularWords;
    public AI_PlayerOne(){
        previousPopularWords=new HashSet<>();
        indexLettersList=0;
        lettersList="eariotnslcudpmhgbfywkvxzjq";
    }
    // change nextGuess for AI-I to guess
    @Override
    public char nextGuess(String previousGuesses){
        char guess=0;
        StringBuilder hiddenPhraseAddSpace=new StringBuilder(WheelOfFortune.hiddenPhrase);
        for(int i=0; i<hiddenPhraseAddSpace.length();i++){
            if(!Character.isLetter(hiddenPhraseAddSpace.charAt(i))){
                hiddenPhraseAddSpace.setCharAt(i,' ');
            }
        }
        String[]targetWords=hiddenPhraseAddSpace.toString().split("");
        int indexWord;
        boolean foundWord=false;
        for(indexWord=0;indexWord<targetWords.length;indexWord++){
            if(targetWords[indexWord].chars().filter(ch->ch=='*').count()==1){
                foundWord=true;
                break;
            }
        }
        if(foundWord) {
            List<String> wordsList = null;
            try {
                wordsList = Files.readAllLines(Paths.get("popular_words.txt"));
            } catch (IOException e) {
                System.out.println(e);
                return guess;
            }
            int wordCount = targetWords[indexWord].length();
            for (String word : wordsList) {
                if (wordCount != word.length() || this.previousPopularWords.contains(word)) {
                    continue;
                }
                int matchCount = 0;
                int indexOfAsterisk = 0;
                for (int i = 0; i < wordCount; i++) {
                    if (targetWords[indexWord].charAt(i) != '*') {
                        if (targetWords[indexWord].charAt(i) == word.charAt(i)) {
                            matchCount++;}
                    } else {
                        indexOfAsterisk = i;
                    }
                }
                if (matchCount == wordCount - 1) {
                    guess = word.charAt(indexOfAsterisk);
                    this.previousPopularWords.add(word);
                    if (previousGuesses.contains("" + guess)) {
                        System.out.println(guess);
                        return guess;
                    }
                }
            }
        }
        while (indexLettersList < lettersList.length()) {
            guess = this.lettersList.charAt(indexLettersList);
            indexLettersList++;
            if (!previousGuesses.contains("" + guess)) {
                System.out.println(guess);
                return guess;
            }
        }
        return guess;
    }
    //give AI-I an ID
    @Override
    public String playerId(){return "AI_Playerone";}
    // reset
    @Override
    public void reset(){
        previousPopularWords=new HashSet<>();
        indexLettersList=0;
    }

    @Override
    public String toString() {
        return "AI_PlayerOne{" +
                "lettersList='" + lettersList + '\'' +
                ", indexLettersList=" + indexLettersList +
                ", previousPopularWords=" + previousPopularWords +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AI_PlayerOne that = (AI_PlayerOne) o;
        return indexLettersList == that.indexLettersList && Objects.equals(lettersList, that.lettersList) && Objects.equals(previousPopularWords, that.previousPopularWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lettersList, indexLettersList, previousPopularWords);
    }
}

