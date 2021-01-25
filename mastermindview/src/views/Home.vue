<template>
  <div class="home">
    <h2 class="boardTitle"> Your own board </h2>
    <Board class="board" BoardId="PlayerBoard" v-on:SelectSpot="SelectSpot"></Board>
    <Colors v-on:SetColor="ChangeColor"></Colors>
    <button v-show="confirmCodeIsShown" v-on:click="SubmitCode" class="myButton">Confirm code</button>
    <button v-show="confirmGuessIsShown" v-on:click="SubmitGuess" class="myButton">Confirm guess</button>
    <button v-on:click="showPanel" class="myButton">Instructions</button>
    <button v-show="restartIsShown" v-on:click="restartGame" class="myButton">Restart game</button>
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
      SpotColor: ' rgb(70, 7, 7)',

      currentRow: 0,
      currentOpponentRow: 1,
      Row: {id: 10, guess: [null, null, null, null], clues: [null, null, null, null]},
      code: null,

      confirmCodeIsShown: true,
      confirmGuessIsShown: false,
      restartIsShown: false,

      codeSubmitted: false,
      opponentJoined: false,
    }
  },
  computed: mapState(['socket']),
  created() {
    this.$alert('Hello Player Please enter your colour code before you start.\n \nIf you want to know how the game works please press on the Instruction button');
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
              if(message.playerId == 1){
                this.currentRow = 0;
              }
              else {
                this.currentRow = null;
              }
              break
            case "JOIN_GAME":
              if(message.playerId != 0) {
                this.$alert("An opponent has joined! Submit a code for your opponent.");
                this.currentRow = 0;
              }
              else{
                this.changePlayerId(parsedMessage);
                this.currentRow = null;
              }
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
              console.log(parsedMessage);
              if(message.playerId == state.socket.socket.currentPlayerId){
                if(this.codeSubmitted){
                  this.currentRow = 1;
                  this.$alert("You have succesfully submitted your code and you can start guessing!");
                  this.sendGetCode();

                }
                else {
                  this.currentRow = null;
                  this.$alert("Your have succesfully submitted your code! Wait untill your opponent has set a code for you.")
                }
              }
              else {
                this.codeSubmitted = true;
                if(this.currentRow == null){
                  this.currentRow = 1;
                  this.$alert("Your opponent has submitted a code for you to guess! You can start guessing!")
                  this.sendGetCode();

                }
                else{
                  this.$alert("Your opponent has submitted a code for you! After submitting a code yourself, you can start guessing!")
                }
              }
              break
            case "LEAVE_GAME":
              if(message.playerId == state.socket.socket.currentPlayerId){
                this.reloadPage();
              }
              else{
                if(this.codeSubmitted){            
                  this.$alert("Your opponent left the game. Feel free to keep guessing yourself!");
                }
                else{
                  this.reloadPage();
                }
              }
              break
            case "RESTART_GAME":
              if(message.playerId == state.socket.socket.currentPlayerId){
                this.emptyPage();
              }
              else{
                this.emptyPage();
                this.$alert("Your opponent wants to battle you again! And a new game has started. Begin with submitting a new code! Don't want to play? Click on 'Leave game'.");
              }
              break
            case "GET_CODE":
              this.code=parsedMessage
              break
          }
        }
      }
    );
  },
  beforeDestroy() {
    this.unsubscribe();
  },
  methods: {
    ...mapActions(['sendGetEmptyRow', 'sendSubmitGuess', 'sendRegisterGame', 'changeGameID', 'changePlayerId', 'sendSubmitCode', 'leaveGame','sendGetCode', 'restartGame']),
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

      var Row = this.$children[2].$children.find(child => {return child.RowId == 0});
      var colors = [
      Row.$children[0].Color, Row.$children[1].Color, Row.$children[2].Color, Row.$children[3].Color];
      if(this.checkColorCode() == true)
      {
        this.sendSubmitCode(colors)
        this.currentRow++;   
        if(this.currentRow == 1){
          this.confirmGuessIsShown = true;
          this.confirmCodeIsShown = false;
        }
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
      }
      if(this.Row.clues[0] != null){
        this.currentRow++;
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
      this.revealCode();
      this.showLostMessage();
      this.restartIsShown = true;
    },
    WinGame() {
      this.restartIsShown = true;
      this.confirmGuessIsShown = false;
      this.currentRow = null;
      this.revealCode();
      this.showWinMessage();
      this.restartIsShown = true;
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
    reloadPage() {
      window.location.reload();
    },
    emptyPage(){

      var Rowo1 = this.$children[2].$children.find(child => {return child.RowId == 1});
         
      Rowo1.$children[4].Color = this.SpotColor;
      Rowo1.$children[5].Color = this.SpotColor;
      Rowo1.$children[6].Color = this.SpotColor;
      Rowo1.$children[7].Color = this.SpotColor;
      

      Rowo1.$children[0].Color = this.SpotColor;
      Rowo1.$children[1].Color = this.SpotColor;
      Rowo1.$children[2].Color = this.SpotColor;
      Rowo1.$children[3].Color = this.SpotColor;

      var Rowo2 = this.$children[2].$children.find(child => {return child.RowId == 2});
         
      Rowo2.$children[4].Color = this.SpotColor;
      Rowo2.$children[5].Color = this.SpotColor;
      Rowo2.$children[6].Color = this.SpotColor;
      Rowo2.$children[7].Color = this.SpotColor;
      

      Rowo2.$children[0].Color = this.SpotColor;
      Rowo2.$children[1].Color = this.SpotColor;
      Rowo2.$children[2].Color = this.SpotColor;
      Rowo2.$children[3].Color = this.SpotColor;

      var Rowo3 = this.$children[2].$children.find(child => {return child.RowId == 3});
         
      Rowo3.$children[4].Color = this.SpotColor;
      Rowo3.$children[5].Color = this.SpotColor;
      Rowo3.$children[6].Color = this.SpotColor;
      Rowo3.$children[7].Color = this.SpotColor;
      

      Rowo3.$children[0].Color = this.SpotColor;
      Rowo3.$children[1].Color = this.SpotColor;
      Rowo3.$children[2].Color = this.SpotColor;
      Rowo3.$children[3].Color = this.SpotColor;

      var Rowo4 = this.$children[2].$children.find(child => {return child.RowId == 4});
         
      Rowo4.$children[4].Color = this.SpotColor;
      Rowo4.$children[5].Color = this.SpotColor;
      Rowo4.$children[6].Color = this.SpotColor;
      Rowo4.$children[7].Color = this.SpotColor;
      

      Rowo4.$children[0].Color = this.SpotColor;
      Rowo4.$children[1].Color = this.SpotColor;
      Rowo4.$children[2].Color = this.SpotColor;
      Rowo4.$children[3].Color = this.SpotColor;

      var Rowo5 = this.$children[2].$children.find(child => {return child.RowId == 5});
         
      Rowo5.$children[4].Color = this.SpotColor;
      Rowo5.$children[5].Color = this.SpotColor;
      Rowo5.$children[6].Color = this.SpotColor;
      Rowo5.$children[7].Color = this.SpotColor;
      

      Rowo5.$children[0].Color = this.SpotColor;
      Rowo5.$children[1].Color = this.SpotColor;
      Rowo5.$children[2].Color = this.SpotColor;
      Rowo5.$children[3].Color = this.SpotColor;

      var Rowo6 = this.$children[2].$children.find(child => {return child.RowId == 6});
         
      Rowo6.$children[4].Color = this.SpotColor;
      Rowo6.$children[5].Color = this.SpotColor;
      Rowo6.$children[6].Color = this.SpotColor;
      Rowo6.$children[7].Color = this.SpotColor;
      

      Rowo6.$children[0].Color = this.SpotColor;
      Rowo6.$children[1].Color = this.SpotColor;
      Rowo6.$children[2].Color = this.SpotColor;
      Rowo6.$children[3].Color = this.SpotColor;

      var Rowo7 = this.$children[2].$children.find(child => {return child.RowId == 7});
         
      Rowo7.$children[4].Color = this.SpotColor;
      Rowo7.$children[5].Color = this.SpotColor;
      Rowo7.$children[6].Color = this.SpotColor;
      Rowo7.$children[7].Color = this.SpotColor;
      

      Rowo7.$children[0].Color = this.SpotColor;
      Rowo7.$children[1].Color = this.SpotColor;
      Rowo7.$children[2].Color = this.SpotColor;
      Rowo7.$children[3].Color = this.SpotColor;

      var Rowo8 = this.$children[2].$children.find(child => {return child.RowId == 8});
         
      Rowo8.$children[4].Color = this.SpotColor;
      Rowo8.$children[5].Color = this.SpotColor;
      Rowo8.$children[6].Color = this.SpotColor;
      Rowo8.$children[7].Color = this.SpotColor;
      

      Rowo8.$children[0].Color = this.SpotColor;
      Rowo8.$children[1].Color = this.SpotColor;
      Rowo8.$children[2].Color = this.SpotColor;
      Rowo8.$children[3].Color = this.SpotColor;

      var Rowo9 = this.$children[2].$children.find(child => {return child.RowId == 9});
         
      Rowo9.$children[4].Color = this.SpotColor;
      Rowo9.$children[5].Color = this.SpotColor;
      Rowo9.$children[6].Color = this.SpotColor;
      Rowo9.$children[7].Color = this.SpotColor;
      

      Rowo9.$children[0].Color = this.SpotColor;
      Rowo9.$children[1].Color = this.SpotColor;
      Rowo9.$children[2].Color = this.SpotColor;
      Rowo9.$children[3].Color = this.SpotColor;

      var Rowo10 = this.$children[2].$children.find(child => {return child.RowId == 10});
         
      Rowo10.$children[4].Color = this.SpotColor;
      Rowo10.$children[5].Color = this.SpotColor;
      Rowo10.$children[6].Color = this.SpotColor;
      Rowo10.$children[7].Color = this.SpotColor;
      

      Rowo10.$children[0].Color = this.SpotColor;
      Rowo10.$children[1].Color = this.SpotColor;
      Rowo10.$children[2].Color = this.SpotColor;
      Rowo10.$children[3].Color = this.SpotColor;

      var Rowo0 = this.$children[2].$children.find(child => {return child.RowId == 0});
      
      Rowo0.$children[0].Color = this.SpotColor;
      Rowo0.$children[1].Color = this.SpotColor;
      Rowo0.$children[2].Color = this.SpotColor;
      Rowo0.$children[3].Color = this.SpotColor;

      var Rowp0 = this.$children[0].$children.find(child => {return child.RowId == 0});      

      Rowp0.$children[0].Color = this.SpotColor;
      Rowp0.$children[1].Color = this.SpotColor;
      Rowp0.$children[2].Color = this.SpotColor;
      Rowp0.$children[3].Color = this.SpotColor;

      var Row = this.$children[0].$children.find(child => {return child.RowId == 1});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 2});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 3});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 4});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 5});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 6});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 7});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 8});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 9});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;

      Row = this.$children[0].$children.find(child => {return child.RowId == 10});
         
      Row.$children[4].Color = this.SpotColor;
      Row.$children[5].Color = this.SpotColor;
      Row.$children[6].Color = this.SpotColor;
      Row.$children[7].Color = this.SpotColor;
      

      Row.$children[0].Color = this.SpotColor;
      Row.$children[1].Color = this.SpotColor;
      Row.$children[2].Color = this.SpotColor;
      Row.$children[3].Color = this.SpotColor;
      
      this.currentRow = 0;
      this.currentOpponentRow = 1;
      this.confirmCodeIsShown = true;
      this.confirmGuessIsShown = false;
      this.restartIsShown = false;
      this.codeSubmitted = false;
    },
    deselectSpot() {
      if(this.SelectedSpot != null){
          this.SelectedSpot.$data.Selected = false;
        }
      this.SelectedSpot = null;
    },
     revealCode(){
      var Row = this.$children[0].$children.find(child => {return child.RowId == 0});
      Row.$children[0].Color=this.code[0];
      Row.$children[1].Color=this.code[1];
      Row.$children[2].Color=this.code[2];
      Row.$children[3].Color=this.code[3];
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

      if(filledRow.clues[0] == 'BLACK' && filledRow.clues[1] == 'BLACK' && filledRow.clues[2] == 'BLACK' && filledRow.clues[3] == 'BLACK') {
        this.LostGame();
      }
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
