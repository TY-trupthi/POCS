//Class component
import React from "react";

class EditContact extends React.Component {
  constructor(props) {
    super(props);
    console.log("eebj ", props);
    // this.state = {
    //   contactName: props.location.state.contact.contactName,
    //   email: props.location.state.contact.email,
    // };
  }

  //onSubmit this method will be called for passing the values to parent
  update = (e) => {
    //to avoid page refresh
    e.preventDefault();
    //for validation
    if (this.state.contactName === "" || this.state.email === "") {
      alert("All the feilds are mandatory");
      return;
    }
    //to the props one function will be passed from parent which recieves the current state object, pass the current state object to this function
    this.props.updateContactHandler(this.state);
    // to clear the data
    this.setState({ contactName: "", email: "" });
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
        <form className="ui form" onSubmit={this.update}>
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
          <button className="ui button blue">Update</button>
        </form>
      </div>
    );
  }
}

export default EditContact;
