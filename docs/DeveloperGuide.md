---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **1. Introduction**

TrackPad is an app for users to create and store itineraries, to facilitate the planning of their dream vacation!
This document includes the various design choices, architecture and implementation features of TrackPad. This document 
is targeted towards developers who want to contribute to and extend our TrackPad app.

--------------------------------------------------------------------------------------------------------------------

## **2. Setting up, getting started**

Follow the [_link_](SettingUp.md) to get set up your environment and get started creating pull request for TrackPad. 

<!--Refer to the guide [_Setting up and getting started_](SettingUp.md).-->

--------------------------------------------------------------------------------------------------------------------

## **3. Design**

This section discusses the current design pattern used by TrackPad. It explains the current architecture of TrackPad. 
Then, there is a more in depth explanation on the design of the Ui, model and storage functionality. 

### 3.1 Architecture

<span style="display:block;align:center">![Architecture Class Diagram](images/devguideimages/ArchitectureDiagram.png)</span>
<div align="center"><sup style="font-size:100%"><i>Figure 1 Architecture Class Diagram</i></sup></div><br>

Figure 1 explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#36-common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#32-ui-component): The UI of the App.
* [**`Logic`**](#33-logic-component): The command executor.
* [**`Model`**](#34-model-component): Holds the data of the App in memory.
* [**`Storage`**](#35-storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

![Class Diagram of the Logic Component](images/devguideimages/LogicClassDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 2 Class Diagram of the Logic Component</i></sup></div><br>

For example, the `Logic` component (seen from Figure 2 above) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

**How the architecture components interact with each other**

![Sequence Diagram of the Various Components](images/devguideimages/ArchitectureSequenceDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 3 Sequence Diagram of the various components</i></sup></div><br>

Figure 3 above shows how the components interact with each other for the scenario where the user issues the command `delete-attraction 1`.

The sections below give more details of each component.

### 3.2 UI component

![Structure of the UI Component](images/devguideimages/UiClassDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 4 Structure of the Ui Component, Ui Class Diagram</i></sup></div><br>

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `AttractionListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

This design is similar to the Architectural design of TrackPad, whereby different UiParts are encapsulated in the MainWindow java controller class. This allows the Logic to minimise interaction with the UI, since the MainWindow manages the changes to any UiPart classes that result from the execution in the Logic component.

### 3.3 Logic

![Structure of the Logic Component](images/devguideimages/LogicClassDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 5 Structure of the Logic Component, Logic Class Diagram</i></sup></div><br>

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` in Figure 5 receives the user command.
1. It uses the `TrackPadParser` class to parse the command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an attraction).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete-attraction 1")` API call.

![Interactions Inside the Logic Component for the `delete-attraction 1` Command](images/devguideimages/DeleteSequenceDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 6 Interactions inside the Logic Component for the `delete-attraction 1` Command</i></sup></div><br>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### 3.4 Model

![Structure of the Model Component](images/devguideimages/ModelClassDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 7 Structure of the Model Component, Model Class Diagram</i></sup></div><br>

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model` component shown in Figure 7,
* stores a `UserPref` object that represents the user’s preferences.
* stores an `AttractionList` object that is a list of all the attractions in TrackPad.
* stores an `ItineraryList` object that is a list of all the itineraries in TrackPad.
* stores an `ItineraryAttractionList` object that is a list of all the attractions in the current selected itinerary.
* exposes two unmodifiable `ObservableList<Attraction>` and `ObservableList<Itinerary>` that can be 'observed' e.g. the UI can be bound to the lists so that the UI automatically updates when the data in the lists change.
* does not depend on any of the other three components.

< markdown="span" class="alert alert-info">:information_source: **Note:** 
An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TrackPad`, which `Attraction` references. This allows `TrackPad` to only require one `Tag` object per unique `Tag`, instead of each `Attraction` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 8 Structure of an alternative Model Component, Alternative Model Class Diagram</i></sup></div></div><br>

### 3.5 Storage

![Structure of the Storage Component](images/devguideimages/StorageClassDiagram.png)
<div align="center"><sup style="font-size:100%"><i>Figure 9 Structure of the Storage Component, Storage Class Diagram</i></sup></div><br>

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-T09-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component shown in Figure 9,
* saves `UserPref` objects containing user preferences in json format and read it back.
* saves `AttractionList` objects containing attraction data in json format and read it back.
* saves `ItineraryList` objects containing itinerary data in json format and read it back.

### 3.6 Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **4. Implementation**
This section describes some noteworthy details on the implementation of core TrackPad feature.

*To be added*

### 3.ia Itinerary Attraction class
This is a type of `Attraction` that goes into the `List<Day>` that resides in `Itinerary`. 

#### 3.ia.1 Current Implementation

`ItineraryAttraction` extends `Attraction`. It is a `Attraction` with 2 extra fields, `startTime` and `endTime`. 
It is stored internally as an `List<Day>`. Additionally, it implements the following operations:


Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.


#### 3.1.2 Design consideration

##### 3.1.2.1 Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Extends `Attraction`.
  * Pros: Access to private fields in 
  * Cons: Hard to implement and May have performance issues in terms of memory usage.

* **Alternative 2:** Use a wrapper class to contain `Attraction`
`ItineraryAttraction` would contain a field `Attraction` inside.
  * Pros: Easy to implement. No coupling with `Attraction`. 
  * Cons: Does not have access to private fields in attraction, would require 

<!--
This section describes some noteworthy details on how certain features are implemented.

### 3.1 \[Proposed\] Undo/redo feature


#### 3.1.1 Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedTrackPad`. It extends `TrackPad` with an undo/redo history, stored internally as an `trackPadStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedTrackPad#commit()` — Saves the current TrackPad state in its history.
* `VersionedTrackPad#undo()` — Restores the previous TrackPad state from its history.
* `VersionedTrackPad#redo()` — Restores a previously undone TrackPad state from its history.

These operations are exposed in the `Model` interface as `Model#commitTrackPad()`, `Model#undoTrackPad()` and `Model#redoTrackPad()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedTrackPad` will be initialized with the initial TrackPad state, and the `currentStatePointer` pointing to that single TrackPad state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th attraction in the TrackPad. The `delete` command calls `Model#commitTrackPad()`, causing the modified state of the TrackPad after the `delete 5` command executes to be saved in the `trackPadStateList`, and the `currentStatePointer` is shifted to the newly inserted TrackPad state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new attraction. The `add` command also calls `Model#commitTrackPad()`, causing another modified TrackPad state to be saved into the `trackPadStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitTrackPad()`, so the TrackPad state will not be saved into the `trackPadStateList`.

</div>

Step 4. The user now decides that adding the attraction was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoTrackPad()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous TrackPad state, and restores the TrackPad to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial TrackPad state, then there are no previous TrackPad states to restore. The `undo` command uses `Model#canUndoTrackPad()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoTrackPad()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the TrackPad to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `trackPadStateList.size() - 1`, pointing to the latest TrackPad state, then there are no undone TrackPad states to restore. The `redo` command uses `Model#canRedoTrackPad()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the TrackPad, such as `list`, will usually not call `Model#commitTrackPad()`, `Model#undoTrackPad()` or `Model#redoTrackPad()`. Thus, the `trackPadStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitTrackPad()`. Since the `currentStatePointer` is not pointing at the end of the `trackPadStateList`, all TrackPad states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### 3.1.2 Design consideration

##### 3.1.2.1 Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire TrackPad.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the attraction being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### 3.2 \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

-->
--------------------------------------------------------------------------------------------------------------------

## **5. Documentation, logging, testing, configuration, dev-ops**

This section shows the various standards TrackPad adheres to.

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
<!--
## **Appendix A: Requirements**
-->

## **Appendix A: Product Scope**

**Target user profile**:

* travelholics who love traveling and keeping track of their trips
* travelled to many different places before
* plans to travel to other countries in the future
* has a need to manage a significant number of tourist attractions
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: 
* manage information for trips and tourist attractions faster than a typical mouse/GUI driven app
* keeps track of different tourist attractions visited by the user
* allows creating an itinerary to track future travels
* customisable shortcuts that the user can set for frequently used commands

## **Appendix B: User Stories**

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                     | I want to …​                                                                    | So that I can…​                                              |
| -------- | ------------------------------ | ---------------------------------------------------------------------------------- | --------------------------------------------------------------- |
| `* * *`  | person planning for my travel	| add tourist attractions	                                                         | keep track of tourist attractions I want to visit
| `* * *`  | person planning for my travel	| delete tourist attractions	                                                     | remove tourist attractions that I might not want to visit
| `* * *`  | person planning for my travel	| find tourist attractions from my list                                              | quickly search for a specific tourist attraction
| `* * *`  | person planning for my travel	| see a list of all the tourist attractions	                                         | get a look at all my tourist attractions at one go
| `* * *`  | person planning for my travel  | clear all tourist attractions from my plan                                         | reset the list of attractions
| `* * *`  | person planning for my travel	| edit the information in my tourist attractions	                                 | update my attractions with new information
| `* * *`  | person planning for my travel	| tag tourist attractions in different categories like food, sightseeing, activities | distinguish between the different kinds of tourist attractions
| `* * *`  | person planning for my travel	| add locations / addresses to my attractions                                        | know where the attraction is located and how to get there
| `* * *`  | person planning for my travel	| add descriptions to my attractions                                                 | know roughly what the attraction is about when viewing them
| `* * *`  | person planning for my travel	| add contact details such as email and phone number to my attractions               | know how to contact them if I need
| `* * *`  | new user	                    | find the user guide easily	                                                     | know what I can do with the app
| `* *`    | person planning for my travel  | create multiple itineraries for different trips	                                 | plan for all my different travelling trips
| `* *`    | person planning for my travel  | add descriptions to my itineraries such as trip details                            | ensure I have all the correct trip information in one place
| `* *`    | person planning for my travel  | add dates to my itineraries                                                        | plan when the trip will take place
| `* *`    | person planning for my travel  | add attractions to my itineraries                                                  | plan which attractions to visit
| `* *`    | person planning for my travel  | delete attractions from my itineraries                                             | remove attractions I do not want to visit anymore
| `* *`    | person planning for my travel  | edit attractions in my itineraries                                                 | update the attractions I am planning to visit in my itineraries
| `* *`    | person planning for my travel  | specify the times at which I visit an attraction within an itinerary               | plan when to visit the attractions
| `* *`    | new user	                    | see the app with sample data	                                                     | see what kind of data the app can store
| `*`      | person planning for my travel	| add opening hours to my attractions                                                | know when to visit
| `*`      | person planning for my travel	| add the estimated price range to my attractions                                    | know which attractions to choose to match my budget
| `*`      | person planning for my travel	| add the estimated budget to my itineraries                                         | plan how much to spend on each trip
| `*`      | person currently traveling	    | mark tourist attractions as visited / not visited                                  | know which attractions I missed
| `*`      | person who had already traveled | give ratings to my attractions                                                    | keep track of which tourist attractions were enjoyable

## **Appendix C: Use Cases**

(For all use cases below, the **System** is the `TrackPad` and the **Actor** is the `user`, unless specified otherwise)
      
**Use case: UC01 - Add a tourist attraction**

**MSS**

1. User requests to add a tourist attraction.
2. User provides the fields of the tourist attraction to be added.
3. TrackPad adds the tourist attraction and shows a success message.

   Use case ends.
      
**Extensions**

* 2a. A field provided for the tourist attraction is invalid.

    * 2a1. TrackPad shows an error message.
    
      Use case resumes at step 2.

* 2b. The tourist attraction to be added already exists in the list of attractions.

    * 2b1. TrackPad shows an error message.
    
      Use case ends.

**Use case: UC02 - Edit a tourist attraction**

**MSS**

1. User requests to edit a tourist attraction.
2. User provides the index of the tourist attraction to be edited.
3. User provides the fields of the tourist attraction to be edited.
4. TrackPad edits the fields and shows a success message.

   Use case ends.
    
**Extensions**

* 2a. The index provided does not exist in the attractions list.

    * 2a1. TrackPad shows an error message.
    
      Use case resumes at step 2.

* 3a. The new field provided for the tourist attraction is invalid.

    * 3a1. TrackPad shows an error message.
    
      Use case resumes as step 3.
      
* 3b. The new field provided for the tourist attraction is the same as the current one.

    * 3b1. TrackPad shows an error message.
    
      Use case ends.

**Use case: UC03 - Delete a tourist attraction**

**MSS**

1. User requests to delete a tourist attraction.
2. User provides the index of the tourist attraction to be deleted.
3. TrackPad deletes the tourist attraction and shows a success message.

   Use case ends.
    
**Extensions**
  
* 2a. The index provided does not exist in the attractions list.

    * 2a1. TrackPad shows an error message.
    
      Use case resumes at step 2.
      
**Use case: UC05 - Find a tourist attraction**

**MSS**

1. User requests to find a tourist attraction.
2. User provides the keyword he is looking for.
3. TrackPad shows a list of all the tourist attractions that match the keyword.

   Use case ends.
    
**Extensions**

* 3a. There are no tourist attractions that matches the keyword.

    * 3a1. TrackPad shows an empty list of attractions.
    
      Use case ends.

**Use case: UC06 - List all tourist attractions**

**MSS**

1.  User requests to list all tourist attractions
2.  TrackPad shows a list of all tourist attractions

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.
 
**Use case: UC07 - Clear all tourist attractions**

**MSS**

1.  User requests to delete all tourist attractions in the list
2.  TrackPad deletes all tourist attractions in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. Error deleting list from storage

    * 3a1. TrackPad shows an error.

      Use case ends.
      
**Use case: UC08 - Add an itinerary**

**MSS**

1.  User requests to add an itinerary.
2.  TrackPad adds the itinerary.

    Use case ends.
      
**Extensions**

* 2a. The given format for the itinerary is invalid.

    * 2a1. TrackPad shows an error message.
    
      Use case resumes at step 1.

**Use case: UC09 - Edit an itinerary**

**MSS**

1.  User requests to list itineraries.
2.  TrackPad shows a list of itineraries.
3.  User requests to edit a specific itinerary in the list.
4.  TrackPad edits the itinerary.

    Use case ends.
      
**Extensions**

* 2a. The list is empty.
      
  Use case ends.
      
* 2b. The format is invalid. 
      
  Use case ends.
  
* 3a. The given index is invalid.

    * 3a1. TrackPad shows an error message.
    
      Use case resumes at step 2.
      
**Use case: UC10 - Delete an itinerary**

**MSS**

1.  User requests to list itineraries.
2.  TrackPad shows a list of itineraries.
3.  User requests to delete a specific itinerary in the list.
4.  TrackPad deletes the itinerary.

    Use case ends.
    
**Extensions**

* 2a. The list is empty.
      
  Use case ends.
      
* 2b. The format is invalid. 
      
  Use case ends.
  
* 3a. The given index is invalid.

    * 3a1. TrackPad shows an error message.
    
      Use case resumes at step 2.
      
**Use case: UC11 - Find an itinerary**

**MSS**

1.  User requests to find an itinerary.
2.  TrackPad shows a list of itineraries matching the keyword entered.

    Use case ends.
    
**Extensions**

* 2a. The list is empty.

  Use case ends.
    
* 3a. The given keyword is not found.

    * 3a1. TrackPad shows an error.

      Use case ends.

## **Appendix D: Non-Functional Requirements**

1.  The product should be able to hold up to 1000 tourist attractions/itineraries/days without a noticeable sluggishness in performance for typical usage.
2.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3.  The user interface should be intuitive enough for users who are not IT-savvy.
4.  The product is not required to recommend new tourist attractions which are not inputted by the user.
5.  The product should be free to download and use.
6.  The system should work on any _mainstream OS_ as long as it has Java `11` or above installed.
7.  The system should work on both 32-bit and 64-bit environments.
8.  The system should respond within five seconds.
9.  The project is expected to be a brown-field project.
10.  The progress of the project is expected to adhere to the schedule provided on the module website.

## **Appendix E: Glossary**

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix F: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### F1 Launch and shutdown

1. Initial launch.

   1. Download the jar file and copy into an empty folder.

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences.

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### F2 Adding a tourist attraction

1. Adding a tourist attraction.

   1. Prerequisites: No attraction in TrackPad has both the name 'Zoo' and the location 'Singapore'.
   
   2. Test case: `add-attraction n/Zoo l/Singapore`<br>
      Expected: New attraction added to the bottom of the attractions list. 
      Details of the added attraction shown in the status message.

   3. Test case: `add-attraction n/Zoo`<br>
      Expected: No attraction added. Error details shown in the status message.

   4. Other incorrect add-attraction commands to try: `add-attraction n/Zoo l/Singapore p/+6591234567`, 
      `add-attraction n/NAME l/LOCATION` (where both the NAME and LOCATION are the same as another attraction in TrackPad)<br>
      Expected: Similar to previous.
      
### F3 Editing a tourist attraction

1. Editing a tourist attraction while all the attractions in TrackPad are shown in the attractions list.

   1. Prerequisites: List all attractions using the `list-attraction` command.

   2. Test case: `edit-attraction 1 p/999`<br>
      Expected: Phone number of first attraction edited to 999. Details of the edited attraction shown in the status message.

   3. Test case: `edit-attraction 0 p/999`<br>
      Expected: No attraction edited. Error details shown in the status message.

   4. Other incorrect edit-attraction commands to try: `edit-attraction 1 r/5.1`,
      `edit-attraction 1 n/NAME` (where NAME is the same as the current name of the first attraction)<br>
      Expected: Similar to previous.
      
### F4 Deleting a tourist attraction

1. Deleting a tourist attraction while all the attractions in TrackPad are shown in the attractions list.

   1. Prerequisites: List all attractions using the `list-attraction` command.

   2. Test case: `delete-attraction 1`<br>
      Expected: First attraction deleted from the list. Details of the deleted attraction shown in the status message.

   3. Test case: `delete-attraction 0`<br>
      Expected: No attraction deleted. Error details shown in the status message.

   4. Other incorrect delete-attraction commands to try: `delete-attraction`, 
      `delete-attraction x` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### F5 Marking an attraction as Visited (Robin)

1. Marking an attraction as Visited while all attractions are being shown

   1. Prerequisites: There is at least one attraction present in the attraction list of TrackPad.

   2. Test case: `markVisited-attraction 1`<br>
   
      1. Scenario 1: First attraction does not have the purple Visited tag.<br>
      Expected: First attraction is marked as visited on the list. Details of the attraction shown in the status message. <br>
      
      1. Scenario 2: First attraction already has the purple Visited tag.<br>
      Expected: First attraction remains unchanged. Error message shown in the result box.

   3. Test case: `markVisited-attraction 1`<br>
      Expected: No attraction is marked as visited. Error details shown in the result box.
      
   4. Other incorrect markVisited commands to try: `markVisited-attraction`, `markVisited-attraction x` (where x is larger than the list size, or less than 0)<br>
      Expected: Similar to previous.

### F6 Finding a tourist attraction

1. Finding a tourist attraction while all the attractions in TrackPad are shown in the attractions list.

   1. Prerequisites: List all attractions using the `list-attraction` command. Attractions Jurong Bird Park and Snow City are in the attractions list.

   2. Test case: `find-attraction jurong`<br>
      Expected: Attractions Jurong Bird Park and Snow City shown in the attractions list. Number of attractions listed shown in the status message.

   3. Test case: `find-attraction #$%`<br>
      Expected: Empty attractions list shown. "0 attractions listed!" shown in the status message.
      
### F7 Listing attractions (Robin)

1. Listing all attractions currently stored in TrackPad

   1. Prerequisites: Lists all attractions using the `list-attraction` command

   2. Test case: `list-attraction`<br>
      Expected: All attractions that are currently stored in the app will be displayed in the Attractions panel.
      
   3. Test case: `list-attraction 1`<br>
      Expected: Everything typed after the space following the command will be ignored, and list-attraction command will be executed successfully.

### F8 Clearing attractions (Robin)

1. Clears all attractions currently stored in TrackPad

   1. Prerequisites: Clears all attractions using the `clear-attraction` command

   2. Test case: `clear-attraction`<br>
      Expected: All attractions that are currently stored in the app will be deleted. An empty attractions panel will be shown.
      
   3. Test case: `clear-attraction 1`<br>
      Expected: Everything typed after the space following the command will be ignored, and clear-attraction command will be executed successfully.

### F9 Adding an itinerary

1. Adding an itinerary

   1. Prerequisites: None.

   2. Test case: `add-itinerary n/Thailand Trip sd/01-08-2020 ed/03-08-2020`<br>
      Expected: An itinerary with the specified name, start date and end date is added to the itinerary list. 
      Details of the added itinerary shown in the status message.

   3. Test case: `add-itinerary `<br>
      Expected: No itinerary added. Error details shown in the status message.

   4. Other incorrect add itinerary commands to try: 
     * Missing compulsory fields (e.g. missing end date): `add-itinerary n/Germany sd/03-02-2020 b/100`<br>
        Expected: Similar to 3.
     * Invalid format for fields (e.g. invalid start date format): `add-itinerary n/Germany sd/03 02 2020 ed/06-02-2020`<br>
        Expected: Similar to 3.
        
### F10 Editing an itinerary

1. Editing an itinerary

   1. Prerequisites: At least one itinerary exists for editing.

   2. Test case: `edit-itinerary 1 n/Japan trip`<br>
      Expected: The name of the first itinerary is changed to `Japan trip`.
      Details of the edited itinerary shown in the status message.

   3. Test case: `edit-itinerary 0 n/Japan trip`<br>
      Expected: No itinerary edited. Error details shown in the status message.

   4. Other incorrect edit itinerary commands to try: 
     * Missing fields: `edit-itinerary 1`<br>
        Expected: Similar to 3.
     * Invalid format for fields (e.g. invalid start date format): `edit-itinerary 1 sd/03 02 2020`<br>
        Expected: Similar to 3.
     * No change in fields: `edit-itinerary 1 n/Germany` when the name is already `Germany`<br>
        Expected: Similar to 3.
        
### F11 Deleting an itinerary

1. Deleting an itinerary while all itineraries are being shown

   1. Prerequisites: List all itineraries using the `list-itinerary` command. Multiple itineraries in the list.

   2. Test case: `delete-itinerary 1`<br>
      Expected: First itinerary is deleted from the list. Details of the deleted itinerary shown in the status message.

   3. Test case: `delete-itinerary 0`<br>
      Expected: No itinerary is deleted. Error details shown in the status message.

   4. Other incorrect delete commands to try:
    * Missing index: `delete-itinerary`<br>
      Expected: Similar to 3
    * Invalid index: `delete-itinerary x`, where x is larger than the list size <br>
      Expected: Similar to 3
      
### F12 Finding an itinerary

1. Finding an itinerary

   1. Prerequisites: TrackPad contains an itinerary with the name `Singapore Tour`.

   2. Test case: `find-itinerary Singapore Tour`<br>
      Expected: The itinerary with the name `Singapore Tour` is found. 

   3. Test case: `find-itinerary`<br>
      Expected: No itinerary is found. Error details shown in the status message.
  
### F20 Exiting the program (Robin)

1. Exits and shutdowns the program

   1. Prerequisite: NIL
   
   2. Test case: `exit`<br>
      Expected: TrackPad shuts down.
   
### F21 Saving data

1. Dealing with missing data files

   1. Prerequisites: Launch TrackPad, enter at least one valid command, then exit the app. 
   
   2. Test case: In the folder where you saved the app, go to the `data` folder. Delete `attractionlist.json`. Launch TrackPad again.<br>
   Expected behavior: TrackPad launches with a sample list of attractions to replace the missing attractions file. After entering a valid command, a new `attractionlist.json` file with the current attractions will be created. 
   
   3. Test case: In the folder where you saved the app, go to the `data` folder. Delete `itinerarylist.json`. Launch TrackPad again.<br>
   Expected behavior: TrackPad launches with a sample list of itineraries to replace the missing itineraries file. After entering a valid command, a new `itinerarylist.json` file with the current itineraries will be created. 
   
2. Dealing with corrupted data files

   1. Prerequisites: Launch TrackPad, enter at least one valid command, then exit the app. 
      
   2. Test case: In the folder where you saved the app, go to the `data` folder. Open `attractionlist.json`, delete some portions of it and save. Launch TrackPad again.<br>
   Expected behavior: TrackPad launches with an empty list of attractions to replace the corrupted attractions file. After entering a valid command, a new `attractionlist.json` file with the current attractions will be created. 
      
   3. Test case: In the folder where you saved the app, go to the `data` folder. Open `itinerarylist.json`, delete some portions of it and save. Launch TrackPad again.<br>
   Expected behavior: TrackPad launches with an empty list of itineraries to replace the corrupted itineraries file. After entering a valid command, a new `itinerarylist.json` file with the current itineraries will be created. 

--------------------------------------------------------------------------------------------------------------------

## **Appendix G: Effort**

Our project was harder than Address Book Level 3(AB3) because while AB3 deals with one entity, TrackPad deals with several
entities, including Attractions, Itineraries as well as Itinerary Attraction. Initially, we had to refactor most of the code, 
to change all instance of Person to Attraction and AddressBook to TrackPad. We also had to change the test cases, and figure out
why some of them failed.

After which, we had to implement itinerary into the app, and make it work similarly to Attraction, but taking in different
fields from Attraction. We also had to create new parsers for Itinerary, so that it can read the itinerary commands. 

In addition, we had to implement an adaptable UI, so that the attraction and itinerary box displays will vary in height, 
since we have optional fields for our entities. We had to create different FXML files, to be compatible with our AttractionCard
and ItineraryCard having multiple Labels.

Also, since we stored attractions as a List of Days in itineraries, it proved a further challenge in reading the itinerary
attractions since we had to go through several layers to reach the list of itinerary attractions. Our UI also contains of 
boxes for the Day, to distinguish between different days of the same itinerary.
