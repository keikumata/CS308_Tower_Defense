package gae.gameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.gameobject.GameObject;
import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;
import engine.interactions.Interaction;
import engine.interactions.InteractionEngine;
import engine.interactions.NoInteraction;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;

/**
 * Class that acts as the data holder of all interactions authored. This will hold the interactions
 * that will be exported out to the engine.
 * 
 * @author Brandon Choi
 *
 */

public class InteractionData {

    private static final List<String> MAP_KEYS = Arrays.asList("Collide", "Do not collide", "Shoot", "Do not shoot");
    //private static final List<Interaction> MAP_VALUES = Arrays.asList();
    
    private List<InteractionEngine> myInteractionEngines;
    private InteractionEngine myCollisions;
    private InteractionEngine myShoots;
    private Map<String, Interaction> interactionMap;

    public InteractionData () {
        myInteractionEngines = new ArrayList<>();
        myCollisions = new CollisionEngine();
        myShoots = new RangeEngine();
        myInteractionEngines.addAll(Arrays.asList(myCollisions, myShoots));
        interactionMap = new HashMap<>();
        fillMap();
    }

    /**
     * returns both interaction engines with respective interactions set in each of them
     * 
     * @return
     */
    public List<InteractionEngine> getEngines () {
        return myInteractionEngines;
    }

    /**
     * returns map of strings that are mapped to the type of interaction in the engine
     * 
     * @return
     */
    public Map<String, Interaction> getInteractionMap () {
        return interactionMap;
    }

    /**
     * fills the map with the correct corresponding values between strings and interactions
     */
    private void fillMap () {
        MAP_KEYS.forEach(e -> {
            interactionMap.put(e, null);
        });
    }

    /**
     * adds interactions based on one list of objects that interact in a certain way with another
     * list of objects
     * 
     * @param one
     * @param i
     * @param two
     */
    public void addInteraction (List<GameObject> one, Interaction i, List<GameObject> two) {
        if (i instanceof BuffImparter) {
            massPut(one, i, two, myCollisions);
        }
        else if (i instanceof ShootAt) {
            massPut(one, i, two, myShoots);
        }
        else {
            massPut(one, new NoInteraction(), two, myCollisions);
            massPut(one, new NoInteraction(), two, myShoots);
        }
    }

    /**
     * places all the interactions between list one and two by iterating through each of them and
     * setting all iterations
     * 
     * @param one
     * @param i
     * @param two
     * @param engine
     */
    private void massPut (List<GameObject> one,
                          Interaction i,
                          List<GameObject> two,
                          InteractionEngine engine) {
        one.forEach(e -> {
            two.forEach(f -> {
                engine.put(e.getLabel(), f.getLabel(), i);
            });
        });
    }
}
