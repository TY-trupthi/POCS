import {
  Box,
  FormControl,
  FormControlLabel,
  FormLabel,
  Grid,
  Radio,
  RadioGroup,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import * as React from "react";
import CheckboxesTags from "./AutoComplete";

const Form = () => {
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
    <div>
      <Box className="px-4 py-2">
        <Grid
          container
          className="d-flex py-1 justify-content-"
          style={{ marginTop: "20px", marginLeft: "20px" }}
        >
          <Grid item sm={6} md={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">First Name</Typography>
            <TextField
              placeholder="Enter First Name"
              name="firstName"
              variant="outlined"
            />
          </Grid>
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">Last Name</Typography>
            <TextField
              variant="outlined"
              placeholder="Enter Last Name"
              name="lastName"
            />
          </Grid>
        </Grid>
        <Grid
          container
          className="d-flex py-1 justify-content-space-between"
          style={{ marginTop: "20px", marginLeft: "20px" }}
        >
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">Email Id</Typography>
            <TextField
              variant="outlined"
              placeholder="Enter Email Id"
              name="emailId"
            />
          </Grid>
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">
              Mobile Number
            </Typography>
            <TextField
              variant="outlined"
              placeholder="Enter Mobile Number"
              name="mobileNumber"
            />
          </Grid>
        </Grid>
        <Grid
          container
          className="d-flex py-1 justify-content-space-between"
          style={{ marginTop: "20px", marginLeft: "20px" }}
        >
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">Langauges</Typography>
            <CheckboxesTags
              placeholder="Select the Languages"
              dropDownValues={languages}
              isMultiple={true}
            />
          </Grid>
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">Skills</Typography>
            <CheckboxesTags
              placeholder="Select the skills"
              dropDownValues={skills}
              isMultiple={true}
            />
          </Grid>
        </Grid>
        <Grid
          container
          className="d-flex py-1 justify-content-space-between"
          style={{ marginTop: "20px", marginLeft: "20px" }}
        >
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">
              Employment Status
            </Typography>
            <CheckboxesTags
              placeholder="Select the Employment Status"
              dropDownValues={employmentStatus}
              isMultiple={false}
            />
          </Grid>

          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">YOE</Typography>
            <TextField
              variant="outlined"
              placeholder="Enter Year of Expirence"
              name="YOE"
            />
          </Grid>
        </Grid>
        <Grid
          container
          className="d-flex py-1 justify-content-space-between"
          style={{ marginTop: "20px", marginLeft: "20px" }}
        >
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">Gender</Typography>
            <FormControl variant="standard">
              <RadioGroup aria-labelledby="demo-error-radios" name="quiz">
                <Stack spacing={2} direction="row">
                  <FormControlLabel
                    value="Male"
                    control={<Radio />}
                    label="M"
                  />
                  <FormControlLabel
                    value="Female"
                    control={<Radio />}
                    label="F"
                  />
                  <FormControlLabel
                    value="Other"
                    control={<Radio />}
                    label="Other"
                  />
                </Stack>
              </RadioGroup>
            </FormControl>
          </Grid>
          <Grid item sm={6} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">
              Preferred Locations
            </Typography>
            <CheckboxesTags
              placeholder="Select the Preferred Locations"
              dropDownValues={locations}
              isMultiple={true}
            />
          </Grid>
        </Grid>
        <Grid
          container
          className="d-flex py-1 justify-content-space-between"
          style={{ marginTop: "20px", marginLeft: "20px" }}
        >
          <Grid item sm={8.2} className="px-2 py-1">
            <Typography className="ff-Ro fs-14 fw-500">About You</Typography>
            <TextField
              fullWidth
              placeholder="Describe about You"
              multiline
              rows={4}
            />
          </Grid>
        </Grid>
      </Box>
    </div>
  );
};

export default Form;
