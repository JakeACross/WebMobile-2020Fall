var button = document.getElementsByClassName("buttons");
var com = 0; //define them as number. rock is 0, paper is 1, and scissors is 2
var user = 0;
var rock = document.getElementById("rock");
var paper = document.getElementById("paper");
var scissors = document.getElementById("scissors");

function comThink() //get random number from 0, 1 and 2
{
    com = Math.floor(Math.random() * 3);
}

function judge()//display a text depending on the result(condition)
{
    if ((user == 0 && com == 2) || (user == 1 && com == 0) || (user == 2 && com == 1)){
        document.getElementById("text").innerHTML = "You won!!!";

    }

    else if (user == com) {
        document.getElementById("text").innerHTML = "It is draw...";
    }

    else {
        document.getElementById("text").innerHTML = "You lost";
    }

}

function showCom()//display a computer's choice depending on the random number
{
    if (com == 0)
        document.getElementById('com-choice').src="images/rock.png";
    else if (com == 1)
        document.getElementById('com-choice').src="images/paper.png";
    else
        document.getElementById('com-choice').src="images/scissors.png";



}

for (var i = 0; i < button.length; i++)//due to this loop, user can anytime he or she wants to play

{

    rock.addEventListener("click", function () {
        user = 0;
        comThink();
        judge();
        showCom();
    });

    paper.addEventListener("click", function () {
        user = 1;
        comThink();
        judge();
        showCom();
    });

    scissors.addEventListener("click", function () {
        user = 2;
        comThink();
        judge();
        showCom();
    });
}


