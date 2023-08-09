import React from "react";
import user from "../images/user.png";
import { Link, useLocation } from "react-router-dom";

const ContactById = (props) => {
  const contacts = props.contacts;
  const { pathname } = useLocation();
  console.log("pathName " + pathname);
  let params = pathname.split("/");
  console.log("params ", params);
  const email = params[params.length - 1];
  const filteredContacts = contacts.filter(
    (contact) => contact.email === email
  );

  if (!filteredContacts) {
    alert("Not Found");
    return;
  }
  const contact = filteredContacts[0];
  return (
    <div className="main">
      <br></br>
      <br></br>
      <div className="ui card centered">
        <div className=" image ">
          <img src={user} alt="user" />
        </div>
        <div className="content">
          <div className="header">{contact.contactName}</div>
          <div>{contact.email}</div>
        </div>
        <Link to="/">
          <button className="ui primary button">Back</button>
        </Link>
      </div>
    </div>
  );
};

export default ContactById;
