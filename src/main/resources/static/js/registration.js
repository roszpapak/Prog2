$(document).ready(function(){

function myFunction() {
    console.log("papa");
    var x = document.getElementById("passwordInput");
    if (x.type === "password") {
      x.type = "text";
    } else {
    console.log("elseag");
      x.type = "password";
    }
 };

function businessFunction() {
    var x = document.getElementById('bInput');
    if (x.style.display === "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
};

$("#show-passbox").change(myFunction);
$("#show-businessbox").change(businessFunction);


$("#sampleForm").submit(function(event){
    event.preventDefault();
    var data = {};
    data.firstName = $("#firstName").val();
    data.lastName = $("#lastName").val();
    data.email = $("#email").val();
    data.password = $("#passwordInput").val();
    data.business = {};
    data.business.name = $("#businessname").val();
    data.business.address = $("#address").val();
    data.business.tags =[];
    $("#tag").val().split(",").forEach(function(item){
        item = item.trim();
        data.business.tags.push({value:item});

    });
    data.business.pnumber = $("#pnumber").val();
    data.business.startTime = $("#stime").val();
    data.business.endTime = $("#etime").val();
    data.business.timeInterval = parseInt($("#timeint").val());
    console.log(data);


    $.ajax({
    type: "POST",
    url:'http://localhost:8080/api/v1/registration',
    data:JSON.stringify(data),
    contentType : 'application/json'
    });

});

});