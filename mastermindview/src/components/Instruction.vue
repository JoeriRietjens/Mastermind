<template>


   <div>
     <pre class="Instruction"> 
       <h2>
Game instructions:
       </h2>
The player places 4 colored code pins in the holes
at the front of his game board.
The code consists of any desired combination of the 6 colors.
You may use 2 or more code pins of the same color.
Your opponent does the same.

When the code is set up, the opponent can start guessing.
You do this through a row of 4 colored code pins on the game board
of the opponent. As the game progresses you will get more
information about the code and you can make increasingly targeted attempts.
Each row of code pins remains for the duration of the round.
After each attempt, the codemaker will tel the opponent what is right and wrong in the list.
This is done by means of black and white score pins.

<b>Black scorepins:</b>
Have both the color and the location of a pin been guessed correctly? Then you put
a black score pin in the score hole. You do this for every guess
pin. The place doesn't matter. The code breaker does not know exactly
which score pins he / she guessed correctly.

<b>White scorepins:</b>
Place a white score pin in a score hole for each code pin of the
right color in the wrong place. Also here is the exact spot of it
white pin in the score holes does not matter.

Leave a score hole blank for each pin of the wrong color.
Score markers are automatically placed by the game.
The code breaker has a maximum of 10 chances to guess the code.
</pre>
</div>

</template>

<script>

    export default {
    name: "Instruction",
    };

</script>

<style scoped>
.Instruction{
  background-color:	rgb(255, 255, 255);
	display:inline-block;
	color:rgb(0, 0, 0);
	font-family:Arial;
	font-size:15px;
	/*font-weight:bold;*/
	text-decoration:none;
}
</style>