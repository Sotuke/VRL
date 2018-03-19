window.onload = function() {
    var xhttp = new XMLHttpRequest();

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
            var data = new FormData();
            data.append("post", text);
            xhttp.open("POST", "/api/submitpost", true);
            xhttp.send(data);
            postBox.value = "";

            posts.innerHTML = `
                    <article class="post">
                        <img class="avatar" src="avatar.svg"><span class="username">Username</span><span class="handle">@username</span><time class="time">now</time>
                        <div class="text">` + text + `</div>
                    </article>
            ` + posts.innerHTML;
        }
    }

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            response = JSON.parse(this.responseText);
            console.log(response);
            if (!response.success) {
                alert("Mingi error oli. Vist ei salvestanud ära.");
            }
        }
    }
}