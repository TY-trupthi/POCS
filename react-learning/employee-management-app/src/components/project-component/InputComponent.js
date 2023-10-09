import { Grid, TextField, Typography } from "@mui/material";
import * as React from "react";

const InputComponent = ({
  textLabel = "",
  required = false,
  disabled = false,
  name = "",
  placeholder = "",
  variant = "",
}) => {
  return (
    <>
      <Grid mb="3px" px="6px">
        <Typography
          sx={{ cursor: "pointer", color: disabled ? "#ccc" : "#000" }}
          className="ff-Ro fs-14 fw-500"
        >
          {textLabel}
          {required && <span className="text-danger ms-1">*</span>}
        </Typography>
      </Grid>
      <TextField variant={variant} placeholder={placeholder} name={name} />
    </>
  );
};

export default InputComponent;
