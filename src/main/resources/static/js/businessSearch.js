$("#businessSearch").submit(function (event){
    event.preventDefault();
    var input = $("#businessSearchInput").val();
    console.log(input);

    $.ajax({
        type: "GET",
        url:'http://localhost:8080/businesses',
        data:JSON.stringify(input),
        contentType : 'application/json'
    });
});
