package org.example;

import it.unimore.dipi.iot.wldt.adapter.digital.DigitalAdapter;
import it.unimore.dipi.iot.wldt.core.state.*;
import it.unimore.dipi.iot.wldt.exception.EventBusException;
import it.unimore.dipi.iot.wldt.exception.WldtDigitalTwinStateEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;


public class PrintDigitalAdapter extends DigitalAdapter<String>{
    public PrintDigitalAdapter(String adapterId) {
        super(adapterId, "");
    }
    private final static Logger logger = LoggerFactory.getLogger(PrintDigitalAdapter.class);

    //////////////////////// PROPERTIES VARIATIONS CALLBACKS /////////////////////////////////////////////////////
    protected void onStateChangePropertyCreated(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //never used;
    };

    protected void onStateChangePropertyDeleted(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //never used;
    };

    protected void onStatePropertyUpdated(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //never used;
    };

    @Override
    protected void onStateChangePropertyUpdated(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        logger.info("adapter Id: [{}] --> {} property is {}", this.getId(), digitalTwinStateProperty.getKey(), digitalTwinStateProperty.getValue());
        System.out.println(digitalTwinStateProperty.getKey() + " property is " + digitalTwinStateProperty.getValue());
    };

    protected void onStatePropertyDeleted(DigitalTwinStateProperty<?> digitalTwinStateProperty){
        //never used;
    };

    //////////////////////// ACTIONS VARIATIONS CALLBACKS /////////////////////////////////////////////////////
    protected void onStateChangeActionEnabled(DigitalTwinStateAction digitalTwinStateAction){
        //never used;
    };

    protected void onStateChangeActionUpdated(DigitalTwinStateAction digitalTwinStateAction){
        //never used;
    };

    protected void onStateChangeActionDisabled(DigitalTwinStateAction digitalTwinStateAction){
        //never used;
    };

    //////////////////////// EVENTS VARIATIONS CALLBACKS /////////////////////////////////////////////////////
    protected void onStateChangeEventRegistered(DigitalTwinStateEvent digitalTwinStateEvent){
        //never used;
    };

    protected void onStateChangeEventRegistrationUpdated(DigitalTwinStateEvent digitalTwinStateEvent){
        //never used;
    };

    protected void onStateChangeEventUnregistered(DigitalTwinStateEvent digitalTwinStateEvent){
        //never used;
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
        //never used;
    };

    protected void onStateChangeRelationshipInstanceCreated(DigitalTwinStateRelationshipInstance<?> digitalTwinStateRelationshipInstance){
        //never used;
    };

    protected void onStateChangeRelationshipDeleted(DigitalTwinStateRelationship<?> digitalTwinStateRelationship){
        //never used;
    };

    protected void onStateChangeRelationshipInstanceDeleted(DigitalTwinStateRelationshipInstance<?> digitalTwinStateRelationshipInstance){
        //never used;
    };

    //////////////////////// ADAPTER CALLBACKS /////////////////////////////////////////////////////
    public void onAdapterStart(){
        //never used;
    };

    public void onAdapterStop(){
        //never used;
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
        //never used;
    }

    public void onDigitalTwinCreate(){
        //never used;
    };

    public void onDigitalTwinStart(){
        //never used;
    };

    public void onDigitalTwinStop(){
        //never used;
    };

    public void onDigitalTwinDestroy(){
        //never used;
    };
}
