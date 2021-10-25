# Exercise Solution: Assembly Line

## Notes
> Simulating real world scenario means however that it has to be working properly and effectively in all scenario that 
> can happen in real life (eg. handling parallel requests, managing edge cases etc.)

The "real life" concurrency has been tackled with mutual exclusion for station instances. A single station can't be 
occupied by two different AssemblyCarEntity. This concern has been implemented non-invasively, just wrapper around the 
stations, so it can be also ignored. As a result the AssemblyCarEntities are processed in a pipeline, so station steps 
are performed on different pieces at a moment. (`AssemblyLineIntegrationTest`).

## Advice for improvements:
The Quality Assurance station builds the car according to the text of the exercise.
> Our assembly line is made by the following four stations:
> - 1 - painting station
> - 2 - mechanic assembly
> - 3 - interior parts assembly
> - 4 - quality assurance **and build**

In my opinion the **building shouldn't be responsibility of the quality assurance station**, but the AssemblyLine. Once
the stations are applied successfully, the car object can be built by `AssemblyLine`. 

Thus multiple QA stations 
could be introduced, so the responsibility of checking different parts on the job can be distributed among the 
QA station classes & smaller QA steps can be performed over the process and could be followed by other building tasks.

Example:
- EngineStation, 
- GearboxStation, 
- DifferentialStation, 
- DriveTrainQaStation, 
- InteriorStation, 
- PaintingStation,
- VisualsQaStation 
- FinalQaStation

(The `build()` method is called by `AssemblyLine`).

## Thanks, was fun!
Thanks for the exercise, I had fun identifying the edge-cases, looking for options for abstraction and applying well-known patterns. :)
