<h1 align="center">Hangman's Project</h1>

<p align="center">⚰️ API's needed for a hangman's game</p>

Table of contents
=================

<!--ts-->
   * [Instruction](#instruction)
   * [Usage](#usage)
   * [API Game](#api-game)
   * [API Word](#api-word)
   * [API User](#api-user)
   * [Docker](#docker)
     * [Public](#public)
<!--te-->

Instruction
============
<p>
All the endpoints that match GET request will be available without authentication.
<br>For the others the player must be authenticated. 
<br>Follow the steps to use on Postman:
<ol>
    <li>1 - the link for authentication is <strong>http://localhost:8080/oauth/token</strong></li>
    <li>2 - must enter on Basic Auth: 
                                <ul>
                                    <li>Username: <strong>myclientid</strong></li>
                                    <li>Password: <strong>myclientsecret</strong></li>
                                </ul>
    </li>
</ol>
</p>

Usage
============
<p>
For the current month will be available on the following AWS enviroment: LINK AWS
</p>

API Game
============
<p>
<strong>GET</strong> - http://localhost:8080/games/{word} -> Word parameter is a Character and will return the following structure of json:
<br>    {
<br>&emsp;        word: String, -- Each turn that the player throws the right guess the text will be filled. 
<br>&emsp;        guess: String, -- Current guess of the player.
<br>&emsp;        misses: String, -- Words that the player has missed.
<br>&emsp;        message: String -- Message that will be displayed at the following three occasions: 
                            <ul>
                                <li>1- For victory will be "YOU'RE SAVED!!"</li>
                                <li>2- For defeat will be "YOU WERE HANGED!!"</li>
                                <li>3- Case the player throws a repeated word "You already play this word!!"</li>
                            </ul>    
<br>    }
<br><strong>DELETE</strong> - localhost:8080/games -> Reset the game
</p>

API Word
============
<p>
<strong>GET</strong> - http://localhost:8080/words -> Returns all the words saved on the database.
<br><strong>GET</strong> - http://localhost:8080/words/{id} -> Returns the word the matches the id.
<br><strong>POST</strong> - http://localhost:8080/games?name=newWord -> save the new word on the database
</p>

API User
============
<p>
<strong>GET</strong> - http://localhost:8080/users/{id} -> Return the User registered with the id.
</p>