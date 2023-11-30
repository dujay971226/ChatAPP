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

## Features
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

3. **Channel Data Access Object (DAO):**
    - Read local file and generate a hashmap.
    - Save data into the local database.
    - Return the channels that the current user recently joined.

4. **Journal Search:**
    - **Search by Content:**
        - Retrieve the last 10 related journals and their URL links.
    - **Search by DOI:**
        - Retrieve the full-text link to access the article.
    - **Search by Author:**
        - Retrieve the full-text link to access the author's information.
    - **Back:**
        - Return to the chat room.


## Relevant API Documentation
To achieve the functionalities mentioned above, we plan to utilize the User API from Weavy.

1. PubNub API: https://www.pubnub.com/docs/sdks/java/api-reference/configuration#methods

2. CORE API: https://api.core.ac.uk/docs/v3



