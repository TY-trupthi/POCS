function sayHello(){
 let createdElement   = document.createElement('h1');
let createdText = document.createTextNode("I am saying Hello");
createdElement.setAttribute('id', 'sayingHello');
createdElement.appendChild(createdText);
document.getElementById('resultDiv').appendChild(createdElement);
}

function clearText(){
    document.getElementById('sayingHello').remove();
}