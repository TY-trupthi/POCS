const axiosValue = require("axios");
// const data = axiosValue.get("http://localhost:8080/page/get");
// console.log(data.data); // this will give undefined because this line will be executed before getting the result from the api
// data
//   .then((res) => {
//     console.log(res.data);
//   })
//   .catch((err) => {
//     console.log(err);
//   });

//solution

getResult();
async function getResult() {
  try {
    const result = await axiosValue.get("http://localhost:8080/page/ge");
    console.log(result.data);
  } catch (err) {
    console.log(err.message);
  }
}
