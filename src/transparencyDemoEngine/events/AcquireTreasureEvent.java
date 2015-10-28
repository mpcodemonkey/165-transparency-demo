package transparencyDemoEngine.events;

import java.util.UUID;

import sage.event.AbstractGameEvent;

public class AcquireTreasureEvent extends AbstractGameEvent 
{ 
 public static final long eventType = 
 UUID.randomUUID().getLeastSignificantBits(); 
 //public long getType() { return eventType; } 
 public String getName() { return new String("Acquire treasure event"); } 
 // programmer-defined parts go here 
 //nothing to see here yet
} 

