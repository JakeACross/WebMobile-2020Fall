function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "https://api.github.com/users/"+user, true);
    xhttp.onload = function () //execute when it is loaded
    {
        if (xhttp.readyState === xhttp.DONE) {
            if (xhttp.status === 200) {
                console.log(xhttp.responseText); //for checking
                showUser(JSON.parse(xhttp.responseText)); //update page content

            }
            else
                noSuchUser(user); //just display alert
        }
    };
    xhttp.send();

}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    document.getElementById('image').src=user.avatar_url;
    document.getElementById('user_name').innerText=user.name;
    document.getElementById('user_id').innerText=user.id;
    document.getElementById('user_url').href=user.url;
    document.getElementById('user_url').innerText=user.html_url;
    document.getElementById('user_repository').innerText=user.public_repos;
    document.getElementById('user_followers').innerText=user.followers;
    document.getElementById('user_following').innerText=user.following;
    document.getElementById('user_location').innerText=user.location;
}


function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    alert("User not found");

}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            getGithubInfo(username);


        }

    })
});
