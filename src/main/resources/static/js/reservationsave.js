$(document).ready(function(){

    $("#reservationSave").submit(function (event){
            event.preventDefault();
            var date = $("#reservationDateInput").val();
            var businessId = location.href.substring(location.href.lastIndexOf('/') + 1)
            var startTime = $('#reservationSelect').val();
            var data = {};
            data.date = date;
            data.businessId = businessId;
            data.startTime = startTime;
            console.log(data);

             $.ajax({
                 type: "POST",
                 url:'http://localhost:8080/reservationsave' ,
                 data:JSON.stringify(data),
                 contentType : 'application/json'

             })
             .done(function(){
                location.reload();
             });

    });

});
