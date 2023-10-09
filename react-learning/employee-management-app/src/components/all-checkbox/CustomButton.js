import * as React from "react";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import SendIcon from "@mui/icons-material/Send";

const ButtonComponent = ({ variant = "outlined" }) => {
  return (
    <>
      <br></br>
      <Stack spacing={2} direction="row">
        <Button variant={variant}>Text</Button>
        <Button variant="contained">Contained</Button>
        <Button variant="outlined">Outlined</Button>
        <Button>Primary</Button>
        <Button disabled>Disabled</Button>
        <Button href="https://www.mailinator.com/">Link</Button>
        <Button
          onClick={() => {
            alert("clicked");
          }}
        >
          Click me
        </Button>
        <Button color="secondary">Secondary</Button>
        <Button variant="contained" color="success">
          Success
        </Button>
        <Button variant="outlined" color="error">
          Error
        </Button>
        <Button size="small">Small</Button>
        <Button size="medium">Medium</Button>
        <Button size="large">Large</Button>
      </Stack>
      <br></br>
      <Stack spacing={2} direction="row">
        <Button variant="outlined" startIcon={<DeleteIcon />}>
          Delete
        </Button>
        <Button variant="contained" endIcon={<SendIcon />}>
          Send
        </Button>
      </Stack>
    </>
  );
};

export default ButtonComponent;
