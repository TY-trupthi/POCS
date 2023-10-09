import * as React from "react";
import Box from "@mui/material/Box";
import Slider from "@mui/material/Slider";

function valuetext(value) {
  return `${value}°C`;
}

const DiscreteSliderLabel = ({
  size = "small",
  valueLabelDisplay = "on",
  defaultValue = 80,
  step = 1,
  marks = [],
}) => {
  return (
    <Box sx={{ width: 300 }}>
      <Slider
        aria-label="Always visible"
        defaultValue={defaultValue}
        getAriaValueText={valuetext}
        step={step}
        marks={marks}
        valueLabelDisplay={valueLabelDisplay}
        disabled={false}
        size={size}
      />
    </Box>
  );
};

export default DiscreteSliderLabel;
