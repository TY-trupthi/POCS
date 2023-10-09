import { collapseClasses } from "@mui/material";
import * as React from "react";

const array = [
  {
    col1: "trupthi",
  },
  {
    col1: "adhiti",
  },
  {
    col1: "guru",
  },
  {
    col1: "trupthi",
  },
];

function descendingComparator(a, b, orderBy) {
  console.log("a", a, "b", b, orderBy);
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === "desc"
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort(array, comparator) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    console.log("assj", a);
    const order = comparator(a[0], b[0]);
    if (order !== 0) {
      return order;
    }
    console.log(a[1] - b[1], "checking");
    return a[1] - b[1];
  });
  return stabilizedThis.map((el) => el[0]);
}

const ReturningFunction = () => {
  const result = stableSort(array, getComparator("desc", "col1"));
  console.log(result, " Result");
};

export default ReturningFunction;
