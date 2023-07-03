const YOU = { div: "#image-div", score: 0, span: "#your-result" };
const DEALER = { div: "#dealer-image-div", score: 0, span: "#dealer-result" };
const IMAGES = [
  "2",
  "3",
  "4",
  "5",
  "6",
  "7",
  "8",
  "9",
  "10",
  "A",
  "J",
  "Q",
  "K",
];
const VALUE = {
  2: 2,
  3: 3,
  4: 4,
  5: 5,
  6: 6,
  7: 7,
  8: 8,
  9: 9,
  10: 10,
  A: 10,
  J: 10,
  Q: 10,
  K: 10,
};

const YOURSCORE = { win: 0, lose: 0, draw: 0 };

const sound = new Audio("swish.m4a");
const win = new Audio("cash.mp3");
const lose = new Audio("aww.mp3");
document.querySelector("#hit").addEventListener("click", hitLogic);
document.querySelector("#deal").addEventListener("click", dealLogic);
document.querySelector("#stand").addEventListener("click", standLogic);

function hitLogic() {
  let selectedValue = Math.floor(Math.random() * 13);
  calculateScore(YOU, selectedValue);
}

function getCards(type, selectedValue) {
  if (type.score <= 21) {
    let image = document.createElement("img");
    image.src = IMAGES[selectedValue] + ".png";
    document.querySelector(type.div).appendChild(image);
    sound.play();
  }
}

function calculateScore(type, selectedValue) {
  let spanElement = document.querySelector(type.span);

  console.log(IMAGES[selectedValue]);
  if (type.score + VALUE[IMAGES[selectedValue]] <= 21) {
    type.score += VALUE[IMAGES[selectedValue]];
    console.log(type.score);
    spanElement.textContent = type.score;
    getCards(type, selectedValue);
  } else {
    if (type == YOU) {
      spanElement.textContent = "BUST";
      spanElement.style.color = "red";
      lose.play();
    } else {
      console.log("getwinner");
      getWinner();
    }
  }
}

function getWinner() {
  let yourSpanElement = document.querySelector(YOU.span);
  let yourDiffence = 21 - YOU.score;
  let dealerDiffence = 21 - DEALER.score;
  if (yourDiffence < dealerDiffence) {
    yourSpanElement.textContent = "YOU WON";
    yourSpanElement.style.color = "green";
    YOURSCORE.win += 1;
    document.querySelector("#wins").textContent = YOURSCORE.win;
    win.play();
  } else if (yourDiffence == dealerDiffence) {
    yourSpanElement.textContent = "DRAW";
    yourSpanElement.style.color = "yellow";
    YOURSCORE.draw += 1;
    document.querySelector("#draws").textContent = YOURSCORE.draw;
    win.play();
  } else {
    yourSpanElement.textContent = "YOU LOST";
    yourSpanElement.style.color = "red";
    lose.play();
    YOURSCORE.lose += 1;
    document.querySelector("#losses").textContent = YOURSCORE.lose;
  }
}

function dealLogic() {
  document
    .querySelector(YOU.div)
    .querySelectorAll("img")
    .forEach((image) => image.remove());
  document
    .querySelector(DEALER.div)
    .querySelectorAll("img")
    .forEach((image) => image.remove());
  YOU.score = 0;
  DEALER.score = 0;
  let yourSpanToClear = document.querySelector(YOU.span);
  yourSpanToClear.textContent = YOU.score;
  yourSpanToClear.style.color = "white";
  let dealerSpanToClear = document.querySelector(DEALER.span);
  dealerSpanToClear.textContent = DEALER.score;
  dealerSpanToClear.style.color = "white";
}

function standLogic() {
  let selectedValue = Math.floor(Math.random() * 13);
  calculateScore(DEALER, selectedValue);
}
