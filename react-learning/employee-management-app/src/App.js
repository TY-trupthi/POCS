import logo from "./logo.svg";
import "./App.css";
import CheckboxesTags from "./components/AutoComplete";
import Checkboxes from "./components/all-checkbox/CustomCheckbox";
import CheckboxComponent from "./components/all-checkbox/Checkbox";
import IndeterminateCheckbox from "./components/IndeterminateCheckbox";
import ButtonComponent from "./components/all-checkbox/CustomButton";
import FloatingActionButtonZoom from "./Tabs";
import RadioButtonsGroup from "./components/RadioButton";
import BasicSelect from "./components/select";
import DiscreteSliderLabel from "./components/Slider";
import MultilineTextFields from "./components/all-text-fields/multiline";
import BasicTextFields from "./components/all-text-fields/text-fields";
import LetterAvatars from "./components/avatar";
import Form from "./components/Form";
import DropDownComponent from "./components/project-component/DropDownComponent";
import CandidateForm from "./components/project-component/CandidateForm";
import TableComponent from "./components/project-component/TableComponent";
import ReturningFunction from "./components/project-component/ReturningFunction";

const marks = [
  {
    value: 0,
    label: "0°C",
    name: "11",
  },
  {
    value: 20,
    label: "20°C",
    name: "11",
  },
  {
    value: 37,
    label: "37°C",
    name: "11",
  },
  {
    value: 100,
    label: "100°C",
    name: "11",
  },
];

const tableColumns = [
  {
    id: "col1",
    label: "Employee ID",
    minWidth: 120,
    align: "center",
    filter: true,
  },
  {
    id: "col2",
    label: "First Name",
    minWidth: 120,
    align: "center",
    filter: true,
  },
  {
    id: "col3",
    label: "Last Name",
    minWidth: 120,
    align: "center",
    filter: true,
  },
];

const tableRows = [
  { id: 1, col1: "TYC01", col2: "Trupthi", col3: "Kale" },
  { id: 2, col1: "TYC02", col2: "Anu", col3: "Kale" },
  { id: 3, col1: "TYC02", col2: "Mouna", col3: "Kale" },
];

function App() {
  return (
    <>
      {/* <CheckboxesTags />
      <ButtonComponent variant="text" />;
      <Checkboxes />
      <CheckboxComponent />
      <IndeterminateCheckbox />
      <FloatingActionButtonZoom />
      <RadioButtonsGroup />
      <BasicSelect />
      <DiscreteSliderLabel
        marks={marks}
        defaultValue="10"
        size="medium"
        valueLabelDisplay="on"
      />
      <BasicTextFields variant="standard" />
      <MultilineTextFields />
      <LetterAvatars /> */}
      {/* <Form /> */}
      {/* <CandidateForm /> */}
      <TableComponent
        tableColumns={tableColumns}
        headerTitle="Candidate Details"
        showHeader={true}
        showfliterDrop={true}
        showSearchInput={true}
        enableRowDetails={true}
        tableRows={tableRows}
      />
    </>
  );
}

export default App;
