import Vue from 'vue'

function generateUUID() { // Public Domain/MIT courtesy of Briguy37 on Stackoverflow
    var d = new Date().getTime();//Timestamp
    var d2 = (performance && performance.now && (performance.now()*1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16;//random number between 0 and 16
        if(d > 0){//Use timestamp until depleted
            r = (d + r)%16 | 0;
            d = Math.floor(d/16);
        } else {//Use microseconds since page-load if supported
            r = (d2 + r)%16 | 0;
            d2 = Math.floor(d2/16);
        }
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}

const localState = {
    count: 0,
    lastSentMessage: '',
    socket: {
        isConnected: false,
        message: '',
        reconnectError: false,
        currentGameId: ''
    }
}

const getters = {}

const actions = {
    async sendRegisterGame({commit}) {
        console.log('registering game')

        var message = {
            operation: "REGISTER_GAME",
            gameId: generateUUID(),
            playerId: 0,
            content: '' 
        }

        localState.socket.currentGameId = message.gameId

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        } else {
            commit('NOT_CONNECTED_ERROR')
        }
    },
    async sendGetEmptyRow({ commit }) {
        console.log('getting empty row')

        var message = {
            operation: "GET_EMPTY_ROW",
            gameId: localState.socket.currentGameId,
            playerId: 0,
            content: ""
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        } else {
            commit('NOT_CONNECTED_ERROR')
        }
    },
    async sendSubmitGuess({commit}, row) {
        console.log('submitting guess')

        var message = {
            operation: "SUBMIT_GUESS",
            gameId: localState.socket.currentGameId,
            playerId: 0,
            content: JSON.stringify(row)
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        } else {
            commit('NOT_CONNECTED_ERROR')
        }
    },
    async sendMessage({ commit }, message) {
        console.log(message)
        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(message)
            commit('SEND_MESSAGE', message)
        } else {
            commit('NOT_CONNECTED_ERROR')
        }
    }
}

const mutations = {
    SEND_MESSAGE(state, message) {
        state.lastSentMessage = message
    },
    NOT_CONNECTED_ERROR(state) {
        console.error(state, 'Not connected to websocket')
    },
    SOCKET_ONOPEN(state, event) {
        Vue.prototype.$socket = event.currentTarget
        state.socket.isConnected = true
    },
    SOCKET_ONCLOSE(state) {
        state.socket.isConnected = false
    },
    SOCKET_ONERROR(state, event) {
        console.error(state, event)
    },
    SOCKET_ONMESSAGE(state, message) {
        state.socket.message = message
    },
    SOCKET_RECONNECT(state, count) {
        console.info(state, count)
    },
    SOCKET_RECONNECT_ERROR(state) {
        state.socket.reconnectError = true
    },
    CHANGE_GAME_ID(state, gameId) {
        state.socket.currentGameId = gameId
    }
}

export default {
    state: localState, getters, actions, mutations
}