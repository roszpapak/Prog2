function showEdit(e) {
    e.preventDefault();
    e.stopPropagation();
    var x = document.getElementById("edit");
    if (x.style.display != "flex") {
        x.style.display = "flex";
    } else {
      x.style.display = "none";
    }
 };

function exitEdit(){
    var x = document.getElementById("edit");
     x.style.display = "none";
}

$("#editButton").click(showEdit);