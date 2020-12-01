import Vue from 'vue'

const localState = {
    count: 0,
    lastSentMessage: '',
    socket: {
        isConnected: false,
        message: '',
        reconnectError: false,
    }
}

const getters = {}

const actions = {
    async sendMessage({ commit }, message) {
        console.log(message)
        if(localState.isConnected) {
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
    }
}

export default {
    state: localState, getters, actions, mutations
}