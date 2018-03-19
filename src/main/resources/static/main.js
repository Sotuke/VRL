window.onload = function() {
    var menuButton = document.getElementById("menubutton");
    var sideMenu = document.getElementById("sidemenu");
    var menuBurger = document.getElementById("menuburger");
    var menuArrow = document.getElementById("menuarrow");
    var postBox = document.getElementById("postbox");
    var postButton = document.getElementById("postbutton");
    var posts = document.getElementById("posts");

    window.addEventListener("resize", function(event) {
        if (window.innerWidth >= 800) {
            sideMenu.style.left = "0px";
        }
    })

    menuButton.onclick = function() {
        if (window.innerWidth <= 800) {
            console.log(menuBurger.style.display);
            console.log(menuArrow.style.display);
            if (sideMenu.style.left === "0px") {
                sideMenu.style.left = "-75%";
                menuBurger.style.display = "inline";
                menuArrow.style.display = "none";
            } else {
                sideMenu.style.left = "0px";
                menuBurger.style.display = "none";
                menuArrow.style.display = "inline";
            }
        }

    }
    function post() {
        
    }
    function PostDTO() {
        // creates a basic data structure, with empty or default values or from parameters
        return {
            post: "eee",
            date: new Date()
    };
    }

    postButton.onclick = function() {
        var text = postBox.value.trim();
        if (text.length > 0 && text.length < 140) {
            var newXHR = new XMLHttpRequest();

            // bind our event listener to the "load" event.
            // "load" is fired when the response to our request is completed and without error.

            // go to http://requestb.in/1k6rql51?inspect to view your request!
            newXHR.open( 'POST', '/proov' );
            //             ^-- IMPORTANT: to send data to the server with it appearing in the url use 'POST'

            // set the header
            // this lets the server know where/how to expect your data
            // visit: http://requestb.in/1k6rql51?inspect
            // you will notice that the data sent over will appear under "FORM/POST PARAMETERS"
            newXHR.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );

            // this is how form data looks like when you send it with the attributes `action="POST"` on your form
            var formData = 'name=Ray';
            newXHR.send(formData);


            //xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            /* AJAX varsti */
            /*posts.innerHTML = `
                    <article class="post">
                        <img class="avatar" src="avatar.svg"><span class="username">Username</span><span class="handle">@username</span><time class="time">now</time>
                        <div class="text">` + text + `</div>
                    </article>
            ` + posts.innerHTML;*/
        }
    }
}