var button = document.getElementsByClassName("btn btn-secondary");
var com = 0;
var user = 0;
var rock = document.getElementById("rock");
var paper = document.getElementById("paper");
var scissors = document.getElementById("scissors");

function comThink(){
    com = Math.floor(Math.random() * 3) + 1;
}

function converter(a){
    if (a == 0)
        user = 0;
    else if (a == 1)
        user = 1;
    else if (a == 2)
        user = 2;



}

function judge(){
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

function showCom(){
    if (com == 0)
        document.getElementById('com-choice').src="images/rock.png";
    else if (com == 1)
        document.getElementById('com-choice').src="images/paper.png";
    else
        document.getElementById('com-choice').src="images/scissors.png";



}





for (var i = 0; i < button.length; i++) {

    rock.addEventListener("click", function () {
        converter(0);
        comThink();
        judge();
        showCom();
    });

    paper.addEventListener("click", function () {
        converter(1);
        comThink();
        judge();
        showCom();
    });

    scissors.addEventListener("click", function () {
        converter(2);
        comThink();
        judge();
        showCom();
    });
}


