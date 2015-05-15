package org.at.gcontroller;

/**
 * Created by otarasenko on 4/21/15.
 */
public class BasicStateMachine  extends   StateMachineBuilder{
        Events doorClosed, drawerOpened, lightOn, panelClosed;
        Commands unlockPanel, lockPanel, lockDoor, unlockDoor;
        States idle, active, waitingForLight,
                waitingForDrawer, unlockedPanel;
        ResetEvents doorOpened;
        protected void defineStateMachine() {
            doorClosed.   code("D1CL");
            drawerOpened. code("D2OP");
            lightOn.      code("L1ON");
            panelClosed.  code("PNCL");
            doorOpened.   code("D1OP");
            unlockPanel.  code("PNUL");
            lockPanel.    code("PNLK");
            lockDoor.     code("D1LK");
            unlockDoor.   code("D1UL");
            idle
                    .actions(unlockDoor, lockPanel)
                    .transition(doorClosed).to(active)
            ;
            active
                    .transition(drawerOpened).to(waitingForLight)
                    .transition(lightOn). to(waitingForDrawer)
            ;
            waitingForLight
                    .transition(lightOn).to(unlockedPanel)
            ;
            waitingForDrawer
                    .transition(drawerOpened).to(unlockedPanel)
            ;
            unlockedPanel
                    .actions(unlockPanel, lockDoor)
                    .transition(panelClosed).to(idle)
            ;
        }
    }
