---
layout: page
title: User Guide
---

TrackPad (TP) is a **desktop app for planning your trips and tracking tourist attractions, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a 
Graphical User Interface (GUI). 
If you can type fast, TP can get your itinerary planned faster than traditional GUI apps.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Target User
Travelholics, wanderlust
- Loves traveling
- Travelled to many places
- Plans to travel to other countries in the future



## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `trackpad.jar` from [here](https://github.com/AY2021S1-CS2103T-T09-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TrackPad.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
    Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png) // todo update this image later on

1. Type the command in the command box and press Enter to execute it. 
    e.g. typing **`help`** and pressing Enter will open the help window.<br>
    Some example commands you can try:

   * **`list`** : Lists all tourist attractions added.

   * **`add`**`n/Singapore Zoo p/62693411 t/hot a/80 Mandai Lake Rd, 729826` : 
   Adds an attraction named `Singapore Zoo` to TrackPad.

   * **`delete`**`3` : Deletes the 3rd attraction shown in the current list.
   
   **`edit`**`3 pr/HIGH` : Edits the 3rd attraction shown in the current list, changing its original
    price range to HIGH.

   * **`clear`** : Deletes all attractions.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/ATTRACTION`, `ATTRACTION` is a parameter which can be used as `add n/USS`.

* Items in square brackets are optional.<br>
  e.g `n/ATTRACTION [t/TAG]` can be used as `n/USS t/28 OCT` or as `n/USS`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Singapore`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/ATTRACTION p/PHONE_NUMBER`, `p/PHONE_NUMBER n/ATTRACTION` is also acceptable.

</div>

### General

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Saving the data

TrackPad data will be saved in the storage automatically after any command that changes the data. 
There is no need to save manually.

### Attraction Features

#### Adding a tourist attraction: `add`

Adds a tourist attraction to the current list.

Format: `add n/ATTRACTION_NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] l/LOCATION [op/OPENING_HOURS]
 [pr/PRICE_RANGE] [r/RATING] [v/VISITED] [t/TAG]…​`
 
* ATTRACTION_NAME: Name of Attraction 
* PHONE_NUMBER: Phone number should only contain numbers and be at least 3 digits long, no spaces
* EMAIL: Emails should be of the format: `local-part@domain`
* ADDRESS: Address of Attraction, can take in any value
* LOCATION: City followed by Country
* OPENING_HOURS: Opening Hours should be of the format: `opening time - closing time` (both in 24h format)
* PRICE_RANGE: Price Range can only take in 3 values: `LOW`, `MEDIUM` or `HIGH`
* RATING: Rating should only contain a number between `0.0` to `5.0` (inclusive), to 1 decimal place
* VISITED: Visited can only take in 2 values: `TRUE` or `FALSE`
* TAG: Tag name should be alphanumeric

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An attraction can have any number of tags (including 0)
</div>

Examples:
* `add n/USS a/8 Sentosa Gateway, 098269 l/Singapore, Singapore`
* `add n/Singapore Zoo p/62693411 t/hot a/80 Mandai Lake Rd, 729826 l/Singapore, Singapore, e/singaporezoo@gmail.com
op/1000-1800 pr/MEDIUM r/4.6 v/FALSE`

#### Listing all tourist attractions : `list`

Shows a list of all tourist attractions in TrackPad.

Format: `list`

#### Editing an attraction : `edit`

Edits an existing attraction in TrackPad.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the attraction at the specified `INDEX`. The index refers to the index number shown in the displayed attraction list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the attraction will be removed i.e adding of tags is not cumulative.
* You can remove all the attraction’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/sgzoo@example.com` Edits the phone number and email address of the 1st attraction to be `91234567` and `sgzoo@example.com` respectively.
*  `edit 2 n/River Safari t/` Edits the name of the 2nd attraction to be `River Safari` and clears all existing tags.

#### Finding a tourist attraction: `find`

Finds tourist attraction which contains the keyword in their names.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `singapore zoo` will match `Singapore Zoo`
* The order of the keywords does not matter. e.g. `Zoo Singapore` will match `Singapore Zoo`
* Only the tourist attraction will be searched.
* Only full words will be matched e.g. `Sento` will not match `Sentosa`

Examples:
* `find Singapore` returns `Singapore Zoo` and `Singpoare Stadium`<br>
  ![result for 'find Singapore'](images/findAlexDavidResult.png) // todo attach real image

#### Deleting a tourist attraction : `delete`

Deletes a tourist attraction from the current list.

Format: `delete INDEX`

* Deletes the attraction at the specified `INDEX`.
* The index refers to the index number shown in the displayed tourist attraction list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd attraction in the TrackPad.
* `find USS` followed by `delete 1` deletes the 1st tourist attraction in the results of the `find` command.

### Editing a tourist attraction: `edit`

Edits a tourist attraction in the current list.

Format: `edit INDEX [n/ATTRACTION_NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [l/LOCATION] [op/OPENING_HOURS]
 [pr/PRICE_RANGE] [r/RATING] [v/VISITED] [t/TAG]…​`
 
* Edits the attraction at the specified `INDEX`.
* The index refers to the index number shown in the displayed tourist attraction list.
* The index **must be a positive integer** 1, 2, 3, …​
* Field entries are the same as the `add` command.
* Any field can be changed by inputting its corresponding prefix in the command.
    * Example: `add n/MBS l/Singapore, Singapore r/4.3` in an attraction list followed by 
    `edit 1 r/4.6` changes the rating of the 1st attraction in the list from `4.3` to `4.6`
* New fields can be added to current attractions 
    * Example: `add n/Singapore Zoo l/Singapore, Singapore` in an attraction list followed by 
    `edit 1 pr/MEDIUM t/animals` adds the price range of the Singapore Zoo as `MEDIUM` and adds an `animals` tag



#### Clearing all attractions : `clear`

Clears all tourist attractions from the TrackPad.

Format: `clear`

### Itinerary Features

#### Adding a new itinerary: `add-itinerary`

Adds a new itinerary to the current list.

Format: `add-itinerary n/ITINERARY [d/DESCRIPTION] sd/START_DATE ed/END_DATE`

* The name, start date and end date fields must be filled in.
* The description field is optional.
* The start date and end date fields take in dates of the format `dd-mm-yyyy`.

Examples:
* `add n/Europe Trip sd/01-12-2020 ed/20-12-2020`
* `add n/Japan holiday d/with friends sd/15-01-2019 ed/30-01-2019`

#### Deleting an itinerary: `delete-itinerary`

Deletes an itinerary from the current list.

Format: `delete-itinerary INDEX`

* Deletes the itinerary at the specified `INDEX`.
* The index refers to the index number shown in the displayed itinerary list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list-itinerary` followed by `delete 2` deletes the 2nd itinerary in the TrackPad.

#### Clearing all itineraries : `clear-itinerary`

Clears all itineraries from the TrackPad.

Format: `clear-itinerary`

#### Editing an itinerary: `edit-itinerary`

Edits an itinerary from the current list.

Format: `edit-itinerary INDEX [n/NAME] [d/DESCRIPTION] [sd/START_DATE] [ed/END_DATE]`

* Edits the itinerary at the specified `INDEX`.
* The index refers to the index number shown in the displayed itinerary list.
* The index **must be a positive integer** 1, 2, 3, …​
* Field entries are the same as the `add` command.
* Any field can be changed by inputting its corresponding prefix in the command.
      * Example: `add-itinerary n/Europe Trip sd/01-12-2020 ed/20-12-2020` in an itinerary list followed by 
      `edit-itinerary 1 sd/06-12-2020` changes the start date of the 1st itinerary in the list from `01-12-2020` to `06-12-2020`

#### Finding an itinerary: `find-itinerary`

Finds an itinerary from the current list which contains the keyword in their names.

Format: `find-itinerary KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `japan` will match `Japan`
* The order of the keywords does not matter. e.g. `Okinawa Japan` will match `Japan Okinawa`
* Only the name of the itinerary will be searched.
* Only full words will be matched e.g. `Jap` will not match `Japan`

Examples:
* `find-itinerary Japan` returns `Japan Okinawa` and `Japan Tokyo`<br>
   // todo attach real image

#### Showing an itinerary: `show-itinerary`

Shows an itinerary from the current list.

Format: `show-itinerary INDEX`

* Shows the itinerary at the specified `INDEX`.
* The index refers to the index number shown in the displayed itinerary list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `show-itinerary 3` returns the 3rd itinerary in the TrackPad.

#### Listing all itineraries: `list-itinerary`

Shows all itineraries in the TrackPad.

Format: `list-itinerary`

### Itinerary Attraction Features
A attraction is an itinerary attraction when it is added into a itinerary.

#### Adding a new itinerary attraction: `add-itinerary-attraction`

Adds a new itinerary attraction to the selected itinerary.

Format: `add-itinerary-attraction att/ATTRACTION_NAME st/START_TIME et/END_TIME day/DAY_VISITING`

* The name of attraction, start time, end time and day visiting fields must be filled in.
* The start time and end time fields take in time of the 24H format `HH-MM`.

Examples:
* `add-itinerary-attraction att/Singapore Zoo st/1000 et/1600 day/3`
* `add-itinerary-attraction att/Mt Fuji st/0900 et/1200 day/2`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The itinerary attraction will be added into the itinerary displayed on the right.
</div>

#### Deleting an itinerary attraction: `delete-itinerary-attraction`

Deletes an itinerary attraction from the itinerary.

Format: `delete-itinerary att/ATTRACTION_NAME`

* Deletes the itinerary attraction specified by the name.
* Only deletes items in the selected itinerary.

Examples:
* `delete-itinerary-attraction att/Singapore Zoo` deletes attraction `Singapore Zoo` from current itinerary.

#### Editing an itinerary attraction : `edit-itinerary-attraction`

Edits an existing itinerary attraction in itinerary.

Format: `edit-itinerary-attraction att/ATTRACTION_NAME [st/START_TIME] [et/END_TIME] [day/DAY_VISITING]`

* Edits the itinerary attraction specified by the name.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit-itinerary-attraction att/Singapore Zoo day/2` Edits the day visiting to day 2 in the itinerary.
*  `edit-itinerary-attraction att/Sentosa st/1500 et/1800` Edits the start time and end time to be `1500` and `1800` respectively.


<!--
### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

-->
--------------------------------------------------------------------------------------------------------------------


<!--
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TrackPad home folder.

--------------------------------------------------------------------------------------------------------------------
-->


## Command summary

### General Commands

Action | Format, Examples
--------|------------------
**Help** | `help`
**Exit** | `exit`

### Attraction-related Commands

Action | Format, Examples
--------|------------------
**Add attraction** | `add n/ATTRACTION p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Singapore Zoo p/62693411 t/hot a/80 Mandai Lake Rd, 729826`
**Clear all attractions** | `clear`
**Delete attraction** | `delete INDEX`<br> e.g., `delete 3`
**Edit attraction** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/Singapore Zoo e/zoo@example.com`
**Find attraction** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Zoo`
**List attractions** | `list`

### Itinerary-related Commands

Action | Format, Examples
--------|------------------
**Add itinerary** | `add-itinerary n/ITINERARY [d/DESCRIPTION] sd/START_DATE ed/END_DATE` <br> e.g., `add-itinerary n/Japan holiday d/with friends sd/15-01-2019 ed/30-01-2019`
**Clear all itineraries** | `clear-itinerary`
**Delete itinerary** | `delete-itinerary INDEX`<br> e.g., `delete-itinerary 3`
**Edit itinerary** | `edit-itinerary INDEX [n/NAME] [d/DESCRIPTION] [sd/START_DATE] [ed/END_DATE]`<br> e.g.,`edit-itinerary 2 n/Singapore journey sd/05-06-2019`
**Find itinerary** | `find-itinerary KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-itinerary Korea`
**Show itinerary** | `show-itinerary INDEX`<br> e.g., `show-itinerary 3`
**List itineraries** | `list-itinerary`

### Itinerary-attraction-related Commands

Action | Format, Examples
--------|------------------
**Add itinerary attraction** | `add-itinerary-attraction att/ATTRACTION_NAME st/START_TIME et/END_TIME day/DAY_VISITING ` <br> e.g., `add-itinerary-attraction att/London Eye st/1400 et/1500 day/5`
**Delete itinerary attraction** | `delete-itinerary-attraction att/ATTRACTION_NAME`<br> e.g., `delete-itinerary-attraction att/London Eye`
**Edit itinerary attraction** | `edit-itinerary-attraction att/ATTRACTION_NAME [st/START_TIME] [et/END_TIME] [day/DAY_VISITING] ` <br> e.g.,`edit-itinerary-attraction att/London Eye st/0900 et/1000 day/2`

