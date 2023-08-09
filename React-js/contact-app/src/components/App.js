import React, { useState, useEffect } from "react";
import "./App.css";

//importing the files that we have created
import Header from "./Header";
import AddContact from "./AddContact";
import EditContact from "./EditContact";
import ContactList from "./ContactList";
import ContactById from "./ContactById";

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import api from "../api/contact";

const LOCAL_STORAGE_KEY = "contacts";

function App() {
  // For single line returning
  // return <div>Hello world</div>;

  // if we are hard coding the value

  // const contacts = [
  //   {
  //     id: 1,
  //     contactName: "Trupthi",
  //     email: "trupthi@gmail.com",
  //   },
  //   {
  //     id: 2,
  //     contactName: "Sushma",
  //     email: "sushma@gmail.com",
  //   },
  // ];

  //using useStates -> react hook
  //initial value will be taken from local storage
  // const [contacts, setContacts] = useState(() => {
  //   return JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
  // });

  const [contacts, setContacts] = useState([]);

  const [searchTerm, setSearchTerm] = useState("");

  const [searchResult, setSearchResult] = useState([]);

  //Retriving data from api
  const retrieveContact = async () => {
    const response = await api.get("/contact");
    return response.data.data;
  };

  //function which receive the state object from component and sets the useState
  const addContactHandler = async (contact) => {
    const request = {
      ...contact,
    };

    const response = await api
      .post("/contact", request)
      .then((res) => {
        const data = res && res.data;
        return { data };
      })
      .catch((err) => {
        alert(err.response.data.data);
      });

    //directly contacts can not be updated, setContacts has to be used
    // setContacts([...contacts, response.data.data]);
  };

  const updateContactHandler = async (contact) => {
    const request = {
      ...contact,
    };

    const response = await api.post("/contact", request);

    //directly contacts can not be updated, setContacts has to be used
    // setContacts([...contacts, response.data.data]);
  };

  const removeContactHandler = async (email) => {
    await api.delete(`/contact/${email}`).catch((err) => {
      alert(err.response.data.message);
    });

    const getAllContacts = async () => {
      const allContacts = await retrieveContact();
      if (allContacts) setContacts(allContacts);
    };

    getAllContacts();
  };

  const searchHandler = (searchTerm) => {
    setSearchTerm(searchTerm);
    if (searchTerm !== "") {
      const filteredContactList = contacts.filter((contact) => {
        return Object.values(contact)
          .join(" ")
          .toLowerCase()
          .includes(searchTerm.toLowerCase());
      });
      setSearchResult(filteredContactList);
    } else {
      setSearchResult(contacts);
    }
  };

  //react hook. Which helps us to render the data when the value changes. This is used to fetch the data from local storage
  // useEffect(() => {
  //   localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(contacts));
  // }, [contacts]);

  useEffect(() => {
    const getAllContacts = async () => {
      const allContacts = await retrieveContact();
      if (allContacts) setContacts(allContacts);
    };

    getAllContacts();
  }, []);

  // For multiple line returning
  return (
    <div className="ui container">
      {/* calling the component
      <Header />
      passing function as a props
      <AddContact addContactHandler={addContactHandler} />
      passing the props to function component
      <ContactList contacts={contacts} getContactId={removeContactHandler} /> */}

      {/* Using router */}
      <Router>
        <Header />
        {/* <Routes>
           //one way of passing props when we are using rout but it causes the performance issue
          <Route
            path="/add"
            Component={() => (
              <AddContact addContactHandler={addContactHandler} />
            )}
          ></Route>
          <Route
            path="/"
            Component={() => (
              <ContactList
                contacts={contacts}
                getContactId={removeContactHandler}
              />
            )}
          ></Route>
        </Routes> */}

        <Routes>
          <Route
            path="/"
            element={
              <ContactList
                contacts={searchTerm.length < 1 ? contacts : searchResult}
                getContactId={removeContactHandler}
                term={searchTerm}
                searchKeyword={searchHandler}
              />
            }
          />
          <Route
            path="/add"
            element={<AddContact addContactHandler={addContactHandler} />}
          />
          <Route
            path="/contact/:email"
            element={<ContactById contacts={contacts} />}
          />
          <Route
            path="/edit"
            element={
              <EditContact updateContactHandler={updateContactHandler} />
            }
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
