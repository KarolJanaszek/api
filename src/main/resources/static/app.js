function togleFav() {
    const favDiv = document.getElementById("favorites");
    const btn = document.getElementById("toggleFavBtn");
    const links = document.getElementsByClassName('link');
    if (favDiv.style.display === "none") {
        btn.value = "Hide favorites";
        favDiv.style.display = "block";
    } else {
        btn.value = "Show favorites";
        favDiv.style.display = "none";
    }
    updateFavLinks(links);
}

function updateFavLinks(links) {
    const favDiv = document.getElementById("favorites");
    if (favDiv.style.display === "none") {
        for (let i = 0 ; i < links.length ; i++) {
            if (links.item(i).href.indexOf("&fav=") >= 0) {
                links.item(i).href = links.item(i).href.substring(0, links.item(i).href.indexOf("&fav="))
            }
        }
    } else {
        for (let i = 0 ; i < links.length ; i++) {
            if (links.item(i).href.indexOf("&fav=") < 0) {
                links.item(i).href = links.item(i).href + "&fav="
             }
        }
    }
}
