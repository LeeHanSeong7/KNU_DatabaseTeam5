import React, { useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useHistory
} from "react-router-dom";
import LoginView from "./views/LoginView"
import AdminMain from "./views/AdminMain";
import UserMain from "./views/UserMain";

function App() {

  const [loggedIn, setLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(true);
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  let history = useHistory();

  function logoutButtonClicked() {
    logout();
    setLoggedIn(false);
  }

  function logout() {

  }

  return (
    <div>
      {
        loggedIn && isAdmin && (  
          <AdminMain logoutButtonClicked={logoutButtonClicked}
          userId={id}
          userPassword={password} />
        )
      }
      {
        loggedIn && !isAdmin && (
          <UserMain 
          logoutButtonClicked={logoutButtonClicked}
          userId={id}
          userPassword={password}
          setIsAdmin={setIsAdmin} />
        )
      }
      {
        !loggedIn && (
          <LoginView 
          setLoggedin={setLoggedIn}
          setId={setId}
          setPassword={setPassword}
          setIsAdmin={setIsAdmin}/>
        )
      }
    </div>
  );
}

function Home() {
  return (
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>

          <li>
            <Link to="/about">About</Link>
          </li>

          <li>
            <Link to="/users">Users</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
}

function About() {
  let history = useHistory();
  function backButtonClicked() {
    history.goBack();
  }
  return (
    <button type="button" onClick={backButtonClicked}>
      뒤로
    </button>
  );
}

function Users() {
  return <h2>Users</h2>;
}

export default App;
