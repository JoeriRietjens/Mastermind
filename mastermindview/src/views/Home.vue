<template>
  <div class="home">
    <h2 class="boardTitle"> Your own board </h2>
    <Board class="board" BoardId="PlayerBoard" v-on:SelectSpot="SelectSpot"></Board>
    <Colors v-on:SetColor="ChangeColor"></Colors>
    <button v-show="confirmCodeIsShown" v-on:click="SubmitCode" class="myButton">Confirm code</button>
    <button v-show="confirmGuessIsShown" v-on:click="SubmitGuess" class="myButton">Confirm guess</button>
    <button v-on:click="showPanel" class="myButton">Instructions</button>
    <button v-show="restartIsShown" v-on:click="reloadPage" class="myButton">Restart game</button>
    <button v-on:click="leaveGame" class="myButton">Leave game</button>
    <h2 class="boardTitle"> Your opponents board </h2>
    <OpponentBoard v-on:SelectCodeSpot="SelectSpot" class="board" BoardId="OpponentBoard"></OpponentBoard>

    <slideout-panel></slideout-panel>
    
  </div>
</template>

<script>
// @ is an alias to /src
import Board from '@/components/Board.vue';
import Colors from '@/components/Colors.vue';
import OpponentBoard from '@/components/OpponentBoard.vue';
import Vue from 'vue';
import VueSlideoutPanel from 'vue2-slideout-panel';
import Instruction from '../components/Instruction.vue';
import VueSimpleAlert from "vue-simple-alert";
import { mapActions, mapState } from 'vuex';

Vue.use(VueSlideoutPanel);
Vue.use(VueSimpleAlert);

export default {
  name: 'Home',
  notifications: {
    showWinMessage: {
      title: "won",
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
      currentRow: '0',
      Row: {id: 10, guess: [null, null, null, null], clues: [null, null, null, null]},
      currentOpponentRow: 1,
      confirmCodeIsShown: true,
      restartIsShown: false,
    }
  },
  computed: mapState(['socket']),
  created() {
    this.$alert("Hello Player Please enter your colour code before you start.\n \nif you want to know how the game works please press on the Instruction button");
    this.unsubscribe = this.$store.subscribe(
      (mutation, state) => { 
        if (mutation.type == "SOCKET_ONOPEN") {
          if (state.socket.socket.isConnected) {
            this.sendRegisterGame()
            this.sendGetEmptyRow()
          }
        }
        else if (mutation.type == "SOCKET_ONMESSAGE") {
          var message = state.socket.socket.message
          var parsedMessage = JSON.parse(message.content);
          switch(message.operation) {
            case "REGISTER_GAME":
              this.changePlayerId(message.playerId)
              this.changeGameID(parsedMessage)
              break
            case "JOIN_GAME":
              this.changePlayerId(parsedMessage)
              break
            case "SUBMIT_GUESS":
              if(message.playerId == state.socket.socket.currentPlayerId){
                this.ChangeClues(parsedMessage)
              }
              else{
                this.changeOpponentRow(parsedMessage);
              }
              break
            case "GET_EMPTY_ROW":
              this.emptyRow = parsedMessage
              break
            case "SUBMIT_CODE":
              console.log(parsedMessage)
              break
            case "LEAVE_GAME":
              this.leaveGame(parsedMessage)
              break
          }
        }
      }
    );
    this.confirmGuessIsShown = false;
  },
  beforeDestroy() {
    this.unsubscribe();
  },
  methods: {
    ...mapActions(['sendGetEmptyRow', 'sendSubmitGuess', 'sendRegisterGame', 'changeGameID', 'changePlayerId', 'sendSubmitCode', 'leaveGame']),
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
        if(this.SelectedSpot != null){
          this.SelectedSpot.$data.Selected = false;
        }
        this.SelectedSpot = obj;
        this.SelectedSpot.$data.Selected = true;
      }
    },
    ChangeColor(color){
      this.SelectedSpot.$data.Color = color;
    },
    SubmitCode() {
      this.deselectSpot();
      var Row = this.$children[2].$children.find(child => {return child.RowId == '0'});
      var colors = [ 
      Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color];
      if(this.checkColorCode() == true)
      {
        this.sendSubmitCode(colors)
        this.setNextRow();
        }
      else
      {
        this.$fire({title:"Colour code input", text:"You haven't made a correct colour code!",type:'warning'});
      }

    },
    SubmitGuess(){
      this.deselectSpot();
      this.Row = Object.assign({}, this.Row); // copy empty row
      var Row = this.$children[0].$children.find(child => {return child.RowId == this.currentRow});
      var colors = [ 
        Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color ];
      this.Row.guess = colors;
      if(this.checkColorCode()==true)
      {
        this.sendSubmitGuess(this.Row);
      }
      else
      {
        this.$fire({title:"Colour input", text:"some inputs don't have a colour!",type:'warning'});
      }
    },
    ChangeClues(filledRow){
      this.Row = filledRow;
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
        this.WinGame();
        this.currentRow = null;
      }
      if(this.Row.clues[0] != null){
        this.currentRow++;
      }
      if(this.currentRow == 1){
        this.confirmGuessIsShown = true;
        this.confirmCodeIsShown = false;
      }
      if(this.currentRow == 11){
        this.currentRow = null;
        this.LostGame();
      }
    },
    LostGame() {
      this.restartIsShown = true;
      this.confirmGuessIsShown = false;
      this.currentRow = null;
      this.showLostMessage();
    },
    WinGame() {
      this.restartIsShown = true;
      this.confirmGuessIsShown = false;
      this.showWinMessage();
    },
    checkColorCode() {
      var Row = this.$children[2].$children.find(child => {return child.RowId == '0'});
      console.log(Row)
      var colors = [ 
        Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color 
        ];
      for(var i=0;i<4;i++)
      {
        if(colors[i]==null)
        {
          return false;
        }
      }
      return true;
    },
    reloadPage(){
      window.location.reload()
    },
    leaveGame(){
      this.leaveGame();
    },
    deselectSpot() {
      if(this.SelectedSpot != null){
          this.SelectedSpot.$data.Selected = false;
        }
      this.SelectedSpot = null;
    },
    changeOpponentRow(filledRow){
      var Row = this.$children[2].$children.find(child => {return child.RowId == this.currentOpponentRow});
      
      if(this.Row.clues[0] != 'BLANK') {
        Row.$children[4].Color = filledRow.clues[0];
      }
      if(this.Row.clues[1] != 'BLANK') {
        Row.$children[5].Color = filledRow.clues[1];
      }
      if(this.Row.clues[2] != 'BLANK') {
        Row.$children[6].Color = filledRow.clues[2];
      }
      if(this.Row.clues[3] != 'BLANK') {
        Row.$children[7].Color = filledRow.clues[3];
      }

      Row.$children[0].Color = filledRow.guess[0];
      Row.$children[1].Color = filledRow.guess[1];
      Row.$children[2].Color = filledRow.guess[2];
      Row.$children[3].Color = filledRow.guess[3];

      this.currentOpponentRow++;
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

.selectedSpot {
  border: 3px groove rgb(255, 255, 255);
}

.dot:hover {
  background-color: rgb(60, 5, 5);
  transition: 0.2s;
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