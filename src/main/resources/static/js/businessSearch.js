$(document).ready(function(){

$("#businessSearch").submit(function (event){
    event.preventDefault();
    var input = $("#businessSearchInput").val();
    var data = {};
    data.keyword = input;
    console.log(input);

    $.ajax({
        type: "GET",
        url:'http://localhost:8080/businesses/findByName',
        data:data
    })
    .done(function(data){
    var tbody = $('#businessTable tbody');
    tbody.empty();
    for(const current of data){
            var tr = document.createElement('tr');
            var td =
                '<td>' + current.name + '</td>' +
                '<td>' + current.address + '</td>' +
                '<td>' + current.pnumber + '</td>';
            td += '<td>';
            for(const element of current.tags ){
                td += '<span>' + element.value + '</span>';
            }
            td += '</td>';
            tr.innerHTML = td;
            tbody.append(tr);
    }
    });
});
});
