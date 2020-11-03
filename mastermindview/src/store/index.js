import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    ColorSpots: {},
  },
  mutations: {
    setPlayerBoard (state, board) {
      state.PlayerBoard = board;
    },
    setOpponentBoard (state, board) {
      state.OpponentBoard = board;
    },
    addColorSpot (state, colorspot) {
      state.ColorSpots = state.ColorSpots + colorspot;
    }
  },
  actions: {
  },
  modules: {
  },
  getters: {
    getColorSpotsColors: (state) => (row) => {
      Spots: state.colorspots.findAll(colorspot => colorspot.$parent.$parent.BaordId == 'PlayerBoard' && colorspot.$parent.RowId == row);
      return {Spots[0].$data.Color, Spots[1].$data.Color, Spots[2].$data.Color, Spots[3].$data.Color};;
    }
  }
})
