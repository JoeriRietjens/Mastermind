import Vue from 'vue'
import Vuex from 'vuex'
import VueNativeSock from 'vue-native-websocket'
import socket from './modules/socket'
import { config } from './modules/config'

Vue.use(Vuex)

export const store = new Vuex.Store({
  modules: {
    socket
  }
})

Vue.use(VueNativeSock, config.baseApiUrl, { store: store, format: 'json' })