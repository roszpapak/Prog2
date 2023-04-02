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
        if(!input){
            alert("Please choose date!");
        }else{
            var id = location.href.substring(location.href.lastIndexOf('/') + 1)
            var data = {};
            data.date = input;
            data.id = id;

            $.ajax({
                type: "GET",
                url:'http://localhost:8080/reservations/' + data.id + '/' + data.date,
            })
            .done(function(data){
                console.log(data);
                if(data.length != 0){
                    var $select = $('#reservationSelect');
                    $('#reservationSelect option').remove();
                    var button = $('#reservationSaveButton').css("display","flex");
                    $("#reservationSelect").css("display","block");

                    for(const current of data){
                        $select.append($("<option />").val(current.first).text("START: " + current.first + "   END: " + current.second));

                    }
                }else{
                    $('#reservationSelect option').remove();
                    $("#reservationSelect").css("display","none");
                    alert("No free reservation is available")
                }
            })
        }

    });
});