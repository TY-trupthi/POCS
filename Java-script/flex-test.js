function generateCat(){
  let imgElement = document.createElement('img');
  imgElement.setAttribute("id", "my-img")
 let flexElement = document.getElementById('my-flex');
 imgElement.src = "User_Logo.png";
 flexElement.appendChild(imgElement);
}

function clearImage(){
    document.getElementById('my-img').remove();
}