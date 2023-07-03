function getBotChoice(){
   return ['rock','pepper', 'scissor'][Math.floor(Math.random() * 3)];
}

function getWinner(...choices){
   if(choices[0]==choices[1]){
     return 0.5;
   }else if(choices.includes('rock') && choices.includes('scissor')){
     return choices.indexOf('rock');
   }else if(choices.includes('rock') && choices.includes('pepper')){
    return choices.indexOf('pepper');
   }else if(choices.includes('scissor') && choices.includes('pepper')){
    return choices.indexOf('scissor');
   }
}

function getMessage(winner){
    if(winner==0.5){
        return "Match Tied"
    }else if(winner==1){
        return "You Lost"
    }else if(winner==0){
        return "You Won"
    }
}

function gameLogic(number){
    console.log(number)
   let yourChoice = number.id;
   let botChoice = getBotChoice();
   let result = getWinner(yourChoice,botChoice);
   let message = getMessage(result);
   getOutput(yourChoice, botChoice, message);
}

function getOutput(yourChoice, botChoice, message){
    let imageDetails= {
        'rock' : document.getElementById('rock').src,
        'pepper' : document.getElementById('pepper').src,
        'scissor' : document.getElementById('scissor').src,
    }

   document.getElementById('rock').remove();
   document.getElementById('pepper').remove();
   document.getElementById('scissor').remove();

   let humanDiv = document.createElement('div');
   let botDiv = document.createElement('div');
   let textDiv = document.createElement('div');

   humanDiv.innerHTML = "<img src= '"+imageDetails[yourChoice] +"'>";

   textDiv.innerHTML = "<h1 style = 'font-size: 60px; padding: 30px'>" +message + "</h1>";

   botDiv.innerHTML = "<img src= '"+imageDetails[botChoice] +"'>"

   let flexContainer = document.getElementById('flex-container-1');
   console.log(flexContainer)
   flexContainer.appendChild(humanDiv);
   flexContainer.appendChild(textDiv);
   flexContainer.appendChild(botDiv);

}
