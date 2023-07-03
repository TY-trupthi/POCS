let pro = new Promise((resolves, rejects) => {
  let a = "Resolves";
  if (a === "Resolve") {
    resolves("Resolved");
  } else {
    rejects("Failed", "Sad");
  }
});

pro
  .then((message) => {
    console.log("Inside Then " + message);
  })
  .catch((message1, message2) => {
    console.log("message1 " + message1 + " message2 " + message2);
  });
