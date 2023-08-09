//Class component
import React, { Component } from "react";

class AddContact extends React.Component {
  // state with class component
  state = {
    contactName: "",
    email: "",
    contactNumber: "",
  };
  //onSubmit this method will be called for passing the values to parent
  add = (e) => {
    //to avoid page refresh
    e.preventDefault();
    //for validation
    if (this.state.contactName === "" || this.state.email === "") {
      alert("All the feilds are mandatory");
      return;
    }
    //to the props one function will be passed from parent which recieves the current state object, pass the current state object to this function
    this.props.addContactHandler(this.state);
    // to clear the data
    this.setState({ contactName: "", email: "", contactNumber: "" });
    //go back to the list page
    window.location.href = "/";
  };
  //directly we can not return from class component, we need to use render() to return
  render() {
    return (
      <div className="ui main">
        <br></br>
        <br></br>
        <h2>Add Contact</h2>
        <form className="ui form" onSubmit={this.add}>
          <div className="field">
            <label>Name</label>
            <input
              type="text"
              name="name"
              placeholder="Name"
              value={this.state.contactName}
              onChange={(e) => {
                this.setState({ contactName: e.target.value });
              }}
            />
          </div>
          <div className="field">
            <label>Email</label>
            <input
              type="text"
              name="email"
              placeholder="Email"
              value={this.state.email}
              onChange={(e) => {
                this.setState({ email: e.target.value });
              }}
            />
          </div>
          <div className="field">
            <label>Contact Number</label>
            <input
              type="text"
              name="contactNumber"
              placeholder="Contact Number"
              value={this.state.contactNumber}
              onChange={(e) => {
                this.setState({ contactNumber: e.target.value });
              }}
            />
          </div>
          <button className="ui button blue">Add</button>
        </form>
      </div>
    );
  }
}

export default AddContact;
