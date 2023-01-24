const eventSource = new EventSource("http://localhost:8080/sender/ssar/receiver/cos");
eventSource.onmessage = (event) => {
    console.log(event)
    const sendParam = JSON.parse(event.data);
    console.log(sendParam)
    initMessages(sendParam)
}


function sendMsgBox(msg, time) {
    return `<div class="sent_msg">
                <p>${msg}</p>
                <span class="time_date"> 11:18 | today </span>
            </div>`;
}

function initMessages(param){
    console.log(param)
    let chatBox = document.querySelector("#chat-box");
    let msgBox = document.querySelector("#chat-msg");

    let chatDiv = document.createElement("div");
    chatBox.className = "outgoing_msg";

    chatDiv.innerHTML = sendMsgBox(param.msg, now);
    chatBox.append(chatDiv);
    msgBox.value = "";
}

function pushMessages(){
    let chatBox = document.querySelector("#chat-box");
    let msgBox = document.querySelector("#chat-msg");

    let chatDiv = document.createElement("div");
    chatBox.className = "outgoing_msg";

    let date = new Date();
    let now = date.getHours() + ":" + date.getMinutes();
    chatDiv.innerHTML = sendMsgBox(msgBox.value, now);
    chatBox.append(chatDiv);
    msgBox.value = "";
}

document.querySelector("#chat-send").addEventListener("click", ()=> {
    pushMessages();
});

document.querySelector("#chat-msg").addEventListener("keydown", (e)=> {
    if(e.keyCode === 13) {
        pushMessages();
    }
});

