<template>
  <div class="home">

    {{Row.id}}
    {{Row.code}}
    <h2> Your own board </h2>
    <Board class="board" BoardId="PlayerBoard" v-on:SelectSpot="SelectSpot"></Board>
    <Colors v-on:SetColor="ChangeColor"></Colors>
    <button v-on:click="PostGuess" class="myButton">Confirm guess</button>
    <h2> Your opponents board </h2>
    <OpponentBoard class="board" BoardId="OpponentBoard" v-on:SelectSpot="SelectSpot"></OpponentBoard>
  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/components/Board.vue';
import Colors from '@/components/Colors.vue';
import OpponentBoard from '@/components/OpponentBoard.vue';
import axios from 'axios';

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
      Row: {id: 10, code: [null, null, null, null], clues: [null, null, null, null]},
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
      axios.get('http://localhost:8080/emptyrow/').then( response => this.SubmitGuess(response.data)).catch(error => console.log(error));
      console.log(this.Row.id);
      
    },
    SubmitGuess(response){
      console.log(response);
      this.Row = response;
      var Row = this.$children[0].$children.find(child => {return child.RowId == this.currentRow});
      var colors = [ 
        Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color ];
      this.Row.code = colors;
      console.log(this.Row.code);
      axios.post('http://localhost:8080/guess/submit/1/', this.Row)
        .then()
        .catch(error => console.log(error));
      
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

.myButton {
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
	background-color:	#888888;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.myButton:hover {
	background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
	background-color:#f6f6f6;
}
.myButton:active {
	position:relative;
	top:1px;
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