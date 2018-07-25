# Topics
create topics and vote them

### Splash Screen
Splash screen is displayed to the user for 4 secs and then user is moved to home screen with current topics


<img src="https://user-images.githubusercontent.com/11436253/43178687-34bd9ab6-9001-11e8-9874-6675fb2603a0.png" width="400">

### Home screen with No Topic :
Create a new topic by clicking on floating action button on bottom right 

<img src="https://user-images.githubusercontent.com/11436253/43178688-34e8374e-9001-11e8-9ecc-75c61d2a3377.png" width="400">

### Create a new topic  
currently only text : max length 255 chars , a toast with max length reached will be shown . Once done writing the title for topic, use done button in action bar to save it to the list 

<img src="https://user-images.githubusercontent.com/11436253/43178689-3515a0e4-9001-11e8-8b7b-42ae12d46f57.png" width="400">


### Vote for topics and see them in in descending order with upvotes  on home screen :
Tap on topic on home screen and vote for it , once marked as `done ` voting , on home screen it will be shown in sorted manner with other topics

Singleton scoped MutableList<Topic> is used to display the data on home screen 
where Topic is a data class with : `data class Topic(id, title , upvotesm isStarred)`
Home screen is `reacting to the streams of topics`  emitted and adding them to the adapter and  refreshing the data .



<img src="https://user-images.githubusercontent.com/11436253/43178685-348f6682-9001-11e8-8aa3-0c2acd07f02b.png" width="400">
  
