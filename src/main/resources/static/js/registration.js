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


if (x.style.display === "block"){
    $("#businessname").attr('minlength',"4");
    $("#address").attr('minlength',"4");
    $("#pnumber").attr('minlength',"4");
    $("#tag").attr('minlength',"4");

    $("#businessname").attr("required", "");
    $("#address").attr("required", "");
    $("#pnumber").attr("required", "");
    $("#tag").attr("required", "");
    $("#etime").attr("required", "");
    $("#stime").attr("required", "");
}else{

    $("#businessname").removeAttr('minlength',"8");
    $("#address").removeAttr('minlength',"8");
    $("#pnumber").removeAttr('minlength',"8");
    $("#tag").removeAttr('minlength',"8");

    $("#businessname").removeAttr("required", "");
    $("#address").removeAttr("required", "");
    $("#pnumber").removeAttr("required", "");
    $("#tag").removeAttr("required", "");
    $("#etime").removeAttr("required", "");
    $("#stime").removeAttr("required", "");
}


};

$("#show-passbox").change(myFunction);
$("#show-businessbox").change(businessFunction);

$("#stime").change(eTimeMin);
$("#etime").change(sTimeMax);





function eTimeMin(){
    $('#etime').attr('min',$('#stime').val());
}

function sTimeMax(){
    $('#stime').attr('max',$('#etime').val());
}

$("#sampleForm").submit(function(event){
    event.preventDefault();
     if($("#businessBoxId input[type=checkbox]").is(":checked")){
     var role = "BUSINESSOWNER";
     } else {role = "USER"};
    var data = {};
    data.firstName = $("#firstName").val();
    data.lastName = $("#lastName").val();
    data.email = $("#email").val();
    data.password = $("#passwordInput").val();
    if($("#businessBoxId input[type=checkbox]").is(":checked")){
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
    }else { data.business = null}
    data.userRole = role;
    console.log(data);
    console.log(role);



    $.ajax({
    type: "POST",
    url:'http://localhost:8080/api/v1/registration',
    data:JSON.stringify(data),
    contentType : 'application/json'
    })
    .done(function(){
       location.href = "/home?registrationconfirm=true";
    });

});

});