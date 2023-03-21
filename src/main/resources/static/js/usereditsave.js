$(document).ready(function(){

    $("#userEditBox").submit(function (event){
            event.preventDefault();
            var data = {};
                data.firstName = $("#firstName").val();
                data.lastName = $("#lastName").val();
                if(document. getElementById("passwordInput").value.length == 0){
                data.password = null}
                else{
                data.password = $("#passwordInput").val();
                }

            console.log(data);

             $.ajax({
                 type: "POST",
                 url:'http://localhost:8080/edituser' ,
                 data:JSON.stringify(data),
                 contentType : 'application/json'
             })
             .done(function(){
                location.reload();
             });
    });
});


function deleteReservation(id) {
      $.ajax({
           type: "POST",
           url:'http://localhost:8080/deleteReservation/'+id,
           })
           .done(function(){
              location.reload();
           });
}