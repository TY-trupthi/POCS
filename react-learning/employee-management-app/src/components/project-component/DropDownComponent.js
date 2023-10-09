import * as React from "react";
import Checkbox from "@mui/material/Checkbox";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import CheckBoxIcon from "@mui/icons-material/CheckBox";
import { Grid, Typography } from "@mui/material";

const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;
const DropDownComponent = ({
  textLabel = "",
  required = false,
  dropDownValues = [],
  placeholder = "",
  isMultiple = false,
  disabled = false,
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
      <Autocomplete
        multiple={isMultiple}
        id="checkboxes-tags-demo"
        options={dropDownValues}
        disableCloseOnSelect
        getOptionLabel={(option) => option.name}
        renderOption={(props, option, { selected }) => (
          <li {...props}>
            <Checkbox
              icon={icon}
              checkedIcon={checkedIcon}
              style={{ marginRight: 8 }}
              checked={selected}
            />
            {option.name}
          </li>
        )}
        style={{ width: 222 }}
        renderInput={(params) => (
          <TextField {...params} placeholder={placeholder} />
        )}
      />
    </>
  );
};

export default DropDownComponent;
