import Vue from 'vue'

const localState = {
    count: 0,
    lastSentMessage: '',
    socket: {
        isConnected: false,
        message: '',
        reconnectError: false,
        currentGameId: '00000000-0000-0000-0000-000000000000',
        currentPlayerId: 0
    }
}


const getters = {}

const actions = {

    async sendLogin({commit}, userinput){
        console.log('sending login')

        var message = {
            operation: "LOGIN",
            gameId: localState.socket.currentGameId,
            playerId: localState.socket.currentPlayerId,
            content: JSON.stringify(userinput)
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        } else {
            commit('NOT_CONNECTED_ERROR')
        }
    },

    async sendRegisterGame({commit}) {
        console.log('registering game')

        var message = {
            operation: "REGISTER_GAME",
            gameId: localState.socket.currentGameId,
            playerId: localState.socket.currentPlayerId,
            content: ''
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        } else {
            commit('NOT_CONNECTED_ERROR')
        }
    },
    async sendSubmitCode({commit}, code) {
        console.log('submitting code')

        var message = {
            operation: "SUBMIT_CODE",
            gameId: localState.socket.currentGameId,
            playerId: localState.socket.currentPlayerId,
            content: JSON.stringify(code)
        }

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
            playerId: localState.socket.currentPlayerId,
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
            playerId: localState.socket.currentPlayerId,
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
    },
    async changeGameID({ commit }, gameID) {
        commit('CHANGE_GAME_ID', gameID)
    },

    async changePlayerId({ commit }, playerId) {
        commit('CHANGE_PLAYER_ID', playerId)
    },
    async leaveGame({ commit }, gameId, playerId) {
        console.log('leaving game')

        var message = {
            operation: "LEAVE_GAME",
            gameId: localState.socket.currentGameId,
            playerId: localState.socket.currentPlayerId,
            content: JSON.stringify(gameId, playerId)
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        }
    },
    async restartGame({ commit }, gameId) {
        console.log('restarting game')

        var message = {
            operation: "RESTART_GAME",
            gameId: localState.socket.currentGameId,
            playerId: localState.socket.currentPlayerId,
            content: JSON.stringify(gameId)
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
            commit('SEND_MESSAGE', message)
        }
    },
    async sendGetCode({commit})
    {
        var message = {
            operation: "GET_CODE",
            gameId: localState.socket.currentGameId,
            playerId: localState.socket.currentPlayerId,
            content: '' 
        }

        if(localState.socket.isConnected) {
            Vue.prototype.$socket.send(JSON.stringify(message))
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
    },
    CHANGE_PLAYER_ID(state, playerId) {
        state.socket.currentPlayerId = playerId
    },
    LEAVE_GAME(state, gameId) {
        state.socket.gameID = gameId
    },
    RESTART_GAME(state, gameId) {
        state.socket.gameID = gameId
    }
}

export default {
    state: localState, getters, actions, mutations
}
