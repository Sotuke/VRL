window.onload = function() {
    var xhttp = new XMLHttpRequest();

    var menuButton = document.getElementById("menubutton");
    var sideMenu = document.getElementById("sidemenu");
    var menuBurger = document.getElementById("menuburger");
    var menuArrow = document.getElementById("menuarrow");
    var postBox = document.getElementById("postbox");
    var postButton = document.getElementById("postbutton");
    var postForm = document.getElementById("postform");
    var posts = document.getElementById("posts");

    // Fontawesome CDN fallback
    for (var i = 0; i < document.styleSheets.length; i++) {
        var sheet = document.styleSheets[i];
        if (sheet.href === "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css") {
            if (!sheet.cssRules.length) {
                var link = document.createElement("link");
                link.type = "text/css";
                link.rel = "stylesheet";
                link.href = "/font-awesome-4.7.0/css/font-awesome.min.css";
                document.head.appendChild(link);
            }
        }
    }

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

    postForm.onsubmit = function() {
        var text = postBox.value.trim();

        if (text.length > 0 && text.length <= 200) {
            var data = new FormData();
            data.append("post", text);
            xhttp.open("POST", "/api/submitpost", true);
            xhttp.send(data);
            postBox.value = "";

            // Add new post to the DOM
            var newPost = document.createElement("article");
            newPost.classList.add("post");
            newPost.innerHTML = `
                <h3 class="hidden">This is a post</h3>
                <img class="avatar" src="/avatar.svg" width="40" height="40" alt="default avatar"><span class="username">Username</span><span class="handle">@username</span><time class="time">now</time>
            `
            var postText = document.createElement("div");
            postText.classList.add("text");
            postText.appendChild(document.createTextNode(text)); // sanitize the text
            newPost.appendChild(postText);

            posts.insertBefore(newPost, posts.firstChild);
        }

        return false;
    }

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            response = JSON.parse(this.responseText);
            //console.log(response);
            if (!response.success) {
                alert("An error occurred. Your post was not saved.");
            }
        }
    }
}