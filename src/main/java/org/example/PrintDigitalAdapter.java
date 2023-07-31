package org.example;

import it.unimore.dipi.iot.wldt.adapter.digital.DigitalAdapter;
import it.unimore.dipi.iot.wldt.core.state.*;
import it.unimore.dipi.iot.wldt.exception.EventBusException;
import it.unimore.dipi.iot.wldt.exception.WldtDigitalTwinStateEventException;

import java.util.stream.Collectors;

/* To make it work, do the following:
 *   1. Sync the Digital Adapter with the DT state in the "onDigitalTwinSync" callback
 *   2. Expose to the external world what is happening populating:
 *       a. Properties: "onStateChangePropertyUpdated"
 *       b. Events: "onDigitalTwinStateEventNotificationReceived"
 *       c. Actions: "onStateChangeActionUpdated"
 *       d. Relationships: "onStateChangeRelationshipCreated" or "onStateChangeRelationshipInstanceCreated"
 */

public class PrintDigitalAdapter extends DigitalAdapter<String>{
    public PrintDigitalAdapter(String adapterId) {
        super(adapterId, "");
    }

    //////////////////////// PROPERTIES VARIATIONS CALLBACKS /////////////////////////////////////////////////////
    protected void onStateChangePropertyCreated(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //to define;
    };

    protected void onStateChangePropertyDeleted(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //to define;
    };

    protected void onStatePropertyUpdated(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //to define;
    };

    @Override
    protected void onStateChangePropertyUpdated(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        System.out.println(digitalTwinStateProperty.getKey() + " property is " + digitalTwinStateProperty.getValue());
    };

    protected void onStatePropertyDeleted(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //to define;
    };

    //////////////////////// ACTIONS VARIATIONS CALLBACKS /////////////////////////////////////////////////////
    protected void onStateChangeActionEnabled(DigitalTwinStateAction digitalTwinStateAction){
        //to define;
    };

    protected void onStateChangeActionUpdated(DigitalTwinStateAction digitalTwinStateAction){
        //to define;
    };

    protected void onStateChangeActionDisabled(DigitalTwinStateAction digitalTwinStateAction){
        //to define;
    };

    //////////////////////// EVENTS VARIATIONS CALLBACKS /////////////////////////////////////////////////////
    protected void onStateChangeEventRegistered(DigitalTwinStateEvent digitalTwinStateEvent){
        //to define;
    };

    protected void onStateChangeEventRegistrationUpdated(DigitalTwinStateEvent digitalTwinStateEvent){
        //to define;
    };

    protected void onStateChangeEventUnregistered(DigitalTwinStateEvent digitalTwinStateEvent){
        //to define;
    };


    //////////////////////// EVENTS NOTIFICATION CALLBACK /////////////////////////////////////////////////////
    @Override
    protected void onDigitalTwinStateEventNotificationReceived(DigitalTwinStateEventNotification<?> digitalTwinStateEventNotification){
        /*
        System.out.println(digitalTwinStateEventNotification.getDigitalEventKey() + " event is " +
                            digitalTwinStateEventNotification.getBody());
         */
    };

    //////////////////////// RELATIONSHIPS CALLBACKS /////////////////////////////////////////////////////

    protected void onStateChangeRelationshipCreated(DigitalTwinStateRelationship<?> digitalTwinStateRelationship){
        //to define;
    };

    protected void onStateChangeRelationshipInstanceCreated(DigitalTwinStateRelationshipInstance<?> digitalTwinStateRelationshipInstance){
        //to define;
    };

    protected void onStateChangeRelationshipDeleted(DigitalTwinStateRelationship<?> digitalTwinStateRelationship){
        //to define;
    };

    protected void onStateChangeRelationshipInstanceDeleted(DigitalTwinStateRelationshipInstance<?> digitalTwinStateRelationshipInstance){
        //to define;
    };

    //////////////////////// ADAPTER CALLBACKS /////////////////////////////////////////////////////
    public void onAdapterStart(){
        //to define;
    };

    public void onAdapterStop(){
        //to define;
    };


    //////////////////////// DT CALLBACKS /////////////////////////////////////////////////////
    @Override
    public void onDigitalTwinSync(IDigitalTwinState digitalTwinState)   // It syncs events
    {
        try {
            digitalTwinState.getEventList()
                    .map(eventList -> eventList.stream()
                            .map(DigitalTwinStateEvent::getKey)
                            .collect(Collectors.toList()))
                    .ifPresent(eventKeys -> {
                        try {
                            observeDigitalTwinEventsNotifications(eventKeys);
                        } catch (EventBusException e) {
                            e.printStackTrace();
                        }
                    });
        } catch(WldtDigitalTwinStateEventException e) {
            e.printStackTrace();
        }
    }

    public void onDigitalTwinUnSync(IDigitalTwinState digitalTwinState){
        //to define;
    }

    public void onDigitalTwinCreate(){
        //to define;
    };

    public void onDigitalTwinStart(){
        //to define;
    };

    public void onDigitalTwinStop(){
        //to define;
    };

    public void onDigitalTwinDestroy(){
        //to define;
    };
}
