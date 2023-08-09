import React from "react";
//importing the file which is there in other package/folder
import user from "../images/user.png";

import { Link } from "react-router-dom";

const ContactCard = (props) => {
  const contact = props.contactDetails;
  return (
    <div className="item">
      <img className="ui avatar image" src={user} alt="user" />
      <div className="content">
        <Link to={`/contact/${contact.email}`}>
          <div className="header">{contact.contactName}</div>
          <div>{contact.email}</div>
          <div>{contact.contactNumber}</div>
        </Link>
        <i
          className="trash alternate outline icon"
          style={{ color: "red", marginTop: "7px" }}
          onClick={() => {
            props.clickHandler(contact.email);
          }}
        ></i>
        {/* <Link
          to={{
            pathname: "/edit",
            state: { contact: contact },
          }}
        >
          <i
            className="edit alternate outline icon"
            style={{ color: "blue", marginTop: "7px" }}
          ></i>
        </Link> */}
      </div>
    </div>
  );
};

export default ContactCard;
