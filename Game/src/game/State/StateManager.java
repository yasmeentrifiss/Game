/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.State;

import java.awt.Graphics2D;

/**
 *
 * @author Yasmeen
 */
public class StateManager {

    private boolean paused;
    private final PauseState pauseState;

    private final State[] states;
    private int currentState;
    private int previousState;

    public static final int NUM_STATES = 4;
    public static final int INTRO = 0;
    public static final int MENU = 1;
    public static final int PLAY = 2;
    public static final int GAMEOVER = 3;

    public StateManager() {

        paused = false;
        pauseState = new PauseState(this);

        states = new State[NUM_STATES];
        setState(INTRO);

    }

    public void setState(int i) {

        previousState = currentState;
        unloadState(previousState);
        currentState = i;

        switch (i) {

            case INTRO:
                states[i] = new Level2State(this);
                states[i].init();
                break;

            case MENU:
                states[i] = new MenuState(this);
                states[i].init();
                break;

            case PLAY:
                states[i] = new PlayState(this);
                states[i].init();
                break;

            case GAMEOVER:
                states[i] = new GameOverState(this);
                states[i].init();
                break;
        }
    }

    public void unloadState(int i) {
        states[i] = null;
    }

    public void setPaused(boolean b) {
        paused = b;
    }

    public void update() {
        if (paused) {
            pauseState.update();
        } else if (states[currentState] != null) {
            states[currentState].update();
        }
    }

    public void draw(Graphics2D g) {
        if (paused) {
            pauseState.draw(g);
        } else if (states[currentState] != null) {
            states[currentState].draw(g);
        }
    }

    public void keyPressed(int k) {
        states[currentState].keyPressed(k);
        
    }

    public void keyReleased(int k) {
        states[currentState].keyReleased(k);
    }
    
    public void keyTyped(int k) {
        states[currentState].keyTyped(k);
    }

}