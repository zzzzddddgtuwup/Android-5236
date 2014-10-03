# Name: Nearby

## Narrative:
This application is a Q&A system based on location. We have many Q&A system online like Quora, StackOverflow and etc. These applications are focusing on more general topic like technical, art or sports. Our application focuses on more specific questions in the life. 

## Need:
In the lab, you don’t know how to use printer and no other people there. You can open our applications and check top10 questions related to this location. You can post a question and nearby people may answer the questions.
You want to check if the library is open this holiday. You open the application and choose the specific location forum and post a question. 

**External services**: google map

**sensors**: GPS

## User cases
### Category I Question and Answers:

User case 1: post a question 

1. User entered one Q&A forum for specific location(default or choose one).
2. Ask question in the forum
3. Receive notification if question being answered and he can click to like the answers. 

User case 2: Answer a question

1. User entered one Q&A forum for specific location(default or choose one).
2. Answer one or more questions in the forum
3. Get score or title for the answers. 

User case 3: Search for the questions being posted in one forum

1. User enter the questions in the input text
2. The app will return the list of relevant results
3. User can choose what he likes


### Category II Profile: 
	
User case 1: login interface

1. User have to register if he didn’t before.
2. After registration, user can login to the app using that username and password.
3. After login, the user will see the main page of app.

User case 2: Price system(Score, title, rank…)

1. The answers or questions will be rated according to the “likes”
2. The user can get scores and title according to the number of “likes”

User case 3: My questions

1. Users want to see his questions again. He doesn’t need to go to the corresponding forum.
2. He can check all his history questions under his main menu.

User case 4: My answers

1. User want to check his answers and “likes”.
2. User can go to main menu and see all his answers.



### Category III Map:

User case 1: Touch the screen to choose the location from a map to enter the specific Q&A district. 

1. User can click map button on main menu and jump to map page.
2. User can enter the specific forum by touching map.

User case 2: Search specific Q&A district. 

1. User click map button to enter the map page
2. User can enter the district or building name to search the forum.
3. Then he can enter the specific forum.

User case 3: Specify user's default location to enter the Q&A district.

1. After enter the map page, the app will use users’s default location to recommend a default forum for the user.


### Category IV Notification: 

User case 1: notification when someone need help

1. If someone is asking questions in your current forum. The user will get notification to ask him to help the man.

User case 2: notification when liked

1. If user’s answers or questions are liked, user will get notification about that.

User case 1: set the frequency of notification

1. User can set the frequency of the notification in the setting. He can choose daily/hourly/real time