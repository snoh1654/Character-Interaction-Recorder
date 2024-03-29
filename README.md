# My Personal Project

## Description of Application:
This application will be used to assist the users when they play the online
game *League of Legends (LoL)*. *LoL* is a multiplayer game with two teams
of 5 players where each player chooses a unique champion - the name for the
characters in the game- from the 150 in the game's cast. However, the game becomes quite complex due to the abundant
amount of knowledge required regarding champions and their interactions with enemy champions. Therefore, it is quite 
daunting for beginners to approach this game and even experienced players struggle to memorize all the small details. 
**Therefore, this application will attempt to assist these players by allowing the user to create a collection of 
champions that they can obtain information from**. This application will accomplish this goal by allowing its user to 
record a Champion's **information, difficulty, and interaction with other champions** as this will help them when they
play the game. In this application, the user will be able to have a collection of champions that they can select to 
retrieve information related to them. In this application there are 3 classes. The Champion class is responsible for
information about a Champion, the OpposingChampion class is responsible for the champions that interact with the user's
Champion, and the ChampionCollection class is responsible for the collection of Champions.

This application will be a helpful tool to everyone who plays *LoL*, which
is a large demographic since it is one of the most popular games in the world. In addition, *LoL*
applications that assist players have recently become quite popular; however, they often only offer information
and does not allow its users to edit the data themselves. Therefore, I chose to pursue this project as I 
frequently play *League of Legends* during my free time, and I always thought that a *LoL* application that allows
the user to edit their own information would be very helpful to me and some of my friends who play the game.


## User Stories

- As a user, I want to be able to add information about a new champion to my collection of champion information
- As a user, I want to be able to delete information about a champion from my collection of champion information
- As a user, I want to be able to get a warning message prior to deleting the champion from my collection
- As a user, I want to be able to update information that I previously added to my collection of champion information
- As a user, I want to be able to read the information that I have written about a champion
- As a user, I want to be able to view the available champions in my collection of champion information
- As a user, I want to be able to save my Champion Collection
- As a user, I want to be able to load and resume writing or viewing my Champion Collection

## User Stories for GUI
- As a user, I want to be able to add a Champion to my collection of champion information
- As a user, I want to be able to remove a Champion from my collection of champion information
- As a user, I want to be able to see a visual of all the champions that I added to my collection
- As a user, I want to be able to save my collection of champion information
- As a user, I want to be able to load my previous collection of champion information

## Phase 4: Task 2
Wed Mar 30 00:29:15 PDT 2022    
Added Sean to the Champion Collection

Wed Mar 30 00:29:19 PDT 2022    
Added Sean2 to the Champion Collection

Wed Mar 30 00:29:22 PDT 2022    
Removed Champion Sean2 from the Champion Collection.

## Phase 4: Task 3
- I would refactor the classes in the model package to use exceptions rather than a "requires" specification. 
Specifically, I would create exceptions such as "ChampionDoesNotExistException," "OutOfDifficultyRangeException," and 
"AlreadyInCollectionException" to indicate errors in the code and increase its robustness.


- I would also refactor my code to reduce coupling within the classes in the model
package. As of now, there is a high level of coupling present between the ChampionCollection, Champion, and
OpposingChampion class. As of now, I learned the observer and composite design patterns in CPSC 210 and these two
ideas are not really applicable to these classes. Therefore, if I had more time, I would research other design
principles that could reduce the model package's coupling.


- I would also try to create an abstract class that Champion and OpposingChampion could implement. I believe that these 
two classes are very similar, so extending an abstract class could help group their related behaviour.


- Finally, with more time, I would refactor the AppFrame class to make my JFrame more efficient. Currently, I am certain
that I am not utilizing some of Swing's methods that could be useful for my GUI. For some sections of AppFrame, 
specifically the loading feature and button placements, I had to brute force the features. Therefore, I am
fairly certain that Java offered methods that have these functionalities, but I simply could not find them.