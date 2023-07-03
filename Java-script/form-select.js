let allButtons=  document.getElementsByTagName('button');
let allButtonsCopy = [...allButtons];
function changeColor(selectedValue){
console.log(selectedValue.value)
 
   switch(selectedValue.value){
      case 'random':
        getRandomColor();
        break;
      case 'red':
        getRedColor();
        break;
      case 'green':
        getGreenColor();
        break;
      case 'warning':
        getWarningColor();
        break;
     case 'noColor':
        removeColor();
        break;
      default : 
   }
}

function getRedColor(){
   for(let i=0; i<allButtons.length; i++){
    allButtons[i].classList.remove( allButtons[i].classList[1]);
    allButtons[i].classList.add('btn-danger')
   }
}

function getGreenColor(){
    for(let i=0; i<allButtons.length; i++){
     allButtons[i].classList.remove( allButtons[i].classList[1]);
     allButtons[i].classList.add('btn-success')
    }
 }

 function getWarningColor(){
    for(let i=0; i<allButtons.length; i++){
     allButtons[i].classList.remove( allButtons[i].classList[1]);
     allButtons[i].classList.add('btn-warning')
    }
 }

 function getRandomColor(){
   let randomColor = ['btn-warning','btn-success','btn-danger'];
    for(let i=0; i<allButtons.length; i++){
     allButtons[i].classList.remove(allButtons[i].classList[1]);
     allButtons[i].classList.add(randomColor[Math.floor(Math.random() * 3)]);
    }
 }

 function removeColor(){
    let randomColor = ['btn-warning','btn-success','btn-danger'];
     for(let i=0; i<allButtons.length; i++){
      allButtons[i].classList.remove(allButtons[i].classList[1]);
     }
  }