function getAvgRating() {
    var url = window.location.href.split("/");
    businessId = url[url.length-1]
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/rating/average/"+businessId,
            type: "GET",
            success: function(star) {
                setAvgStar(star);
            },
            error: function(error) {
                reject(error);
            }
        });
    });
}


function setAvgStar(star){
    $("#avgRating").text(star);

    for (let i = 1; i < parseInt(Math.round(star), 10)+1; i++) {
        const elem = document.getElementById("rateStar"+i);
        elem.classList.add("checked");
    }
}

getAvgRating();




function changeAction(id){
    const ids= ["saveDateBox","sendFeedbacksBox","feedbacksBox","chatBox"];

    ids.forEach(e => {
        const elem = document.getElementById(e);
        elem.style.display = "none";
    })
    const getId = id+"Box";
    console.log(getId);
    if(getId != "feedbacksBox"){
        const elem = document.getElementById(getId);
        elem.style.display = "block";
    } else{
        const elem = document.getElementById(getId);
        elem.style.display = "flex";
    }
    if(getId == "chatBox"){
        const chatDiv = $("#chats");
        chatDiv.scrollTop(chatDiv.prop('scrollHeight'));
    }


}