<template>
  <div class="home">

    <h2 class="boardTitle"> Your own board </h2>
    <Board class="board" BoardId="PlayerBoard" v-on:SelectSpot="SelectSpot"></Board>
    <Colors v-on:SetColor="ChangeColor"></Colors>
    <button v-on:click="SubmitCode" class="myButton">Confirm code</button>
    <button v-on:click="PostGuess" class="myButton">Confirm guess</button>
    <button v-on:click="showPanel" class="myButton">Instructions</button>
    <h2 class="boardTitle"> Your opponents board </h2>
    <OpponentBoard v-on:SelectCodeSpot="SelectCodeSpot" class="board" BoardId="OpponentBoard"></OpponentBoard>

    <slideout-panel></slideout-panel>

  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/components/Board.vue';
import Colors from '@/components/Colors.vue';
import OpponentBoard from '@/components/OpponentBoard.vue';
import axios from 'axios';
import Vue from 'vue';
import VueSlideoutPanel from 'vue2-slideout-panel';
import Instruction from '../components/Instruction.vue';
import VueSimpleAlert from "vue-simple-alert";

Vue.use(VueSlideoutPanel);
Vue.use(VueSimpleAlert);
//vue.components(Instruction,{});

export default {
  name: 'Home',
  notifications: {
    showWinMessage: {
      title: "win",
      message: "Player has won!",
      type: "info"
    },
    showLostMessage: {
      title: "lost",
      message: "Player has lost!",
      type: "info"
    }
  },
  components: {
    Board,
    Colors,
    OpponentBoard
  },
  data() {
    return {
      SelectedSpot: null,
      currentRow: 'RowOne',
      Row: {id: 10, code: [null, null, null, null], clues: [null, null, null, null]},
    }
  },
  methods: {
    showPanel() {
    const panel1Handle = this.$showPanel({
      component : Instruction,
      openOn: 'left',
      props: {
        

        //any data you want passed to your component
      }
    });
    
    panel1Handle.promise
      // .then(result => {
        
      // });
    
  },

    SelectSpot(obj){
      if (obj.$parent.RowId == this.currentRow){
        this.SelectedSpot = obj;
      }
    },
    ChangeColor(color){
      this.SelectedSpot.$data.Color = color;
    },
    SelectCodeSpot(SelectCodeSpot) {
      this.SelectedSpot = SelectCodeSpot;
    },
    SubmitCode() {
      var Row = this.$children[2].$children.find(child => {return child.RowId == 'code'});
      var colors = [ 
      Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color];
      if(this.checkColorCode()==true)
      {
      axios.post('http://localhost:8080/code/submit/0/', colors).then().catch(error => console.log(error));
      }
      else
      {
          this.$fire({title:"Colour code input", text:"You didn't have made your colour code!",type:'warning'});
      }

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
      this.Row.guess = colors;
      console.log(this.Row.code);
            if(this.checkColorCode()==true)
      {
      axios.post('http://localhost:8080/guess/submit/1/', this.Row)
        .then(response => this.ChangeClues(response.data))
        .catch(error => console.log(error));
      }
      else
      {
        this.$fire({title:"Colour input", text:"some inputs don't have a colour!",type:'warning'});
      }
    },
    ChangeClues(response){
      this.Row = response;
      console.log(response);
      var Row = this.$children[0].$children.find(child => {return child.RowId == this.currentRow});
      
      if(this.Row.clues[0] != 'BLANK') {
        Row.$children[4].Color = this.Row.clues[0];
      }
      if(this.Row.clues[1] != 'BLANK') {
        Row.$children[5].Color = this.Row.clues[1];
      }
      if(this.Row.clues[2] != 'BLANK') {
        Row.$children[6].Color = this.Row.clues[2];
      }
      if(this.Row.clues[3] != 'BLANK') {
        Row.$children[7].Color = this.Row.clues[3];
      }
      if(this.Row.clues[0] == 'BLACK' && this.Row.clues[1] == 'BLACK' && this.Row.clues[2] == 'BLACK' && this.Row.clues[3] == 'BLACK') {
        this.showWinMessage();
        this.currentRow = null;
      }
      if(this.Row.clues[0] != null){
        this.setNextRow();
        this.SelectedSpot = null;
      }
    },
    LostGame() {
      this.showLostMessage();
    },
    WinGame() {
      this.showWinMessage();
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
          this.currentRow = null;
          this.SelectedSpot = null;
          this.showLostMessage();
          break;
        case null:
          this.SelectedSpot = null;
          break;
      }
    },
    
    checkColorCode:function() {
      console.log("CheckColorCode");
        var Row = this.$children[2].$children.find(child => {return child.RowId == 'code'});
      var colors = [ 
        Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color ];
        for(var i=0;i<4;i++)
        {
          if(colors[i]==null)
          {
            return false;
          }
        }
        return true;
    }
  }
}
</script>

<style>
.board {
  margin: 10px;
  padding: 5px;
  box-shadow: -5px 5px 10px black;
  border: 0px solid black;
  border-radius: 15px;
  background-color: rgb(82, 11, 11);
}

.boardTitle {
  color: white;
}

.myButton {
	background-color:	rgb(82, 11, 11);
	border-radius:6px;
	border:1px solid black;
	display:inline-block;
	cursor:pointer;
	color:white;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
  margin-left: 10px;
  margin-top: 5px;
	text-decoration:none;
  height: 40px;
  transition: 0.2s;
}

.myButton:hover {
	background-color: rgb(60, 5, 5);
  transition: 0.2s;
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

.dot {
  height: 40px;
  width: 40px;
  background-color: rgb(70, 7, 7);
  box-shadow: inset 0px 0px 2px black;
  border-radius: 50%;
  display: inline-block;
  border: 3px groove rgb(10, 5, 5);
}

.colorSpot .dot{  
  height: 50px;
  width: 50px;
  border-radius: 50%;
  display: inline-block;
  border: 0px;
  box-shadow: -2px 2px 5px black;
  margin: 5px;
}
</style>