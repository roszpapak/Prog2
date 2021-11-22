function showEdit(e) {
    e.preventDefault();
    e.stopPropagation();
    var x = document.getElementById("editbox");
    if (x.style.display === "none") {
        x.style.display = "flex";
            console.log("papa");
    } else {
        console.log("elseag");
      x.style.display = "none";
    }
 };
$("#editButton").click(showEdit);