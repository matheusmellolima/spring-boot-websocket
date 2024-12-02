function connect(event) { 
    username = document.querySelector('#name').value.trim(); 
    console.log("Connecting to chat as: " + username);

    if(username) { 
        usernamePage.classList.add('hidden'); 
        chatPage.classList.remove('hidden'); 
 
        var socket = new SockJS('/ws'); 
        stompClient = Stomp.over(socket); 
 
        stompClient.connect({}, onConnected, onError); 
    } 
    event.preventDefault(); 
} 

 

function onConnected() { 
    // Subscribe to the Public Topic 
    stompClient.subscribe('/topic/public', onMessageReceived); 
 
    // Tell your username to the server 
    stompClient.send("/app/addUser", 
        {}, 
        JSON.stringify({sender: username, type: 'JOIN'}) 
    ) 
 
    connectingElement.classList.add('hidden'); 
    console.log("Connected");
} 
 
 
function onError(error) { 
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!'; 
    connectingElement.style.color = 'red'; 
    console.log("Could not connect to WebSocket server. Please refresh this page to try again!");
} 
 
 
function sendMessage(event) {
    var messageContent = messageInput.value.trim(); 
    console.log("Sending message: " + messageContent);
    if(messageContent && stompClient) { 
        var chatMessage = { 
            sender: username, 
            content: messageInput.value, 
            type: 'CHAT' 
        }; 
        stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage)); 
        messageInput.value = ''; 
        console.log("Message sent");
    } 
    event.preventDefault(); 
} 