import { config } from './config'
import axios from 'axios'

const localState = {
    user: {},
    loggedIn: false,
    connection: new WebSocket(config.baseApiUrl)
}

const getters = {
    user: (state) => state.user,
    loggedIn: (state) => state.loggedIn
}

const actions = {
    async setAccount({ commit }, userName) {
        const resp = await axios.get(config.baseApiUrl + 'user/byUserName/' + userName)

        commit('setUser', resp.data)
    }
}

const mutations = {
    setUser: (state, user) => {state.user = user; state.loggedIn = true}
}

export default {
    state: localState, getters, actions, mutations
}