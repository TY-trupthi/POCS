import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Stack from "@mui/material/Stack";
import { deepOrange, deepPurple } from "@mui/material/colors";
import { AvatarGroup } from "@mui/material";

const LetterAvatars = ({ letter = "A" }) => {
  return (
    <Stack direction="row" spacing={2}>
      <Avatar>{letter}</Avatar>
      <Avatar sx={{ bgcolor: deepOrange[500] }}>{letter}</Avatar>
      <Avatar sx={{ bgcolor: deepPurple[500] }}>{letter}</Avatar>
      <AvatarGroup max={4}>
        <Avatar>{letter}</Avatar>
        <Avatar>{letter}</Avatar>
        <Avatar>{letter}</Avatar>
        <Avatar>{letter}</Avatar>
        <Avatar>{letter}</Avatar>
      </AvatarGroup>
    </Stack>
  );
};

export default LetterAvatars;
