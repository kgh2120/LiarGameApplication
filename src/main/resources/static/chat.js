function getElement(ID) {
    return document.getElementById(ID);
}


function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}

var stompClient = null;
var nickName = uuidv4();
var btn_send = getElement("btn_send");
var text_area = getElement("text_area");
var draw_text = getElement("draw_text");
var sender = getElement("input_sender");
var roomId = getElement("roomNum");


function init() {

    sender.value = nickName;

    var socket = new SockJS('/chat-room');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/sub/room/' + roomId.innerText, function (message) { // room ID를 알아서 구독을 해야 함.
            readMessage(JSON.parse(message.body));
        });
    });

    btn_send.addEventListener('click', function () {
        sendMessage();
        console.log("now click");
    });

    text_area.onkeyup = function (ev) {
        if (ev.key === "Enter") {
            sendMessage();
            console.log("now enter");
        }
    };


    setTimeout(() => open(), 1000);
}

function readMessage(message) {

    let clazz = "";
    if(message.sender === nickName)
        clazz = "chat_my";
    else if(message.sender === "[시스템]"){
        draw_text.innerHTML += "<div class = 'outter'>";
        clazz = "chat_system";
    }
    else
        clazz = "chat_other";

    let item = `<div class="${clazz}">
                    <span>${message.sender}</span><span> : ${message.contents}</span>
                </div>`;
    if(message.sender === "[시스템]")
        item += "</div>";
    draw_text.innerHTML += item
    text_area.value = "";
}

function sendMessage() {
    let msg = {
        messageType: "ON",
        roomId: roomId.innerText,
        sender: nickName,
        contents: text_area.value
    }
    stompClient.send("/pub/message", {}, JSON.stringify(msg));
    console.log("msg : " + JSON.stringify(msg));
}

function open() {
    var msg = {
        messageType: "OPEN",
        roomId: roomId.innerText,
        sender: nickName,
        contents: "아령하세얀"
    };
    stompClient.send("/pub/message", {}, JSON.stringify(msg));

}

init();

