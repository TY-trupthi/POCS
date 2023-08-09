//Function component
import React, { useRef } from "react";
import ContactCard from "./ContactCard";
import { Link } from "react-router-dom";

//iterating over the props and passing it to one more component
const ContactList = (props) => {
  const deleteHandler = (id) => {
    props.getContactId(id);
  };
  const inputElement = useRef("");
  const renderedList = props.contacts.map((contact) => {
    return (
      <ContactCard contactDetails={contact} clickHandler={deleteHandler} />
    );
  });

  const getSearchTerm = () => {
    props.searchKeyword(inputElement.current.value);
  };
  return (
    <div className="main">
      <br></br>
      <br></br>
      <h2>
        Contact List
        <Link to="/add">
          <button className="ui right floated primary button">
            Add Contact
          </button>
        </Link>
      </h2>
      <div className="ui search">
        <div className="ui icon input">
          <input
            ref={inputElement}
            type="text"
            placeholder="Search Contacts"
            className="prompt"
            value={props.term}
            onChange={getSearchTerm}
          />

          <i className="search icon"></i>
        </div>
      </div>
      <div className="ui celled list">
        {renderedList.length > 0 ? renderedList : "No Contacts Available"}
      </div>
    </div>
  );
};

export default ContactList;
