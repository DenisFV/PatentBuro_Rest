import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

let stompClient;
const handlers = []

export function connect() {
    const socket = new SockJS('/gs-guide-websocket')
    stompClient = Stomp.over(socket)
    stompClient.debug = () => {}
    stompClient.connect({}, frame => {
        // console.log('Connected: ' + frame)
        stompClient.subscribe('/topic/activity', request => {
            handlers.forEach(handler => handler(JSON.parse(request.body)))
        })
    })
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

export function sendRequest(request) {
    stompClient.send("/app/changeRequest", {}, JSON.stringify(request))
}