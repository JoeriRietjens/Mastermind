<template>
  <div class="home">
    <h2> Your own bord </h2>
    <Board class="board" BoardId="PlayerBoard" v-on:SelectSpot="SelectSpot"></Board>
    <Colors v-on:SetColor="ChangeColor"></Colors>
    <button v-on:click="PostGuess">Confirm guess</button>
    <h2> Your opponents board </h2>
    <OpponentBoard class="board" BoardId="OpponentBoard" v-on:SelectSpot="SelectSpot"></OpponentBoard>
  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/components/Board.vue';
import Colors from '@/components/Colors.vue';
import OpponentBoard from '@/components/OpponentBoard.vue';

export default {
  name: 'Home',
  components: {
    Board,
    Colors,
    OpponentBoard,
  },
  data() {
    return {
      SelectedSpot: null,
      currentRow: 'RowOne',
    }
  },
  methods: {
    SelectSpot(obj){
      this.SelectedSpot = obj;
      this.SelectedSpot.$data.selected = false;
    },
    ChangeColor(color){
      this.SelectedSpot.$data.Color = color;
    },
    PostGuess(){
      console.log("Guess confirmed");
      
      //row:{
      //  document.querySelector('#' + this.currentRow).querySelector('#SpotOne').Color,
      //  document.querySelector('#' + this.currentRow).querySelector('#SpotTwo').Color,
      //  document.querySelector('#' + this.currentRow).querySelector('#SpotThree').Color,
      //  document.querySelector('#' + this.currentRow).querySelector('#SpotFour').Color;
      //}
      console.log(this.PlayerBoard.BoardRow.ColorSpot[0].$data.Color)
      
      //axios.post();
      //document.querySelector('#' + this.currentRow).querySelector('#HintOne').style.backgroundColor = hints[0];
      //document.querySelector('#' + this.currentRow).querySelector('#HintTwo').style.backgroundColor = hints[1];
      //document.querySelector('#' + this.currentRow).querySelector('#HintThree').style.backgroundColor = hints[2];
      //document.querySelector('#' + this.currentRow).querySelector('#HintFour').style.backgroundColor = hints[3];
      this.setNextRow();
    },
    setNextRow(){
      switch (this.currentRow){
        case 'RowOne':
          this.currentRow = 'RowTwo';
          break;
        case 'RowTwo':
          this.currentRow = 'RowThree';
          break;
        case 'RowThree':
          this.currentRow = 'RowFour';
          break;
        case 'RowFour':
          this.currentRow = 'RowFive';
          break;
        case 'RowFive':
          this.currentRow = 'RowSix';
          break;
        case 'RowSix':
          this.currentRow = 'RowSeven';
          break;
        case 'RowSeven':
          this.currentRow = 'RowEight';
          break;
        case 'RowEight':
          this.currentRow = 'RowNine';
          break;
        case 'RowNine':
          this.currentRow = 'RowTen';
          break;
        case 'RowTen':
          //youlost();
          break;
      }
    }
  }
}
</script>

<style scoped>
.board {
  margin: 10px;
}

.homeElements {
  position: relative;
  display: inline-block;
}

.colors {
  top: -350px;
}

.board {
    border-style: ridge;
    width: 750px;
    height: 250px;
    position: relative;
    display: inline-block;
}

.row {
    position: relative;
    display: inline-block;
    padding: 5px 10px;
}

#code {
    top: -20px;
}
</style>