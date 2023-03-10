let rating = 1;
function rateBusiness(toId,fromId) {

    let rateMessage = document.getElementById("rateMessage").value;
    console.log(toId);
    console.log(fromId);
    console.log(rateMessage)
    console.log(rating);
    var data = {};
        data.fromId = fromId;
        data.toId = toId;
        data.value = rating;
        data.message = rateMessage;


    $.ajax({
       type: "POST",
       url:'http://localhost:8080/rating/save',
       data:JSON.stringify(data),
       contentType : 'application/json'
       })
       .done(function(){
          location.reload();
       });
}

function starColorChange(id){
    let starCount = parseInt(id.charAt(id.length - 1), 10)
    rating = starCount;
    for (let i = 1; i < 6; i++) {
        const elem = document.getElementById("star"+i);
        elem.classList.remove("checked");
    }

    for (let i = 1; i < starCount+1; i++) {
        const elem = document.getElementById("star"+i);
        elem.classList.add("checked");
    }
}