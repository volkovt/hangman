<h1 align="center">Hangman's Project</h1>

<p align="center">⚰️ API's needed for a hangman's game</p>

Table of contents
=================

<!--ts-->
   * [Instruction](#apigame)
   * [Usage](#usage)
   * [API Game](#apigame)
   * [API Word](#apiword)
   * [API User](#apiuser)
   * [Docker](#docker)
     * [Public](#public)
<!--te-->

Instruction
============
All the endpoints that match GET request will be available without authentication
For the others the player must be authenticated. Follow the steps to use on Postman: 
1 - the link for authentication is http://localhost:8080/oauth/token
2 - must enter on Basic Auth: 
                            * Username: myclientid
                            * Password: myclientsecret

Usage
============
For the current month will be available on the following AWS enviroment: LINK AWS

API Game
============
GET - http://localhost:8080/games/{word} -> Word parameter is a Character and will return the following structure of json:
    {
        word: String, -- Each turn that the player throws the right guess the text will be filled. 
        guess: String, -- Current guess of the player.
        misses: String, -- Words that the player has missed.
        message: String -- Message that will be displayed at the following three occasions: 
                           1- For victory will be "YOU'RE SAVED!!"
                           2- For defeat will be "YOU WERE HANGED!!"
                           3- Case the player throws a repeated word "You already play this word!!"
    }
DELETE - localhost:8080/games -> Reset the game

API Word
============
GET - http://localhost:8080/words -> Returns all the words saved on the database.
GET - http://localhost:8080/words/{id} -> Returns the word the matches the id.
POST - http://localhost:8080/games?name=newWord -> save the new word on the database

API User
============
GET - http://localhost:8080/users/{id} -> Return the User registered with the id.