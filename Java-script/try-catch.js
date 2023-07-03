try {
  throw new SyntaxError("Syntax worng");
} catch (err) {
  console.log("error " + err);
} finally {
  console.log("Finally");
}
