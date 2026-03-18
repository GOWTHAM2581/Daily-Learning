import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./redux/actions";

function App() {
  const dispatch = useDispatch();
  const { users, loading, error } = useSelector((state) => state);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Redux Saga Example</h2>

      <button onClick={() => dispatch(getUser())}>
        Fetch Users
      </button>

      {loading && <p>Loading...</p>}
      {error && <p>Error: {error}</p>}

      <ul>
        {users.map((user) => (
          <li key={user.id}>{user.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;