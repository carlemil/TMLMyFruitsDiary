# TMLMyFruitsDiary
My Fruits Diary is a mobile application where you can store the number of fruits you have eaten each day.

Requierment 5 - "should be updated if the list of fruit changes on the server" 
	is not possible without polling or push, and polling is not realy an 
	alternative we want to pursue.

Requierment 6 - is a very poor ideea for android due to the nature of the lifecycle
	of activity/fragments. Room ()sql orm for android + etags could be used to 
	handle this in a propper way.

POST Entry
Documentation and implementation differs, doc says add but impl does set
	POST ?https://fruitdiary.test.themobilelife.com/api/entry/{entryId}/fruit/{fruitId}?amount={nrOfFruit} 
	Parameter entryId: The id of the entry to add fruit to  - Parameter fruitId: The id of the fruit to be added to the entry   - Parameter nrOfFruit: The number of fruit to be added to the entry


Left to do:

Change from lineaylayout to recylerview in entry details page and databind, same same as for the entries list.
Add a in memory cache to avoid hitting the network for every reaload of data.
Make the recyclerview viewholder generic, example can be found  in https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4
Cleanup strings.xml, group by screen.
Rewers xml layout names, activity_main.xml -> main_activtiy.xml
Can we move the clicklistener from the viewholder to avoid creating one per view, and put it on the recyclerview instead?


Done:

Requirement 1 - The UI should at least have two tabs, ”My Diary” and ”About”.  

Requirement 2 - The existing entries should be presented through a list of some
 sort and a specific entry should be viewable through a detail view. Each row
 in the entries list should present the date of the entry, the number of fruits
 and the total number of vitamins in the eaten fruits that date
 
Requirement 3 - It should be possible to add and remove date entries.  

Requirement 5  The available fruits should be loaded during app start. They 
could be locally stored, but should be updated if the list of fruit changes 
on the server. 
(Loaded on demand and not on app start.)
 
Requirement 7  When adding a fruit to an entry, an image of the current fruit 
should be visible. The image can be cached.  

Not doing:

Requirement 4  It should be possible the change/edit the different fruits and 
the number of fruits for each entry. 
  
Requirement 6  The entries should not be locally stored other than in memory 
after reading from the back end.  
 
Requirement 8  When adding a fruit to an entry the name of the fruit and the 
number of vitamins the fruit contains should be visible to the user. 
