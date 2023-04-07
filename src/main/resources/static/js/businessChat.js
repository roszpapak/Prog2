var stompClient = null;
var toId;
var fromId;
var urlToId;
var urlFromId;
function  connect() {

    getLoggedUserId().then(function(Id) {
        fromId = Id;
        urlFromId = Id;
        var url = window.location.href.split("/");
        getUserId(url[url.length-1]).then(function(userId){
            toId = userId;
            urlToId = toId;
            var socket = new SockJS('/stomp-endpoint');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function (frame) {
                    console.log('Connected: ' + frame);
                    if (urlToId < urlFromId) {
                        var temp = urlToId;
                        urlToId = urlFromId;
                        urlFromId = temp;
                    }
                    endpoint = urlFromId+"_"+urlToId;
                    let url = '/topic/sendMessage/' + endpoint;
                    stompClient.subscribe(url, function (greeting) {
                        appendToChat(greeting.body);
                    });
                });
            setMessageSeen();
            addStyleToMessages();
        }).catch(function(error) {
              console.log("Error retrieving user ID from Business ID: " + error);
          });
    }).catch(function(error) {
        console.log("Error retrieving user ID: " + error);
    });

}

function appendToChat(message) {
    const chatDiv = $("#chats");
    var object = JSON.parse(message);
    const para = document.createElement("p");
    const node = document.createTextNode(object.messageContent);
    para.appendChild(node);
    if(object.fromId == fromId){
        para.style.textAlign = "right";
    }
    if(object.fromId == toId){
        console.log("Should be seen!");
        $.ajax({
          url: "http://localhost:8080/setMessageSeen/"+object.id,
          type: "GET"
        });
    }

    chatDiv.append(para);
    chatDiv.scrollTop(chatDiv.prop('scrollHeight'));
}

function send() {
    var data = {};
    data.fromId = fromId;
    data.toId = toId;

    endpoint = urlFromId+"_"+urlToId;
    let url = '/app/sendMessage/' + endpoint;
    data.message = $("#chatMessage").val();
    if(isWhiteSpace(data.message)){
        alert("Please write a message first!")
    }else{
        stompClient.send(url, {},JSON.stringify(data));
        $("#chatMessage").val('');
    }

}

function isWhiteSpace(str) {
  return /^\s*$/.test(str);
}

function addStyleToMessages(){
    var elements = document.getElementsByClassName(fromId);
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.textAlign = "right";
      }
}


function getLoggedUserId() {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/getLoggedUser",
            type: "GET",
            success: function(id) {
                resolve(id);
            },
            error: function(error) {
                reject(error);
            }
        });
    });
}

function getUserId(id) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: "http://localhost:8080/getUserId/business/"+id,
            type: "GET",
            success: function(userId) {
                resolve(userId);
            },
            error: function(error) {
                reject(error);
            }
        });
    });
}

function setMessageSeen(){
    $.ajax({
        url: "http://localhost:8080/setMessagesSeen/"+fromId+"/"+toId,
        type: "GET"
    });
}



connect();
