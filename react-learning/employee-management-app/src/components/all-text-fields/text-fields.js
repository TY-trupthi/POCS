import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";

const BasicTextFields = ({ variant = "outlined" }) => {
  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": { m: 1, width: "25ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" label="Outlined" variant="outlined" />
      <TextField id="filled-basic" label="Filled" variant="filled" />
      <TextField id="standard-basic" label="Standard" variant="standard" />
      <TextField
        required
        id="outlined-required"
        label="Required"
        defaultValue="Hello World"
        variant={variant}
      />
      <TextField
        disabled
        id="outlined-disabled"
        label="Disabled"
        defaultValue="Hello World"
        variant={variant}
      />
      <TextField
        id="outlined-password-input"
        label="Password"
        type="password"
        autoComplete="current-password"
        variant={variant}
      />
      <TextField
        id="outlined-read-only-input"
        label="Read Only"
        defaultValue="Hello World"
        variant={variant}
        InputProps={{
          readOnly: true,
        }}
      />
      <TextField
        id="outlined-number"
        label="Number"
        type="number"
        variant={variant}
        InputLabelProps={{
          shrink: true,
        }}
      />
      <TextField
        id="outlined-search"
        label="Search field"
        type="search"
        variant={variant}
      />
      <TextField
        id="outlined-helperText"
        label="Helper text"
        defaultValue="Default Value"
        variant={variant}
        helperText="Some important text"
      />
      <TextField
        error
        id="outlined-error"
        label="Error"
        defaultValue="Hello World"
      />
      <TextField
        error
        id="outlined-error-helper-text"
        label="Error"
        defaultValue="Hello World"
        helperText="Incorrect entry."
      />
    </Box>
  );
};

export default BasicTextFields;
