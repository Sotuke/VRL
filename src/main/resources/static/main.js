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

    postButton.onclick = function() {
        var text = postBox.value.trim();
        if (text.length > 0 && text.length < 140) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/", true);
            //xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send();
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