$(document).ready(function(){

    $(function(){
        var dtToday = new Date();

        var month = dtToday.getMonth() + 1;
        var day = dtToday.getDate();
        var year = dtToday.getFullYear();
        if(month < 10)
            month = '0' + month.toString();
        if(day < 10)
            day = '0' + day.toString();

        var maxDate = year + '-' + month + '-' + day;

        // or instead:
        // var maxDate = dtToday.toISOString().substr(0, 10);


        $('#reservationDateInput').attr('min', maxDate);
    });

    $("#reservationSearch").submit(function (event){
        event.preventDefault();
        var input = $("#reservationDateInput").val();
        var id = location.href.substring(location.href.lastIndexOf('/') + 1)
        var data = {};
        data.date = input;
        data.id = id;
        console.log(data);

        $.ajax({
            type: "GET",
            url:'http://localhost:8080/reservations/' + data.id + '/' + data.date,
            //data:data
        })
        .done(function(data){
        var $select = $('#reservationSelect');
        $('#reservationSelect option').remove();
        var button = $('#reservationSaveButton').css("display","flex");
        for(const current of data){
            console.log(current);
            $select.append($("<option />").val(current.first).text("START: " + current.first + "  END: " + current.second));
        }
        })
    });
});