# Chat App with Academic Journal Search Project

## Project Overview
This project is a basic chat system that enables multiple users to engage in real-time conversations while also 
providing a feature to search academic journals. The chat functionality is powered by the PubNub API, facilitating 
seamless communication, and the academic journal search is facilitated by the CORE API.

## Focus Areas
Our team's primary focus for this project includes:

1. Real-time Communication: Implementing a real-time chat system where users can send and receive messages instantly.

2. User Account Construction: Users can create their own accounts and secure user accounts, in order to protect user data and conversations.

3. Message Persistence: Storing chat messages securely and providing the ability to retrieve chat history.

4. Academic Journal Search: Utilizes the CORE API to search and retrieve academic journal information. Users can seamlessly access academic resources within the chat app.

5. User Experience: Creating an intuitive and user-friendly interface for seamless communication.

## Usecases
1. **User Authentication:**
    - **Signup:**
        - Create an account with a username and password.
        - Switch to the login view.
        - Save user information into a local CSV file.
    - **Login:**
        - Switch to the signup view.
        - If the provided information matches the CSV file, jump to the profile view.

2. **Profile:**
    - Display user information.
    - Logout: Return to the login view.
    - Create: Jump to the create room view.
    - Subscribe: Jump to the subscribe room view.

3. **Setting Up Chat Room**
   - Create a new room to start a chat. 
   - Join someone else's room and chat with others.

4. **Channel Data Access Object (DAO):**
    - Read local file and generate a hashmap.
    - Save data into the local database.
    - Return the channels that the current user recently joined.

5. **Journal Search:**
    - **Search by Content:**
        - Retrieve the last 10 related journals and their URL links.
    - **Search by DOI:**
        - Retrieve the full-text link to access the article.
    - **Search by Author:**
        - Retrieve the full-text link to access the author's information.
    - **Back:**
        - Return to the chat room.
6. **Room:**
7. **Setting of the Room:**
   - **Currently Active Users**
        - Get all users currently in this room.
   - **Room History**
        - Fetch the most recent 100 chat history of this room. 
   - **Delete History Message*
        - Choose the history messages sent by you and delete.
   - 


## Relevant API Documentation
To achieve the functionalities mentioned above, we plan to utilize the User API from Weavy.

1. PubNub API: https://www.pubnub.com/docs/sdks/java/api-reference/configuration#methods

2. SEMANTIC SCHOLAR API: https://api.semanticscholar.org/graph/v1



