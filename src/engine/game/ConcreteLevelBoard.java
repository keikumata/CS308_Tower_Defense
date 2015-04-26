package engine.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import engine.fieldsetting.Settable;
import gameworld.GameWorld;


/**
 * This class contains all levels that exist in a game.
 *
 * @authors Sierra Smith, Cosette Goldstein
 *
 */
@Settable
public class ConcreteLevelBoard extends Observable implements LevelBoard {
private String myTitle="";
    private List<Level> myLevels;
    private int curLevelIndex;

    public ConcreteLevelBoard () {
        myLevels = new ArrayList<Level>();
        curLevelIndex = 0;
    }

    @Override
    public void addLevel (Level newLevel) {
        myLevels.add(newLevel);
    }

    /**
     * This method is called every frame to update the current level and check if the level has
     * ended.
     * If it has ended, notify the View.
     */
    @Override
    public void update () {
        if (myLevels.get(curLevelIndex).isWon() || myLevels.get(curLevelIndex).isLost()) {
            System.out.println("level over");
            setChanged();
            notifyObservers();
        }
        else {
            myLevels.get(curLevelIndex).update();
        }
    }

    /**
     * Returns true if the final level is won or if any level is lost.
     *
     * @return
     */
    @Override
    public boolean gameOver () {
        boolean answer =
                ((curLevelIndex == myLevels.size() - 1) && (myLevels.get(curLevelIndex).isWon())) ||
                        myLevels.get(curLevelIndex).isLost();
        return answer;
    }

    @Override
    public String getCurrentLevelMap () {
        return myLevels.get(curLevelIndex).getLevelBackground();
    }

    // note: do we really need this method? no other object knows how to deal with a "level"
    // rather, should the method just be called update current level? which can change the level
    @Override
    public void startNextLevel () {
        // TODO Auto-generated method stub
        curLevelIndex++;
    }

    @Override
    public GameWorld getGameWorld () {
        return myLevels.get(curLevelIndex).getGameWorld();
    }

    @Settable
    public void setLevels (List<Level> levels) {
        myLevels = levels;
    }

    @Override
    public Level getCurrentLevel () {
        if (myLevels.size() > curLevelIndex) {
            return myLevels.get(curLevelIndex);
        }
        return null;
    }
}
