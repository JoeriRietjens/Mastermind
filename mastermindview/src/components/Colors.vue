<template>
    <div>
        <div class="row">
            <span class="dot" id="green"></span> <br>
            <span class="dot" id="yellow"></span> <br>
            <span class="dot" id="blue"></span> <br>
        </div>
        <div class="row">
            <span class="dot" id="red"></span> <br>
            <span class="dot" id="white"></span> <br>
            <span class="dot" id="black"></span> <br>
        </div>
    </div>
</template>

<script>

export default {
    name: 'Colors',
    components: {
    }
}
</script>

<style scoped>
.dot {  
    height: 50px;
    width: 50px;
    border-radius: 50%;
    display: inline-block;
    border: groove;
}

#green {
    background-color: greenyellow;
}
#red {  
    background-color: red;
}

#blue {  
    background-color: blue;
}

#white {  
    background-color: white;
}

#yellow {  
    background-color: yellow;
}

#black {  
    background-color: black;
}

.row {
    display: inline-block;
    position: relative;
}
</style>