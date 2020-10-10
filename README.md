# playground

This project is a part of a spike for Iheartmedia on the options for relpacing the way we handle recycler view adapters (MultiTypeAdapter) with Google's concat adapter and Groupie.

When looking at options I paid particular attention to ease of conversion from our current handing of data flow, use of multiple view holders, and use of carousels. 

In researching options I have made a practice project that shows a list of users in a carousel and a list of blog posts in a typical linear list. I made 3 branches in this project. One for MutliType Adapter, one for Concat adapter, and one for Groupie. 

# Tech
- Hilt (Dependency Injection)
- Flow (Data Layer reactive programming)
- Coroutines (Asynchronous programming)
- Live Data (Ui layer reactive programming)
- MVVM (for a simple clean architecture)
- Network Bound Resource (keep DB as single source in a clean way)
- Room (DB)
- Glide (Image Loading)

<img src="https://firebasestorage.googleapis.com/v0/b/github-images.appspot.com/o/Screen%20Shot%202020-10-10%20at%201.37.16%20PM.png?alt=media&token=e4bcbb31-d769-48da-896a-9654d4c9400c" width="1000" />
