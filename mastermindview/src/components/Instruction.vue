<template>


   <div>
     <pre class="Instruction"> 
       <h2>
Spel instructies:
       </h2>
De speler plaatst 4 gekleurde codepinnetjes in de gaatjes 
aan de voorkant van zijn speelbord.
De code bestaat uit ieder gewenste combinatie van de 6 kleuren. 
Je mag 2 of meer codepinnetjes van dezelfde kleur gebruiken.
Je tegenstander doet hetzelfde en stelt ook een code op.

Wanneer de code is opgezet, kan de tegenspeler beginnen met raden.
Dit doe je door een rij van 4 gekleurde codepinnetjes op het spelbord
van de tegenstander te zetten. Naarmate het spel vordert, krijg je meer
informatie over de code en kun je steeds gerichtere pogingen wagen. 
Elke rij codepinnetjes blijft gedurende de ronde staan. 
Na iedere poging moet de
codemaker de tegenstander vertellen wat er goed en fout is in het rijtje.
Dat gebeurt door middel van zwarte en witte scorepinnetjes.

<b>Zwarte scorepinnetjes:</b>
Is zowel de kleur als de plek van een pinnetje correct geraden? Dan zet je 
een zwart scorepinnetje in het scoregaatje. Dit doe je voor elk goed geraden
pinnetje. De plek is niet van belang. De codekraker weet dus niet precies
welke scorepinnen hij/zij goed geraden heeft. 

<b>Witte scorepinnetjes:</b>
Zet een wit scorepinnetje in een scoregaatje voor elke codepinnetje van de
juiste kleur op een verkeerde plaats. Ook hierbij is de exacte plek van het
witte pinnetje in de scoregaten niet van belang.

Laat een scoregaatje leeg voor elk codepinnetje van een verkeerde kleur.
Scorepinnetjes worden automatisch door het spel geplaatst.
De codekraker heeft maximaal 10 kansen om de code te raden.
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