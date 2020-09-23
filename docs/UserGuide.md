---
layout: page
title: User Guide for TrackPad v1.1
---

TrackPad (TP) is a **desktop app for planning and tracking attractions visited and recording of previous places that 
were travelled to, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a 
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

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2021S1-CS2103T-T09-3/tp/releases).

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

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a tourist attraction: `add`

Adds a tourist attraction to the current list.

Format: `add n/ATTRACTION p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An attraction can have any number of tags (including 0)
</div>

Examples:
* `add n/USS a/8 Sentosa Gateway, 098269`
* `add n/Singapore Zoo p/62693411 t/hot a/80 Mandai Lake Rd, 729826`

### Listing all tourist attractions : `list`

Shows a list of all tourist attractions in TrackPad.

Format: `list`

<!---
### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
-->

### Finding a tourist attraction: `find`

Finds tourist attraction which contains the keyword in their names.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `singapore zoo` will match `Singapore Zoo`
* The order of the keywords does not matter. e.g. `Zoo Singapore` will match `Singapore Zoo`
* Only the tourist attraction will be searched.
* Only full words will be matched e.g. `Sento` will not match `Sentosa`

Examples:
* `find Singapore` returns `Singapore Zoo` and `Singpoare Stadium`<br>
  ![result for 'find Singapore'](images/findAlexDavidResult.png) // todo attach real image

### Deleting a tourist attraction : `delete`

Deletes a tourist attraction from the current list.

Format: `delete INDEX`

* Deletes the attraction at the specified `INDEX`.
* The index refers to the index number shown in the displayed tourist attraction list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd attraction in the TrackPad.
* `find USS` followed by `delete 1` deletes the 1st tourist attraction in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the TrackPad.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TrackPad data will be saved in the storage automatically after any command that changes the data. 
There is no need to save manually.


<!--
### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

-->
--------------------------------------------------------------------------------------------------------------------


<!--
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------
-->


## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/ATTRACTION p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Singapore Zoo p/62693411 t/hot a/80 Mandai Lake Rd, 729826`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Zoo`
**List** | `list`
**Help** | `help`

<!--**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`-->
