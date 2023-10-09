import { Grid, Typography } from "@mui/material";
import * as React from "react";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";

const DatePickerComponent = ({
  label = "",
  value = null,
  readOnly = false,
  textLabel = "",
  placeholder = "--select--",
  disablePast = false,
  disableFuture = false,
  required = false,
  inputFormat = "dd/MM/yyyy",
  disabled = false,
  minDate = null,
  maxDate = null,
}) => {
  return (
    <>
      <Grid mb="3px" px="6px">
        <Typography
          className="ff-Ro fs-14 fw-500"
          sx={{ color: disabled ? "#ccc" : "#000" }}
        >
          {textLabel}
          {required && <span className="text-danger ms-1">*</span>}
        </Typography>
      </Grid>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <DatePicker
          disableFuture={disableFuture}
          disablePast={disablePast}
          disabled={disabled}
          label={label}
          value={value}
          minDate={minDate}
          maxDate={maxDate}
          inputFormat={inputFormat}
          inputProps={{
            placeholder: placeholder,
          }}
          readOnly={readOnly}
        />
      </LocalizationProvider>
    </>
  );
};

export default DatePickerComponent;
