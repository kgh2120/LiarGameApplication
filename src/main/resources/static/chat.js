function getId(id) {
    return document.getElementById(id);
}
let websocket;
let data = {};
let field_nickname = getId("field_nickname");
let roomNum = getId("field_roomId");
let btn_login = getId("btn_login");
let talk = getId("talk");
let msg = getId("msg");
let btn_send = getId("btnSend");

btn_login.onclick = function (){
    webSocket = new WebSocket("ws://localhost:8080/chatting/**");
    webSocket.onopen = function (){
        field_nickname.readOnly=true;
        data.mid = "[시스템]";
        data.roomNum = roomNum.value;
        data.msg = `${field_nickname.value}님이 접속하셨습니다.`;
        data.date = new Date().toLocaleString();
        let sendData = JSON.stringify(data);
        webSocket.send(sendData);


    }


    webSocket.onmessage = function (msg){
        console.log(msg);
        console.log(msg.data);
        data = JSON.parse(msg.data);
        if (data.roomNum === roomNum.value) {
            let clazz = "";
            if(data.mid == field_nickname.value)
                clazz = "chat_my";
            else if(data.mid == "[시스템]"){
                talk.innerHTML += "<div class = 'outter'>";
                clazz = "chat_system";
            }
                
            else
                clazz = "chat_other";
            let item = `<div class = ${clazz}>
                    <span>${data.mid}</span>[${data.date}]<span> : ${data.msg}</span>
                </div>`;
                if(data.mid == "[시스템]")
                        item += "</div>";
            talk.innerHTML += item;
            talk.scrollTop = talk.scrollHeight;
        }

    }
}
msg.onkeyup = function (ev) {
    if (ev.key === "Enter") {
        send();
    }
};

btn_send.onclick = function () {
    send();

}
function send() {
    if (msg.value.trim() !== '') {
        data.mid = field_nickname.value;
        data.msg = msg.value;
        data.date = new Date().toLocaleString();
        data.roomNum = roomNum.value;
        let sendData = JSON.stringify(data);
        webSocket.send(sendData);
    }
    msg.value = '';
}