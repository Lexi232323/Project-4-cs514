import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
//This class will have much of the code from your existing WheelOfFortune class, including readPhrases, randomPhrase, getHiddenPhrase, and processGuess. It should also define an abstract method getGuess(String previousGuesses), which returns a char, thus requiring all concrete WheelOfFortune games to implement it. And of course WheelOfFortune needs to implement the abstract methods in its parent Game.
public abstract class WheelOfFortune extends GuessingGame{
    protected int remainingChances;
    protected String phrase;
    protected static StringBuilder hiddenPhrase;
    protected StringBuilder previousGuesses;
    private char guess;
    protected List<String> phraseList= new ArrayList<>();
    protected Set<Integer> usedPhraseIndex=new HashSet<>();

    // read phrase
    public WheelOfFortune(){
        readPhrases();
    }
    //get phrase from file
    public void readPhrases(){
        try{
            this.phraseList=Files.readAllLines(Paths.get("test.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    //choose a phrase from list
    public void randomPhrase() {
        Random rand = new Random();
        int index;
        while(true){
            index=rand.nextInt(phraseList.size());
            if(!usedPhraseIndex.contains(index)) {
                usedPhraseIndex.add(index);
                break;
            }
        }
        this.phrase=this.phraseList.get(index);
        this.remainingChances=(int)phrase.chars().filter(Character::isLetter).distinct().count()+4;
    }
    // concrete different method
    @Override
    public String generateHiddenPhrase(String phrase) {
        phrase=this.phrase;
        hiddenPhrase = new StringBuilder(phrase);
        int n = phrase.length();
        for (int i = 0; i < n; i++)
        {
            char ch = phrase.charAt(i);
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')
            {
                hiddenPhrase.setCharAt(i, '*');
            }
        }
        return hiddenPhrase.toString();
    }
    // concrete different method
    @Override
    public int playingProgress(){
        previousGuesses=new StringBuilder();
        this.randomPhrase();
        generateHiddenPhrase(this.phrase);
        System.out.println("Phrase: "+phrase);
        System.out.println("Welcome to WOF Game! The secret code is hidden.");
        while (remainingChances > 0 && !phrase.contentEquals(hiddenPhrase)){
            System.out.println("Hidden Phrase: "+ hiddenPhrase);
            String guesses=getGuess(previousGuesses.toString());
            guess=guesses.charAt(0);
            previousGuesses.append(guess);
            if(!processGuess()){
                remainingChances--;
                System.out.println("You Miss! You have "+remainingChances+" chance.");
            }else{
                remainingChances--;
                System.out.println("Bingo!"+ remainingChances+ " chances to guess");
                System.out.println("The Updated Hidden Phrase: "+ hiddenPhrase);
            }
        }
        System.out.println("Answer: "+ phrase);
        int score;
        if(remainingChances>=0&&hiddenPhrase.toString().equals(phrase)){
            score=10+remainingChances;
            System.out.println("You Win! You got "+score+" points.");
        }else{
            score=0;
            System.out.println("You lost.");
        }
        return score;
    }
    // new method in this game
    public boolean processGuess(){
        boolean match=false;
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(guess)== Character.toLowerCase(phrase.charAt(i))) {
                hiddenPhrase.setCharAt(i,phrase.charAt(i));
                match=true;
            }
        }

        return match;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortune that = (WheelOfFortune) o;
        return remainingChances== that.remainingChances && guess == that.guess && Objects.equals(phrase, that.phrase) && Objects.equals(previousGuesses, that.previousGuesses) && Objects.equals(phraseList, that.phraseList) && Objects.equals(usedPhraseIndex, that.usedPhraseIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainingChances, phrase, previousGuesses, guess, phraseList, usedPhraseIndex);
    }

    @Override
    public String toString() {
        return "WheelOfFortune{" +
                "remainingChanges=" + remainingChances +
                ", phrase='" + phrase + '\'' +
                ", previousGuesses=" + previousGuesses +
                ", phraseList=" + phraseList +
                ", usedPhraseIndex=" + usedPhraseIndex +
                '}';
    }
}



