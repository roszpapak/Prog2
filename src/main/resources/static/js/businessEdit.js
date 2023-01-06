$(document).ready(function(){

    $("#businessEditForm").submit(function (event){
            event.preventDefault();
            var data = {};
            data.phone = $("#pNumber").val();
            data.name = $("#name").val();
            data.address = $("#address").val();
            data.tags =[];


            data.startTime = $("#startTime").val();
            data.endTime = $("#endTime").val();

            console.log(data);
            console.log(JSON.stringify(data));

             $.ajax({
                 type: "POST",
                 url:'http://localhost:8080/editBusiness',
                 data:JSON.stringify(data),
                 contentType : 'application/json'

             })
             .done(function(){
                location.reload();
             });

    });

});