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

    $("#changePictureForm").submit(function (event){

        let formData = new FormData();

        let file = $("#file")[0].files[0];

        formData.append('file',file);


         $.ajax({
             type: "POST",
             url:'http://localhost:8080/changePicture',
             data:formData,
             contentType : false,
             processData:false
         })
         .done(function(){
               location.reload();
         });
    });



    $(function() {
      $('input[name="daterange"]').daterangepicker({
        opens: 'left'
      }, function(start, end, label) {
        console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        var data = {};
        data.start = start.format('YYYY-MM-DD');
        data.end = end.format('YYYY-MM-DD');

        $.ajax({
         type: "POST",
         url:'http://localhost:8080/takeHoliday',
         data:JSON.stringify(data),
         contentType : 'application/json'

         })
         .done(function(){
            location.reload();
         });
      });

    });

});

function deleteHoliday(id) {
      $.ajax({
           type: "POST",
           url:'http://localhost:8080/deleteHoliday/'+id,
           })
           .done(function(){
              location.reload();
           });
}


function deleteReservation(id) {
      $.ajax({
           type: "POST",
           url:'http://localhost:8080/deleteReservation/'+id,
           })
           .done(function(){
              location.reload();
           });
}