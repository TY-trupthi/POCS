import { Box, Grid } from "@mui/material";
import * as React from "react";
import DropDownComponent from "./DropDownComponent";
import InputComponent from "./InputComponent";
import DatePickerComponent from "./DatePickerComponent";

const CandidateForm = () => {
  const skills = [
    { id: "1", name: "React" },
    { id: "2", name: "Java" },
    { id: "3", name: "Java script" },
    { id: "4", name: "HTML" },
  ];
  const languages = [
    { id: "1", name: "English" },
    { id: "2", name: "Kannada" },
    { id: "3", name: "Hindi" },
  ];
  const employmentStatus = [
    { id: "1", name: "Fresher" },
    { id: "2", name: "Expirence" },
  ];
  const locations = [
    { id: "1", name: "Bangalore" },
    { id: "2", name: "Chennai" },
    { id: "2", name: "Noida" },
  ];
  return (
    <Box>
      <Grid
        container
        className="d-flex justify-content-space-between align-items-center px-4 pt-2"
      >
        <Grid item sm={6} md={4} className="px-4 py-4">
          <InputComponent
            textLabel="First Name"
            required={true}
            placeholder="Enter Your First Name"
            variant="outlined"
            name="firstName"
          />
        </Grid>
        <Grid item sm={6} md={4} className="px-4 py-4">
          <InputComponent
            textLabel="Last Name"
            required={true}
            placeholder="Enter Your Last Name"
            variant="outlined"
            name="lastName"
          />
        </Grid>
        <Grid item sm={6} md={4} className="px-4 py-4">
          <InputComponent
            textLabel="Mobile Number"
            required={true}
            placeholder="Enter Mobile Number"
            variant="outlined"
            name="mobileNumber"
          />
        </Grid>
        <Grid item sm={6} md={4} className="px-4 py-4">
          <DropDownComponent
            textLabel="Skills"
            required={true}
            dropDownValues={skills}
            placeholder="Select Your Skills"
            isMultiple={true}
          />
        </Grid>
        <Grid item sm={6} md={4} className="px-4 py-4">
          <DropDownComponent
            textLabel="Languages"
            required={true}
            dropDownValues={languages}
            placeholder="Select Languages"
            isMultiple={true}
          />
        </Grid>
        <Grid item sm={6} md={4} className="px-4 py-4">
          <DropDownComponent
            textLabel="Employment Status"
            required={true}
            dropDownValues={employmentStatus}
            placeholder="Select Employeement Status"
            isMultiple={false}
          />
        </Grid>
        <Grid item sm={6} md={4} className="px-4 py-4">
          <DatePickerComponent
            placeholder="Select Date of Birth"
            textLabel="DOB"
            inputFormat="dd/MM/yyyy"
            required={true}
            disableFuture={true}
          />
        </Grid>
      </Grid>
    </Box>
  );
};

export default CandidateForm;
