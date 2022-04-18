var stompClient = null;
var socket = new SockJS('/websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/chatting', function (send) {
        showGreeting(JSON.parse(send.body).content);
    });
});

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    location.replace("/");
}

function sendName() {
    stompClient.send("/app/msg", {}, JSON.stringify({'content': $("#message").val(), 'sender': $("#user").val()}));
    document.getElementById("message").value = "";
}

function showGreeting(message) {
    var textarea = document.getElementById('chat');
    textarea.append("\n" + message);
    textarea.scrollTop = textarea.scrollHeight
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});