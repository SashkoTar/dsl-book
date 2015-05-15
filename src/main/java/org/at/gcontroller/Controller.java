package org.at.gcontroller;

/**
 * Created by otarasenko on 4/21/15.
 */
public class Controller {

    private State currentState;
    private StateMachine machine;

    public CommandChannel getCommandChannel() {
        return commandsChannel;
    }
    private CommandChannel commandsChannel;
    public void handle(String eventCode) {
        if (currentState.hasTransition(eventCode))
            transitionTo(currentState.targetState(eventCode));
        else if (machine.isResetEvent(eventCode))
            transitionTo(machine.getStart());
        // Игнорирование неизвестных событий
    }
    private void transitionTo(State target) {
        currentState = target;
        currentState.executeActions(commandsChannel);
    }

}
